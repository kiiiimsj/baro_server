package com.wantchu.wantchu_server2.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;

@NoArgsConstructor
@Getter
@Setter
public class PriceByDayVo {

    private String date;
    private int defaultPrice;
    private int extraPrice;

    public PriceByDayVo(String date, int defaultPrice, int extraPrice) {
        this.date = date;
        this.defaultPrice = defaultPrice;
        this.extraPrice = extraPrice;
    }

    public HashMap<String, Object> convertMap(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("date", date);
        map.put("default_price", defaultPrice);
        map.put("extra_price", extraPrice);
        return map;
    }
}
