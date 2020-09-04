package com.wantchu.wantchu_server2.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class FavoriteVo {

    private String phone;
    private int store_id;

    public FavoriteVo(String phone, int store_id) {
        this.phone = phone;
        this.store_id = store_id;
    }
}