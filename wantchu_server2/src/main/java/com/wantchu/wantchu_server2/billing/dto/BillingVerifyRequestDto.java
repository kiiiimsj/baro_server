package com.wantchu.wantchu_server2.billing.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class BillingVerifyRequestDto {

    private String receipt_id;
    private int price;

    public BillingVerifyRequestDto(String receipt_id, int price) {
        this.receipt_id = receipt_id;
        this.price = price;
    }
}
