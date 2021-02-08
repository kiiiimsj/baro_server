package com.wantchu.wantchu_server2.vo;

import com.wantchu.wantchu_server2.business.DateConverter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashMap;

@NoArgsConstructor
@Getter
@Setter
public class OrderVo {

    private int order_id;
    private int discount_rate;
    private LocalDateTime order_date;
    private String phone;
    private int store_id;
    private int menu_id;
    private String menu_name;
    private int menu_defaultprice;
    private int order_count;
    private String receipt_id;
    private String order_state;
    private String requests;

    public OrderVo(int order_id,int discount_rate, LocalDateTime order_date, String phone, int store_id, int menu_id, int menu_defaultprice, String requests) {
        this.order_id = order_id;
        this.discount_rate = discount_rate;
        this.order_date = order_date;
        this.phone = phone;
        this.store_id = store_id;
        this.menu_id = menu_id;
        this.menu_defaultprice = menu_defaultprice;
        this.requests = requests;
    }

    public HashMap<String, Object> convertMap() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("order_id", order_id);
        map.put("discount_rate", discount_rate);
        map.put("order_date", DateConverter.convertDateWithTime(order_date));
        map.put("phone", phone);
        map.put("store_id", store_id);
        map.put("menu_id", menu_id);
        map.put("menu_name", menu_name);
        map.put("menu_defaultprice", menu_defaultprice);
        map.put("requests", requests);
        return map;
    }
}
