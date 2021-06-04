package com.wantchu.wantchu_server2.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;

@NoArgsConstructor
@Getter
@Setter
public class StoreInfoFindByTypeVo {

    private int store_id;
    private String store_name;
    private String store_location;
    private String store_info;
    private String store_image;
    private String is_open;
    private double distance;
    private int discount_rate;

    @Builder
    public StoreInfoFindByTypeVo(int store_id, String store_name, String store_info, double distance,
                                 String store_location, String store_image, String is_open,int discount_rate) {
        this.store_id = store_id;
        this.store_name = store_name;
        this.store_info = store_info;
        this.store_location = store_location;
        this.store_image = store_image;
        this.is_open = is_open;
        this.distance = distance;
        this.discount_rate = discount_rate;
    }

    public HashMap<String, Object> convertMap() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("store_id", store_id);
        map.put("store_name", store_name);
        map.put("distance",distance);
        map.put("store_info", store_info);
        map.put("store_location", store_location);
        map.put("is_open", is_open);
        map.put("store_image", store_image);
        map.put("discount_rate", discount_rate);
        return map;
    }
}
