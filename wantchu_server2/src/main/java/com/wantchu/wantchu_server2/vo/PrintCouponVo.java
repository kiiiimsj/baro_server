package com.wantchu.wantchu_server2.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;

@NoArgsConstructor
@Getter
@Setter
public class PrintCouponVo {
    private int coupon_id;
    private String coupon_title;

    @Builder
    public PrintCouponVo(int coupon_id, String coupon_title) {
        this.coupon_id = coupon_id;
        this.coupon_title = coupon_title;
    }

    public HashMap<String, Object> convertMap() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("coupon_id", coupon_id);
        map.put("coupon_title", coupon_title);
        return map;
    }
}
