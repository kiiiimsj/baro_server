package com.wantchu.wantchu_server2.dao;

import com.wantchu.wantchu_server2.business.SQL;
import com.wantchu.wantchu_server2.favorite.dto.FavoriteListDto;
import com.wantchu.wantchu_server2.favorite.exception.FavoriteDeleteException;
import com.wantchu.wantchu_server2.favorite.exception.FavoriteExistException;
import com.wantchu.wantchu_server2.favorite.exception.FavoriteInfoNotFoundException;
import com.wantchu.wantchu_server2.favorite.exception.FavoriteSaveException;
import com.wantchu.wantchu_server2.vo.FavoriteInfoDistanceVo;
import com.wantchu.wantchu_server2.vo.FavoriteInfoVo;
import com.wantchu.wantchu_server2.vo.FavoriteVo;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.jetbrains.annotations.NotNull;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.util.List;

@Component
public class FavoriteDao {

    private JdbcTemplate jdbcTemplate;

    public FavoriteDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<FavoriteInfoDistanceVo> findFavInfoByPhone(@NotNull FavoriteListDto dto) throws FavoriteInfoNotFoundException{
        List<FavoriteInfoDistanceVo> list = jdbcTemplate.query(
                SQL.Favorite.FIND_FAVORITES_BY_PHONE,
                (resultSet, i) -> {
                    FavoriteInfoDistanceVo infoVo = new FavoriteInfoDistanceVo();
                     infoVo.setStore_id(resultSet.getInt("store_id"));
                     infoVo.setDistance(resultSet.getDouble("distance"));
                     infoVo.setStore_name(resultSet.getString("store_name"));
                     infoVo.setStore_location(resultSet.getString("store_location"));
                     infoVo.setStore_info(resultSet.getString("store_info"));
                     if(resultSet.getString("store_image") == null) {
                         infoVo.setStore_image("default.png");
                     } else {
                         infoVo.setStore_image(resultSet.getString("store_image"));
                     }
                     infoVo.setIs_open(resultSet.getString("is_open"));
                     return infoVo;
                }
                , dto.getLatitude(),dto.getLongitude(),dto.getLatitude(),dto.getPhone());
        if(list.size() == 0) {
            throw new FavoriteInfoNotFoundException();
        } else {
            return list;
        }
    }

    public void saveFavorite(FavoriteVo favoriteVo) throws FavoriteSaveException {
        try {
            jdbcTemplate.update(
                    connection -> {
                        PreparedStatement preparedStatement = connection.prepareStatement(SQL.Favorite.INSERT_FAVORITE);
                        preparedStatement.setString(1, favoriteVo.getPhone());
                        preparedStatement.setInt(2, favoriteVo.getStore_id());
                        return preparedStatement;
                    }
            );
        } catch(Exception e) {
            throw new FavoriteSaveException();
        }
    }

    public void deleteFavorite(FavoriteVo favoriteVo) throws FavoriteDeleteException {
        try{
            jdbcTemplate.update(
                    connection -> {
                        PreparedStatement preparedStatement = connection.prepareStatement(SQL.Favorite.DELETE_FAVORITE);
                        preparedStatement.setString(1, favoriteVo.getPhone());
                        preparedStatement.setInt(2, favoriteVo.getStore_id());
                        return preparedStatement;
                    }
            );
        }
        catch(Exception e){
            throw new FavoriteDeleteException();
        }
    }

    public boolean existFavorite(String phone, int store_id) {
        System.out.println(store_id);
        List<FavoriteVo> results = jdbcTemplate.query(
                SQL.Favorite.CHECK_FAVORITE,
                (resultSet, i) -> new FavoriteVo(), phone, store_id);
        if(results.size() == 0){
            return false;
        }
        else{
            return true;
        }
    }
}
