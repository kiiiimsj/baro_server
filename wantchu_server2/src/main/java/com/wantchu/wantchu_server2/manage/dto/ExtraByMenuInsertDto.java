package com.wantchu.wantchu_server2.manage.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ExtraByMenuInsertDto {
    private int extra_id;
    private int menu_id;
    private int extra_maxcount;

    public ExtraByMenuInsertDto(int extra_id, int menu_id, int extra_maxcount) {
        this.extra_id = extra_id;
        this.menu_id = menu_id;
        this.extra_maxcount = extra_maxcount;
    }
}
