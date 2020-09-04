package com.wantchu.wantchu_server2.billing.model.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Cancel {

    public String receipt_id; // 부트페이에서 발급하는 고유 영수증 ID
    public String name;  // 판매된 대표 상품명
    public String reason;  // 취소 사유
    public Double price;  // 가격
}
