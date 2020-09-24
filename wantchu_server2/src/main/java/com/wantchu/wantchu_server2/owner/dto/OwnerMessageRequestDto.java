package com.wantchu.wantchu_server2.owner.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OwnerMessageRequestDto {

    private String phone;
    private String title;
    private String content;
    private int store_id;
    private String owner_device_token;
}
