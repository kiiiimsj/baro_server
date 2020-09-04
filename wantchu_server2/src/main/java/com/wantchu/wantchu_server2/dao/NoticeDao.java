package com.wantchu.wantchu_server2.dao;

import com.wantchu.wantchu_server2.business.SQL;
import com.wantchu.wantchu_server2.notice.dto.NoticeSaveRequestDto;
import com.wantchu.wantchu_server2.notice.exception.NoticeIdNotFoundException;
import com.wantchu.wantchu_server2.notice.exception.NoticeNotFoundByCodeException;
import com.wantchu.wantchu_server2.notice.exception.NoticeNotFoundException;
import com.wantchu.wantchu_server2.vo.NoticeVo;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.util.List;

@Component
public class NoticeDao {

    private JdbcTemplate jdbcTemplate;

    public NoticeDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<NoticeVo> findAll() throws NoticeNotFoundException {
        List<NoticeVo> list = jdbcTemplate.query(
                SQL.Notice.FIND_ALL,
                (resultSet, i) -> {
                    NoticeVo noticeVo = new NoticeVo(resultSet.getInt("notice_id"), resultSet.getString("title"), resultSet.getString("content"), resultSet.getString("notice_code"), resultSet.getTimestamp("notice_date").toLocalDateTime());
                    return noticeVo;
                }
        );
        if(list.size() == 0) {
            throw new NoticeNotFoundException();
        } else {
            return list;
        }
    }

    public List<NoticeVo> findByCode(String notice_code) throws NoticeNotFoundByCodeException {
        List<NoticeVo> list = jdbcTemplate.query(
                SQL.Notice.FIND_BY_CODE,
                (resultSet, i) -> {
                    NoticeVo noticeVo = new NoticeVo(resultSet.getInt("notice_id"), resultSet.getString("title"), resultSet.getString("content"), resultSet.getString("notice_code"), resultSet.getTimestamp("notice_date").toLocalDateTime());
                    return noticeVo;
                }
                , notice_code);
        if(list.size() == 0) {
            throw new NoticeNotFoundByCodeException();
        } else {
            return list;
        }
    }

    public NoticeVo findByNoticeId(int notice_id) throws NoticeIdNotFoundException {
        List<NoticeVo> list = jdbcTemplate.query(
                SQL.Notice.FIND_BY_ID,
                (rs, rowNum) -> {
                    NoticeVo noticeVo = new NoticeVo();
                    noticeVo.setNotice_id(notice_id);
                    noticeVo.setTitle(rs.getString("title"));
                    noticeVo.setContent(rs.getString("content"));
                    noticeVo.setNotice_code(rs.getString("notice_code"));
                    noticeVo.setNotice_date(rs.getTimestamp("notice_date").toLocalDateTime());
                    return noticeVo;
                }
                , notice_id);
        if(list.size() == 0) {
            throw new NoticeIdNotFoundException();
        } else {
            return list.get(0);
        }
    }

    public void save(NoticeSaveRequestDto requestDto) {
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL.Notice.INSERT_NOTICE);
            preparedStatement.setString(1, requestDto.getTitle());
            preparedStatement.setString(2, requestDto.getContent());
            preparedStatement.setString(3, requestDto.getNotice_code());
            return preparedStatement;
        });
    }
}
