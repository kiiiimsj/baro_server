package com.wantchu.wantchu_server2.manage.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CouponInsertDto {
    private String coupon_title;
    private String coupon_content;
    private int coupon_condition;
    private int counpon_discount;
    private String coupon_type;
    private String coupon_enddate;
    private int coupon_number;
}
