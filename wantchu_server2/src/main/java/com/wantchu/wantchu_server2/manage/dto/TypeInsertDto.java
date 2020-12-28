package com.wantchu.wantchu_server2.manage.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TypeInsertDto {
    private String type_code;
    private String type_name;
    private String type_image;

    public TypeInsertDto(String type_code, String type_name, String type_image) {
        this.type_code = type_code;
        this.type_name = type_name;
        this.type_image = type_image;
    }
}
