package com.wantchu.wantchu_server2.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class MemberVo {

    private String phone;
    private String email;
    private String pass;
    private String nick;
    private LocalDateTime created_date;
    private String device_token;
}

