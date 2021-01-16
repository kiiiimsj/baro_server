package com.wantchu.wantchu_server2.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;

@Getter
@Setter
@NoArgsConstructor

public class PrintExtraVo {
    private int extra_id;
    private int extra_price;
    private String extra_name;

    public PrintExtraVo(int extra_id, int extra_price, String extra_name) {
        this.extra_id = extra_id;
        this.extra_price = extra_price;
        this.extra_name = extra_name;
    }

    public HashMap<String, Object> convertMap() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("extra_id", extra_id);
        map.put("extra_price", extra_price);
        map.put("extra_name", extra_name);
        return map;
    }
}
