package com.wantchu.wantchu_server2.member.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberEmailUpdateRequestDto {

    private String phone;
    private String email;

    public MemberEmailUpdateRequestDto(String phone, String email) {
        this.phone = phone;
        this.email = email;
    }
}
