package com.wantchu.wantchu_server2.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;

@Getter
@Setter
@NoArgsConstructor
public class FindOrderListByPhoneForManageVo {
    private String order_date;
    private String receipt_id;
    private String order_state;

    public FindOrderListByPhoneForManageVo(String order_date,String receipt_id,String order_state){
        this.order_date = order_date;
        this.receipt_id = receipt_id;
        this.order_state = order_state;
    }

    public HashMap<String, Object> convertMap(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("order_date", order_date);
        map.put("receipt_id", receipt_id);
        map.put("order_state", order_state);
        return map;
    }
}
