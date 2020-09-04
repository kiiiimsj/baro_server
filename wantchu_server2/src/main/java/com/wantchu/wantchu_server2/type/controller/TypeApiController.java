package com.wantchu.wantchu_server2.type.controller;

import com.wantchu.wantchu_server2.business.WriteToServer;
import com.wantchu.wantchu_server2.type.service.TypeService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@Component
@RestController
public class TypeApiController {

    private final TypeService typeService;

    @GetMapping("/TypeFindAll.do")
    public void findAll(@NotNull HttpServletResponse response) {
        org.json.simple.JSONObject jsonObject = typeService.findAll();
        WriteToServer.send(response, jsonObject);
    }
}
