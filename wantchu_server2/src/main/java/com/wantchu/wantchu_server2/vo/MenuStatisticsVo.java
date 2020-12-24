package com.wantchu.wantchu_server2.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;

@NoArgsConstructor
@Getter
@Setter
public class MenuStatisticsVo {
    private String menu_name;
    private int menu_total_price;
    private int menu_count;

    public MenuStatisticsVo(String menu_name, int menu_total_price, int menu_count){
        this.menu_name = menu_name;
        this.menu_total_price = menu_total_price;
        this.menu_count = menu_count;
    }

    public HashMap<String, Object> convertMap() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("menu_name", menu_name);
        map.put("menu_total_price", menu_total_price);
        map.put("menu_count", menu_count);
        return map;
    }
}
