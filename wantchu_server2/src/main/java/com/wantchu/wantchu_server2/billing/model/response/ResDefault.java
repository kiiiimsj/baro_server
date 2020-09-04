package com.wantchu.wantchu_server2.billing.model.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ResDefault {
    public int status;
    public int code;
    public String message;
}
