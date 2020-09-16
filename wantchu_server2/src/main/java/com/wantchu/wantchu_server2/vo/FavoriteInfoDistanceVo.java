package com.wantchu.wantchu_server2.vo;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;

@NoArgsConstructor
@Getter
@Setter
public class FavoriteInfoDistanceVo {

    private int store_id;
    private double distance;
    private String store_name;
    private String store_info;
    private String store_location;
    private String store_image;
    private String is_open;

    public FavoriteInfoDistanceVo(int store_id,double distance, String store_info, String store_name, String store_location, String store_image) {
        this.store_id = store_id;
        this.store_info = store_info;
        this.distance = distance;
        this.store_name = store_name;
        this.store_location = store_location;
        this.store_image = store_image;
    }

    public HashMap<String, Object> convertMap() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("store_id", store_id);
        map.put("distance",distance);
        map.put("store_name", store_name);
        map.put("store_info", store_info);
        map.put("store_location", store_location);
        map.put("store_image", store_image);
        map.put("is_open", is_open);
        return map;
    }
}

