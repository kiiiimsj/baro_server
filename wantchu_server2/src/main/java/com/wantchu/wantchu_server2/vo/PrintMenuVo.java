package com.wantchu.wantchu_server2.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;

@NoArgsConstructor
@Getter
@Setter
public class PrintMenuVo {
    private int menu_id;
    private String menu_name;
    private int menu_defaultprice;

    @Builder
    public PrintMenuVo(int menu_id, String menu_name, int menu_defaultprice) {
        this.menu_id = menu_id;
        this.menu_name = menu_name;
        this.menu_defaultprice = menu_defaultprice;
    }

    public HashMap<String, Object> convertMap() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("menu_id", menu_id);
        map.put("menu_name", menu_name);
        map.put("menu_defaultprice", menu_defaultprice);
        return map;
    }
}
