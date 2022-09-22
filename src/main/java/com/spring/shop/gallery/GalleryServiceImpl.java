package com.spring.shop.gallery;

import java.util.List;
import java.util.Map;

import javax.swing.plaf.synth.SynthOptionPaneUI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;
import org.springframework.web.multipart.MultipartFile;

import com.spring.shop.HomeController;
import com.spring.shop.utils.FileDto;
import com.spring.shop.utils.FileService;
import com.spring.shop.utils.Paging;
import com.spring.shop.utils.SearchDto;

@Service
public class GalleryServiceImpl implements GalleryService {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private GalleryRepository galleryRepository;

    @Autowired
    private PlatformTransactionManager platformTransactionManager;

    @Autowired
    private Paging paging;

    @Autowired
    private FileService fileService;

    @Override
    public List<GalleryDto> list(SearchDto dto) {
        dto = paging.generate(dto);
        List<GalleryDto> lists = galleryRepository.list(dto);
        return lists;
    }

    @Override
    public int add(GalleryDto dto, List<MultipartFile> files) throws Exception {
        TransactionStatus status = platformTransactionManager.getTransaction(new DefaultTransactionAttribute());

        try {
            galleryRepository.add(dto);
            if(!files.get(0).isEmpty()) {
                List<Map<String, Object>> params = fileService.fileUpload("Gallery", files, dto.getGb_id());

                galleryRepository.savedfile(params);
            }
            platformTransactionManager.commit(status);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            platformTransactionManager.rollback(status);
            return 0;
        }
        //		return galleryRepository.add(noticeDto);
    }

    @Override
    public GalleryDto select(GalleryDto dto) {

        GalleryDto gallery = galleryRepository.select(dto);

        List<FileDto> files = galleryRepository.photoSelect(dto);

        gallery.setFileList(files);

        return gallery;
    }

    @Override
    public int edit(GalleryDto dto, List<MultipartFile> files) {
        TransactionStatus status = platformTransactionManager.getTransaction(new DefaultTransactionAttribute());

        try {
            logger.info("갤러리 수정 (service)");
            galleryRepository.edit(dto);

            if(!files.get(0).isEmpty()) {
                logger.info("갤러리 수정 확인(service)");

                List<Map<String, Object>> params = fileService.fileUpload("Gallery", files, dto.getGb_id());
                logger.info("갤러리 사진 수정 (service)");
                galleryRepository.savedfile(params);
            }

            platformTransactionManager.commit(status);

            return 1;
        }catch(Exception e) {
            e.printStackTrace();
            platformTransactionManager.rollback(status);
            return 0;
        }

    }

    @Override
    public int delete(GalleryDto dto) {
        TransactionStatus status = platformTransactionManager.getTransaction(new DefaultTransactionAttribute());

        try {
            List<FileDto> files = galleryRepository.photoSelect(dto);

            galleryRepository.delete(dto);
            galleryRepository.photoDeleteAll(dto);


            int result = fileService.fileDeleteAll(files);

            if(result < 0) {
                throw new Exception("파일 삭제 안됨 예외 발생");
            }

            platformTransactionManager.commit(status);

            return 1;
        }catch(Exception e) {
            e.printStackTrace();
            platformTransactionManager.rollback(status);
            return 0;
        }
    }


    @Override
    public int photoDelete(DeleteDto dto) {
        TransactionStatus status = platformTransactionManager.getTransaction(new DefaultTransactionAttribute());

        try {
            galleryRepository.photoDelete(dto);

            int result = fileService.fileDelete(dto.getSaved_file_name());
            if(result < 0) {
                throw new Exception("파일 삭제 안됨 예외 발생");
            }

            platformTransactionManager.commit(status);

            return 1;
        }catch(Exception e) {
            e.printStackTrace();
            platformTransactionManager.rollback(status);
            return 0;
        }
    }
}
