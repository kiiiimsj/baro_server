package com.wantchu.wantchu_server2.inquiry.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class InquirySaveRequestDto {

    private String email;
    private String title;
    private String content;

    public InquirySaveRequestDto(String email, String title, String content) {
        this.email = email;
        this.title = title;
        this.content = content;
    }
}
