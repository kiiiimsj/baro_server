package com.wantchu.wantchu_server2.manage.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MarketingDto {
    private String title;
    private String content;

    public MarketingDto(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
