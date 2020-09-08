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
    private int price;

    public PriceByDayVo(String date, int price) {
        this.date = date;
        this.price = price;
    }

    public HashMap<String, Object> convertMap(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("date", date);
        map.put("price", price);
        return map;
    }
}
