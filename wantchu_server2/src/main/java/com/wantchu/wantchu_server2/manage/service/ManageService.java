package com.wantchu.wantchu_server2.manage.service;

import com.google.firebase.database.annotations.NotNull;
import com.wantchu.wantchu_server2.business.ObjectMaker;
import com.wantchu.wantchu_server2.dao.ManageDao;
import com.wantchu.wantchu_server2.manage.dto.TypeInsertDto;
import com.wantchu.wantchu_server2.manage.dto.UltraInsertDto;
import com.wantchu.wantchu_server2.vo.PrintTypeVo;
import com.wantchu.wantchu_server2.vo.PrintUltraVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Component
@Service
public class ManageService {
    private final ManageDao manageDao;

    //시작

    @SuppressWarnings("unchecked")
    public org.json.simple.JSONObject typeInsert(@NotNull TypeInsertDto requestDto) {
        org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();
        try{
            manageDao.insertType(requestDto);
            jsonObject.put("result", true);
            jsonObject.put("message", "정상적으로 type등록이 완료되었습니다.");
        }
        catch(Exception e){
            jsonObject = ObjectMaker.getJSONObjectWithException(e);
        }
        return jsonObject;
    }

    @SuppressWarnings("unchecked")
    public org.json.simple.JSONObject typeDelete(String type_code) {
        org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();
        try {
            manageDao.deleteType(type_code);
            jsonObject.put("result", true);
            jsonObject.put("message", "해당 type을 정상적으로 제거하였습니다.");
        }
        catch(Exception e){
            jsonObject = ObjectMaker.getJSONObjectWithException(e);
        }
        return jsonObject;
    }

    @SuppressWarnings("unchecked")
    public org.json.simple.JSONObject typePrint() {
        org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();
        try{
            List<PrintTypeVo> list = manageDao.printType();
            jsonObject.put("result", true);
            jsonObject.put("message", "성공적으로 type list 출력");
            org.json.simple.JSONArray jsonArray = ObjectMaker.getSimpleJSONArray();
            for(PrintTypeVo type : list) {
                org.json.simple.JSONObject jTemp = ObjectMaker.getSimpleJSONObject();
                jTemp.putAll(type.convertMap());
                jsonArray.add(jTemp);
            }
            jsonObject.put("type", jsonArray);
        }
        catch(Exception e){
            jsonObject = ObjectMaker.getJSONObjectWithException(e);
        }
        return jsonObject;
    }

    @SuppressWarnings("unchecked")
    public org.json.simple.JSONObject insertUltra(@NotNull UltraInsertDto requestDto) {
        org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();
        try{
            manageDao.insertUltra(requestDto);
            jsonObject.put("result", true);
            jsonObject.put("message", "정상적으로 ultra 등록이 완료되었습니다.");
        }
        catch (Exception e) {
            jsonObject = ObjectMaker.getJSONObjectWithException(e);
        }
        return jsonObject;
    }

    @SuppressWarnings("unchecked")
    public org.json.simple.JSONObject ultraDelete(int store_id) {
        org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();
        try{
            manageDao.deleteUltra(store_id);
            jsonObject.put("result", true);
            jsonObject.put("message", "해당 ultra를 정상적으로 제거하였습니다.");
        }
        catch (Exception e){
            jsonObject = ObjectMaker.getJSONObjectWithException(e);
        }
        return jsonObject;
    }

    @SuppressWarnings("unchecked")
    public org.json.simple.JSONObject ultraPrint() {
        org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();
        try{
            List<PrintUltraVo> list = manageDao.printUltra();
            jsonObject.put("result", true);
            jsonObject.put("message", "성공적으로 ultra list 출력");
            org.json.simple.JSONArray jsonArray = ObjectMaker.getSimpleJSONArray();
            for(PrintUltraVo ultra : list) {
                org.json.simple.JSONObject jTemp = ObjectMaker.getSimpleJSONObject();
                jTemp.putAll(ultra.convertMap());
                jsonArray.add(jTemp);
            }
            jsonObject.put("ultra", jsonArray);
        }
        catch(Exception e) {
            jsonObject = ObjectMaker.getJSONObjectWithException(e);
        }
        return jsonObject;

    }


}
