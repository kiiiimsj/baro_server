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
public class CouponVo {

    private int coupon_id;
    private String coupon_title;
    private String coupon_content;
    private int coupon_condition;
    private int coupon_discount;
    private String coupon_type;
    private LocalDateTime coupon_enddate;

    public HashMap<String, Object> convertMap() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("coupon_id", coupon_id);
        map.put("coupon_title", coupon_title);
        map.put("coupon_content", coupon_content);
        map.put("coupon_condition", coupon_condition);
        map.put("coupon_discount", coupon_discount);
        map.put("coupon_type", coupon_type);
        map.put("coupon_enddate", DateConverter.convertDateWithoutTime(coupon_enddate));
        return map;
    }
}
