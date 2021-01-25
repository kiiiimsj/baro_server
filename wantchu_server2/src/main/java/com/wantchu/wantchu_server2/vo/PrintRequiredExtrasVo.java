package com.wantchu.wantchu_server2.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;

@Getter
@Setter
@NoArgsConstructor
public class PrintRequiredExtrasVo {
    private int extra_id;
    private String extra_group;
    private int store_id;
    private String extra_name;
    private int extra_price;

    public PrintRequiredExtrasVo(int extra_id,String extra_group, int store_id, String extra_name, int extra_price) {
        this.extra_id = extra_id;
        this.extra_group = extra_group;
        this.store_id = store_id;
        this.extra_name = extra_name;
        this.extra_price = extra_price;
    }
    public HashMap<String, Object> convertMap(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("extra_id", extra_id);
        map.put("extra_group", extra_group);
        map.put("store_id", store_id);
        map.put("extra_name", extra_name);
        map.put("extra_price", extra_price);
        return map;
    }
}
