package com.wantchu.wantchu_server2.manage.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryInsertDto {
    private int store_id;
    private String category_name;

    public CategoryInsertDto(int store_id, String category_name) {
        this.store_id = store_id;
        this.category_name = category_name;
    }
}
