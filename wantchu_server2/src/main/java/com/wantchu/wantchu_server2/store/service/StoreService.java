package com.wantchu.wantchu_server2.store.service;

import com.wantchu.wantchu_server2.business.ObjectMaker;
import com.wantchu.wantchu_server2.dao.StoreDao;
import com.wantchu.wantchu_server2.store.dto.StoreInfoFindByKeywordDto;
import com.wantchu.wantchu_server2.store.dto.StoreInfoFindByTypeDto;
import com.wantchu.wantchu_server2.store.dto.StoreLocationDto;
import com.wantchu.wantchu_server2.store.exception.*;
import com.wantchu.wantchu_server2.vo.*;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
@RequiredArgsConstructor
@Service
public class StoreService {

    private final StoreDao storeDao;

    @SuppressWarnings("unchecked")
    public org.json.simple.JSONObject findById(int store_id) {
        org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();
        try {
            StoreVo storeVo = storeDao.findByStoreId(store_id);
            jsonObject.put("result", true);
            jsonObject.put("message", "가게 정보 가져오기 성공.");
            jsonObject.put("store_id", storeVo.getStore_id());
            jsonObject.put("type_code", storeVo.getType_code());
            jsonObject.put("store_name", storeVo.getStore_name());
            jsonObject.put("store_latitude", storeVo.getStore_latitude());
            jsonObject.put("store_longitude", storeVo.getStore_longitude());
            jsonObject.put("store_opentime", storeVo.getStore_opentime());
            jsonObject.put("store_closetime", storeVo.getStore_closetime());
            jsonObject.put("store_phone", storeVo.getStore_phone());
            jsonObject.put("store_daysoff", storeVo.getStore_daysoff());
            jsonObject.put("store_location", storeVo.getStore_location());
            jsonObject.put("store_info", storeVo.getStore_info());
            jsonObject.put("store_image", storeVo.getStore_image());
            jsonObject.put("is_open", storeVo.getIs_open());
            jsonObject.put("representative_name", storeVo.getRepresentative_name());
            jsonObject.put("business_number", storeVo.getBusiness_number());
        } catch(StoreIdNotFoundException exception) {
            jsonObject = ObjectMaker.getJSONObjectWithException(exception);
        }
        return jsonObject;
    }

    @SuppressWarnings("unchecked")
    public org.json.simple.JSONObject findByUltra(@NotNull StoreLocationDto requestDto){
        org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();
        try{
            List<StoreInfoUltraNewVo> list = storeDao.storeSearchByUltra(requestDto);
            jsonObject.put("result", true);
            jsonObject.put("message", "울트라가게 출력 성공");
            org.json.simple.JSONArray jsonArray = ObjectMaker.getSimpleJSONArray();
            for(StoreInfoUltraNewVo store: list){
                org.json.simple.JSONObject jTemp = ObjectMaker.getSimpleJSONObject();
                jTemp.putAll(store.convertMap());
                jsonArray.add(jTemp);
            }
            jsonObject.put("store", jsonArray);
        }
        catch(StoreSearchException e){
            jsonObject = ObjectMaker.getJSONObjectWithException(e);
        }
        return jsonObject;
    }

//    @SuppressWarnings("unchecked")
//    public org.json.simple.JSONObject storeSearch(String keyword,int startPoint) {
//        org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();
//        try {
//            List<StoreVo> list = storeDao.storeSearch(keyword,startPoint);
//            jsonObject.put("result", true);
//            jsonObject.put("message", "검색 성공");
//            org.json.simple.JSONArray jsonArray = ObjectMaker.getSimpleJSONArray();
//            for(StoreVo store : list) {
//                org.json.simple.JSONObject jTemp = ObjectMaker.getSimpleJSONObject();
//                jTemp.putAll(store.convertMap());
//                jsonArray.add(jTemp);
//            }
//            jsonObject.put("store", jsonArray);
//        } catch(StoreSearchException e) {
//            jsonObject = ObjectMaker.getJSONObjectWithException(e);
//        }
//        return jsonObject;
//    }

    @SuppressWarnings("unchecked")
    public org.json.simple.JSONObject findInfoByTypeCode(@NotNull StoreInfoFindByTypeDto requestDto) {
        org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();
        try {
            List<StoreInfoFindByTypeVo> list = storeDao.findInfoByTypeCode(requestDto);
            jsonObject.put("result", true);
            jsonObject.put("message", "type_code별로 가게 정보 가져오기 성공");
            org.json.simple.JSONArray jsonArray = ObjectMaker.getSimpleJSONArray();
            for(StoreInfoFindByTypeVo storeInfo : list) {
                org.json.simple.JSONObject jTemp = ObjectMaker.getSimpleJSONObject();
                jTemp.putAll(storeInfo.convertMap());
                jsonArray.add(jTemp);
            }
            jsonObject.put("store", jsonArray);
        } catch(StoreTypeNotFoundException e) {
            jsonObject = ObjectMaker.getJSONObjectWithException(e);
        }
        return jsonObject;
    }

