package com.wantchu.wantchu_server2.dao;

import com.google.firebase.database.annotations.NotNull;
import com.wantchu.wantchu_server2.business.SQL;
import com.wantchu.wantchu_server2.favorite.exception.FavoriteDeleteException;
import com.wantchu.wantchu_server2.manage.dto.TypeInsertDto;
import com.wantchu.wantchu_server2.manage.dto.UltraInsertDto;
import com.wantchu.wantchu_server2.manage.exception.DeleteTypeException;
import com.wantchu.wantchu_server2.manage.exception.DeleteUltraException;
import com.wantchu.wantchu_server2.manage.exception.NotFoundTypeException;
import com.wantchu.wantchu_server2.manage.exception.NotFoundUltraException;
import com.wantchu.wantchu_server2.member.dto.MemberRegisterRequestDto;
import com.wantchu.wantchu_server2.vo.FavoriteVo;
import com.wantchu.wantchu_server2.vo.PrintTypeVo;
import com.wantchu.wantchu_server2.vo.PrintUltraVo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.List;

@Component
public class ManageDao {

    private JdbcTemplate jdbcTemplate;

    public ManageDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public void insertType(@NotNull TypeInsertDto requestDto) {
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL.Manage.INSERT_TYPE);
            preparedStatement.setString(1, requestDto.getType_code());
            preparedStatement.setString(2, requestDto.getType_name());
            preparedStatement.setString(3, requestDto.getType_image());
            return preparedStatement;
        });
    }

    public void deleteType(String type_code) throws DeleteTypeException {
        try{
            jdbcTemplate.update(connection -> {
                PreparedStatement preparedStatement = connection.prepareStatement(SQL.Manage.DELETE_TYPE);
                preparedStatement.setString(1, type_code);
                return preparedStatement;
            });
        }
        catch(Exception e) {
            throw new DeleteTypeException();
        }
    }

    public List<PrintTypeVo> printType() throws NotFoundTypeException {
        List<PrintTypeVo> list = jdbcTemplate.query(
                SQL.Manage.PRINT_TYPE,
                (resultSet, i) -> {
                    PrintTypeVo vo = new PrintTypeVo(resultSet.getString("type_code"), resultSet.getString("type_name"));
                    return vo;
                });
        if(list.size() == 0) {
            throw new NotFoundTypeException();
        }
        else{
            return list;
        }
    }

    public void insertUltra(@NotNull UltraInsertDto requestDto) {
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL.Manage.INSERT_ULTRA);
            preparedStatement.setInt(1, requestDto.getStore_id());
            return preparedStatement;
        });
    }

    public void deleteUltra(int store_id) throws DeleteUltraException {
        try{
            jdbcTemplate.update(connection -> {
                PreparedStatement preparedStatement = connection.prepareStatement(SQL.Manage.DELETE_ULTRA);
                preparedStatement.setInt(1, store_id);
                return preparedStatement;
            });
        }
        catch (Exception e) {
            throw new DeleteUltraException();
        }
    }

    public List<PrintUltraVo> printUltra() throws NotFoundUltraException {
        List<PrintUltraVo> list = jdbcTemplate.query(
                SQL.Manage.PRINT_ULTRA,
                (resultSet, i) -> {
                    PrintUltraVo vo = new PrintUltraVo(resultSet.getInt("store_id"), resultSet.getString("store_name"));
                    return vo;
                });
        if(list.size() == 0) {
            throw new NotFoundUltraException();
        }
        else{
            return list;
        }
    }




}
