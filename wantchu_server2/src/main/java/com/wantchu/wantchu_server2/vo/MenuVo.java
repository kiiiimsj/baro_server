package com.wantchu.wantchu_server2.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;

@NoArgsConstructor
@Getter
@Setter
public class MenuVo {

    private int menu_id;
    private int menu_defaultprice;
    private int category_id;
    private String menu_name;
    private String menu_info;
    private int store_id;

    public MenuVo(int menu_id, int menu_defaultprice, int category_id, String menu_name, String menu_info, int store_id) {
        this.menu_id = menu_id;
        this.menu_defaultprice = menu_defaultprice;
        this.category_id = category_id;
        this.menu_name = menu_name;
        this.menu_info = menu_info;
        this.store_id = store_id;
    }


    public HashMap<String, Object> convertMap() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("menu_id", menu_id);
        map.put("menu_defaultprice", menu_defaultprice);
        map.put("category_id", category_id);
        map.put("menu_name", menu_name);
        map.put("menu_info", menu_info);
        map.put("store_id", store_id);
        return map;
    }

}
