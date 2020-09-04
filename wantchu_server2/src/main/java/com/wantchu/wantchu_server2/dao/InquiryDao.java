package com.wantchu.wantchu_server2.dao;

import com.wantchu.wantchu_server2.business.SQL;
import com.wantchu.wantchu_server2.inquiry.dto.InquirySaveRequestDto;
import com.wantchu.wantchu_server2.inquiry.exception.InquiryNotFoundByEmailException;
import com.wantchu.wantchu_server2.inquiry.exception.InquiryNotFoundByIdException;
import com.wantchu.wantchu_server2.vo.InquiryVo;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.util.List;

@Component
public class InquiryDao {

    private JdbcTemplate jdbcTemplate;

    public InquiryDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<InquiryVo> findInquiryListByEmail(String email) throws InquiryNotFoundByEmailException{
        List<InquiryVo> list = jdbcTemplate.query(
                SQL.Inquiry.FIND_BY_EMAIL,
                (resultSet, i) -> {
                    InquiryVo inquiryVo = new InquiryVo();
                    inquiryVo.setInquiry_id(resultSet.getInt("inquiry_id"));
                    inquiryVo.setTitle(resultSet.getString("title"));
                    inquiryVo.setInquiry_date(resultSet.getTimestamp("inquiry_date").toLocalDateTime());
                    inquiryVo.setIs_replied(resultSet.getString("is_replied"));
                    return inquiryVo;
                }
                , email);
        if(list.size() == 0) {
            throw new InquiryNotFoundByEmailException();
        } else {
            return list;
        }
    }

    public InquiryVo findInquiryById(int inquiry_id) throws InquiryNotFoundByIdException {
        List<InquiryVo> list = jdbcTemplate.query(
                SQL.Inquiry.FIND_BY_ID,
                (resultSet, i) -> {
                    InquiryVo inquiryVo = new InquiryVo();
                    inquiryVo.setInquiry_id(resultSet.getInt("inquiry_id"));
                    inquiryVo.setEmail(resultSet.getString("email"));
                    inquiryVo.setTitle(resultSet.getString("title"));
                    inquiryVo.setContent(resultSet.getString("content"));
                    inquiryVo.setInquiry_date(resultSet.getTimestamp("inquiry_date").toLocalDateTime());
                    inquiryVo.setIs_replied(resultSet.getString("is_replied"));
                    return inquiryVo;
                }
                , inquiry_id);
        if(list.size() == 0) {
            throw new InquiryNotFoundByIdException();
        } else {
            return list.get(0);
        }
    }

    public void save(InquirySaveRequestDto requestDto) {
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement preparedStatement = connection.prepareStatement(SQL.Inquiry.INSERT_INQUIRY);
                    preparedStatement.setString(1, requestDto.getEmail());
                    preparedStatement.setString(2, requestDto.getTitle());
                    preparedStatement.setString(3, requestDto.getContent());
                    return preparedStatement;
                }
        );
    }
}
