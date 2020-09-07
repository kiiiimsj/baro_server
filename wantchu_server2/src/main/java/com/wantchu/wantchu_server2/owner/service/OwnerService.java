package com.wantchu.wantchu_server2.owner.service;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.wantchu.wantchu_server2.business.ObjectMaker;
import com.wantchu.wantchu_server2.business.UpdateType;
import com.wantchu.wantchu_server2.dao.OwnerDao;
import com.wantchu.wantchu_server2.fcmtest.FcmUtil;
import com.wantchu.wantchu_server2.member.exception.MemberPhoneNotFoundException;
import com.wantchu.wantchu_server2.owner.dto.*;
import com.wantchu.wantchu_server2.owner.exception.*;
import com.wantchu.wantchu_server2.store.exception.StoreIdNotFoundException;
import com.wantchu.wantchu_server2.vo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;

@RequiredArgsConstructor
@Component
@Service
public class OwnerService {

    private final OwnerDao ownerDao;

    @SuppressWarnings("unchecked")
    public org.json.simple.JSONObject login(String phone, String pass) {
        org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();
        try {
            OwnerVo ownerVo = ownerDao.isValidAccount(phone, pass);
            jsonObject.put("result", true);
            jsonObject.put("message", "정상적으로 점주 로그인이 되었습니다.");
            jsonObject.put("store_id", ownerVo.getStore_id());
            jsonObject.put("nick", ownerVo.getNick());
            jsonObject.put("store_name", ownerVo.getStore_name());
            jsonObject.put("email", ownerVo.getEmail());
        } catch(OwnerLoginException exception) {
            jsonObject = ObjectMaker.getJSONObjectWithException(exception);
        }
        return jsonObject;
    }

    @SuppressWarnings("unchecked")
    public org.json.simple.JSONObject updatePassword(OwnerPassUpdateRequestDto requestDto) {
        org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();
        try {
            ownerDao.updatePassword(requestDto);
            jsonObject.put("result", true);
            jsonObject.put("message", "비밀번호가 정상적으로 변경되었습니다.");
        } catch(OwnerPassUpdateException exception) {
            jsonObject = ObjectMaker.getJSONObjectWithException(exception);
        }
        return jsonObject;
    }

    @SuppressWarnings("unchecked")
    public org.json.simple.JSONObject updateEmail(OwnerEmailUpdateRequestDto requestDto) {
        org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();
        try {
            if(!ownerDao.isEmailInUse(requestDto.getEmail())) {
                ownerDao.updateEmail(requestDto);
                jsonObject.put("result", true);
                jsonObject.put("message", "이메일이 정상적으로 변경되었습니다.");
            }
            else {
                throw new OwnerEmailInUseException();
            }
        } catch(OwnerEmailInUseException e) {
            jsonObject = ObjectMaker.getJSONObjectWithException(e);
        }
        return jsonObject;
    }

    @SuppressWarnings("unchecked")
    public org.json.simple.JSONObject register(OwnerRegisterRequestDto requestDto) {
        org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();
        try {
            if(!ownerDao.isEmailInUse(requestDto.getEmail())) {
                if(!ownerDao.isPhoneInUse(requestDto.getPhone())) {
                    ownerDao.register(requestDto);
                    jsonObject.put("result", true);
                    jsonObject.put("message", "점주 가입에 성공했습니다. 가게를 등록해주세요.");
                }
                else {
                    throw new OwnerPhoneInUseException();
                }
            }
            else {
                throw new OwnerEmailInUseException();
            }
        } catch(OwnerEmailInUseException e) {
            jsonObject = ObjectMaker.getJSONObjectWithException(e);
            jsonObject.put("errno", e.getErrno());
        } catch(OwnerPhoneInUseException e) {
            jsonObject = ObjectMaker.getJSONObjectWithException(e);
            jsonObject.put("errno", e.getErrno());
        }
        return jsonObject;
    }

    @SuppressWarnings("unchecked")
    public org.json.simple.JSONObject setOwnerStore(OwnerStoreSetRequestDto requestDto) {
        org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();
        try {
            if(ownerDao.isValidStoreId(requestDto.getStore_id())) {
                ownerDao.setOwnerStore(requestDto);
                jsonObject.put("result", true);
                jsonObject.put("message", "정상적으로 가게 등록이 완료되었습니다.");
            }
            else {
                throw new StoreIdNotFoundException();
            }
        } catch(StoreIdNotFoundException e) {
            jsonObject = ObjectMaker.getJSONObjectWithException(e);
        }
        return jsonObject;
    }

