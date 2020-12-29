package com.wantchu.wantchu_server2.manage.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EventInsertDto {
    private String event_image;
    private String event_title;
    private String event_content;
    private String event_startdate;
    private String event_enddate;

    public EventInsertDto(String event_image, String event_title, String event_content, String event_startdate, String event_enddate) {
        this.event_image = event_image;
        this.event_title = event_title;
        this.event_content = event_content;
        this.event_startdate = event_startdate;
        this.event_enddate = event_enddate;
    }
}
