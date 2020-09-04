package com.wantchu.wantchu_server2.notice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NoticeSaveRequestDto {

    private String title;
    private String content;
    private String notice_code;

    public NoticeSaveRequestDto(String title, String content, String notice_code) {
        this.title = title;
        this.content = content;
        this.notice_code = notice_code;
    }
}
