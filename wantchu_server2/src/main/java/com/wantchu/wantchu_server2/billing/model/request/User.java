package com.wantchu.wantchu_server2.billing.model.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class User {
    public String user_id;
    public String email;
    public String name;
    public int gender;
    public String birth;
    public String phone;
}
