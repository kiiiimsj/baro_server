package com.wantchu.wantchu_server2.favorite.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FavoriteListDto {
    private String phone;
    private double latitude;
    private double longitude;

    public FavoriteListDto(String phone, double latitude, double longitude) {
        this.phone = phone;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
