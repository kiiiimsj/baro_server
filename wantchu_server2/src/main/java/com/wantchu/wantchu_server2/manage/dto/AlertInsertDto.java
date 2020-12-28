package com.wantchu.wantchu_server2.manage.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AlertInsertDto {
    private int alert_id;
    private String alert_title;
    private String alert_content;

    public AlertInsertDto(int alert_id, String alert_title, String alert_content) {
        this.alert_id = alert_id;
        this.alert_title = alert_title;
        this.alert_content = alert_content;
    }
}
