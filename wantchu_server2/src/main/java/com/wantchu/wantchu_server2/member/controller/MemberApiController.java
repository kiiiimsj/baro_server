package com.wantchu.wantchu_server2.member.controller;

import com.wantchu.wantchu_server2.business.WriteToServer;
import com.wantchu.wantchu_server2.member.dto.*;
import com.wantchu.wantchu_server2.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.annotation.RegEx;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@Component
@RestController
public class MemberApiController {


    private final MemberService memberService;

    @PostMapping("/MemberLogin.do")
    public void login(@RequestBody @NotNull MemberLoginRequestDto requestDto, @NotNull HttpServletResponse response) {
        org.json.simple.JSONObject jsonObject = memberService.login(requestDto);
        WriteToServer.send(response, jsonObject);
    }

    @GetMapping("/MemberPhoneCheck.do")
    public void login(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response) {
        String phone = request.getParameter("phone");
        org.json.simple.JSONObject jsonObject = memberService.isPhoneInUse(phone);
        WriteToServer.send(response, jsonObject);
    }

    @PostMapping("/MemberRegister.do")
    public void register(@RequestBody @NotNull MemberRegisterRequestDto registerRequestDto, @NotNull HttpServletResponse response) {
        org.json.simple.JSONObject jsonObject = memberService.register(registerRequestDto);
        WriteToServer.send(response, jsonObject);
    }

    @PutMapping("/MemberPassUpdate.do")
    public void updatePassword(@RequestBody @NotNull MemberPassUpdateRequestDto requestDto, @NotNull HttpServletResponse response) {
        org.json.simple.JSONObject jsonObject = memberService.updatePassword(requestDto);
        WriteToServer.send(response, jsonObject);
    }

    @PutMapping("/MemberEmailUpdate.do")
    public void updateEmail(@RequestBody @NotNull MemberEmailUpdateRequestDto requestDto, @NotNull HttpServletResponse response) {
        org.json.simple.JSONObject jsonObject = memberService.updateEmail(requestDto);
        WriteToServer.send(response, jsonObject);
    }


}
