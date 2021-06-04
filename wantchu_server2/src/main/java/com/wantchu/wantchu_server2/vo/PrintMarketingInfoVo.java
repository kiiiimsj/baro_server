package com.wantchu.wantchu_server2.vo;

import com.wantchu.wantchu_server2.business.DateConverter;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashMap;

@NoArgsConstructor
@Getter
@Setter
public class PrintMarketingInfoVo {
    private String device_token;

    public HashMap<String, Object> convertMap(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("device_token", device_token);
        return map;
    }

    @Builder
    public PrintMarketingInfoVo(String device_token){
        this.device_token = device_token;
    }
}

