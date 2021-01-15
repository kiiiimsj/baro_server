package com.wantchu.wantchu_server2.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;

@NoArgsConstructor
@Getter
@Setter
public class PrintStoreVo {
    private int store_id;
    private String type_code;
    private String store_name;

    @Builder
    public PrintStoreVo(int store_id, String type_code,String store_name) {
        this.store_id = store_id;
        this.type_code = type_code;
        this.store_name = store_name;
    }

    public HashMap<String, Object> convertMap() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("store_id", store_id);
        map.put("type_code", type_code);
        map.put("store_name", store_name);
        return map;
    }
}
