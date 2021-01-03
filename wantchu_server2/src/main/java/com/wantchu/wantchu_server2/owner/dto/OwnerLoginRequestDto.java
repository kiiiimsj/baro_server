package com.wantchu.wantchu_server2.owner.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OwnerLoginRequestDto {

    private String id;
    private String pass;

    public OwnerLoginRequestDto(String id, String pass) {
        this.id = id;
        this.pass = pass;
    }
}
