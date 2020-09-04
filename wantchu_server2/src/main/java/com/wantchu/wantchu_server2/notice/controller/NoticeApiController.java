package com.wantchu.wantchu_server2.notice.controller;

import com.wantchu.wantchu_server2.business.WriteToServer;
import com.wantchu.wantchu_server2.notice.dto.NoticeSaveRequestDto;
import com.wantchu.wantchu_server2.notice.service.NoticeService;
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
public class NoticeApiController {

    private final NoticeService noticeService;

    @GetMapping("/NoticeFindAll.do")
    public void findAll(@NotNull HttpServletResponse response) {
        org.json.simple.JSONObject jsonObject = noticeService.findAll();
        WriteToServer.send(response, jsonObject);
    }

    @GetMapping("/NoticeFindById.do")
    public void findNotice(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response) {
        int notice_id = Integer.parseInt(request.getParameter("notice_id"));
        org.json.simple.JSONObject jsonObject = noticeService.findByNoticeId(notice_id);
        WriteToServer.send(response, jsonObject);
    }

    @PostMapping("/NoticeSave.do")
    public void save(@RequestBody @NotNull NoticeSaveRequestDto requestDto, @NotNull HttpServletResponse response) {
        org.json.simple.JSONObject jsonObject = noticeService.save(requestDto);
        WriteToServer.send(response, jsonObject);
    }

    @GetMapping("/NoticeFindByCode.do")
    public void noticeByCode(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response) {
        String notice_code = request.getParameter("notice_code");
        org.json.simple.JSONObject jsonObject = noticeService.findByCode(notice_code);
        WriteToServer.send(response, jsonObject);
    }
}
