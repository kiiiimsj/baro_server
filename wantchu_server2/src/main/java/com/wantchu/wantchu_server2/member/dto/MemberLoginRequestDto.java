package com.wantchu.wantchu_server2.member.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberLoginRequestDto {

    private String phone;
    private String pass;
    private String device_token;
}
