package com.wantchu.wantchu_server2.owner.controller;

import com.wantchu.wantchu_server2.business.WriteToServer;
import com.wantchu.wantchu_server2.owner.dto.*;
import com.wantchu.wantchu_server2.owner.service.OwnerService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@Component
@RestController
public class OwnerApiController {

    private final OwnerService ownerService;

    @PostMapping("/OwnerLogin.do")
    public void login(@RequestBody @NotNull OwnerLoginRequestDto requestDto, @NotNull HttpServletResponse response) {
        org.json.simple.JSONObject jsonObject = ownerService.login(requestDto);
        WriteToServer.send(response, jsonObject);
    }

    @PutMapping("/OwnerPassUpdate.do")
    public void updatePassword(@RequestBody @NotNull OwnerPassUpdateRequestDto requestDto, @NotNull HttpServletResponse response) {
        org.json.simple.JSONObject jsonObject = ownerService.updatePassword(requestDto);
        WriteToServer.send(response, jsonObject);
    }

    @PutMapping("/OwnerEmailUpdate.do")
    public void updateEmail(@RequestBody @NotNull OwnerEmailUpdateRequestDto requestDto, @NotNull HttpServletResponse response) {
        org.json.simple.JSONObject jsonObject = ownerService.updateEmail(requestDto);
        WriteToServer.send(response, jsonObject);
    }

    @PostMapping("/OwnerRegister.do")
    public void register(@RequestBody @NotNull OwnerRegisterRequestDto requestDto, @NotNull HttpServletResponse response) {
        org.json.simple.JSONObject jsonObject = ownerService.register(requestDto);
        WriteToServer.send(response, jsonObject);
    }

//    @PutMapping("/OwnerSetStore.do")
//    public void setOwnerStore(@RequestBody @NotNull OwnerStoreSetRequestDto requestDto, @NotNull HttpServletResponse response) {
//        org.json.simple.JSONObject jsonObject = ownerService.setOwnerStore(requestDto);
//        WriteToServer.send(response, jsonObject);
//    }

//    @PutMapping("/OwnerUpdates.do")
//    public void ownerUpdates(@RequestBody @NotNull String updateRequest, @NotNull HttpServletResponse response) {
//        org.json.simple.JSONObject jsonObjectForResponse = ownerService.ownerUpdates(updateRequest);
//        WriteToServer.send(response, jsonObjectForResponse);
//    }

    @PostMapping("/OwnerFindPriceBetweenDate.do")
    public void findPriceBetweenDate(@RequestBody OwnerPriceBetweenDateRequestDto requestDto, HttpServletResponse response) {
        org.json.simple.JSONObject jsonObject = ownerService.findPriceBetweenDate(requestDto);
        WriteToServer.send(response, jsonObject);
    }

    @PutMapping("/OwnerSetStoreStatus.do")
    public void setStoreOpenOrClosed(@RequestBody OwnerStoreStatusUpdateRequestDto requestDto, HttpServletResponse response){
        org.json.simple.JSONObject jsonObject = ownerService.setStoreOpenOrClosed(requestDto.getIs_open(), requestDto.getStore_id());
        WriteToServer.send(response, jsonObject);
    }

    @PostMapping("/OwnerSendMessage.do")
    public void sendMessageToCustomer(@RequestBody OwnerMessageRequestDto requestDto, HttpServletResponse response) {
        org.json.simple.JSONObject jsonObject = ownerService.sendMessageToCustomer(requestDto);
        WriteToServer.send(response, jsonObject);
    }
    @PutMapping("/OwnerSetOrderStatus.do")
    public void setOrderStatus(@RequestBody OwnerSetStatusRequestDto requestDto, HttpServletResponse response){
        org.json.simple.JSONObject jsonObject = ownerService.setStatusToCustomer(requestDto.getReceipt_id(), requestDto.getStore_id());
        WriteToServer.send(response, jsonObject);
    }

    //store_id필요?
    @PutMapping("/OwnerSetOrderStatusComplete.do")
    public void setOrderStatusComplete(@RequestBody OwnerSetStatusCompleteRequestDto requestDto, HttpServletResponse response){
        org.json.simple.JSONObject jsonObject = ownerService.setStatusCompleteToCustomer(requestDto.getReceipt_id(), requestDto.getStore_id());
        WriteToServer.send(response, jsonObject);
    }

    @PostMapping("/OwnerSetstatistics.do")
    public void setStatistics(@RequestBody OwnerSetStatisticsRequestDto requestDto, HttpServletResponse response){
        org.json.simple.JSONObject jsonObject = ownerService.setStatistics(requestDto);
        WriteToServer.send(response, jsonObject);
    }

    @PostMapping("/OwnerMenuStatistics.do")
    public void setMenu(@RequestBody OwnerSetStatisticsRequestDto requestDto, HttpServletResponse response) {
        org.json.simple.JSONObject jsonObject = ownerService.setMenuStatistics(requestDto);
        WriteToServer.send(response, jsonObject);
    }

    @GetMapping("/OwnerCalculate.do")
    public void calculate(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response) {
        int store_id = Integer.parseInt(request.getParameter("store_id"));
        org.json.simple.JSONObject jsonObject = ownerService.getCalculate(store_id);
        WriteToServer.send(response, jsonObject);
    }
    @PostMapping("/SetStoreDiscount.do")
    public void setStoreDiscount(@RequestBody OwnerSetStoreDiscountDto requestDto, HttpServletResponse response) {
        org.json.simple.JSONObject jsonObject = ownerService.setStoreDiscount(requestDto);
        WriteToServer.send(response, jsonObject);
    }

}
