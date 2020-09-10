package com.wantchu.wantchu_server2.order.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class OrderCompletePhoneDto {
    private int store_id;
    private String phone;
    private int start;
}
