package com.wantchu.wantchu_server2.manage.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ExtraInsertDto {
    private int extra_price;
    private String extra_name;
    private int store_id;

    public ExtraInsertDto(int extra_price, String extra_name, int store_id){
        this.extra_price = extra_price;
        this.extra_name = extra_name;
        this.store_id = store_id;
    }

}
