package com.wantchu.wantchu_server2.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;

@NoArgsConstructor
@Getter
@Setter
public class PrintAlertVo {
    private int alert_id;
    private String alert_title;

    @Builder
    public PrintAlertVo(int alert_id, String alert_title) {
        this.alert_id = alert_id;
        this.alert_title = alert_title;
    }

    public HashMap<String, Object> convertMap() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("alert_id", alert_id);
        map.put("alert_title", alert_title);
        return map;
    }
}
