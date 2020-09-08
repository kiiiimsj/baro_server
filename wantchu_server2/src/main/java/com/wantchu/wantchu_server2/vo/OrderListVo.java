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
public class OrderListVo {

    private String receipt_id;
    private String store_name;
    private LocalDateTime order_date;
    private int total_count;
    private String order_state;

    public OrderListVo(String receipt_id, String store_name, LocalDateTime order_date) {
        this.receipt_id = receipt_id;
        this.store_name = store_name;
        this.order_date = order_date;
    }

    public HashMap<String, Object> convertMap() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("receipt_id", receipt_id);
        map.put("store_name", store_name);
        map.put("order_date", DateConverter.convertDateWithTime(order_date));
        map.put("total_count", total_count);
        map.put("order_state",order_state);
        return map;
    }
}
