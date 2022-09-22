
package com.spring.shop.gallery;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.spring.shop.HomeController;
import com.spring.shop.login.MemberDto;
import com.spring.shop.utils.SearchDto;

@Controller
public class GalleryController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private GalleryService galleryService;

    @RequestMapping(value = "/galleryBoardPage", method = RequestMethod.GET)
    public String galleryBoardPage(Model model, GalleryDto galleryDto) {
        logger.info("갤러리 페이지");

        model.addAttribute("content", "gallery/gallery.jsp" );

        return "home";
    }

    @RequestMapping(value = "/getGalleryList", method = RequestMethod.GET)
    @ResponseBody
    public List<GalleryDto> getGalleryList(SearchDto dto) {
        logger.info("갤러리 목록 불러오기 ");

        List<GalleryDto> list = galleryService.list(dto);

        return list;
    }

    @RequestMapping(value = "/galleryAddPage", method = RequestMethod.GET)
    public String galleryAddPage(Model model, GalleryDto galleryDto, HttpServletRequest req) throws Exception {
        logger.info("공지사항 추가 페이지");

        model.addAttribute("content", "gallery/galleryAdd.jsp");

        return "home";
    }

    @RequestMapping(value = "/galleryAdd", method = RequestMethod.POST)
    public String galleryAdd(Model model, GalleryDto galleryDto, HttpServletRequest req, @RequestParam("file") List<MultipartFile> files) throws Exception {
        logger.info("갤러리 추가");

        MemberDto loginUser = (MemberDto) req.getSession().getAttribute("loginUser");
//		galleryDto.setGb_writer(loginUser.getMi_id());

        galleryDto.setGb_writer("admin");
        int result = galleryService.add(galleryDto, files);

        if(result > 0) {
//			model.addAttribute("MSG", "정상처리 되었습니다");
            return "redirect:/galleryBoardPage";
        } else {
            model.addAttribute("MSG", "오류 발생");
            model.addAttribute("content", "gallery/galleryAdd.jsp");
        }

        return "home";
    }

    @RequestMapping(value = "/galleryDetailPage", method = RequestMethod.GET)
    public String galleryDetailPage(Model model, GalleryDto galleryDto) {
        logger.info("갤러리 상세 페이지");

        GalleryDto galleryContent = galleryService.select(galleryDto);

        System.out.println(galleryContent.getGb_id());
        System.out.println(galleryContent.getFileList());

        model.addAttribute("galleryContent", galleryContent);
        model.addAttribute("content", "gallery/galleryDetail.jsp");

        return "home";
    }

    @RequestMapping(value = "/galleryEdit", method = RequestMethod.POST)
    public String noticeEdit(Model model, GalleryDto galleryDto, HttpServletRequest req,@RequestParam("file") List<MultipartFile> files) {
        logger.info("갤러리 수정!!");

        int result = galleryService.edit(galleryDto, files);

        if(result > 0) {
            model.addAttribute("MSG", "수정 완료");
            model.addAttribute("content", "gallery/gallery.jsp");
        } else {
            model.addAttribute("MSG", "수정 실패");
            model.addAttribute("content", "gallery/gallery.jsp");
            return "home";
        }

        return "home";
    }

    @RequestMapping(value = "/galleryDelete", method = RequestMethod.GET)
    public String noticeDelete(Model model, GalleryDto galleryDto) {
        logger.info("갤러리 삭제");

        int result = galleryService.delete(galleryDto);

        if(result > 0) {
            return "redirect:/galleryBoardPage";
        } else {
            model.addAttribute("MSG", "삭제 실패");
            model.addAttribute("content", "gallery/galleryDetail.jsp");
        }

        return "home";
    }

    @RequestMapping(value = "/photoDelete", method = RequestMethod.GET)
    @ResponseBody
    public String photoDelete(Model model, DeleteDto deleteDto) {
        logger.info("갤러리 사진 삭제");
        System.out.println(deleteDto.getBoard_id());
        System.out.println(deleteDto.getFile_num());
        System.out.println(deleteDto.getSaved_file_name());
        logger.info("-------------------------");

        int result = galleryService.photoDelete(deleteDto);

        return "home";
    }
}
