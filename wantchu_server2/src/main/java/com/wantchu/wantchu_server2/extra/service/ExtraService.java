package com.wantchu.wantchu_server2.extra.service;

import com.wantchu.wantchu_server2.business.ObjectMaker;
import com.wantchu.wantchu_server2.dao.ExtraDao;
import com.wantchu.wantchu_server2.extra.exception.ExtraNotFoundByMenuIdException;
import com.wantchu.wantchu_server2.vo.ExtraVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
@RequiredArgsConstructor
@Service
public class ExtraService {

    private final ExtraDao extraDao;

    @SuppressWarnings("unchecked")
    public org.json.simple.JSONObject findByMenuId(int menu_id) {
        org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();
        try {
            List<ExtraVo> list = extraDao.findByMenuId(menu_id);
            jsonObject.put("result", true);
            jsonObject.put("message", "menu_id로 extra 정보 가져오기 성공");
            org.json.simple.JSONArray jsonArray = ObjectMaker.getSimpleJSONArray();
            for (ExtraVo extra : list) {
                org.json.simple.JSONObject jTemp = ObjectMaker.getSimpleJSONObject();
                jTemp.putAll(extra.convertMap());
                jsonArray.add(jTemp);
            }
            jsonObject.put("extra", jsonArray);
        } catch(ExtraNotFoundByMenuIdException exception) {
            jsonObject = ObjectMaker.getJSONObjectWithException(exception);
        }
        return jsonObject;
    }
}
