package com.wantchu.wantchu_server2.owner.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OwnerEmailUpdateRequestDto {

    private String phone;
    private String email;

    public OwnerEmailUpdateRequestDto(String phone, String email) {
        this.phone = phone;
        this.email = email;
    }
}
