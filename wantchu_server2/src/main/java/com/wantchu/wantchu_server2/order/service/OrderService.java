package com.wantchu.wantchu_server2.order.service;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.wantchu.wantchu_server2.business.DateConverter;
import com.wantchu.wantchu_server2.business.ObjectMaker;
import com.wantchu.wantchu_server2.coupon.exception.CouponHistoryNotFoundException;
import com.wantchu.wantchu_server2.dao.OrderDao;
import com.wantchu.wantchu_server2.fcmtest.FcmUtil;
import com.wantchu.wantchu_server2.order.dto.*;
import com.wantchu.wantchu_server2.order.exception.OrderNotFoundByPhoneException;
import com.wantchu.wantchu_server2.order.exception.OrderNotFoundException;
import com.wantchu.wantchu_server2.store.exception.StoreIdNotFoundException;
import com.wantchu.wantchu_server2.vo.*;
import io.netty.util.internal.SuppressJava6Requirement;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.swing.text.StyledEditorKit;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import static jdk.nashorn.internal.objects.Global.print;

@RequiredArgsConstructor
@Component
@Service
public class OrderService {

    private final OrderDao orderDao;

    @SuppressWarnings("unchecked")
    public org.json.simple.JSONObject orderInsert(String orderInsertRequest) {
        JSONObject jsonObject = new JSONObject(orderInsertRequest);
        String phone = jsonObject.getString("phone");
        String receipt_id = jsonObject.getString("receipt_id");
        int store_id = jsonObject.getInt("store_id");
        int total_price = jsonObject.getInt("total_price");
        int discount_price = jsonObject.getInt("discount_price");
        int coupon_id = jsonObject.getInt("coupon_id");
        String requests = jsonObject.getString("requests");
        int discount_rate = jsonObject.getInt("discount_rate");

        if((discount_price != -1) && (coupon_id != -1)) {
            CouponHistoryVo historyVo = new CouponHistoryVo();
            historyVo.setStore_id(store_id);
            historyVo.setDiscount_price(discount_price);
            historyVo.setCoupon_id(coupon_id);
            historyVo.setPhone(phone);
            historyVo.setTotal_price(total_price);
            historyVo.setReceipt_id(receipt_id);
            orderDao.setCouponAsUsed(historyVo);
            orderDao.insertCouponHistory(historyVo);
        }

        JSONArray orders = jsonObject.getJSONArray("orders");
        for(int i = 0; i < orders.length(); i++) {
            JSONObject order = orders.getJSONObject(i);
            OrderVo orderVo = new OrderVo();
            orderVo.setPhone(phone);
            orderVo.setStore_id(store_id);
            orderVo.setMenu_id(order.getInt("menu_id"));
            orderVo.setMenu_name(order.getString("menu_name"));
            orderVo.setMenu_defaultprice(order.getInt("menu_defaultprice"));
            orderVo.setOrder_count(order.getInt("order_count"));
            orderVo.setReceipt_id(receipt_id);
            orderVo.setOrder_state("PREPARING");
            orderVo.setRequests(requests);
            orderVo.setDiscount_rate(discount_rate);
            int order_id = orderDao.orderInsert(orderVo);
            JSONArray extraOrders = order.getJSONArray("extras");
            for(int j = 0; j < extraOrders.length(); j++) {
                JSONObject extraOrder = extraOrders.getJSONObject(j);
                ExtraOrderVo extraOrderVo = new ExtraOrderVo();
                extraOrderVo.setOrder_id(order_id);
                extraOrderVo.setExtra_id(extraOrder.getInt("extra_id"));
                extraOrderVo.setExtra_price(extraOrder.getInt("extra_price"));
                extraOrderVo.setExtra_count(extraOrder.getInt("extra_count"));
                extraOrderVo.setExtra_name(extraOrder.getString("extra_name"));
                orderDao.extraOrderInsert(extraOrderVo);
            }
        }

        org.json.simple.JSONObject jsonObjectResponse = ObjectMaker.getSimpleJSONObject();
        jsonObjectResponse.put("result", true);
        jsonObjectResponse.put("message", "주문이 정상적으로 접수되었습니다.");
        return jsonObjectResponse;
    }

