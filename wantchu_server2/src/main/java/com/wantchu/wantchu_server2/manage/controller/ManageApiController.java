package com.wantchu.wantchu_server2.manage.controller;

import com.google.firebase.database.annotations.NotNull;
import com.wantchu.wantchu_server2.business.WriteToServer;
import com.wantchu.wantchu_server2.manage.dto.*;
import com.wantchu.wantchu_server2.manage.service.ManageService;
import com.wantchu.wantchu_server2.member.dto.MemberRegisterRequestDto;
import com.wantchu.wantchu_server2.owner.dto.OwnerRegisterRequestDto;
import com.wantchu.wantchu_server2.vo.FavoriteVo;
import com.wantchu.wantchu_server2.vo.OwnerVo;
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

    //ultra스토어 insert해주기
    @PostMapping("/UltraInsert.do")
    public void ultraInsert(@RequestBody @NotNull UltraInsertDto request, @NotNull HttpServletResponse response) {
        org.json.simple.JSONObject jsonObject = manageService.insertUltra(request);
        WriteToServer.send(response, jsonObject);
    }

    //ultra 삭제하기
    @GetMapping("UltraDelete.do")
    public void ultraDelete(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response) {
        int store_id = Integer.parseInt(request.getParameter("store_id"));
        org.json.simple.JSONObject jsonObject = manageService.ultraDelete(store_id);
        WriteToServer.send(response, jsonObject);
    }

    //ultra스토어 리스트 가져오기
    @GetMapping("/UltraPrint.do")
    public void ultraPrint(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response) {
        org.json.simple.JSONObject jsonObject = manageService.ultraPrint();
        WriteToServer.send(response, jsonObject);
    }

    //new스토어 insert해주기
    @PostMapping("/NewStoreInsert.do")
    public void newStoreInsert(@RequestBody @NotNull NewStoreInsertDto request, @NotNull HttpServletResponse response) {
        org.json.simple.JSONObject jsonObject = manageService.insertNewStore(request);
        WriteToServer.send(response, jsonObject);
    }

    //ultra 삭제하기
    @GetMapping("NewStoreDelete.do")
    public void newStoreDelete(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response) {
        int store_id = Integer.parseInt(request.getParameter("store_id"));
        org.json.simple.JSONObject jsonObject = manageService.newStoreDelete(store_id);
        WriteToServer.send(response, jsonObject);
    }

    //ultra스토어 리스트 가져오기
    @GetMapping("/NewStorePrint.do")
    public void newStorePrint(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response) {
        org.json.simple.JSONObject jsonObject = manageService.newStorePrint();
        WriteToServer.send(response, jsonObject);
    }

    //Alert insert해주기
    @PostMapping("/AlertInsert.do")
    public void alertInsert(@RequestBody @NotNull AlertInsertDto request, @NotNull HttpServletResponse response) {
        org.json.simple.JSONObject jsonObject = manageService.insertAlert(request);
        WriteToServer.send(response, jsonObject);
    }

    //alert 삭제하기
    @GetMapping("/AlertDelete.do")
    public void alertDelete(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response) {
        int alert_id = Integer.parseInt(request.getParameter("alert_id"));
        org.json.simple.JSONObject jsonObject = manageService.alertDelete(alert_id);
        WriteToServer.send(response, jsonObject);
    }
