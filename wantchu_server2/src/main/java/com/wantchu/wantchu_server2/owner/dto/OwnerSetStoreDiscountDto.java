package com.wantchu.wantchu_server2.owner.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OwnerSetStoreDiscountDto {

    private int store_id;
    private int discount_rate;

    public OwnerSetStoreDiscountDto(int store_id, int discount_rate) {
        this.store_id = store_id;
        this.discount_rate = discount_rate;
    }
}
