package com.wantchu.wantchu_server2.dao;

import com.google.firebase.database.annotations.NotNull;
import com.wantchu.wantchu_server2.business.SQL;
import com.wantchu.wantchu_server2.favorite.exception.FavoriteDeleteException;
import com.wantchu.wantchu_server2.manage.dto.TypeInsertDto;
import com.wantchu.wantchu_server2.manage.exception.DeleteTypeException;
import com.wantchu.wantchu_server2.manage.exception.NotFoundTypeException;
import com.wantchu.wantchu_server2.member.dto.MemberRegisterRequestDto;
import com.wantchu.wantchu_server2.vo.FavoriteVo;
import com.wantchu.wantchu_server2.vo.PrintTypeVo;
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
            //기능 추가 preparedStatement.setString(1, registerRequestDto.getPhone()); 이런식
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


}
