package com.spring.shop.gallery;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.spring.shop.utils.SearchDto;

public interface GalleryService {

    List<GalleryDto> list(SearchDto dto);

    int add(GalleryDto dto, List<MultipartFile> files) throws Exception;

    GalleryDto select(GalleryDto dto);

    int edit(GalleryDto dto, List<MultipartFile> files);

    int delete(GalleryDto dto);

    int photoDelete(DeleteDto Dto);
}
