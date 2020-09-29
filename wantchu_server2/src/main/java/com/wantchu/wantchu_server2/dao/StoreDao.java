package com.wantchu.wantchu_server2.dao;

import com.wantchu.wantchu_server2.business.SQL;
import com.wantchu.wantchu_server2.store.dto.StoreInfoFindByTypeDto;
import com.wantchu.wantchu_server2.store.dto.StoreLocationDto;
import com.wantchu.wantchu_server2.store.exception.StoreIdNotFoundException;
import com.wantchu.wantchu_server2.store.exception.StoreSearchException;
import com.wantchu.wantchu_server2.store.exception.StoreTypeNotFoundException;
import com.wantchu.wantchu_server2.vo.StoreInfoFindByTypeVo;
import com.wantchu.wantchu_server2.vo.StoreInfoVo;
import com.wantchu.wantchu_server2.vo.StoreLocationVo;
import com.wantchu.wantchu_server2.vo.StoreVo;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.jetbrains.annotations.NotNull;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StoreDao {

    private JdbcTemplate jdbcTemplate;

    public StoreDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public StoreVo findByStoreId(int store_id) throws StoreIdNotFoundException {
        try {
            StoreVo storeVo = jdbcTemplate.queryForObject(
                    SQL.Store.FIND_BY_STORE_ID,
                    (resultSet, i) -> {
                        StoreVo storeVo1 = new StoreVo(resultSet.getInt("store_id"), resultSet.getString("type_code"), resultSet.getString("store_name"), resultSet.getDouble("store_latitude"), resultSet.getDouble("store_longitude"), resultSet.getString("store_opentime"), resultSet.getString("store_closetime"), resultSet.getString("store_phone"), resultSet.getString("store_daysoff"), resultSet.getString("store_location"), resultSet.getString("store_image"), resultSet.getString("store_info"));
                        if(resultSet.getString("store_image") == null) {
                            storeVo1.setStore_image("default.png");
                        } else {
                            storeVo1.setStore_image(resultSet.getString("store_image"));
                        }
                        storeVo1.setIs_open(resultSet.getString("is_open"));
                        return storeVo1;
                    }
                    , store_id);
            return storeVo;
        } catch(EmptyResultDataAccessException e) {
            throw new StoreIdNotFoundException();
        }
    }

    public List<StoreVo> storeSearch(String keyword,int startPoint) throws StoreSearchException {
        String real_keyword = "%" + keyword.trim() + "%";
        List<StoreVo> list = jdbcTemplate.query(
                SQL.Store.STORE_SEARCH,
                (resultSet, i) -> {
                    StoreVo storeVo = new StoreVo(resultSet.getInt("store_id"), resultSet.getString("type_code"), resultSet.getString("store_name"), resultSet.getDouble("store_latitude"), resultSet.getDouble("store_longitude"), resultSet.getString("store_opentime"), resultSet.getString("store_closetime"), resultSet.getString("store_phone"), resultSet.getString("store_daysoff"), resultSet.getString("store_location"), resultSet.getString("store_image"), resultSet.getString("store_info"));
                    storeVo.setIs_open(resultSet.getString("is_open"));
                    return storeVo;
                }
                , real_keyword,startPoint,startPoint+20);
        if(list.size() == 0) {
            throw new StoreSearchException();
        } else {
            return list;
        }
    }

    public List<StoreInfoFindByTypeVo> findInfoByTypeCode(@NotNull StoreInfoFindByTypeDto dto) throws StoreTypeNotFoundException {
        List<StoreInfoFindByTypeVo> list = jdbcTemplate.query(
                SQL.Store.FIND_INFO_BY_TYPE_CODE,
                (resultSet, i) -> {
                    StoreInfoFindByTypeVo storeInfoVo = StoreInfoFindByTypeVo.builder()
                    .store_id(resultSet.getInt("store_id"))
                    .store_name(resultSet.getString("store_name"))
                    .store_info(resultSet.getString("store_info"))
                    .store_location(resultSet.getString("store_location"))
                    .is_open(resultSet.getString("is_open"))
                    .store_image(resultSet.getString("store_image") == null ? "default.png" : resultSet.getString("store_image"))
                            .distance(resultSet.getDouble("distance"))
                            .build();
                    return storeInfoVo;
                }
                , dto.getLatitude(),dto.getLongitude(),dto.getLatitude(),dto.getType_code(),dto.getStartPoint(),dto.getStartPoint()+20);
        if(list.size() == 0) {
            throw new StoreTypeNotFoundException();
        } else {
            return list;
        }
    }

    public List<StoreLocationVo> findAllStoreLocation(StoreLocationDto dto) {
        List<StoreLocationVo> list = jdbcTemplate.query(
                //SQL.Store.FIND_ALL_STORE_LOCATION,
                SQL.Store.FIND_ALL_STORE_LOCATION2,
                (resultSet, i) -> {
                    StoreLocationVo locationVo = StoreLocationVo.builder()
                            .store_id(resultSet.getInt("store_id"))
                            .store_name(resultSet.getString("store_name"))
                            .store_latitude(resultSet.getDouble("store_latitude"))
                            .store_longitude(resultSet.getDouble("store_longitude"))
                            .distance(resultSet.getDouble("distance"))
                            .build();
                    return locationVo;
                }
        ,dto.getLatitude(),dto.getLongitude(),dto.getLatitude());
        return list;
    }

    public int findStoreIdByStoreName(String store_name) {
        List<StoreVo> list = jdbcTemplate.query(
                SQL.Store.FIND_STORE_ID_BY_NAME,
                (resultSet, i) -> {
                    StoreVo storeVo = new StoreVo();
                    storeVo.setStore_id(resultSet.getInt("store_id"));
                    return storeVo;
                }
                , store_name);
        if(list.size() == 0) {
            return -1;
        } else {
            return list.get(0).getStore_id();
        }
    }

    public String isStoreOpen(int store_id) {
        String is_open = jdbcTemplate.queryForObject(
                SQL.Store.IS_STORE_OPEN, String.class, store_id);
        return is_open;
    }
}
