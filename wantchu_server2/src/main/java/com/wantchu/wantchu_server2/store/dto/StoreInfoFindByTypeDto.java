package com.wantchu.wantchu_server2.store.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StoreInfoFindByTypeDto {
    private String type_code;
    private double latitude;
    private double longitude;
}
