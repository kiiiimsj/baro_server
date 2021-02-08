package com.wantchu.wantchu_server2.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;

@NoArgsConstructor
@Getter
@Setter
public class OrderDetailVo {

    private int menu_defaultprice;
    private String menu_name;
    private String extra_name;
    private int extra_price;
    private int extra_count;
    private int order_count;
    private String order_state;
    private int discount_rate;

    public OrderDetailVo(int menu_defaultprice, String menu_name, String extra_name, int extra_price, int extra_count,int discount_rate) {
        this.menu_defaultprice = menu_defaultprice;
        this.menu_name = menu_name;
        this.extra_name = extra_name;
        this.extra_price = extra_price;
        this.extra_count = extra_count;
        this.discount_rate = discount_rate;
    }

    public HashMap<String, Object> convertMap() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("menu_defaultprice", menu_defaultprice);
        map.put("menu_name", menu_name);
        map.put("extra_name", extra_name);
        map.put("extra_price", extra_price);
        map.put("extra_count", extra_count);
        map.put("order_state", order_state);
        map.put("discount_rate", discount_rate);
        return map;
    }
}
