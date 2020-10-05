package com.wantchu.wantchu_server2.menu.service;

import com.wantchu.wantchu_server2.business.ObjectMaker;
import com.wantchu.wantchu_server2.dao.MenuDao;
import com.wantchu.wantchu_server2.menu.exception.MenuDeleteSoldOutException;
import com.wantchu.wantchu_server2.menu.exception.MenuNotFoundByStoreIdException;
import com.wantchu.wantchu_server2.menu.exception.MenuSaveSoldOutException;
import com.wantchu.wantchu_server2.vo.MenuVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Component
@Service
public class MenuService {

    private final MenuDao menuDao;

    @SuppressWarnings("unchecked")
    public org.json.simple.JSONObject findByStoreId(int store_id) {
        org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();
        try {
            List<MenuVo> list = menuDao.findByStoreId(store_id);
            jsonObject.put("result", true);
            jsonObject.put("message", "해당 store_id로 메뉴 정보 가져오기 성공");
            org.json.simple.JSONArray jsonArray = ObjectMaker.getSimpleJSONArray();
            for(MenuVo menu : list) {
                org.json.simple.JSONObject jTemp = ObjectMaker.getSimpleJSONObject();
                jTemp.putAll(menu.convertMap());
                jsonArray.add(jTemp);
            }
            jsonObject.put("menu", jsonArray);
        } catch(MenuNotFoundByStoreIdException exception) {
            jsonObject = ObjectMaker.getJSONObjectWithException(exception);
        }
        return jsonObject;
    }

    public org.json.simple.JSONObject updateSaveSoldOut(int menu_id) {
        org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();
        try{
            menuDao.updateSaveSoldOut(menu_id);
            jsonObject.put("result", true);
            jsonObject.put("message", "품절처리되었습니다.");
        }
        catch(MenuSaveSoldOutException exception){
            jsonObject = ObjectMaker.getJSONObjectWithException(exception);
        }
        return jsonObject;
    }
    public org.json.simple.JSONObject updateDeleteSoldOut(int menu_id) {
        org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();
        try{
            menuDao.updateDeleteSoldeOut(menu_id);
            jsonObject.put("result", true);
            jsonObject.put("message", "판매중처리되었습니다.");
        }
        catch(MenuDeleteSoldOutException exception){
            jsonObject = ObjectMaker.getJSONObjectWithException(exception);
        }
        return jsonObject;
    }
}
