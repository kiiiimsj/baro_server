package com.wantchu.wantchu_server2.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;

@NoArgsConstructor
@Getter
@Setter
public class StoreVo {

    private int store_id;
    private String type_code;
    private String store_name;
    private double store_latitude;
    private double store_longitude;
    private String store_opentime;
    private String store_closetime;
    private String store_phone;
    private String store_daysoff;
    private String store_location;
    private String store_image;
    private String store_info;
    private String is_open;
    private String representative_name;
    private String business_number;

    public StoreVo(int store_id, String type_code, String store_name, double store_latitude, double store_longitude, String store_opentime, String store_closetime, String store_phone, String store_daysoff, String store_location, String store_image, String store_info, String representative_name, String business_number) {
        this.store_id = store_id;
        this.type_code = type_code;
        this.store_name = store_name;
        this.store_latitude = store_latitude;
        this.store_longitude = store_longitude;
        this.store_opentime = store_opentime;
        this.store_closetime = store_closetime;
        this.store_phone = store_phone;
        this.store_daysoff = store_daysoff;
        this.store_location = store_location;
        this.store_image = store_image;
        this.store_info = store_info;
        this.representative_name = representative_name;
        this.business_number = business_number;
    }

    public HashMap<String, Object> convertMap() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("store_id", store_id);
        map.put("type_code", type_code);
        map.put("store_name", store_name);
        map.put("store_latitude", store_latitude);
        map.put("store_longitude", store_longitude);
        map.put("store_opentime", store_opentime);
        map.put("store_closetime", store_closetime);
        map.put("store_phone", store_phone);
        map.put("store_daysoff", store_daysoff);
        map.put("store_location", store_location);
        map.put("store_image", store_image);
        map.put("store_info", store_info);
        map.put("is_open", is_open);
        map.put("representative_name", representative_name);
        map.put("business_number", business_number);
        return map;
    }
}