    @SuppressWarnings("unchecked")
    public org.json.simple.JSONObject findInfoByKeyword(@NotNull StoreInfoFindByKeywordDto requestDto) {
        org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();
        try {
            List<StoreInfoFindByTypeVo> list = storeDao.findInfoByKeyword(requestDto);
            jsonObject.put("result", true);
            jsonObject.put("message", "type_code별로 가게 정보 가져오기 성공");
            org.json.simple.JSONArray jsonArray = ObjectMaker.getSimpleJSONArray();
            for(StoreInfoFindByTypeVo storeInfo : list) {
                org.json.simple.JSONObject jTemp = ObjectMaker.getSimpleJSONObject();
                jTemp.putAll(storeInfo.convertMap());
                jsonArray.add(jTemp);
            }
            jsonObject.put("store", jsonArray);
        } catch(StoreKeywordNotFoundException e) {
            jsonObject = ObjectMaker.getJSONObjectWithException(e);
        }
        return jsonObject;
    }


    @SuppressWarnings("unchecked")
    public org.json.simple.JSONObject findAllStoreLocation(StoreLocationDto dto) {
        List<StoreLocationVo> list = storeDao.findAllStoreLocation(dto);
        org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();
        jsonObject.put("result", true);
        jsonObject.put("message", "모든 가게의 위치 정보 가져오기 성공");
        org.json.simple.JSONArray jsonArray = ObjectMaker.getSimpleJSONArray();
        for(StoreLocationVo locationVo : list) {
            org.json.simple.JSONObject jTemp = ObjectMaker.getSimpleJSONObject();

            jTemp.putAll(locationVo.convertMap());
            jsonArray.add(jTemp);
        }
        jsonObject.put("store", jsonArray);
        return jsonObject;
    }

    @SuppressWarnings("unchecked")
    public org.json.simple.JSONObject findStoreIdByStoreName(String store_name) {
        org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();
        int store_id = storeDao.findStoreIdByStoreName(store_name);
        try {
            if (store_id == -1) {
                throw new StoreNameNotFoundException();
            } else {
                jsonObject.put("result", true);
                jsonObject.put("message", "가게 id 가져오기 성공");
                jsonObject.put("store_id", store_id);
            }
        } catch(StoreNameNotFoundException e) {
            jsonObject = ObjectMaker.getJSONObjectWithException(e);
        }
        return jsonObject;
    }

    @SuppressWarnings("unchecked")
    public org.json.simple.JSONObject isStoreOpen(int store_id) {
        org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();
        String is_open = storeDao.isStoreOpen(store_id);
        if(is_open.equals("Y")) {
            jsonObject.put("result", true);
            jsonObject.put("message", "해당 매장이 영업 중 입니다.");
        } else if(is_open.equals("N")) {
            jsonObject.put("result", false);
            jsonObject.put("message", "해당 매장이 영업 중이 아닙니다.");
        } else {
            jsonObject.put("result", false);
            jsonObject.put("message", "해당 매장이 존재하지 않습니다.");
        }
        return jsonObject;
    }

    @SuppressWarnings("unchecked")
    public org.json.simple.JSONObject findByNew(@NotNull StoreLocationDto requestDto) {
        org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();
        try{
            List<StoreInfoUltraNewVo> list = storeDao.storeSearchByNew(requestDto);
            jsonObject.put("result", true);
            jsonObject.put("message", "신규가게 출력 성공");
            org.json.simple.JSONArray jsonArray = ObjectMaker.getSimpleJSONArray();
            for(StoreInfoUltraNewVo store : list){
                org.json.simple.JSONObject jTemp = ObjectMaker.getSimpleJSONObject();
                jTemp.putAll(store.convertMap());
                jsonArray.add(jTemp);
            }
            jsonObject.put("store", jsonArray);
        }
        catch (StoreSearchException e){
            jsonObject = ObjectMaker.getJSONObjectWithException(e);
        }
        return jsonObject;
    }

    public JSONObject storeFindAll(@NotNull StoreLocationDto requestDto) {
        org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();
        try {
            List<StoreFindAllVo> list = storeDao.findAllStore(requestDto);
            jsonObject.put("result", true);
            jsonObject.put("message", "type_code별로 가게 정보 가져오기 성공");
            org.json.simple.JSONArray jsonArray = ObjectMaker.getSimpleJSONArray();
            for(StoreFindAllVo all : list) {
                org.json.simple.JSONObject jTemp = ObjectMaker.getSimpleJSONObject();
                jTemp.putAll(all.convertMap());
                jsonArray.add(jTemp);
            }
            jsonObject.put("store", jsonArray);
        } catch(StoreAllNotFoundException e) {
            jsonObject = ObjectMaker.getJSONObjectWithException(e);
        }
        return jsonObject;
    }

    public JSONObject getStoreDiscount(int store_id) {
        org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();
        int discount_rate = storeDao.getStoreDiscount(store_id);
        jsonObject.put("result", true);
        jsonObject.put("message", "가게 할인률 출력");
        jsonObject.put("discount_rate", discount_rate);
        return jsonObject;
    }
}