//
    //alert 리스트 가져오기
    @GetMapping("/AlertPrint.do")
    public void alertPrint(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response) {
        org.json.simple.JSONObject jsonObject = manageService.alertPrint();
        WriteToServer.send(response, jsonObject);
    }
    @PostMapping("/FxOwnerRegister.do")
    public void FxOwnerRegister(@RequestBody @NotNull FxOwnerRegisterRequestDto requestDto, @org.jetbrains.annotations.NotNull HttpServletResponse response) {
        org.json.simple.JSONObject jsonObject = manageService.register(requestDto);
        WriteToServer.send(response, jsonObject);
    }
    //store 추가
    @PostMapping("/StoreInsert.do")
    public void storeInsert(@RequestBody @NotNull StoreInsertDto request, @NotNull HttpServletResponse response) {
        org.json.simple.JSONObject jsonObject = manageService.storeInsert(request);
        WriteToServer.send(response, jsonObject);
    }

    //store 삭제
    @GetMapping("/StoreDelete.do")
    public void storeDelete(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response) {
        int store_id = Integer.parseInt(request.getParameter("store_id"));
        org.json.simple.JSONObject jsonObject = manageService.storeDelete(store_id);
        WriteToServer.send(response, jsonObject);
    }

    //store_name을 통해 관련 store 리스트 출력 ***********************
    @GetMapping("/StorePrintByStoreName.do")
    public void storePrint(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response) {
        String store_name = request.getParameter("store_name");
        org.json.simple.JSONObject jsonObject = manageService.storePrintByStoreName(store_name);
        WriteToServer.send(response, jsonObject);
    }

    //category 추가
    @PostMapping("/CategoryInsert.do")
    public void categoryInsert(@RequestBody @NotNull CategoryInsertDto request, @NotNull HttpServletResponse response) {
        org.json.simple.JSONObject jsonObject = manageService.categoryInsert(request);
        WriteToServer.send(response, jsonObject);
    }

    //해당 가게의 category 삭제
    @GetMapping("/CategoryDelete.do")
    public void categoryDelete(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response) {
        int category_id = Integer.parseInt(request.getParameter("category_id"));
        org.json.simple.JSONObject jsonObject = manageService.categoryDelete(category_id);
        WriteToServer.send(response, jsonObject);
    }

    //store_id를 통해서 해당 가게의 모든 category정보 출력 ********************
    @GetMapping("/CategoryPrintByStoreId.do")
    public void menuPrintByStoreId(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response) {
        int store_id = Integer.parseInt(request.getParameter("store_id"));
        org.json.simple.JSONObject jsonObject = manageService.categoryPrintByStoreId(store_id);
        WriteToServer.send(response, jsonObject);
    }

    //menu 추가
    @PostMapping("/MenuInsert.do")
    public void menuInsert(@RequestBody @NotNull MenuInsertDto request, @NotNull HttpServletResponse response) {
        org.json.simple.JSONObject jsonObject = manageService.menuInsert(request);
        WriteToServer.send(response, jsonObject);
    }

    //해당 가게의 해당 category의 해당 menu 삭제
    @GetMapping("/MenuDelete.do")
    public void menuDelete(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response) {
        int menu_id = Integer.parseInt(request.getParameter("menu_id"));
        org.json.simple.JSONObject jsonObject = manageService.menuDelete(menu_id);
        WriteToServer.send(response, jsonObject);
    }

    //category id를 통해서 모든 menu 리스트 출력 **************************
    @GetMapping("/MenuPrintByCategoryId.do")
    public void menuPrintByCategory(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response) {
        int category_id = Integer.parseInt(request.getParameter("category_id"));
        org.json.simple.JSONObject jsonObject = manageService.menuPrintByCategoryId(category_id);
        WriteToServer.send(response, jsonObject);
    }

    //이벤트 추가
    @PostMapping("/EventInsert.do")
    public void eventInsert(@RequestBody @NotNull EventInsertDto request, @NotNull HttpServletResponse response) {
        org.json.simple.JSONObject jsonObject = manageService.eventInsert(request);
        WriteToServer.send(response, jsonObject);
    }

    //이번트 삭제
    @GetMapping("/EventDelete.do")
    public void eventDelete(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response) {
        int event_id = Integer.parseInt(request.getParameter("event_id"));
        org.json.simple.JSONObject jsonObject = manageService.eventDelete(event_id);
        WriteToServer.send(response, jsonObject);
    }

    //이벤트 리스트 출력
    @GetMapping("/EventPrint.do")
    public void eventPrint(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response) {
        org.json.simple.JSONObject jsonObject = manageService.eventPrint();
        WriteToServer.send(response, jsonObject);
    }

    //쿠폰 추가
    @PostMapping("/CouponInsert.do")
    public void couponInsert(@RequestBody @NotNull CouponInsertDto request, @NotNull HttpServletResponse response) {
        org.json.simple.JSONObject jsonObject = manageService.couponInsert(request);
        WriteToServer.send(response, jsonObject);
    }

    //쿠폰 제거 - history 쪽은 어떻게 처리할지 생각하기
    @GetMapping("/CouponDelete.do")
    public void couponDelete(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response) {
        int coupon_id = Integer.parseInt(request.getParameter("coupon_id"));
        org.json.simple.JSONObject jsonObject = manageService.couponDelete(coupon_id);
        WriteToServer.send(response, jsonObject);
    }

    //쿠폰 리스트 출력
    @GetMapping("/CouponPrint.do")
    public void couponPrint(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response) {
        org.json.simple.JSONObject jsonObject = manageService.couponPrint();
        WriteToServer.send(response, jsonObject);
    }
    //휴대폰으로 점주아이디 찾기
    @GetMapping("/FindOwnerByPhone.do")
    public void findOwnerByPhone(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response) {
        String ownerPhone = request.getParameter("owner_phone");
        org.json.simple.JSONObject jsonObject = manageService.findOwnerByPhone(ownerPhone);
        WriteToServer.send(response, jsonObject);
    }

    //엑스트라 추가
    @PostMapping("/ExtraInsert.do")
    public void extraInsert(@RequestBody @NotNull ExtraInsertDto request, @NotNull HttpServletResponse response) {
        org.json.simple.JSONObject jsonObject = manageService.extraInsert(request);
        WriteToServer.send(response, jsonObject);
    }
    //엑스트라 제거
    @GetMapping("/ExtraDelete.do")
    public void extraDelete(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response) {
        int extra_id = Integer.parseInt(request.getParameter("extra_id"));
        org.json.simple.JSONObject jsonObject = manageService.extraDelete(extra_id);
        WriteToServer.send(response, jsonObject);
    }
    //엑스트라 리스트 출력
    @GetMapping("/ExtraPrint.do")
    public void extraPrint(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response) {
        int store_id = Integer.parseInt(request.getParameter("store_id"));
        org.json.simple.JSONObject jsonObject = manageService.extraPrint(store_id);
        WriteToServer.send(response, jsonObject);
    }

    //엑스트라 & 메뉴 매칭 추가
    @PostMapping("/InsertExtraByMenu.do")
    public void insertExtraByMenu(@RequestBody @NotNull ExtraByMenuInsertDto request, @NotNull HttpServletResponse response) {
        org.json.simple.JSONObject jsonObject = manageService.insertExtraByMenu(request);
        WriteToServer.send(response, jsonObject);
    }
    //엑스트라 & 메뉴 매칭 삭제
    @GetMapping("/DeleteExtraByMenu.do")
    public void deleteExtraByMenu(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        org.json.simple.JSONObject jsonObject = manageService.deleteExtraByMenu(id);
        WriteToServer.send(response, jsonObject);
    }
    //엑스트라 & 메뉴 리스트 출력
    @GetMapping("/ExtraByMenuPrint.do")
    public void extraByMenuPrint(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response) {
        int menu_id = Integer.parseInt(request.getParameter("menu_id"));
        org.json.simple.JSONObject jsonObject = manageService.extraByMenuPrint(menu_id);
        WriteToServer.send(response, jsonObject);
    }

    //해당 번호의 주문 정보 가져오기
    @GetMapping("/FindOrderListByPhoneForManage.do")
    public void findOrderListByPhoneForManage(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response) {
        String phone = request.getParameter("phone");
        org.json.simple.JSONObject jsonObject = manageService.findOrderListByPhoneForManage(phone);
        WriteToServer.send(response, jsonObject);
    }
    @PostMapping("/SendMarketing.do")
    public void printMarketingInfo(@RequestBody @NotNull MarketingDto request, @NotNull HttpServletResponse response) {
        org.json.simple.JSONObject jsonObject = manageService.printMarketingInfo(request);
        WriteToServer.send(response, jsonObject);
    }
    //달별 정산 정보 가져오기
    @GetMapping("/PayBackMoney.do")
    public void PayBackMoney(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response) {
        String date = request.getParameter("date");
        org.json.simple.JSONObject jsonObject = manageService.payBackMoney(date);
        WriteToServer.send(response, jsonObject);
    }
    @GetMapping("/PictureTest.do")
    public void PictureTest(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response) {
        org.json.simple.JSONObject jsonObject = manageService.pictureTest();
        WriteToServer.send(response, jsonObject);
    }
//    @PostMapping("/PictureTest.do")
//    public void PictureTest(@RequestBody @NotNull MarketingDto request, @NotNull HttpServletResponse response) {
//        org.json.simple.JSONObject jsonObject = manageService.pictureTest(request);
//        WriteToServer.send(response, jsonObject);
//    }
}
