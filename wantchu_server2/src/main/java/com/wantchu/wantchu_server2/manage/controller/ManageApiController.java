package com.wantchu.wantchu_server2.manage.controller;

import com.google.firebase.database.annotations.NotNull;
import com.wantchu.wantchu_server2.business.WriteToServer;
import com.wantchu.wantchu_server2.manage.service.ManageService;
import com.wantchu.wantchu_server2.vo.FavoriteVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@Component
@RestController
public class ManageApiController {

    private final ManageService manageService;

    //type 추가 - type 이름/이미지 받기
//    @PutMapping("/ManageTypeInsert.do")
//    public void typeInsert(@RequestBody @NotNull String request, @NotNull HttpServletResponse response) {
//        org.json.simple.JSONObject jsonObject = manageService.insertType(request);
//        WriteToServer.send(response, jsonObject);
//    }
//
//    //type_code 입력을 통한 삭제(type을 어떻게 불러올 것인가?)
//    @GetMapping("/ManageTypeDelete.do")
//    public void typeDelete(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response) {
//        String type_code = request.getParameter("type_code");
//        org.json.simple.JSONObject jsonObject = manageService.typeDelete(type_code);
//        WriteToServer.send(response, jsonObject);
//    }

    //ultra_store 추가(기존 store를 나열해야함..)

    //ultra_store 삭제(기존 ultra_store뽑아와서 선택)

    //new_store도 마찬가지



}
