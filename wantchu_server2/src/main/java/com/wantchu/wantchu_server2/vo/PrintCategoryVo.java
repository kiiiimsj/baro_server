package com.wantchu.wantchu_server2.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;

@NoArgsConstructor
@Getter
@Setter
public class PrintCategoryVo {
    private int category_id;
    private String category_name;

    public PrintCategoryVo(int category_id, String category_name) {
        this.category_id = category_id;
        this.category_name = category_name;
    }

    public HashMap<String, Object> convertMap() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("category_id", category_id);
        map.put("category_name", category_name);
        return map;
    }
}
