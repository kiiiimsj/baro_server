package com.wantchu.wantchu_server2.owner.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OwnerStoreSetRequestDto {

    private String phone;
    private int store_id;

    public OwnerStoreSetRequestDto(String phone, int store_id) {
        this.phone = phone;
        this.store_id = store_id;
    }
}
