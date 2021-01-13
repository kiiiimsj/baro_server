package com.wantchu.wantchu_server2.manage.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UltraInsertDto {
    private int store_id;
    private String store_large_image;

    public UltraInsertDto(int store_id, String store_large_image) {
        this.store_id = store_id;
        this.store_large_image = store_large_image;
    }
}
