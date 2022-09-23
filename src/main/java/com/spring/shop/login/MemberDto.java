package com.spring.shop.login;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MemberDto {
    private String mi_id;
    private String mi_pw;
    private String mi_email;
    private String mi_name;
    private String mi_mobile;
    private String mi_tell;
    private String mi_address;
    private int mi_postcode;
    private String mi_detailaddress;
    private String mi_createdat;

}
