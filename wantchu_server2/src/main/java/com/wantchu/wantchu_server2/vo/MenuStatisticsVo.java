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
    private int default_price;
    private int extra_price;
    private int menu_count;

    public MenuStatisticsVo(String menu_name, int default_price, int extra_price, int menu_count){
        this.menu_name = menu_name;
        this.default_price = default_price;
        this.extra_price = extra_price;
        this.menu_count = menu_count;
    }

    public HashMap<String, Object> convertMap() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("menu_name", menu_name);
        map.put("default_price", default_price);
        map.put("extra_price", extra_price);
        map.put("menu_count", menu_count);
        return map;
    }
}
