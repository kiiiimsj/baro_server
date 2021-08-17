package com.wantchu.wantchu_server2.billing.controller;

import com.wantchu.wantchu_server2.billing.BootpayApi;
import com.wantchu.wantchu_server2.billing.dto.BillingCancelRequestDto;
import com.wantchu.wantchu_server2.billing.dto.BillingVerifyRequestDto;
import com.wantchu.wantchu_server2.billing.model.request.Cancel;
import com.wantchu.wantchu_server2.billing.model.request.User;
import com.wantchu.wantchu_server2.business.ObjectMaker;
import com.wantchu.wantchu_server2.business.WriteToServer;
import com.wantchu.wantchu_server2.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor
@Component
@RestController
public class BillingApiController {

    private final OrderService orderService;

    private static final String REST_APPLICATION_ID = "5fa1191418e1ae00274f48f3";
    private static final String PRIVATE_KEY = "BXMefhLcIaj38dJnghkUT3FrpnrdrZn5HUYNJKMsRkE=";

    @SuppressWarnings("unchecked")
    @PostMapping("/BillingGetUserToken.do")
    public void getUserToken(@RequestBody @NotNull User user, HttpServletResponse response) {
        BootpayApi api = new BootpayApi(REST_APPLICATION_ID, PRIVATE_KEY);
        goGetToken(api);
        String user_id = user.getUser_id();
        String phone = user.getPhone();
        User userForUserToken = new User();
        userForUserToken.setUser_id(user_id);
        userForUserToken.setPhone(phone);
        try {
            String userTokenResponse = api.getUserToken(userForUserToken);

            org.json.JSONObject jsonUserTokenResponseObject = new org.json.JSONObject(userTokenResponse);
            org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();
            org.json.JSONObject userTokenData = jsonUserTokenResponseObject.getJSONObject("data");
            jsonObject.put("user_token", userTokenData.getString("user_token"));
            jsonObject.put("expired_unixtime", userTokenData.getLong("expired_unixtime"));
            jsonObject.put("expired_localtime", userTokenData.getString("expired_localtime"));
            WriteToServer.send(response, jsonObject);
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    @PostMapping("/BillingVerify.do")
    public void verifyBilling(@RequestBody BillingVerifyRequestDto requestDto, HttpServletResponse response) {
        BootpayApi api = new BootpayApi(REST_APPLICATION_ID, PRIVATE_KEY);
        try {
            api.getAccessToken();
        } catch(Exception e){
            e.printStackTrace();
        }
        goVerify(api, requestDto, response);
    }

    @PostMapping("/BillingCancel.do")
    public void cancelBilling(@RequestBody BillingCancelRequestDto requestDto, HttpServletResponse response) {
        BootpayApi api = new BootpayApi(REST_APPLICATION_ID, PRIVATE_KEY);
        try {
            api.getAccessToken();
        } catch(Exception e) {
            e.printStackTrace();
        }
        goCancel(api, requestDto, response);
    }

    private void goGetToken(BootpayApi api) {
        try {
            api.getAccessToken();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private void goVerify(BootpayApi api, BillingVerifyRequestDto requestDto, HttpServletResponse response) {
        int priceFromRequestDto = requestDto.getPrice();
        org.json.simple.JSONObject jsonObjectForResponse = ObjectMaker.getSimpleJSONObject();
        try {
            HttpResponse httpResponse = api.verify(requestDto.getReceipt_id());
            String jsonResponseFromBootPay = IOUtils.toString(httpResponse.getEntity().getContent(), StandardCharsets.UTF_8);
            org.json.JSONObject jsonObject = new org.json.JSONObject(jsonResponseFromBootPay);
            int status = jsonObject.getInt("status");
            int code = jsonObject.getInt("code");
            String message = jsonObject.getString("message");
            if(status == 200) {
                org.json.JSONObject data = jsonObject.getJSONObject("data");
                int priceFromData = data.getInt("price");
                int statusFromData = data.getInt("status");
                if((priceFromData == priceFromRequestDto) && (statusFromData == 1)) {
                    jsonObjectForResponse.put("result", true);
                    jsonObjectForResponse.put("message", message);
                    jsonObjectForResponse.put("code", code);
                } else {
                    jsonObjectForResponse.put("result", false);
                    jsonObjectForResponse.put("message", message);
                    jsonObjectForResponse.put("code", code);
                }
            } else {
                jsonObjectForResponse.put("result", false);
                jsonObjectForResponse.put("message", message);
                jsonObjectForResponse.put("code", code);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

        WriteToServer.send(response, jsonObjectForResponse);
    }

    @SuppressWarnings("unchecked")
    private void goCancel(BootpayApi api, @NotNull BillingCancelRequestDto requestDto, HttpServletResponse response) {
        Cancel cancel = new Cancel();
        cancel.setReceipt_id(requestDto.getReceipt_id());
        cancel.setName(requestDto.getStore_name() + "(점주 : " + requestDto.getNick() + ")");
        cancel.setReason(requestDto.getCancel_reason());
        org.json.simple.JSONObject jsonObjectForResponse = ObjectMaker.getSimpleJSONObject();

        try {
            HttpResponse httpResponse = api.cancel(cancel);
            String responseFromBootPay = IOUtils.toString(httpResponse.getEntity().getContent(), StandardCharsets.UTF_8);
            org.json.JSONObject jsonObject = new org.json.JSONObject(responseFromBootPay);
            int status = jsonObject.getInt("status");
            int code = jsonObject.getInt("code");
            String message = jsonObject.getString("message");
            if(status == 200) {
                orderService.setOrderStateAsCancel(requestDto.getReceipt_id());
                org.json.JSONObject data = jsonObject.getJSONObject("data");
                int request_cancel_price = data.getInt("request_cancel_price");
                int cancelled_price = data.getInt("cancelled_price");
                String revoked_at = data.getString("revoked_at");
                jsonObjectForResponse.put("result", true);
                jsonObjectForResponse.put("code", code);
                jsonObjectForResponse.put("message", message);
                jsonObjectForResponse.put("request_cancel_price", request_cancel_price);
                jsonObjectForResponse.put("cancelled_price", cancelled_price);
                jsonObjectForResponse.put("revoked_at", revoked_at);
            } else {
                jsonObjectForResponse.put("result", false);
                jsonObjectForResponse.put("code", code);
                jsonObjectForResponse.put("message", message);
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        WriteToServer.send(response, jsonObjectForResponse);
    }
}
