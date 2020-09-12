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
public class EventVo {
    private int event_id;
    private String event_image;
    private String event_title;
    private String event_content;
    private LocalDateTime event_startdate;
    private LocalDateTime event_enddate;

    public HashMap<String, Object> convertMap(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("event_id", event_id);
        map.put("event_image", event_image);
        map.put("event_title", event_title);
        map.put("event_content", event_content);
        map.put("event_startdate", DateConverter.convertDateWithoutTime(event_startdate));
        map.put("event_enddate", DateConverter.convertDateWithoutTime(event_enddate));
        return map;
    }

    @Builder
    public EventVo(int event_id, String event_image, String event_title, String event_content, LocalDateTime event_startdate, LocalDateTime event_enddate){
        this.event_id = event_id;
        this.event_image = event_image;
        this.event_title = event_title;
        this.event_content = event_content;
        this.event_startdate = event_startdate;
        this.event_enddate = event_enddate;
    }
}
