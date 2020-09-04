package com.wantchu.wantchu_server2.billing.model.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Token {
    public String application_id;
    public String private_key;
}
