package com.wantchu.wantchu_server2.order.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class OrderStateUpdateRequestDto {

    private String receipt_id;
}
