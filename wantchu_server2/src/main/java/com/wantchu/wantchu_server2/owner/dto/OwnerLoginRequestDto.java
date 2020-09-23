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
    private String owner_device_token;

    public OwnerLoginRequestDto(String phone, String pass, String owner_device_token) {
        this.phone = phone;
        this.pass = pass;
        this.owner_device_token = owner_device_token;
    }
}
