package com.wantchu.wantchu_server2.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;

@NoArgsConstructor
@Getter
@Setter
public class ExtraVo {

    private int extra_id;
    private int extra_price;
    private String extra_name;
    private String extra_group;
    private int store_id;
    private int menu_id;
    private String is_required;
    private int extra_maxcount;

    public ExtraVo(int extra_id, int extra_price, String extra_name, String extra_group) {
        this.extra_id = extra_id;
        this.extra_price = extra_price;
        this.extra_name = extra_name;
        this.extra_group = extra_group;
    }

    public HashMap<String, Object> convertMap() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("extra_id", extra_id);
        map.put("extra_price", extra_price);
        map.put("extra_name", extra_name);
        map.put("extra_group", extra_group);
        map.put("extra_maxcount", extra_maxcount);
        return map;
    }
}
