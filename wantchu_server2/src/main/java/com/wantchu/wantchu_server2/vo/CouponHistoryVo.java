package com.wantchu.wantchu_server2.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;

@NoArgsConstructor
@Getter
@Setter
public class CouponHistoryVo {

    private int couponhistory_id;
    private String phone;
    private int coupon_id;
    private int store_id;
    private int discount_price;
    private int total_price;
    private String store_name;
    private String receipt_id;

    public HashMap<String, Object> convertMap() {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("total_price", total_price);
        hashMap.put("store_name", store_name);
        hashMap.put("discount_price", discount_price);
        return hashMap;
    }
}
