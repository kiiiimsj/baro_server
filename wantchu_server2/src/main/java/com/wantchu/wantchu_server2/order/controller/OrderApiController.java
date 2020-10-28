package com.wantchu.wantchu_server2.order.controller;

import com.google.firebase.auth.internal.HttpErrorResponse;
import com.wantchu.wantchu_server2.business.WriteToServer;
import com.wantchu.wantchu_server2.order.dto.OrderCompleteBetweenDateReqeustDto;
import com.wantchu.wantchu_server2.order.dto.OrderCompletePhoneDto;
import com.wantchu.wantchu_server2.order.dto.OrderMessageRequestDto;
import com.wantchu.wantchu_server2.order.dto.OrderStateUpdateRequestDto;
import com.wantchu.wantchu_server2.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static jdk.nashorn.internal.objects.Global.print;

@RequiredArgsConstructor
@Component
@RestController
public class OrderApiController {

    private final OrderService orderService;

    @PostMapping("/OrderInsert.do")
    @Transactional
    public void insertOrder(@RequestBody @NotNull String orderInsertRequest, @NotNull HttpServletResponse response) {
        org.json.simple.JSONObject jsonObjectForResponse = orderService.orderInsert(orderInsertRequest);
        WriteToServer.send(response, jsonObjectForResponse);
    }
    @GetMapping("/OrderListFindByPhone.do")
    public void orderFindByPhone(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response) {
        String phone = request.getParameter("phone");
        int startPoint = Integer.valueOf(request.getParameter("startPoint"));
        org.json.simple.JSONObject jsonObject = orderService.orderListFindByPhone(phone,startPoint);
        WriteToServer.send(response, jsonObject);
    }

    @GetMapping("/OrderFindByReceiptId.do")
    public void orderFindByReceiptId(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response) {
        String receipt_id = request.getParameter("receipt_id");
        org.json.simple.JSONObject jsonObject = orderService.orderFindByReceiptId(receipt_id);
        WriteToServer.send(response, jsonObject);
    }


    @GetMapping("/OrderFindByReceiptIdDefault.do")
    public void orderFindByReceiptIdDefault(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response) {
        String receipt_id = request.getParameter("receipt_id");
        org.json.simple.JSONObject jsonObject = orderService.orderFindByReceiptIdDefault(receipt_id);
        WriteToServer.send(response, jsonObject);
    }

    @GetMapping("/OrderFindByOrderIdExtra.do")
    public void orderFindByOrderIdExtra(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response) {
        int order_id = Integer.parseInt(request.getParameter("order_id"));
        org.json.simple.JSONObject jsonObject = orderService.orderFindByOrderIdExtra(order_id);
        WriteToServer.send(response, jsonObject);
    }

    @GetMapping("/OrderTotalCountByPhone.do")
    public void findOrderCountByPhone(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response) {
        String phone = request.getParameter("phone");
        org.json.simple.JSONObject jsonObject = orderService.findOrderCountByPhone(phone);
        WriteToServer.send(response, jsonObject);
    }

    @GetMapping("/OrderFindByStoreId.do")
    public void findPreparingOrdersByStoreId(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response) {
        int store_id = Integer.parseInt(request.getParameter("store_id"));
        org.json.simple.JSONObject jsonObject = orderService.findPreparingOrdersByStoreId(store_id);
        WriteToServer.send(response, jsonObject);
    }

//    @PutMapping("/OrderStatusDone.do")
//    public void setOrderStatusAsDone(@RequestBody OrderStateUpdateRequestDto requestDto, HttpServletResponse response) {
//        String receipt_id = requestDto.getReceipt_id();
//        org.json.simple.JSONObject jsonObject = orderService.setOrderStateAsDone(receipt_id);
//        WriteToServer.send(response, jsonObject);
//    }

//    @GetMapping("/OrderFindAllByStoreId.do")
//    public void findAllOrdersByStoreId(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response) {
//        int store_id = Integer.parseInt(request.getParameter("store_id"));
//        int start = Integer.parseInt(request.getParameter("start"));
//        int end = Integer.parseInt(request.getParameter("end"));
//        org.json.simple.JSONObject jsonObject = orderService.findAllOrdersByStoreId(store_id, start, end);
//        WriteToServer.send(response, jsonObject);
//    }
    @GetMapping("/OrderListDoneOrCancelForOwner.do")
    public void findOrderListDoneOrCancel(@NotNull HttpServletRequest request,@NotNull HttpServletResponse response){
        String receipt_id = request.getParameter("receipt_id");
        org.json.simple.JSONObject jsonObject = orderService.orderFindByReceiptId(receipt_id);
        WriteToServer.send(response,jsonObject);
    }
    @GetMapping("/OrderProgressing.do")
    public void showOrderProgrogressing(@NotNull HttpServletRequest request,@NotNull HttpServletResponse response){
        String _phone = request.getParameter("phone");
        org.json.simple.JSONObject jsonObject = orderService.orderFindPrepareOrAcceptByPhone(_phone);
        WriteToServer.send(response,jsonObject);
    }

    @PostMapping("/OrderCompleteListByDate.do")
    public void findDoneOrdersByDate(@RequestBody OrderCompleteBetweenDateReqeustDto reqeustDto, HttpServletResponse response){
        org.json.simple.JSONObject jsonObject = orderService.findDoneOrdersByDate(reqeustDto);
        WriteToServer.send(response, jsonObject);
    }

    @GetMapping("/OrderProgressingDetail.do")
    public void showOrderProgressingDetails(@NotNull HttpServletRequest request,@NotNull HttpServletResponse response){
        String receipt_id = request.getParameter("receipt_id");
        org.json.simple.JSONObject jsonObject = orderService.orderFindByReceiptId(receipt_id);
        WriteToServer.send(response,jsonObject);
    }
    @PostMapping("/OrderCompleteListByPhone.do")
    public void findDoneOrCancelOrdersByPhone(@RequestBody OrderCompletePhoneDto requestDto, HttpServletResponse response){
        org.json.simple.JSONObject jsonObject = orderService.findDoneOrCancelByPhone(requestDto);
        WriteToServer.send(response, jsonObject);
    }
    @PostMapping("/OrderSendMessage.do")
    public void sendMessageToOwner(@RequestBody OrderMessageRequestDto requestDto, HttpServletResponse response){
        org.json.simple.JSONObject jsonObject = orderService.sendMessageToOwner(requestDto);
        WriteToServer.send(response, jsonObject);
    }
}
