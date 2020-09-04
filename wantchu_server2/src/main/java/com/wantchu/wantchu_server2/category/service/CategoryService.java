package com.wantchu.wantchu_server2.category.service;

import com.wantchu.wantchu_server2.business.ObjectMaker;
import com.wantchu.wantchu_server2.category.exception.CategoryNotFoundByStoreIdException;
import com.wantchu.wantchu_server2.dao.CategoryDao;
import com.wantchu.wantchu_server2.vo.CategoryVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
@RequiredArgsConstructor
@Service
public class CategoryService {

    private final CategoryDao categoryDao;

    @SuppressWarnings("unchecked")
    public org.json.simple.JSONObject findByStoreId(int store_id) {
        org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();
        try {
            List<CategoryVo> list = categoryDao.findByStoreId(store_id);
            jsonObject.put("result", true);
            jsonObject.put("message", "해당 store_id로 카테고리 목록 불러오기 성공");
            org.json.simple.JSONArray jsonArray = ObjectMaker.getSimpleJSONArray();
            for(CategoryVo category : list) {
                org.json.simple.JSONObject jTemp = ObjectMaker.getSimpleJSONObject();
                jTemp.putAll(category.convertMap());
                jsonArray.add(jTemp);
            }
            jsonObject.put("category", jsonArray);
        } catch(CategoryNotFoundByStoreIdException e){
            jsonObject = ObjectMaker.getJSONObjectWithException(e);
        }
        return jsonObject;
    }
}