    @SuppressWarnings("unchecked")
    public org.json.simple.JSONObject ownerUpdates(String updateRequest) {
        org.json.JSONObject jsonObject = new org.json.JSONObject(updateRequest);
        org.json.simple.JSONObject jsonObjectForResponse = ObjectMaker.getSimpleJSONObject();
        int store_id = jsonObject.getInt("store_id");
        org.json.JSONArray updates = jsonObject.getJSONArray("updates");
        for(int i = 0; i < updates.length(); i++) {
            org.json.JSONObject update = updates.getJSONObject(i);
            String update_type = update.getString("update_type");
            try {
                switch (update_type.toUpperCase()) {
                    case UpdateType.UPDATE_MENU_PRICE: {
                        MenuVo menuVo = new MenuVo();
                        menuVo.setStore_id(store_id);
                        int menu_id = update.getInt("menu_id");
                        menuVo.setMenu_id(menu_id);
                        int menu_defaultprice = update.getInt("menu_defaultprice");
                        menuVo.setMenu_defaultprice(menu_defaultprice);
                        ownerDao.updateMenuPrice(menuVo);
                        break;
                    }
                    case UpdateType.UPDATE_MENU_NAME: {
                        MenuVo menuVo = new MenuVo();
                        menuVo.setStore_id(store_id);
                        int menu_id = update.getInt("menu_id");
                        menuVo.setMenu_id(menu_id);
                        String menu_name = update.getString("menu_name");
                        menuVo.setMenu_name(menu_name);
                        ownerDao.updateMenuName(menuVo);
                        break;
                    }
                    case UpdateType.UPDATE_MENU_INFO:{
                        MenuVo menuVo = new MenuVo();
                        menuVo.setStore_id(store_id);
                        int menu_id = update.getInt("menu_id");
                        String menu_info = update.getString("menu_info");
                        menuVo.setMenu_id(menu_id);
                        menuVo.setMenu_info(menu_info);
                        ownerDao.updateMenuInfo(menuVo);
                        break;
                    }
                    case UpdateType.NEW_MENU: {
                        MenuVo menuVo = new MenuVo();
                        menuVo.setStore_id(store_id);
                        String menu_name = update.getString("menu_name");
                        menuVo.setMenu_name(menu_name);
                        int menu_defaultprice = update.getInt("menu_defaultprice");
                        menuVo.setMenu_defaultprice(menu_defaultprice);
                        int category_id = update.getInt("category_id");
                        menuVo.setCategory_id(category_id);
                        String menu_info = update.getString("menu_info");
                        menuVo.setMenu_info(menu_info);
                        ownerDao.insertMenu(menuVo);
                        break;
                    }
                    case UpdateType.UPDATE_EXTRA_PRICE: {
                        ExtraVo extraVo = new ExtraVo();
                        extraVo.setStore_id(store_id);
                        int extra_id = update.getInt("extra_id");
                        extraVo.setExtra_id(extra_id);
                        int extra_price = update.getInt("extra_price");
                        extraVo.setExtra_price(extra_price);
                        ownerDao.updateExtraPrice(extraVo);
                        break;
                    }
                    case UpdateType.UPDATE_EXTRA_NAME: {
                        ExtraVo extraVo = new ExtraVo();
                        extraVo.setStore_id(store_id);
                        int extra_id = update.getInt("extra_id");
                        String extra_name = update.getString("extra_name");
                        extraVo.setExtra_id(extra_id);
                        extraVo.setExtra_name(extra_name);
                        ownerDao.updateExtraName(extraVo);
                        break;
                    }
                    case UpdateType.NEW_EXTRA: {
                        ExtraVo extraVo = new ExtraVo();
                        extraVo.setStore_id(store_id);
                        String extra_name = update.getString("extra_name");
                        int extra_price = update.getInt("extra_price");
                        int menu_id = update.getInt("menu_id");
                        String is_required = update.getString("is_required");
                        String extra_group = update.getString("extra_group");
                        extraVo.setExtra_name(extra_name);
                        extraVo.setExtra_price(extra_price);
                        extraVo.setMenu_id(menu_id);
                        extraVo.setIs_required(is_required);
                        extraVo.setExtra_group(extra_group);
                        ownerDao.insertExtra(extraVo);
                        break;
                    }
                    case UpdateType.UPDATE_STORE_NAME: {
                        StoreVo storeVo = new StoreVo();
                        storeVo.setStore_id(store_id);
                        String store_name = update.getString("store_name");
                        storeVo.setStore_name(store_name);
                        ownerDao.updateStoreName(storeVo);
                        break;
                    }
                    case UpdateType.NEW_CATEGORY: {
                        CategoryVo categoryVo = new CategoryVo();
                        categoryVo.setStore_id(store_id);
                        String category_name = update.getString("category_name");
                        categoryVo.setCategory_name(category_name);
                        ownerDao.insertCategory(categoryVo);
                        break;
                    }
                    case UpdateType.UPDATE_STORE_LATLNG: {
                        StoreVo storeVo = new StoreVo();
                        storeVo.setStore_id(store_id);
                        double store_latitude = update.getDouble("store_latitude");
                        double store_longitude = update.getDouble("store_longitude");
                        storeVo.setStore_latitude(store_latitude);
                        storeVo.setStore_longitude(store_longitude);
                        ownerDao.updateStoreLatLng(storeVo);
                        break;
                    }
                    case UpdateType.UPDATE_STORE_TIME: {
                        StoreVo storeVo = new StoreVo();
                        storeVo.setStore_id(store_id);
                        String store_opentime = update.getString("store_opentime");
                        String store_closetime = update.getString("store_closetime");
                        storeVo.setStore_opentime(store_opentime);
                        storeVo.setStore_closetime(store_closetime);
                        ownerDao.updateStoreTime(storeVo);
                        break;
                    }
                    case UpdateType.UPDATE_STORE_PHONE: {
                        StoreVo storeVo = new StoreVo();
                        storeVo.setStore_id(store_id);
                        String store_phone = update.getString("store_phone");
                        storeVo.setStore_phone(store_phone);
                        ownerDao.updateStorePhone(storeVo);
                        break;
                    }
                    case UpdateType.UPDATE_STORE_DAYSOFF: {
                        StoreVo storeVo = new StoreVo();
                        storeVo.setStore_id(store_id);
                        String store_daysoff = update.getString("store_daysoff");
                        storeVo.setStore_daysoff(store_daysoff);
                        ownerDao.updateStoreDaysOff(storeVo);
                        break;
                    }
                    case UpdateType.UPDATE_STORE_LOCATION: {
                        StoreVo storeVo = new StoreVo();
                        storeVo.setStore_id(store_id);
                        String store_location = update.getString("store_location");
                        storeVo.setStore_location(store_location);
                        ownerDao.updateStoreLocation(storeVo);
                        break;
                    }
                    case UpdateType.UPDATE_STORE_INFO: {
                        StoreVo storeVo = new StoreVo();
                        storeVo.setStore_id(store_id);
                        String store_info = update.getString("store_info");
                        storeVo.setStore_info(store_info);
                        ownerDao.updateStoreInfo(storeVo);
                        break;
                    }
                    case UpdateType.DELETE_EXTRA: {
                        int extra_id = update.getInt("extra_id");
                        ownerDao.deleteExtra(extra_id);
                        break;
                    }
                    case UpdateType.DELETE_MENU: {
                        int menu_id = update.getInt("menu_id");
                        ownerDao.deleteMenu(menu_id);
                    }
                }
                jsonObjectForResponse.put("result", true);
                jsonObjectForResponse.put("message", "수정 처리가 완료되었습니다.");
            } catch(Exception e) {
                e.printStackTrace();
                jsonObjectForResponse.put("result", false);
                jsonObjectForResponse.put("message", "알 수 없는 오류가 발생했습니다.");
            }
        }
        return jsonObjectForResponse;
    }

