package com.wantchu.wantchu_server2.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;

@NoArgsConstructor
@Getter
@Setter
public class PrintEventVo {
    private int event_id;
    private String event_title;

    @Builder
    public PrintEventVo(int event_id, String event_title) {
        this.event_id = event_id;
        this.event_title = event_title;
    }

    public HashMap<String, Object> convertMap() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("event_id", event_id);
        map.put("event_title", event_title);
        return map;
    }
}
