package com.wantchu.wantchu_server2.manage.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MenuInsertDto {
    private int menu_defaultprice;
    private String menu_name;
    private String menu_info;
    private String menu_image;

    public MenuInsertDto(int menu_defaultprice, String menu_name, String menu_info, String menu_image) {
        this.menu_defaultprice = menu_defaultprice;
        this.menu_name = menu_name;
        this.menu_info = menu_info;
        this.menu_image = menu_image;
    }
}
