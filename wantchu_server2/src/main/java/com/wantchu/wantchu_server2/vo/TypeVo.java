package com.wantchu.wantchu_server2.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;

@NoArgsConstructor
@Getter
@Setter
public class TypeVo {

    private String type_code;
    private String type_name;
    private String type_image;

    public HashMap<String, Object> convertMap() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("type_code", type_code);
        map.put("type_name", type_name);
        map.put("type_image", type_image);
        return map;
    }

    @Builder
    public TypeVo(String type_code, String type_name, String type_image) {
        this.type_image = type_image;
        this.type_name = type_name;
        this.type_code = type_code;
    }
}