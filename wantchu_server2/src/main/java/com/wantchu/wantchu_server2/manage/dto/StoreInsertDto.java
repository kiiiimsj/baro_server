package com.wantchu.wantchu_server2.manage.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StoreInsertDto {
    private String type_code;
    private String store_name;
    private Double store_latitude;
    private Double store_longitude;
    private String store_opentime;
    private String store_closetime;
    private String store_dayoff;
    private String store_location;
    private String store_info;
    private String store_image;

    public StoreInsertDto(String type_code, String store_name, Double store_latitude, Double store_longitude, String store_opentime, String store_closetime, String store_dayoff, String store_location, String store_info, String store_image) {
        this.type_code = type_code;
        this.store_name = store_name;
        this.store_latitude = store_latitude;
        this.store_longitude = store_longitude;
        this.store_opentime = store_opentime;
        this.store_closetime = store_closetime;
        this.store_dayoff = store_dayoff;
        this.store_location = store_location;
        this.store_info = store_info;
        this.store_image = store_image;
    }
}
