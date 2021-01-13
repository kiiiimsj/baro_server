package com.wantchu.wantchu_server2.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;

@NoArgsConstructor
@Getter
@Setter
public class PrintNewStoreVo {
    private int new_store_id;
    private int store_id;
    private String store_name;

    @Builder
    public PrintNewStoreVo(int new_store_id, int store_id, String store_name) {
        this.new_store_id = new_store_id;
        this.store_id = store_id;
        this.store_name = store_name;
    }

    public HashMap<String, Object> convertMap() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("store_id", store_id);
        map.put("new_store_id", new_store_id);
        map.put("store_name", store_name);
        return map;
    }
}