    @SuppressWarnings("unchecked")
    public org.json.simple.JSONObject orderListFindByPhone(String phone,int startPoint) {
        org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();
        try {
            List<OrderListVo> list = orderDao.findAbstractOrderInfo(phone,startPoint);
            jsonObject.put("result", true);
            jsonObject.put("message", "전화번호로 주문 정보 가져오기 성공");
            org.json.simple.JSONArray jsonArray = ObjectMaker.getSimpleJSONArray();
            for(OrderListVo info : list) {
                org.json.simple.JSONObject jTemp = ObjectMaker.getSimpleJSONObject();
                jTemp.putAll(info.convertMap());
                int extraSum = orderDao.findExtraOrderTotalPrice(info.getReceipt_id());
                int orderSum = orderDao.findOrderTotalPrice(info.getReceipt_id());
                int discount_rate = orderDao.getDiscount_rate_by_receipt_id(info.getReceipt_id());
                jTemp.put("total_price", extraSum + orderSum);
                jTemp.put("discount_rate", discount_rate);
                jsonArray.add(jTemp);
            }
            jsonObject.put("order", jsonArray);
        } catch(OrderNotFoundByPhoneException e) {
            jsonObject = ObjectMaker.getJSONObjectWithException(e);
        }
        return jsonObject;
    }

    @SuppressWarnings("unchecked")
    public org.json.simple.JSONObject orderFindByReceiptId(String receipt_id) {
        org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();
        String requests = orderDao.getRequests(receipt_id);
        List<Integer> orderIdList = orderDao.findOrderIdsByReceiptId(receipt_id);
        int discount_rate = orderDao.getDiscount_rate_by_receipt_id(receipt_id);
        int coupon_discount = orderDao.getCoupon_Discount_by_receipt_id(receipt_id);
        Iterator<Integer> iterator = orderIdList.iterator();
        jsonObject.put("result", true);
        jsonObject.put("message", "상세 주문 내역 가져오기 성공");
        jsonObject.put("requests", requests);
        jsonObject.put("discount_rate", discount_rate);
        jsonObject.put("coupon_discount", coupon_discount);
        org.json.simple.JSONArray arrayOfOrders = ObjectMaker.getSimpleJSONArray();

        while(iterator.hasNext()) {
            int order_id = iterator.next();
            org.json.simple.JSONObject objectOfOrder = ObjectMaker.getSimpleJSONObject();
            objectOfOrder.put("order_id", order_id);
            OrderDetailVo detailVo = orderDao.findOrderDetailByOrderId(order_id);
            objectOfOrder.put("menu_name", detailVo.getMenu_name());
            objectOfOrder.put("menu_defaultprice", detailVo.getMenu_defaultprice());
            objectOfOrder.put("order_count", detailVo.getOrder_count());
            objectOfOrder.put("order_state", detailVo.getOrder_state());

            org.json.simple.JSONArray arrayOfExtras = ObjectMaker.getSimpleJSONArray();

            List<ExtraOrderVo> extraOrderList = orderDao.findExtraOrdersByOrderId(order_id);
            Iterator<ExtraOrderVo> extraOrderIterator = extraOrderList.iterator();
            if(extraOrderIterator.hasNext()) {
                while(extraOrderIterator.hasNext()) {
                    ExtraOrderVo extraOrderVo = extraOrderIterator.next();
                    org.json.simple.JSONObject objectOfExtra = ObjectMaker.getSimpleJSONObject();
                    objectOfExtra.put("extra_name", extraOrderVo.getExtra_name());
                    objectOfExtra.put("extra_price", extraOrderVo.getExtra_price());
                    objectOfExtra.put("extra_count", extraOrderVo.getExtra_count());
                    arrayOfExtras.add(objectOfExtra);
                    objectOfOrder.put("extras", arrayOfExtras);
                }
            }
            else {
                objectOfOrder.put("extras", arrayOfExtras);
            }
            arrayOfOrders.add(objectOfOrder);
        }
        jsonObject.put("orders", arrayOfOrders);
        return jsonObject;
    }

