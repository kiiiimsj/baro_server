package com.wantchu.wantchu_server2.dao;

import com.wantchu.wantchu_server2.alert.exception.AlertNotFoundException;
import com.wantchu.wantchu_server2.alert.exception.DoNotHaveAnyMoreAlert;
import com.wantchu.wantchu_server2.business.SQL;
import com.wantchu.wantchu_server2.favorite.exception.FavoriteDeleteException;
import com.wantchu.wantchu_server2.vo.AlertVo;
import com.wantchu.wantchu_server2.vo.FavoriteVo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.List;

@Component
public class AlertDao {
    private JdbcTemplate jdbcTemplate;

    public AlertDao(DataSource dataSource){ this.jdbcTemplate = new JdbcTemplate(dataSource);}

    public AlertVo getLatestAlertId() throws DoNotHaveAnyMoreAlert {
        List<AlertVo> list = jdbcTemplate.query(
                SQL.Alert.GET_RECENTLY_ALERT_ID,
                (resultSet, i ) -> {
                    AlertVo alertVo = AlertVo.builder()
                            .alert_id(resultSet.getInt("alert_id")).build();
                 return alertVo;
            }
        );
        if(list.size()==0){
            throw new DoNotHaveAnyMoreAlert();
        }
        else{
            return list.get(0);
        }
    }

    public List<AlertVo> findAll(String phone) throws AlertNotFoundException {
        List<AlertVo> list = jdbcTemplate.query(
                SQL.Alert.FIND_ALERT_ALL,
                (resultSet, i) -> {
                    AlertVo alertVo = AlertVo.builder()
                            .id(resultSet.getInt("id"))
                            .alert_id(resultSet.getInt("alert_id"))
                            .is_read(resultSet.getString("is_read"))
                            .alert_title(resultSet.getString("alert_title"))
                            .alert_startdate(resultSet.getTimestamp("alert_startdate").toLocalDateTime())
                            .build();
                    return alertVo;
                }
        , phone);
        if(list.size() == 0){
            throw new AlertNotFoundException();
        }
        else{
            return list;
        }
    }

    public AlertVo getRecently() throws AlertNotFoundException {
        List<AlertVo> date = jdbcTemplate.query(
                SQL.Alert.GET_RECENTLY_ALERT_DATE,
                (resultSet,i) -> {
                    AlertVo recently = AlertVo.builder()
                            .alert_startdate(resultSet.getTimestamp("alert_startdate").toLocalDateTime())
                            .build();
                    return recently;
                }
                );
        if(date.size()==0){
            throw new AlertNotFoundException();
        }
        else{
            return date.get(0);
        }
    }

    public int getNewAlertCount(String phone) {
        Integer result = jdbcTemplate.queryForObject(SQL.Alert.FIND_NEW_ALERT_COUNT, Integer.class, phone);
        return result;
    }

    public String getAlertDetail(int alert_id) {
        String content = jdbcTemplate.queryForObject(SQL.Alert.GET_ALERT_DETAIL_CONTENT, String.class, alert_id);
        return content;
    }

    public void getAlertReadCheck(int alert_id, String phone) {
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement preparedStatement = connection.prepareStatement(SQL.Alert.ALERT_READ_CHECK);
                    preparedStatement.setInt(1, alert_id);
                    preparedStatement.setString(2, phone);
                    return preparedStatement;
                }
        );
    }

    public void insertExtraByMenu(String phone) {
        jdbcTemplate.update( con -> {
            PreparedStatement preparedStatement = con.prepareStatement(SQL.Alert.INSERT_ALL_FOR_NEW);
            preparedStatement.setString(1,phone);
            return  preparedStatement;
        });
    }
}

//    public void deleteFavorite(FavoriteVo favoriteVo) throws FavoriteDeleteException {
//        try{
//            jdbcTemplate.update(
//                    connection -> {
//                        PreparedStatement preparedStatement = connection.prepareStatement(SQL.Favorite.DELETE_FAVORITE);
//                        preparedStatement.setString(1, favoriteVo.getPhone());
//                        preparedStatement.setInt(2, favoriteVo.getStore_id());
//                        return preparedStatement;
//                    }
//            );
//        }
//        catch(Exception e){
//            throw new FavoriteDeleteException();
//        }
//    }