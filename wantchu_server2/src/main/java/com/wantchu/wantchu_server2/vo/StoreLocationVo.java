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
    private int store_id;
    private String store_name;
    private double store_latitude;
    private double store_longitude;
    private double distance;

    @Builder
    public StoreLocationVo(int store_id,String store_name, double store_latitude, double store_longitude,double distance) {
        this.store_id = store_id;
        this.store_name = store_name;
        this.store_latitude = store_latitude;
        this.store_longitude = store_longitude;
        this.distance = distance;
    }

    public HashMap<String, Object> convertMap() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("store_id",store_id);
        map.put("store_name", store_name);
        map.put("store_latitude", store_latitude);
        map.put("store_longitude", store_longitude);
        map.put("distance",distance);
        return map;
    }
}
