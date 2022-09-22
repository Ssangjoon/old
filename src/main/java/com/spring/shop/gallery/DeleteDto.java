package com.spring.shop.gallery;

import java.util.List;

import com.spring.shop.utils.FileDto;

public class DeleteDto {
    private int board_id;
    private int file_num;
    private String saved_file_name;
    private List<FileDto> deleteNameList;

    public DeleteDto() {
        // TODO Auto-generated constructor stub
    }



    public DeleteDto(int board_id, int file_num, String saved_file_name, List<FileDto> deleteNameList) {
        super();
        this.board_id = board_id;
        this.file_num = file_num;
        this.saved_file_name = saved_file_name;
        this.deleteNameList = deleteNameList;
    }



    public int getBoard_id() {
        return board_id;
    }

    public void setBoard_id(int board_id) {
        this.board_id = board_id;
    }

    public int getFile_num() {
        return file_num;
    }

    public void setFile_num(int file_num) {
        this.file_num = file_num;
    }

    public String getSaved_file_name() {
        return saved_file_name;
    }

    public void setSaved_file_name(String saved_file_name) {
        this.saved_file_name = saved_file_name;
    }

    public List<FileDto> getDeleteNameList() {
        return deleteNameList;
    }

    public void setDeleteNameList(List<FileDto> deleteNameList) {
        this.deleteNameList = deleteNameList;
    }




}