    @SuppressWarnings("unchecked")
    public org.json.simple.JSONObject findOrderCountByPhone(String phone) {
        org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();
        int orderCount = orderDao.findOrderCountByPhone(phone);
        jsonObject.put("result", true);
        jsonObject.put("total_orders", orderCount);
        return jsonObject;
    }

    @SuppressWarnings("unchecked")
    public org.json.simple.JSONObject findPreparingOrdersByStoreId(int store_id) {
        org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();
        try {
            List<OrderVo> receiptIdList = orderDao.findReceiptIdsOfPreparingOrders(store_id);
            Iterator<OrderVo> receiptIdIterator = receiptIdList.iterator();
            jsonObject.put("result", true);
            jsonObject.put("message", "처리 해야할 주문 요청 존재");
            org.json.simple.JSONArray arrayOfOrders = ObjectMaker.getSimpleJSONArray();
            while(receiptIdIterator.hasNext()) {
                OrderVo orderVo2 = receiptIdIterator.next();
                String receipt_id = orderVo2.getReceipt_id();
                String orderState = orderVo2.getOrder_state();
                org.json.simple.JSONObject objectOfOrder = ObjectMaker.getSimpleJSONObject();
                objectOfOrder.put("order_state",orderState);
                objectOfOrder.put("receipt_id", receipt_id);
                OrderVo orderVo = orderDao.findPreparingOrderInfoByReceiptId(receipt_id);
                objectOfOrder.put("phone", orderVo.getPhone());
                objectOfOrder.put("order_count", orderVo.getOrder_count());
                objectOfOrder.put("order_date", DateConverter.convertDateWithTime(orderVo.getOrder_date()));
                objectOfOrder.put("discount_rate",orderDao.getDiscount_rate_by_receipt_id(receipt_id));
                int extraOrderSum = orderDao.findExtraOrderTotalPrice(receipt_id);
                int menuDefaultPriceSum = orderDao.findOrderTotalPrice(receipt_id);
                objectOfOrder.put("total_price", extraOrderSum + menuDefaultPriceSum);
                try {
                    CouponHistoryVo historyVo = orderDao.findOrderPriceInfoByReceiptId(receipt_id);
                    objectOfOrder.put("discount_price", historyVo.getDiscount_price());
                } catch(CouponHistoryNotFoundException e){
                    objectOfOrder.put("discount_price", 0);
                }
                arrayOfOrders.add(objectOfOrder);
            }
            jsonObject.put("orders", arrayOfOrders);
        } catch(Exception e){
            jsonObject = ObjectMaker.getJSONObjectWithException(e);
        }
        return jsonObject;
    }

//    @SuppressWarnings("unchecked")
//    public org.json.simple.JSONObject findAllOrdersByStoreId(int store_id, int start, int end) {
//        org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();
//        try {
//            List<String> receiptIdList = orderDao.findReceiptIdsOfAllOrders(store_id, start, end);
//            Iterator<String> receiptIdIterator = receiptIdList.iterator();
//            jsonObject.put("result", true);
//            jsonObject.put("message", "주문 내역 가져오기 성공.");
//            org.json.simple.JSONArray arrayOfOrders = ObjectMaker.getSimpleJSONArray();
//            while(receiptIdIterator.hasNext()) {
//                String receipt_id = receiptIdIterator.next();
//                org.json.simple.JSONObject objectOfOrder = ObjectMaker.getSimpleJSONObject();
//                objectOfOrder.put("receipt_id", receipt_id);
//                OrderVo orderVo = orderDao.findAllOrderInfoByReceiptId(receipt_id);
//                objectOfOrder.put("phone", orderVo.getPhone());
//                objectOfOrder.put("order_count", orderVo.getOrder_count());
//                objectOfOrder.put("order_date", DateConverter.convertDateWithTime(orderVo.getOrder_date()));
//                objectOfOrder.put("order_state", orderVo.getOrder_state());
//                int extraOrderSum = orderDao.findExtraOrderTotalPrice(receipt_id);
//                int menuDefaultPriceSum = orderDao.findOrderTotalPrice(receipt_id);
//                objectOfOrder.put("total_price", extraOrderSum + menuDefaultPriceSum);
//                try {
//                    CouponHistoryVo historyVo = orderDao.findOrderPriceInfoByReceiptId(receipt_id);
//                    objectOfOrder.put("discount_price", historyVo.getDiscount_price());
//                } catch(CouponHistoryNotFoundException exception) {
//                    objectOfOrder.put("discount_price", 0);
//                }
//                arrayOfOrders.add(objectOfOrder);
//            }
//            jsonObject.put("orders", arrayOfOrders);
//        } catch(Exception exception) {
//            jsonObject = ObjectMaker.getJSONObjectWithException(exception);
//        }
//        return jsonObject;
//    }

