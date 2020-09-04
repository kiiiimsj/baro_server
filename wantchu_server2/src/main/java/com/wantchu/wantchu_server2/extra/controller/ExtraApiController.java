package com.wantchu.wantchu_server2.extra.controller;

import com.wantchu.wantchu_server2.business.WriteToServer;
import com.wantchu.wantchu_server2.extra.service.ExtraService;
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
public class ExtraApiController {

    private final ExtraService extraService;

    @GetMapping("/ExtraFindByMenuId.do")
    public void findByMenuId(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response) {
        int menu_id = Integer.parseInt(request.getParameter("menu_id"));
        org.json.simple.JSONObject jsonObject = extraService.findByMenuId(menu_id);
        WriteToServer.send(response, jsonObject);
    }
}
