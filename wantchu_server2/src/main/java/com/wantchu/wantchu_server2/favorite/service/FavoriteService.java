package com.wantchu.wantchu_server2.favorite.service;

import com.wantchu.wantchu_server2.business.ObjectMaker;
import com.wantchu.wantchu_server2.dao.FavoriteDao;
import com.wantchu.wantchu_server2.favorite.dto.FavoriteExistRequestDto;
import com.wantchu.wantchu_server2.favorite.dto.FavoriteListDto;
import com.wantchu.wantchu_server2.favorite.exception.FavoriteDeleteException;
import com.wantchu.wantchu_server2.favorite.exception.FavoriteExistException;
import com.wantchu.wantchu_server2.favorite.exception.FavoriteInfoNotFoundException;
import com.wantchu.wantchu_server2.favorite.exception.FavoriteSaveException;
import com.wantchu.wantchu_server2.vo.FavoriteInfoDistanceVo;
import com.wantchu.wantchu_server2.vo.FavoriteInfoVo;
import com.wantchu.wantchu_server2.vo.FavoriteVo;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Component
@Service
public class FavoriteService {
//
    private final FavoriteDao favoriteDao;

    @SuppressWarnings("unchecked")
    public org.json.simple.JSONObject findFavInfoByPhone(@NotNull FavoriteListDto dto) {
        org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();
        try {
            List<FavoriteInfoDistanceVo> list = favoriteDao.findFavInfoByPhone(dto);
            jsonObject.put("result", true);
            jsonObject.put("message", dto.getPhone() + "의즐겨찾기 정보 가져오기 성공");
            org.json.simple.JSONArray jsonArray = ObjectMaker.getSimpleJSONArray();
            for(FavoriteInfoDistanceVo info : list) {
                org.json.simple.JSONObject jTemp = ObjectMaker.getSimpleJSONObject();
                jTemp.putAll(info.convertMap());
                jsonArray.add(jTemp);
            }
            jsonObject.put("favorite", jsonArray);
        } catch(FavoriteInfoNotFoundException exception) {
            jsonObject = ObjectMaker.getJSONObjectWithException(exception);
        }//
        return jsonObject;
    }

    @SuppressWarnings("unchecked")
    public org.json.simple.JSONObject saveFavorite(FavoriteVo favoriteVo) {
        org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();
        try {
            favoriteDao.saveFavorite(favoriteVo);
            jsonObject.put("result", true);
            jsonObject.put("message", "즐겨찾기가 정상적으로 등록되었습니다.");
        } catch(FavoriteSaveException exception) {
            jsonObject = ObjectMaker.getJSONObjectWithException(exception);
        }
        return jsonObject;
    }

    @SuppressWarnings("unchecked")
    public org.json.simple.JSONObject deleteFavorite(FavoriteVo favoriteVo) {
        org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();
        try {
            favoriteDao.deleteFavorite(favoriteVo);
            jsonObject.put("result", true);
            jsonObject.put("message", "즐겨찾기 목록에서 삭제되었습니다.");
        } catch(FavoriteDeleteException exception) {
            jsonObject = ObjectMaker.getJSONObjectWithException(exception);
        }
        return jsonObject;
    }
    @SuppressWarnings("unchecked")
    public org.json.simple.JSONObject existFavorite(FavoriteExistRequestDto favoriteExistRequestDto){
        org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();
        try{
            if(favoriteDao.existFavorite(favoriteExistRequestDto.getPhone(), favoriteExistRequestDto.getStore_id())){
                jsonObject.put("result", true);
                jsonObject.put("message", "해당회원이 즐겨찾기한 가게입니다.");
            }
            else{
                throw new FavoriteExistException();
            }
        }
        catch(FavoriteExistException e){
            jsonObject = ObjectMaker.getJSONObjectWithException(e);
        }
        return jsonObject;
    }
}
