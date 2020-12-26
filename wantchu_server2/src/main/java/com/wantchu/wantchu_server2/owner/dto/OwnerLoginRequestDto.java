package com.wantchu.wantchu_server2.owner.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OwnerLoginRequestDto {

    private String phone;
    private String pass;

    public OwnerLoginRequestDto(String phone, String pass) {
        this.phone = phone;
        this.pass = pass;
    }
}
