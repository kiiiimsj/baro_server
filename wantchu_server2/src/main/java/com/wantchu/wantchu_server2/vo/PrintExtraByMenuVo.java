package com.wantchu.wantchu_server2.vo;

import com.wantchu.wantchu_server2.business.DateConverter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;

@Getter
@Setter
@NoArgsConstructor
public class PrintExtraByMenuVo {
    private int id;
    private int extra_maxcount;
    private String extra_name;
    private int extra_price;

    public PrintExtraByMenuVo(int id,int extra_maxcount,String extra_name,int extra_price){
        this.id = id;
        this.extra_maxcount = extra_maxcount;
        this.extra_name = extra_name;
        this.extra_price = extra_price;
    }

    public HashMap<String, Object> convertMap(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("extra_maxcount", extra_maxcount);
        map.put("extra_name", extra_name);
        map.put("extra_price", extra_price);
        return map;
    }
}
