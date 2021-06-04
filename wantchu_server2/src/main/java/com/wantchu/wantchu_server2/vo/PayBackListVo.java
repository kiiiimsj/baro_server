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
public class PayBackListVo {

    private int store_id;
    private String store_name;
    private int payBackSum;

    public PayBackListVo(int store_id,String store_name,int payBackSum) {
        this.store_id = store_id;
        this.store_name = store_name;
        this.payBackSum = payBackSum;
    }

    public HashMap<String, Object> convertMap() {
        HashMap<String, Object> map = new HashMap<>();

        map.put("store_id", store_id);
        map.put("store_name", store_name);
        map.put("payBackSum", payBackSum);
        return map;
    }


}
