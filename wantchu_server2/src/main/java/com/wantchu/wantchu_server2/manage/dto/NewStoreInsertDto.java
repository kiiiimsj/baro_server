package com.wantchu.wantchu_server2.manage.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NewStoreInsertDto {
    private int store_id;

    public NewStoreInsertDto(int store_id) {
        this.store_id = store_id;
    }
}
