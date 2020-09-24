package com.wantchu.wantchu_server2.owner.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OwnerSetStatisticsRequestDto {
    private int store_id;
    private String start_date;
    private String end_date;
    private String owner_device_token;
}
