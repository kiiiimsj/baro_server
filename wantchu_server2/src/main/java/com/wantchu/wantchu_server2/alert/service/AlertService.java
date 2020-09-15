package com.wantchu.wantchu_server2.alert.service;

import com.google.protobuf.util.Timestamps;
import com.wantchu.wantchu_server2.alert.exception.AlertNotFoundException;
import com.wantchu.wantchu_server2.business.DateConverter;
import com.wantchu.wantchu_server2.business.ObjectMaker;
import com.wantchu.wantchu_server2.dao.AlertDao;
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
    public org.json.simple.JSONObject alertFind() {
        org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();
        try{
            List<AlertVo> list = alertDao.findAll();
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
}
