package com.wantchu.wantchu_server2.dao;

import com.wantchu.wantchu_server2.business.SQL;
import com.wantchu.wantchu_server2.member.dto.MemberEmailUpdateRequestDto;
import com.wantchu.wantchu_server2.member.dto.MemberLoginRequestDto;
import com.wantchu.wantchu_server2.member.dto.MemberPassUpdateRequestDto;
import com.wantchu.wantchu_server2.member.dto.MemberRegisterRequestDto;
import com.wantchu.wantchu_server2.member.exception.MemberLoginException;
import com.wantchu.wantchu_server2.member.exception.MemberPassUpdateException;
import com.wantchu.wantchu_server2.vo.MemberVo;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

@RequiredArgsConstructor
@Repository
@Component
public class MemberDao {

    private final JdbcTemplate jdbcTemplate;


    public MemberDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public MemberVo isValidAccount(String phone, String password) throws MemberLoginException {
        List<MemberVo> results = jdbcTemplate.query(
                SQL.Member.IS_VALID_ACCOUNT,
                (rs, rowNum) -> {
                    MemberVo member = new MemberVo();
                    member.setPhone(rs.getString("phone"));
                    member.setNick(rs.getString("nick"));
                    member.setEmail(rs.getString("email"));
                    member.setCreated_date(rs.getTimestamp("create_date").toLocalDateTime());
                    return member;
                }, phone, password);
        if(results.size() == 0) {
            throw new MemberLoginException();
        } else {
            return results.get(0);
        }
    }

    public boolean isPhoneInUse(String phone) {
        List<MemberVo> results = jdbcTemplate.query(
                SQL.Member.CHECK_PHONE,
                (rs, rowNum) -> new MemberVo()
                , phone);
        if(results.size() == 0) {
            return false;
        } else {
            return true;
        }
    }

    public boolean isEmailInUse(String email) {
        List<MemberVo> results = jdbcTemplate.query(
                SQL.Member.CHECK_EMAIL,
                (rs, rowNum) -> new MemberVo()
                , email);
        if(results.size() == 0) {
            return false;
        } else {
            return true;
        }
    }

    public void register(MemberRegisterRequestDto registerRequestDto) {
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL.Member.REGISTER);
            preparedStatement.setString(1, registerRequestDto.getPhone());
            preparedStatement.setString(2, registerRequestDto.getEmail());
            preparedStatement.setString(3, registerRequestDto.getNick());
            preparedStatement.setString(4, registerRequestDto.getPass());
            return preparedStatement;
        });
    }

    public void updatePassword(MemberPassUpdateRequestDto requestDto) throws MemberPassUpdateException {
        int result = jdbcTemplate.update(connection -> {
                PreparedStatement preparedStatement = connection.prepareStatement(SQL.Member.UPDATE_PASS);
                preparedStatement.setString(1, requestDto.getPass());
                preparedStatement.setString(2, requestDto.getPhone());
                return preparedStatement;
        });
        if(result == 0) {
            throw new MemberPassUpdateException();
        }
    }

    public void updateEmail(MemberEmailUpdateRequestDto requestDto) {
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL.Member.UPDATE_EMAIL);
            preparedStatement.setString(1, requestDto.getEmail());
            preparedStatement.setString(2, requestDto.getPhone());
            return preparedStatement;
        });
    }

    public void updateDeviceToken(MemberLoginRequestDto requestDto) {
        jdbcTemplate.update(connection -> {
           PreparedStatement preparedStatement = connection.prepareStatement(SQL.Member.UPDATE_DEVICE_TOKEN);
           preparedStatement.setString(1, requestDto.getDevice_token());
           preparedStatement.setString(2, requestDto.getPhone());
           return preparedStatement;
        });
    }
}
