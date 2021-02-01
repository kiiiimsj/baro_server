package com.wantchu.wantchu_server2.store.controller;

import com.wantchu.wantchu_server2.business.WriteToServer;
import com.wantchu.wantchu_server2.store.dto.StoreInfoFindByKeywordDto;
import com.wantchu.wantchu_server2.store.dto.StoreInfoFindByTypeDto;
import com.wantchu.wantchu_server2.store.dto.StoreLocationDto;
import com.wantchu.wantchu_server2.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@Component
@RestController
public class StoreApiController {

    private final StoreService storeService;

    @GetMapping("/StoreFindById.do")
    public void findById(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response) {
        int store_id = Integer.parseInt(request.getParameter("store_id"));
        org.json.simple.JSONObject jsonObject = storeService.findById(store_id);
        WriteToServer.send(response, jsonObject);
    }

    @PostMapping("/StoreSearchByKeyword.do")
    public void searchStore(@RequestBody @NotNull StoreInfoFindByKeywordDto requestDto, @NotNull HttpServletResponse response) {
        org.json.simple.JSONObject jsonObject = storeService.findInfoByKeyword(requestDto);
        WriteToServer.send(response, jsonObject);
    }

    @PostMapping("/StoreInfoFindByType.do")
    public void findInfoByTypeCode(@RequestBody @NotNull StoreInfoFindByTypeDto requestDto, @NotNull HttpServletResponse response) {
        org.json.simple.JSONObject jsonObject = storeService.findInfoByTypeCode(requestDto);
        WriteToServer.send(response, jsonObject);
    }

    @PostMapping("/StoreAllLocation.do")
    public void findAllStoreLocation(@RequestBody @NotNull StoreLocationDto requestDto, @NotNull HttpServletResponse response) {
        org.json.simple.JSONObject jsonObject = storeService.findAllStoreLocation(requestDto);
        WriteToServer.send(response, jsonObject);
    }

    @GetMapping("/StoreFindIdByStoreName.do")
    public void findStoreIdByStoreName(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response) {
        String store_name = request.getParameter("store_name");
        org.json.simple.JSONObject jsonObject = storeService.findStoreIdByStoreName(store_name);
        WriteToServer.send(response, jsonObject);
    }

    @GetMapping("/StoreCheckIsOpen.do")
    public void isStoreOpen(HttpServletRequest request, HttpServletResponse response) {
        int store_id = Integer.parseInt(request.getParameter("store_id"));
        org.json.simple.JSONObject jsonObject = storeService.isStoreOpen(store_id);
        WriteToServer.send(response, jsonObject);
    }

    @PostMapping("/StoreFindByUltra.do")
    public void findByUltra(@RequestBody @NotNull StoreLocationDto requestDto, @NotNull HttpServletResponse response) {
        org.json.simple.JSONObject jsonObject = storeService.findByUltra(requestDto);
        WriteToServer.send(response, jsonObject);
    }

    @PostMapping("/StoreFindByNew.do")
    public void findByNew(@RequestBody @NotNull StoreLocationDto requestDto, @NotNull HttpServletResponse response) {
        org.json.simple.JSONObject jsonObject = storeService.findByNew(requestDto);
        WriteToServer.send(response, jsonObject);
    }
    @PostMapping("/StoreFindAll.do")
    public void storeFindAll(@RequestBody @NotNull StoreLocationDto requestDto, @NotNull HttpServletResponse response) {
        org.json.simple.JSONObject jsonObject = storeService.storeFindAll(requestDto);
        WriteToServer.send(response, jsonObject);
    }
}