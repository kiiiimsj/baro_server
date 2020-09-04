package com.wantchu.wantchu_server2.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;

@NoArgsConstructor
@Getter
@Setter
public class StoreLocationVo {

    private String store_name;
    private double store_latitude;
    private double store_longitude;

    @Builder
    public StoreLocationVo(String store_name, double store_latitude, double store_longitude) {
        this.store_name = store_name;
        this.store_latitude = store_latitude;
        this.store_longitude = store_longitude;
    }

    public HashMap<String, Object> convertMap() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("store_name", store_name);
        map.put("store_latitude", store_latitude);
        map.put("store_longitude", store_longitude);
        return map;
    }
}
