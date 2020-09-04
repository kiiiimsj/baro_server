package com.wantchu.wantchu_server2.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;

@NoArgsConstructor
@Getter
@Setter
public class FavoriteInfoVo {

    private int store_id;
    private double store_latitude;
    private double store_longitude;
    private String store_name;
    private String store_info;
    private String store_location;
    private String store_image;
    private String is_open;

    public FavoriteInfoVo(int store_id, double store_latitude, double store_longitude, String store_info, String store_name, String store_location, String store_image) {
        this.store_id = store_id;
        this.store_info = store_info;
        this.store_latitude = store_latitude;
        this.store_longitude = store_longitude;
        this.store_name = store_name;
        this.store_location = store_location;
        this.store_image = store_image;
    }

    public HashMap<String, Object> convertMap() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("store_id", store_id);
        map.put("store_latitude", store_latitude);
        map.put("store_longitude", store_longitude);
        map.put("store_name", store_name);
        map.put("store_info", store_info);
        map.put("store_location", store_location);
        map.put("store_image", store_image);
        map.put("is_open", is_open);
        return map;
    }
}
