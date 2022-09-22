package com.spring.shop.notice;

public class NoticeDto {
    private int nb_id;
    private String nb_title;
    private String nb_content;
    private String nb_writer;
    private String nb_createdat;

    public NoticeDto() {
        // TODO Auto-generated constructor stub
    }

    public NoticeDto(int nb_id, String nb_title, String nb_content, String nb_writer, String nb_createdat) {
        super();
        this.nb_id = nb_id;
        this.nb_title = nb_title;
        this.nb_content = nb_content;
        this.nb_writer = nb_writer;
        this.nb_createdat = nb_createdat;
    }

    public int getNb_id() {
        return nb_id;
    }


    public void setNb_id(int nb_id) {
        this.nb_id = nb_id;
    }


    public String getNb_title() {
        return nb_title;
    }


    public void setNb_title(String nb_title) {
        this.nb_title = nb_title;
    }


    public String getNb_content() {
        return nb_content;
    }


    public void setNb_content(String nb_content) {
        this.nb_content = nb_content;
    }


    public String getNb_writer() {
        return nb_writer;
    }


    public void setNb_writer(String nb_writer) {
        this.nb_writer = nb_writer;
    }


    public String getNb_createdat() {
        return nb_createdat;
    }


    public void setNb_createdat(String nb_createdat) {
        this.nb_createdat = nb_createdat;
    }
}
