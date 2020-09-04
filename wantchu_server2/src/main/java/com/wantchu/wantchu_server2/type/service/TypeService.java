package com.wantchu.wantchu_server2.type.service;

import com.wantchu.wantchu_server2.business.ObjectMaker;
import com.wantchu.wantchu_server2.dao.TypeDao;
import com.wantchu.wantchu_server2.type.exception.TypeNotFoundException;
import com.wantchu.wantchu_server2.vo.TypeVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Component
@Service
public class TypeService {

    private final TypeDao typeDao;

    @SuppressWarnings("unchecked")
    public org.json.simple.JSONObject findAll() {
        org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();

        try {
            List<TypeVo> list = typeDao.findAll();
            jsonObject.put("result", true);
            jsonObject.put("message", "types 가져오기 성공");
            org.json.simple.JSONArray jsonArray = ObjectMaker.getSimpleJSONArray();
            for(TypeVo type : list) {
                org.json.simple.JSONObject jTemp = ObjectMaker.getSimpleJSONObject();
                jTemp.putAll(type.convertMap());
                jsonArray.add(jTemp);
            }
            jsonObject.put("type", jsonArray);
        } catch(TypeNotFoundException e) {
            jsonObject = ObjectMaker.getJSONObjectWithException(e);
        }
        return jsonObject;
    }
}
