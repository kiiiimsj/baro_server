package com.wantchu.wantchu_server2.manage.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class FxOwnerRegisterRequestDto {

    private String phone;
    private String email;
    private String id;
    private String pass;

    public FxOwnerRegisterRequestDto(String phone, String email, String id, String pass) {
        this.phone = phone;
        this.email = email;
        this.id = id;
        this.pass = pass;
    }
}
