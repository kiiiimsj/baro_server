package com.wantchu.wantchu_server2.vo;

import com.wantchu.wantchu_server2.business.DateConverter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashMap;

@NoArgsConstructor
@Getter
@Setter
public class NoticeVo {

    private int notice_id;
    private String title;
    private String content;
    private String notice_code;
    private LocalDateTime notice_date;

    public NoticeVo(String title, String content,String notice_code) {
        this.title = title;
        this.content = content;
        this.notice_code = notice_code;
    }

    public NoticeVo(int notice_id, String title, String  content, String notice_code, LocalDateTime notice_date) {
        this.notice_id = notice_id;
        this.title = title;
        this.content = content;
        this.notice_code = notice_code;
        this.notice_date = notice_date;
    }

    public HashMap<String, Object> convertMap() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("notice_id", notice_id);
        map.put("title", title);
        map.put("content", content);
        map.put("notice_code", notice_code);
        map.put("notice_date", DateConverter.convertDateWithoutTime(notice_date));
        return map;
    }
}
