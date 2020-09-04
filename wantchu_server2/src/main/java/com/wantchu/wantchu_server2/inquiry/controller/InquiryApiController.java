package com.wantchu.wantchu_server2.inquiry.controller;

import com.wantchu.wantchu_server2.business.WriteToServer;
import com.wantchu.wantchu_server2.inquiry.dto.InquirySaveRequestDto;
import com.wantchu.wantchu_server2.inquiry.service.InquiryService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@Component
@RestController
public class InquiryApiController {

    private final InquiryService inquiryService;

    @GetMapping("/InquiryListFindByEmail.do")
    public void findInquiryListByPhone(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response) {
        String email = request.getParameter("email");
        org.json.simple.JSONObject jsonObject = inquiryService.findInquiryListByEmail(email);
        WriteToServer.send(response, jsonObject);
    }

    @GetMapping("/InquiryFindById.do")
    public void inquiryFindById(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response) {
        int inquiry_id = Integer.parseInt(request.getParameter("inquiry_id"));
        org.json.simple.JSONObject jsonObject = inquiryService.findInquiryById(inquiry_id);
        WriteToServer.send(response, jsonObject);
    }

    @PostMapping("/InquirySave.do")
    public void inquirySave(@RequestBody @NotNull InquirySaveRequestDto requestDto, @NotNull HttpServletResponse response) {
        org.json.simple.JSONObject jsonObject = inquiryService.save(requestDto);
        WriteToServer.send(response, jsonObject);
    }
}
