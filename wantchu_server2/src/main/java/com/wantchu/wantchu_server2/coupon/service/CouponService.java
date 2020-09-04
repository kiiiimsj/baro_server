package com.wantchu.wantchu_server2.coupon.service;

import com.wantchu.wantchu_server2.business.ObjectMaker;
import com.wantchu.wantchu_server2.coupon.exception.CouponHistoryNotFoundException;
import com.wantchu.wantchu_server2.coupon.exception.CouponNoUsableFoundException;
import com.wantchu.wantchu_server2.coupon.exception.CouponNotFoundByPhoneException;
import com.wantchu.wantchu_server2.dao.CouponDao;
import com.wantchu.wantchu_server2.vo.CouponHistoryVo;
import com.wantchu.wantchu_server2.vo.CouponVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
@RequiredArgsConstructor
@Service
public class CouponService {

    private final CouponDao couponDao;

    @SuppressWarnings("unchecked")
    public org.json.simple.JSONObject findCouponsByPhone(String phone) {
        org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();
        try {
            List<CouponVo> list = couponDao.findCouponsByPhone(phone);
            jsonObject.put("result", true);
            jsonObject.put("message", "쿠폰 정보를 가져오기 성공");
            org.json.simple.JSONArray jsonArray = ObjectMaker.getSimpleJSONArray();
            for(CouponVo coupon : list) {
                org.json.simple.JSONObject jTemp = ObjectMaker.getSimpleJSONObject();
                jTemp.putAll(coupon.convertMap());
                jsonArray.add(jTemp);
            }
            jsonObject.put("coupon", jsonArray);
        } catch(CouponNotFoundByPhoneException exception) {
            jsonObject = ObjectMaker.getJSONObjectWithException(exception);
        }
        return jsonObject;
    }

    @SuppressWarnings("unchecked")
    public org.json.simple.JSONObject findCouponCountByPhone(String phone) {
        org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();
        int couponCount = couponDao.findCouponCountByPhone(phone);
        jsonObject.put("result", true);
        jsonObject.put("message", "쿠폰 개수 가져오기 성공");
        jsonObject.put("coupon_count", couponCount);
        return jsonObject;
    }

    @SuppressWarnings("unchecked")
    public org.json.simple.JSONObject findCouponHistoryByPhone(String phone) {
        org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();
        try {
            List<CouponHistoryVo> list = couponDao.findCouponHistoryByPhone(phone);
            jsonObject.put("result", true);
            jsonObject.put("message", "쿠폰 사용 내역 가져오기 성공");
            org.json.simple.JSONArray jsonArray = ObjectMaker.getSimpleJSONArray();
            for(CouponHistoryVo historyVo : list) {
                org.json.simple.JSONObject jTemp = ObjectMaker.getSimpleJSONObject();
                jTemp.putAll(historyVo.convertMap());
                jsonArray.add(jTemp);
            }
            jsonObject.put("history", jsonArray);
        } catch(CouponHistoryNotFoundException exception){
            jsonObject = ObjectMaker.getJSONObjectWithException(exception);
        }
        return jsonObject;
    }

    @SuppressWarnings("unchecked")
    public org.json.simple.JSONObject findUsableCouponsByPhoneAndPrice(String phone, int price) {
        org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();
        try {
            List<CouponVo> list = couponDao.findUsableCouponsByPhoneAndPrice(phone, price);
            jsonObject.put("result", true);
            jsonObject.put("message", "적용 가능한 쿠폰 목록 가져오기 성공");

            org.json.simple.JSONArray jsonArray = ObjectMaker.getSimpleJSONArray();
            for(CouponVo couponVo : list) {
                org.json.simple.JSONObject jTemp = ObjectMaker.getSimpleJSONObject();
                jTemp.putAll(couponVo.convertMap());
                jsonArray.add(jTemp);
            }
            jsonObject.put("coupon", jsonArray);
        } catch(CouponNoUsableFoundException exception) {
            jsonObject = ObjectMaker.getJSONObjectWithException(exception);
        }
        return jsonObject;
    }
}
