package com.wantchu.wantchu_server2.coupon.controller;

import com.wantchu.wantchu_server2.business.WriteToServer;
import com.wantchu.wantchu_server2.coupon.service.CouponService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@Component
@RestController
public class CouponApiController {

    private final CouponService couponService;

    @GetMapping("/CouponFindByPhone.do")
    public void findCouponsByPhone(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response) {
        String phone = request.getParameter("phone");
        org.json.simple.JSONObject jsonObject = couponService.findCouponsByPhone(phone);
        WriteToServer.send(response, jsonObject);
    }

    @GetMapping("/CouponCountByPhone.do")
    public void findCouponCountByPhone(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response) {
        String phone = request.getParameter("phone");
        org.json.simple.JSONObject jsonObject = couponService.findCouponCountByPhone(phone);
        WriteToServer.send(response, jsonObject);
    }

    @GetMapping("/CouponHistoryByPhone.do")
    public void findCouponHistoryByPhone(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response) {
        String phone = request.getParameter("phone");
        org.json.simple.JSONObject jsonObject = couponService.findCouponHistoryByPhone(phone);
        WriteToServer.send(response, jsonObject);
    }

    @GetMapping("/CouponFindUsable.do")
    public void findUsableCoupons(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response) {
        String phone = request.getParameter("phone");
        int price = Integer.parseInt(request.getParameter("price"));
        org.json.simple.JSONObject jsonObject = couponService.findUsableCouponsByPhoneAndPrice(phone, price);
        WriteToServer.send(response, jsonObject);
    }

    @GetMapping("/CouponInsertByNumber.do")
    public void insertCouponNumber(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response) {
        String phone = request.getParameter("phone");
        String coupon_number = request.getParameter("coupon_number");
        org.json.simple.JSONObject jsonObject = couponService.insertCouponNumber(phone, coupon_number);
        WriteToServer.send(response, jsonObject);
    }
}