    @SuppressWarnings("unchecked")
    public org.json.simple.JSONObject findPriceBetweenDate(OwnerPriceBetweenDateRequestDto requestDto) {
        org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();
        int sumOfExtras = ownerDao.findExtraOrderTotalPriceOfStore(requestDto);
        int sumOfMenuDefault = ownerDao.findMenuDefaultTotalPriceOfStore(requestDto);
        int couponTotalPrice = ownerDao.findCouponDiscountPriceOfStore(requestDto);
        jsonObject.put("total_price", sumOfExtras + sumOfMenuDefault);
        jsonObject.put("coupon_total_price", couponTotalPrice);
        return jsonObject;
    }

    @SuppressWarnings("unchecked")
    public org.json.simple.JSONObject setStoreOpenOrClosed(String is_open, int store_id){
        ownerDao.setStoreOpenOrClosed(is_open, store_id);
        org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();
        jsonObject.put("result", true);
        jsonObject.put("message", "정상 처리 되었습니다.");
        return jsonObject;
    }

    @SuppressWarnings("unchecked")
    public org.json.simple.JSONObject sendMessageToCustomer(OwnerMessageRequestDto requestDto) {
        org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();
        try {
            String device_token = ownerDao.getDeviceTokenByPhone(requestDto.getPhone());
            FcmUtil fcmUtil = new FcmUtil();
            fcmUtil.send_FCM(device_token, requestDto.getTitle(), requestDto.getContent(), jsonObject);
            jsonObject.put("result", true);
            jsonObject.put("message", "메시지 전송 성공");
        } catch(MemberPhoneNotFoundException exception) {
            jsonObject = ObjectMaker.getJSONObjectWithException(exception);
        } catch(FirebaseMessagingException exception) {
            jsonObject = ObjectMaker.getJSONObjectWithException(exception);
        } catch(IOException exception) {
            jsonObject = ObjectMaker.getJSONObjectWithException(exception);
        }
        return jsonObject;
    }
    @SuppressWarnings("unchecked")
    public org.json.simple.JSONObject setStatusToCustomer(String receipt_id){
        org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();
        ownerDao.setStatusFirst(receipt_id);
        jsonObject.put("result", true);
        jsonObject.put("message", "정상 처리 되었습니다.");
        return jsonObject;
    }
}