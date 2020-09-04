package com.wantchu.wantchu_server2.owner.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class OwnerPassUpdateRequestDto {

    private String phone;
    private String pass;

    public OwnerPassUpdateRequestDto(String phone, String pass) {
        this.phone = phone;
        this.pass = pass;
    }
}
