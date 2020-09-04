package com.wantchu.wantchu_server2.member.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberPassUpdateRequestDto {

    public MemberPassUpdateRequestDto(String phone, String pass) {
        this.pass = pass;
        this.phone = phone;
    }

    private String phone;
    private String pass;

}
