package com.wantchu.wantchu_server2.owner.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class OwnerPriceBetweenDateRequestDto {

    private int store_id;
    private String startDate;
    private String endDate;
    private String owner_device_token;
}
