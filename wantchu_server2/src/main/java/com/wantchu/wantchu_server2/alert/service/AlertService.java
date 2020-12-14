package com.wantchu.wantchu_server2.alert.service;

import com.wantchu.wantchu_server2.alert.exception.AlertNotFoundException;
import com.wantchu.wantchu_server2.business.DateConverter;
import com.wantchu.wantchu_server2.business.ObjectMaker;
import com.wantchu.wantchu_server2.dao.AlertDao;
import com.wantchu.wantchu_server2.alert.exception.DoNotHaveAnyMoreAlert;
import com.wantchu.wantchu_server2.vo.AlertVo;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
@RequiredArgsConstructor
@Service
public class AlertService {

    private final AlertDao alertDao;

    @SuppressWarnings("unchecked")
    public org.json.simple.JSONObject alertFind(String phone) {
        org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();
        try{
            List<AlertVo> list = alertDao.findAll(phone);
            jsonObject.put("result", true);
            jsonObject.put("message", "알림 가져오기 성공");
            org.json.simple.JSONArray jsonArray = ObjectMaker.getSimpleJSONArray();
            for(AlertVo alert : list){
                org.json.simple.JSONObject jTemp = ObjectMaker.getSimpleJSONObject();
                jTemp.putAll(alert.convertMap());
                jsonArray.add(jTemp);
            }
            jsonObject.put("alert", jsonArray);
        }
        catch(AlertNotFoundException e){
            jsonObject = ObjectMaker.getJSONObjectWithException(e);
        }
        return jsonObject;
    }

    public JSONObject alertRecently() {
        org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();
        AlertVo date;
        try { date = alertDao.getRecently();
            date.convertMap();
            jsonObject.put("result", true);
            jsonObject.put("message", "알림 가져오기 성공");
            jsonObject.put("recentlyUpdate", DateConverter.convertDateWithTime(date.getAlert_startdate()));
        } catch (AlertNotFoundException e) {
            jsonObject = ObjectMaker.getJSONObjectWithException(e);
        }
        return jsonObject;
    }

    public JSONObject getLatestAlert() {
        org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();
        AlertVo alertVo;
        try{
            alertVo = alertDao.getLatestAlertId();
            jsonObject.put("result",true);
            jsonObject.put("message","최신의 alert정보 가져오기 성공");
            jsonObject.put("recentlyAlertId",alertVo.getAlert_id());
        }catch(DoNotHaveAnyMoreAlert e){
            jsonObject = ObjectMaker.getJSONObjectWithException(e);
        }
        return jsonObject;
    }

    public JSONObject getNewAlertCount(String phone) {
        org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();
        int count = alertDao.getNewAlertCount(phone);
        jsonObject.put("result", true);
        jsonObject.put("message", "안읽은 알림의 갯수 가져오기 성공");
        jsonObject.put("count", count);
        return jsonObject;//
    }

    public JSONObject getAlertDetail(int alert_id) {
        org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();
        String content = alertDao.getAlertDetail(alert_id);
        jsonObject.put("result", true);
        jsonObject.put("message", "상세 알림 내용 출력 성공");
        jsonObject.put("content", content);
        return jsonObject;
    }

    public JSONObject alertReadCheck(int alert_id, String phone) {
        org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();
        alertDao.getAlertReadCheck(alert_id, phone);
        jsonObject.put("result", true);
        jsonObject.put("message", "안읽은 메세지 읽음 처리로 바꾸기 성공");
        return jsonObject;
    }

}
