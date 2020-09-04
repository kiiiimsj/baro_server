package com.wantchu.wantchu_server2.owner.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class OwnerRegisterRequestDto {

    private String phone;
    private String email;
    private String nick;
    private String pass;

    public OwnerRegisterRequestDto(String phone, String email, String nick, String pass) {
        this.phone = phone;
        this.email = email;
        this.nick = nick;
        this.pass = pass;
    }
}
