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
    private int id;
    private int alert_id;
    private String is_read;
    private String alert_title;
    private LocalDateTime alert_startdate;

    public HashMap<String, Object> convertMap(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("alert_id", alert_id);
        map.put("is_read", is_read);
        map.put("alert_title", alert_title);
        map.put("alert_startdate", DateConverter.convertDateWithoutTime(alert_startdate));
        return map;
    }

    @Builder
    public AlertVo(int id, int alert_id, String is_read, String alert_title, LocalDateTime alert_startdate){
        this.id = id;
        this.alert_id = alert_id;
        this.is_read = is_read;
        this.alert_title = alert_title;
        this.alert_startdate = alert_startdate;
    }
}

