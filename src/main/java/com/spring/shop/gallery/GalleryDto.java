package com.spring.shop.gallery;

import java.util.List;

import com.spring.shop.utils.FileDto;

public class GalleryDto {
    private int gb_id;
    private String gb_title;
    private String gb_content;
    private String gb_writer;
    private String gb_createdat;
    private String saved_file_name;
    private List<FileDto> fileList;

    public GalleryDto() {
        // TODO Auto-generated constructor stub
    }

    public GalleryDto(int gb_id, String gb_title, String gb_content, String gb_writer, String gb_createdat,
                      String saved_file_name, List<FileDto> fileList) {
        super();
        this.gb_id = gb_id;
        this.gb_title = gb_title;
        this.gb_content = gb_content;
        this.gb_writer = gb_writer;
        this.gb_createdat = gb_createdat;
        this.saved_file_name = saved_file_name;
        this.fileList = fileList;
    }

    public int getGb_id() {
        return gb_id;
    }

    public void setGb_id(int gb_id) {
        this.gb_id = gb_id;
    }

    public String getGb_title() {
        return gb_title;
    }

    public void setGb_title(String gb_title) {
        this.gb_title = gb_title;
    }

    public String getGb_content() {
        return gb_content;
    }

    public void setGb_content(String gb_content) {
        this.gb_content = gb_content;
    }

    public String getGb_writer() {
        return gb_writer;
    }

    public void setGb_writer(String gb_writer) {
        this.gb_writer = gb_writer;
    }

    public String getGb_createdat() {
        return gb_createdat;
    }

    public void setGb_createdat(String gb_createdat) {
        this.gb_createdat = gb_createdat;
    }

    public String getSaved_file_name() {
        return saved_file_name;
    }

    public void setSaved_file_name(String saved_file_name) {
        this.saved_file_name = saved_file_name;
    }

    public List<FileDto> getFileList() {
        return fileList;
    }

    public void setFileList(List<FileDto> fileList) {
        this.fileList = fileList;
    }

}
