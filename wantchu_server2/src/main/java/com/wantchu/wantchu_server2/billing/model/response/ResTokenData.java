package com.wantchu.wantchu_server2.billing.model.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ResTokenData {
    public String token;
    public long server_time;
    public long expired_at;
}
