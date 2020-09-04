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
public class InquiryVo {

    private int inquiry_id;
    private String email;
    private String title;
    private String content;
    private LocalDateTime inquiry_date;
    private String is_replied;

    public HashMap<String, Object> convertToListMap() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("inquiry_id", inquiry_id);
        map.put("title", title);
        map.put("inquiry_date", DateConverter.convertDateWithTime(inquiry_date));
        map.put("is_replied", is_replied);
        return map;
    }
}
