package com.wantchu.wantchu_server2.store.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StoreInfoFindByKeywordDto {
    private String keyword;
    private double latitude;
    private double longitude;
    private int startPoint;
}
