package com.wantchu.wantchu_server2.event.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EventSimpleResponseDto {
    private int event_id;
    private String event_image;

    public EventSimpleResponseDto(int event_id, String event_image){
        this.event_id = event_id;
        this.event_image = event_image;
    }
}