package com.wantchu.wantchu_server2.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;

@NoArgsConstructor
@Getter
@Setter
public class PrintTypeVo {
    private String type_code;
    private String type_name;

    @Builder
    public PrintTypeVo(String type_code, String type_name) {
        this.type_code = type_code;
        this.type_name = type_name;
    }

    public HashMap<String, Object> convertMap() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("type_code", type_code);
        map.put("type_name", type_name);
        return map;
    }
}
