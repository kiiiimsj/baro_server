package com.wantchu.wantchu_server2.favorite.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FavoriteExistRequestDto {
    private String phone;
    private int store_id;

    public FavoriteExistRequestDto(String phone, int store_id){
        this.phone = phone;
        this.store_id = store_id;
    }
}
