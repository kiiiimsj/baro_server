package com.wantchu.wantchu_server2.billing.model.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Item {

    public String item_name;
    public int qty;
    public String unique;
    public long price;
}
