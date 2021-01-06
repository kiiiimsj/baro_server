package com.wantchu.wantchu_server2.manage.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MenuInsertDto {
    private int menu_defaultprice;
    private int category_id;
    private String menu_name;
    private String menu_info;
    private int store_id;
    private String menu_image;

    public MenuInsertDto(int menu_defaultprice,int category_id, String menu_name, String menu_info, int store_id,String menu_image) {
        this.menu_defaultprice = menu_defaultprice;
        this.category_id = category_id;
        this.menu_name = menu_name;
        this.menu_info = menu_info;
        this.store_id = store_id;
        this.menu_image = menu_image;
    }
}
