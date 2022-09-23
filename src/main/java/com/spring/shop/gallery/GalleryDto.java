package com.spring.shop.gallery;

import java.util.List;

import com.spring.shop.utils.FileDto;
import lombok.Data;

@Data
public class GalleryDto {
    private int gb_id;
    private String gb_title;
    private String gb_content;
    private String gb_writer;
    private String gb_createdat;
    private String saved_file_name;
    private List<FileDto> fileList;
}
