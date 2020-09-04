package com.wantchu.wantchu_server2.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ExtraOrderVo {

    private int extraorder_id;
    private int order_id;
    private int extra_price;
    private int extra_count;
    private int extra_id;
    private String extra_name;

    public ExtraOrderVo(int extraorder_id, int order_id, int extra_price, int extra_count, int extra_id) {
        this.extraorder_id = extraorder_id;
        this.order_id = order_id;
        this.extra_price = extra_price;
        this.extra_count = extra_count;
        this.extra_id = extra_id;
    }

}
