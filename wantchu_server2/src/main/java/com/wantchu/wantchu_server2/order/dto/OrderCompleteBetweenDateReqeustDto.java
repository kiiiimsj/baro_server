package com.wantchu.wantchu_server2.order.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class OrderCompleteBetweenDateReqeustDto {
    private int store_id;
    private String start_date;
    private String end_date;
}
