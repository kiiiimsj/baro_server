package com.wantchu.wantchu_server2.event.service;

import com.wantchu.wantchu_server2.business.DateConverter;
import com.wantchu.wantchu_server2.business.ObjectMaker;
import com.wantchu.wantchu_server2.dao.EventDao;
import com.wantchu.wantchu_server2.event.dto.EventSimpleResponseDto;
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
            List<EventSimpleResponseDto> list = eventDao.findAll();
            jsonObject.put("result", true);
            jsonObject.put("message", "event 대략 정보 가져오기 성공");
            org.json.simple.JSONArray jsonArray = ObjectMaker.getSimpleJSONArray();
            for(EventSimpleResponseDto event : list){
                org.json.simple.JSONObject jTemp = ObjectMaker.getSimpleJSONObject();
                jTemp.put("event_id", event.getEvent_id());
                jTemp.put("event_image", event.getEvent_image());
                jsonArray.add(jTemp);
            }
            jsonObject.put("event", jsonArray);
        }
        catch(EventNotFoundException e){
            jsonObject = ObjectMaker.getJSONObjectWithException(e);
        }
        return jsonObject;
    }

    @SuppressWarnings("unchecked")
    public org.json.simple.JSONObject findDetail(int event_id){
        org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();
        try{
            EventVo eventVo = eventDao.findDetail(event_id);
            jsonObject.put("result", true);
            jsonObject.put("message", event_id + "의 상세 이벤트 내용 가져오기 성공");
            jsonObject.put("event_id", eventVo.getEvent_id());
            jsonObject.put("event_image", eventVo.getEvent_image());
            jsonObject.put("event_title", eventVo.getEvent_title());
            jsonObject.put("event_content", eventVo.getEvent_content());
            jsonObject.put("event_startdate", DateConverter.convertDateWithoutTime(eventVo.getEvent_startdate()));
            jsonObject.put("event_enddate", DateConverter.convertDateWithoutTime(eventVo.getEvent_enddate()));
        }
        catch(EventNotFoundException e){
            jsonObject = ObjectMaker.getJSONObjectWithException(e);
        }
        return jsonObject;
    }

}
