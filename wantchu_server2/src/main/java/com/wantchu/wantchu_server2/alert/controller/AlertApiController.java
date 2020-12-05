package com.wantchu.wantchu_server2.alert.controller;

import com.google.firebase.database.annotations.NotNull;
import com.wantchu.wantchu_server2.alert.service.AlertService;
import com.wantchu.wantchu_server2.business.WriteToServer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@Component
@RestController
public class AlertApiController {

    private final AlertService alertService;

    @GetMapping("/AlertFindAll.do")
    public void alertFind(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response){
        String phone = request.getParameter("phone");
        org.json.simple.JSONObject jsonObject = alertService.alertFind(phone);
        WriteToServer.send(response, jsonObject);
    }

    @GetMapping("/GetRecentlyAlertDate.do")
    public void alertRecently(@NotNull HttpServletResponse response){
        org.json.simple.JSONObject jsonObject = alertService.alertRecently();
        WriteToServer.send(response, jsonObject);
    }
    @GetMapping("/GetLatestAlertWhenMemberLogin.do")
    public void getLatestAlert(@NotNull HttpServletResponse response) {
        org.json.simple.JSONObject jsonObject = alertService.getLatestAlert();
        WriteToServer.send(response, jsonObject);
    }

    @GetMapping("/GetNewAlertCount.do")
    public void getNewAlertCount(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response) {
        String phone = request.getParameter("phone");
        org.json.simple.JSONObject jsonObject = alertService.getNewAlertCount(phone);
        WriteToServer.send(response, jsonObject);
    }

}
