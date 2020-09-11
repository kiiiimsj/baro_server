package com.wantchu.wantchu_server2.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class OwnerVo {

    private String phone;
    private String email;
    private String nick;
    private String pass;
    private int store_id;
    private String store_name;
    private String is_open;

    @Builder
    public OwnerVo(String phone, String store_name, String email, String nick, int store_id, String is_open) {
        this.phone = phone;
        this.store_name = store_name;
        this.email = email;
        this.nick = nick;
        this.store_id = store_id;
        this.is_open = is_open;
    }
}
