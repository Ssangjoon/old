package com.spring.shop.gallery;

import java.util.List;
import java.util.Map;

import com.spring.shop.utils.FileDto;
import com.spring.shop.utils.SearchDto;

public interface GalleryMapper {

    List<GalleryDto> list(SearchDto dto);

    int add(GalleryDto dto);

    GalleryDto select(GalleryDto dto);

    int edit(GalleryDto dto);

    int delete(GalleryDto dto);

    int photoDelete(DeleteDto dto);

    int photoDeleteAll(GalleryDto dto);

    int savedFile(List<Map<String, Object>> params);

    int addLogo(GalleryDto dto);

    List<FileDto> selectPhoto(GalleryDto dto);
}
