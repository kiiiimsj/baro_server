package com.wantchu.wantchu_server2.order.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderMessageRequestDto {
    private int store_id;
    private String receipt_id;
}
