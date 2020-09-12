package com.wantchu.wantchu_server2.dao;

import com.wantchu.wantchu_server2.business.SQL;
import com.wantchu.wantchu_server2.event.exception.EventNotFoundException;
import com.wantchu.wantchu_server2.vo.EventVo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

@Component
public class EventDao {
    private final JdbcTemplate jdbcTemplate;

    public EventDao(DataSource dataSource){ this.jdbcTemplate = new JdbcTemplate(dataSource); }

    public List<EventVo> findAll() throws EventNotFoundException {
        List<EventVo> list = jdbcTemplate.query(
                SQL.Event.FIND_ALL,
                (resultSet, i) -> {
                    EventVo eventVo = EventVo.builder()
                            .event_id(resultSet.getInt("event_id"))
                            .event_image(resultSet.getString("event_image") == null ? "default.png" : resultSet.getString("event_image"))
                            .event_title(resultSet.getString("event_title"))
                            .event_content(resultSet.getString("event_content"))
                            .event_startdate(resultSet.getTimestamp("event_startdate").toLocalDateTime())
                            .event_enddate(resultSet.getTimestamp("event_enddate").toLocalDateTime())
                            .build();
                    return eventVo;
                }
        );
        if(list.size() == 0){
            throw new EventNotFoundException();
        }
        else{
            return list;
        }
    }

}
