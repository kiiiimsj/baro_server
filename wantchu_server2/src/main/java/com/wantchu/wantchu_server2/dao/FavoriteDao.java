package com.wantchu.wantchu_server2.dao;

import com.wantchu.wantchu_server2.business.SQL;
import com.wantchu.wantchu_server2.favorite.exception.FavoriteDeleteException;
import com.wantchu.wantchu_server2.favorite.exception.FavoriteInfoNotFoundException;
import com.wantchu.wantchu_server2.favorite.exception.FavoriteSaveException;
import com.wantchu.wantchu_server2.vo.FavoriteInfoVo;
import com.wantchu.wantchu_server2.vo.FavoriteVo;
import org.apache.tomcat.jdbc.pool.DataSource;
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

    public List<FavoriteInfoVo> findFavInfoByPhone(String phone) throws FavoriteInfoNotFoundException{
        List<FavoriteInfoVo> list = jdbcTemplate.query(
                SQL.Favorite.FIND_FAVORITES_BY_PHONE,
                (resultSet, i) -> {
                     FavoriteInfoVo infoVo = new FavoriteInfoVo();
                     infoVo.setStore_id(resultSet.getInt("store_id"));
                     infoVo.setStore_latitude(resultSet.getDouble("store_latitude"));
                     infoVo.setStore_longitude(resultSet.getDouble("store_longitude"));
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
                , phone);
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
        int result = jdbcTemplate.update(
                connection -> {
                    PreparedStatement preparedStatement = connection.prepareStatement(SQL.Favorite.DELETE_FAVORITE);
                    preparedStatement.setString(1, favoriteVo.getPhone());
                    preparedStatement.setInt(2, favoriteVo.getStore_id());
                    return preparedStatement;
                }
        );
        if(result == 0) throw new FavoriteDeleteException();
    }
}