    public void setOrderStateAsCancel(String receipt_id) {
        orderDao.setOrderStateAsCancel(receipt_id);
    }

//    @SuppressWarnings("unchecked")
//    public org.json.simple.JSONObject setOrderStateAsDone(String receipt_id) {
//        org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();
//        orderDao.setOrderStateAsDone(receipt_id);
//        jsonObject.put("result", true);
//        jsonObject.put("message", "제조 완료 상태로 변경 완료");
//        return jsonObject;
//    }

    public org.json.simple.JSONObject orderFindPrepareOrAcceptByPhone(String phone) {
        org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();
        try {
            List<OrderListVo> list = orderDao.findOrderPrepareOrAcceptByPhone(phone);
            jsonObject.put("result", true);
            jsonObject.put("message", "전화번호로 주문 정보 가져오기 성공");
            org.json.simple.JSONArray jsonArray = ObjectMaker.getSimpleJSONArray();
            for (OrderListVo info : list) {
                org.json.simple.JSONObject jTemp = ObjectMaker.getSimpleJSONObject();
                jTemp.putAll(info.convertMapOnlyHour());
                int extraSum = orderDao.findExtraOrderTotalPrice(info.getReceipt_id());
                int orderSum = orderDao.findOrderTotalPrice(info.getReceipt_id());
                int discount_rate = orderDao.getDiscount_rate_by_receipt_id(info.getReceipt_id());
                int coupon_discount = orderDao.getCoupon_Discount_by_receipt_id(info.getReceipt_id());
                jTemp.put("total_price", extraSum + orderSum);
                jTemp.put("discount_rate", discount_rate);
                jTemp.put("coupon_discount", coupon_discount);
                jsonArray.add(jTemp);
            }
            jsonObject.put("order", jsonArray);
        } catch (OrderNotFoundByPhoneException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
    @SuppressWarnings("unchecked")
    public org.json.simple.JSONObject findDoneOrdersByDate(OrderCompleteBetweenDateReqeustDto reqeustDto){
        org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();
        //Boolean device_result = null;
        try{
            //device_result = orderDao.duplicateToken(reqeustDto.getStore_id(), reqeustDto.getOwner_device_token());
            List<String> receiptIdList = orderDao.findReceiptIdsOfDoneOrders(reqeustDto);
            Iterator<String> receiptIdIterator = receiptIdList.iterator();
            jsonObject.put("result", true);
            //jsonObject.put("device_result", device_result);
            jsonObject.put("message", "완료/취소된 주문 내역 가져오기 성공");
            org.json.simple.JSONArray arrayOfOrders = ObjectMaker.getSimpleJSONArray();
            while(receiptIdIterator.hasNext()){
                String receipt_id = receiptIdIterator.next();
                org.json.simple.JSONObject objectOfOrder = ObjectMaker.getSimpleJSONObject();
                objectOfOrder.put("receipt_id", receipt_id);
                OrderVo orderVo = orderDao.findAllOrderInfoByReceiptId(receipt_id);
                objectOfOrder.put("phone", orderVo.getPhone());
                objectOfOrder.put("order_count", orderVo.getOrder_count());
                objectOfOrder.put("order_date", DateConverter.convertDateWithTime(orderVo.getOrder_date()));
                objectOfOrder.put("order_state", orderVo.getOrder_state());
                int extraOrderSum = orderDao.findExtraOrderTotalPrice(receipt_id);
                int menuDefaultPriceSum = orderDao.findOrderTotalPrice(receipt_id);
                int discount_rate = orderDao.getDiscount_rate_by_receipt_id(receipt_id);
                objectOfOrder.put("discount_rate", discount_rate);
                objectOfOrder.put("total_price", extraOrderSum + menuDefaultPriceSum);
                try {
                    CouponHistoryVo historyVo = orderDao.findOrderPriceInfoByReceiptId(receipt_id);
                    objectOfOrder.put("discount_price", historyVo.getDiscount_price());
                } catch(CouponHistoryNotFoundException exception) {
                    objectOfOrder.put("discount_price", 0);
                }
                arrayOfOrders.add(objectOfOrder);
            }
            jsonObject.put("orders", arrayOfOrders);
        }
        catch(Exception e){
            jsonObject = ObjectMaker.getJSONObjectWithException(e);
            //jsonObject.put("device_result", device_result);
        }
        return jsonObject;
    }
    public org.json.simple.JSONObject findDoneOrdersByDateAndPhone(OrderCompleteBetweenDateAndPhoneDto reqeustDto){
        org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();
        //Boolean device_result = null;
        try{
            //device_result = orderDao.duplicateToken(reqeustDto.getStore_id(), reqeustDto.getOwner_device_token());
            List<String> receiptIdList = orderDao.findReceiptIdsOfDoneOrdersByDateAndPhone(reqeustDto);
            Iterator<String> receiptIdIterator = receiptIdList.iterator();
            jsonObject.put("result", true);
            //jsonObject.put("device_result", device_result);
            jsonObject.put("message", "완료/취소된 주문 내역 가져오기 성공");
            org.json.simple.JSONArray arrayOfOrders = ObjectMaker.getSimpleJSONArray();
            while(receiptIdIterator.hasNext()){
                String receipt_id = receiptIdIterator.next();
                org.json.simple.JSONObject objectOfOrder = ObjectMaker.getSimpleJSONObject();
                objectOfOrder.put("receipt_id", receipt_id);
                OrderVo orderVo = orderDao.findAllOrderInfoByReceiptId(receipt_id);
                objectOfOrder.put("phone", orderVo.getPhone());
                objectOfOrder.put("order_count", orderVo.getOrder_count());
                objectOfOrder.put("order_date", DateConverter.convertDateWithTime(orderVo.getOrder_date()));
                objectOfOrder.put("order_state", orderVo.getOrder_state());
                int extraOrderSum = orderDao.findExtraOrderTotalPrice(receipt_id);
                int menuDefaultPriceSum = orderDao.findOrderTotalPrice(receipt_id);
                int discount_rate = orderDao.getDiscount_rate_by_receipt_id(receipt_id);
                objectOfOrder.put("discount_rate", discount_rate);
                objectOfOrder.put("total_price", extraOrderSum + menuDefaultPriceSum);
                try {
                    CouponHistoryVo historyVo = orderDao.findOrderPriceInfoByReceiptId(receipt_id);
                    objectOfOrder.put("discount_price", historyVo.getDiscount_price());
                } catch(CouponHistoryNotFoundException exception) {
                    objectOfOrder.put("discount_price", 0);
                }
                arrayOfOrders.add(objectOfOrder);
            }
            jsonObject.put("orders", arrayOfOrders);
        }
        catch(Exception e){
            jsonObject = ObjectMaker.getJSONObjectWithException(e);
            //jsonObject.put("device_result", device_result);
        }
        return jsonObject;
    }
    @SuppressWarnings("unchecked")
    public org.json.simple.JSONObject findDoneOrCancelByPhone(OrderCompletePhoneDto requestDto){
        org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();
        Boolean device_result = null;
        try{
            device_result = orderDao.duplicateToken(requestDto.getStore_id(), requestDto.getOwner_device_token());
            List<String> receiptIdList = orderDao.findReceiptIdsOfDoneOrCancelByPhone(requestDto);
            Iterator<String> receiptIdIterator = receiptIdList.iterator();
            jsonObject.put("result", true);
            jsonObject.put("device_result", device_result);
            jsonObject.put("message", "완료/취소된 주문 내역 가져오기 성공");
            org.json.simple.JSONArray arrayOfOrders = ObjectMaker.getSimpleJSONArray();
            while(receiptIdIterator.hasNext()){
                String receipt_id = receiptIdIterator.next();
                org.json.simple.JSONObject objectOfOrder = ObjectMaker.getSimpleJSONObject();
                objectOfOrder.put("receipt_id", receipt_id);
                OrderVo orderVo = orderDao.findAllOrderInfoByReceiptId(receipt_id);
                objectOfOrder.put("phone", orderVo.getPhone());
                objectOfOrder.put("order_count", orderVo.getOrder_count());
                objectOfOrder.put("order_date", DateConverter.convertDateWithTime(orderVo.getOrder_date()));
                objectOfOrder.put("order_state", orderVo.getOrder_state());
                int extraOrderSum = orderDao.findExtraOrderTotalPrice(receipt_id);
                int menuDefaultPriceSum = orderDao.findOrderTotalPrice(receipt_id);
                objectOfOrder.put("total_price", extraOrderSum + menuDefaultPriceSum);
                try {
                    CouponHistoryVo historyVo = orderDao.findOrderPriceInfoByReceiptId(receipt_id);
                    objectOfOrder.put("discount_price", historyVo.getDiscount_price());
                } catch(CouponHistoryNotFoundException exception) {
                    objectOfOrder.put("discount_price", 0);
                }
                arrayOfOrders.add(objectOfOrder);
            }
            jsonObject.put("orders", arrayOfOrders);
        }
        catch(Exception e){
            jsonObject = ObjectMaker.getJSONObjectWithException(e);
            jsonObject.put("device_result", device_result);
        }
        return jsonObject;
    }

    @SuppressWarnings("unchecked")
    public org.json.simple.JSONObject sendMessageToOwner(OrderMessageRequestDto requestDto){
        org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();
        try{
            String owner_device_token = orderDao.getDeviceTokenByStoreId(requestDto.getStore_id());
            System.out.println(owner_device_token);
            FcmUtil fcmUtil = new FcmUtil();
            fcmUtil.send_owner_FCM(owner_device_token, requestDto.getReceipt_id(), jsonObject);
            jsonObject.put("result", true);
            jsonObject.put("message", "메시지 전송 성공");
        }
        catch(StoreIdNotFoundException exception){
            jsonObject = ObjectMaker.getJSONObjectWithException(exception);
        } catch(FirebaseMessagingException exception){
            jsonObject = ObjectMaker.getJSONObjectWithException(exception);
        } catch(IOException exception){
            jsonObject = ObjectMaker.getJSONObjectWithException(exception);
        }
        return jsonObject;
    }

    @SuppressWarnings("unchecked")
    public org.json.simple.JSONObject ordercheck(OrderCheckDto requestDto){
        org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();
        try{
            String order_state = orderDao.orderStateCheck(requestDto.getReceipt_id());
            jsonObject.put("order_state", order_state);
            jsonObject.put("result", true);
            jsonObject.put("message", "주문상태전송 성공");
        }
        catch(OrderNotFoundException e){
            jsonObject = ObjectMaker.getJSONObjectWithException(e);
        }
        catch(Exception e){
            jsonObject = ObjectMaker.getJSONObjectWithException(e);
        }
        return jsonObject;
    }
}
