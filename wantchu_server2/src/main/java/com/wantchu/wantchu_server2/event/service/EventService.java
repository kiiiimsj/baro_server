package com.wantchu.wantchu_server2.event.service;

import com.wantchu.wantchu_server2.business.ObjectMaker;
import com.wantchu.wantchu_server2.dao.EventDao;
import com.wantchu.wantchu_server2.event.exception.EventNotFoundException;
import com.wantchu.wantchu_server2.vo.EventVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Component
@Service
public class EventService {

    private final EventDao eventDao;

    @SuppressWarnings("unchecked")
    public org.json.simple.JSONObject findAll(){
        org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();

        try{
            List<EventVo> list = eventDao.findAll();
            jsonObject.put("result", true);
            jsonObject.put("message", "event 가져오기 성공");
            org.json.simple.JSONArray jsonArray = ObjectMaker.getSimpleJSONArray();
            for(EventVo event : list){
                org.json.simple.JSONObject jTemp = ObjectMaker.getSimpleJSONObject();
                jTemp.putAll(event.convertMap());
                jsonArray.add(jTemp);
            }
            jsonObject.put("event", jsonArray);
        }
        catch(EventNotFoundException e){
            jsonObject = ObjectMaker.getJSONObjectWithException(e);
        }
        return jsonObject;
    }

}
