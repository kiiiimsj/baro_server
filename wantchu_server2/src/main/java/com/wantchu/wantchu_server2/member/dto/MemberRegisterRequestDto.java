package com.wantchu.wantchu_server2.member.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberRegisterRequestDto {

    private String phone;
    private String email;
    private String nick;
    private String pass;
    private String marketing;

    public MemberRegisterRequestDto(String phone, String email, String nick, String pass,String marketing) {
        this.phone = phone;
        this.email = email;
        this.nick = nick;
        this.pass = pass;
        this.marketing = marketing;
    }
}
