package com.wantchu.wantchu_server2.billing.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class BillingCancelRequestDto {
    private String receipt_id;
    private String cancel_reason;
    private String store_name;
    private String nick;
}
