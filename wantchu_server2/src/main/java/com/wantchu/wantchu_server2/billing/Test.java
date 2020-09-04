package com.wantchu.wantchu_server2.billing;

import com.wantchu.wantchu_server2.billing.model.request.Cancel;
import com.wantchu.wantchu_server2.billing.model.request.User;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;

public class Test {

    static BootpayApi api;
    public static void main(String[] args) {
        api = new BootpayApi("5f3b897b2fa5c20028eecfea", "YDWkp5CW1mi5RGg3fqG33q8/XUJ9stz/v4TRQNVU3fo=");
        goGetToken();
        //goVerify();
        goCancel(); // 환불 처리 메소드
        System.out.println("-===============-");
        System.out.println(api.getToken());
        User user = new User();
        user.setUser_id("01093756927");
        user.setPhone("01093756927");
        try {
            System.out.println(api.getUserToken(user));
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void goGetToken() {
        try {
            api.getAccessToken();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void goVerify() {
        try {
            HttpResponse res = api.verify("5f3e1e6a18e1ae0025e2f0e1");
            String str = IOUtils.toString(res.getEntity().getContent(), "UTF-8");
            org.json.JSONObject jsonObject = new org.json.JSONObject(str);
            if(jsonObject.getInt("status") == 200) {
                System.out.println("status is " + jsonObject.getInt("status"));
                // 두번째 if문
                org.json.JSONObject data = jsonObject.getJSONObject("data");
                int price = data.getInt("price");
                int status = data.getInt("status");
                System.out.println("price is " + price);
                System.out.println("data status is " + status);
            }
            System.out.println(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void goCancel() {
        Cancel cancel = new Cancel();
        cancel.receipt_id = "5f3b89ea2fa5c2004f8fecb2";
        cancel.name = "테스트 결제 취소해기";
        cancel.reason = "만들기 귀찮아요;";

        try {
            HttpResponse res = api.cancel(cancel);
            String str = IOUtils.toString(res.getEntity().getContent(), "UTF-8");
            System.out.println(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
