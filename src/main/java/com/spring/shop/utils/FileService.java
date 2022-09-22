package com.spring.shop.utils;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    List<Map<String, Object>> fileUpload(String type, List<MultipartFile> files, int id) throws Exception;
    int fileDelete(String name);
    int fileDeleteAll(List<FileDto> fileDto) throws Exception;
}
