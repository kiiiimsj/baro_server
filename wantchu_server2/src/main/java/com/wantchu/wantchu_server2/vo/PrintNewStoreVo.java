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
    private int store_id;
    private String store_name;

    @Builder
    public PrintNewStoreVo(int store_id, String store_name) {
        this.store_id = store_id;
        this.store_name = store_name;
    }

    public HashMap<String, Object> convertMap() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("store_id", store_id);
        map.put("store_name", store_name);
        return map;
    }
}
