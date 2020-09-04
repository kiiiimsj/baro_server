package com.wantchu.wantchu_server2.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;

@NoArgsConstructor
@Getter
@Setter
public class CategoryVo {

    private int category_id;
    private int store_id;
    private String category_name;

    public CategoryVo(int category_id, int store_id, String category_name) {
        this.category_id = category_id;
        this.store_id = store_id;
        this.category_name = category_name;
    }

    public HashMap<String, Object> convertMap() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("category_id", category_id);
        map.put("store_id", store_id);
        map.put("category_name", category_name);
        return map;
    }
}
