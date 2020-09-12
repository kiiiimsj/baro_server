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
public class AlertVo {
    private int alert_id;
    private String alert_title;
    private String alert_content;
    private LocalDateTime alert_startdate;

    public HashMap<String, Object> convertMap(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("alert_id", alert_id);
        map.put("alert_title", alert_title);
        map.put("alert_content", alert_content);
        map.put("alert_startdate", DateConverter.convertDateWithoutTime(alert_startdate));
        return map;
    }

    @Builder
    public AlertVo(int alert_id, String alert_title, String alert_content, LocalDateTime alert_startdate){
        this.alert_id = alert_id;
        this.alert_title = alert_title;
        this.alert_content = alert_content;
        this.alert_startdate = alert_startdate;
    }
}

