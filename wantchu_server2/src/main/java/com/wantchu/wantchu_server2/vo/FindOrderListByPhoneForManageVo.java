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
    private String store_name;
    private int store_id;
    private String representative_name;

    public FindOrderListByPhoneForManageVo(String order_date,String receipt_id,String order_state,String store_name,int store_id,String representative_name){
        this.order_date = order_date;
        this.receipt_id = receipt_id;
        this.order_state = order_state;
        this.store_name = store_name;
        this.store_id = store_id;
        this.representative_name = representative_name;
    }

    public HashMap<String, Object> convertMap(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("order_date", order_date);
        map.put("receipt_id", receipt_id);
        map.put("order_state", order_state);
        map.put("store_name", store_name);
        map.put("store_id", store_id);
        map.put("representative_name", representative_name);
        return map;
    }
}
