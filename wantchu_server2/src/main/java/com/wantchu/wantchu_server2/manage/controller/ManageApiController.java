package com.wantchu.wantchu_server2.manage.controller;

import com.google.firebase.database.annotations.NotNull;
import com.wantchu.wantchu_server2.business.WriteToServer;
import com.wantchu.wantchu_server2.manage.dto.AlertInsertDto;
import com.wantchu.wantchu_server2.manage.dto.NewStoreInsertDto;
import com.wantchu.wantchu_server2.manage.dto.TypeInsertDto;
import com.wantchu.wantchu_server2.manage.dto.UltraInsertDto;
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
    @PostMapping("/TypeInsert.do")
    public void typeInsert(@RequestBody @NotNull TypeInsertDto request, @NotNull HttpServletResponse response) {
        org.json.simple.JSONObject jsonObject = manageService.typeInsert(request);
        WriteToServer.send(response, jsonObject);
    }

    //type_code 입력을 통한 삭제(type을 어떻게 불러올 것인가?)
    @GetMapping("/TypeDelete.do")
    public void typeDelete(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response) {
        String type_code = request.getParameter("type_code");
        org.json.simple.JSONObject jsonObject = manageService.typeDelete(type_code);
        WriteToServer.send(response, jsonObject);
    }

    //type 리스트 가져오기
    @GetMapping("/TypePrint.do")
    public void typePrint(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response) {
        org.json.simple.JSONObject jsonObject = manageService.typePrint();
        WriteToServer.send(response, jsonObject);
    }

//    //ultra스토어 insert해주기
//    @PostMapping("/UltraInsert.do")
//    public void ultraInsert(@RequestBody @NotNull UltraInsertDto request, @NotNull HttpServletResponse response) {
//        org.json.simple.JSONObject jsonObject = manageService.insertUltra(request);
//        WriteToServer.send(response, jsonObject);
//    }
//
//    //ultra 삭제하기
//    @GetMapping("UltraDelete.do")
//    public void ultraDelete(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response) {
//        int store_id = Integer.parseInt(request.getParameter("store_id"));
//        org.json.simple.JSONObject jsonObject = manageService.ultraDelete(store_id);
//        WriteToServer.send(response, jsonObject);
//    }
//
//    //ultra스토어 리스트 가져오기
//    @GetMapping("/UltraPrint.do")
//    public void ultraPrint(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response) {
//        org.json.simple.JSONObject jsonObject = manageService.ultraPrint();
//        WriteToServer.send(response, jsonObject);
//    }
//
//    //new스토어 insert해주기
//    @PostMapping("/NewStoreInsert.do")
//    public void newStoreInsert(@RequestBody @NotNull NewStoreInsertDto request, @NotNull HttpServletResponse response) {
//        org.json.simple.JSONObject jsonObject = manageService.insertNewStore(request);
//        WriteToServer.send(response, jsonObject);
//    }
//
//    //ultra 삭제하기
//    @GetMapping("NewStoreDelete.do")
//    public void newStoreDelete(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response) {
//        int store_id = Integer.parseInt(request.getParameter("store_id"));
//        org.json.simple.JSONObject jsonObject = manageService.newStoreDelete(store_id);
//        WriteToServer.send(response, jsonObject);
//    }
//
//    //ultra스토어 리스트 가져오기
//    @GetMapping("/UltraPrint.do")
//    public void newStorePrint(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response) {
//        org.json.simple.JSONObject jsonObject = manageService.newStorePrint();
//        WriteToServer.send(response, jsonObject);
//    }
//
//    //Alert insert해주기
//    @PostMapping("/AlertInsert.do")
//    public void alertInsert(@RequestBody @NotNull AlertInsertDto request, @NotNull HttpServletResponse response) {
//        org.json.simple.JSONObject jsonObject = manageService.insertAlert(request);
//        WriteToServer.send(response, jsonObject);
//    }
//
//    //alert 삭제하기
//    @GetMapping("/AlertDelete.do")
//    public void alertDelete(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response) {
//        int alert_id = Integer.parseInt(request.getParameter("alert_id"));
//        org.json.simple.JSONObject jsonObject = manageService.alertDelete(alert_id);
//        WriteToServer.send(response, jsonObject);
//    }
//
//    //alert 리스트 가져오기
//    @GetMapping("/AlertPrint.do")
//    public void alertPrint(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response) {
//        org.json.simple.JSONObject jsonObject = manageService.alertPrint();
//        WriteToServer.send(response, jsonObject);
//    }

}
