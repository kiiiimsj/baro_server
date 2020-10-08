package com.wantchu.wantchu_server2.dao;

import com.wantchu.wantchu_server2.business.SQL;
import com.wantchu.wantchu_server2.event.dto.EventSimpleResponseDto;
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

    public List<EventSimpleResponseDto> findAll() throws EventNotFoundException {
        List<EventSimpleResponseDto> list = jdbcTemplate.query(
                SQL.Event.FIND_ALL,
                (resultSet, i) -> {
                    EventSimpleResponseDto eventSimpleResponseDto = new EventSimpleResponseDto();
                    eventSimpleResponseDto.setEvent_id(resultSet.getInt("event_id"));
                    eventSimpleResponseDto.setEvent_image(resultSet.getString("event_image"));
                    return eventSimpleResponseDto;
                });
        if(list.size() == 0){
            throw new EventNotFoundException();
        }
        else{
            return list;
        }
    }

    public EventVo findDetail(int event_id) throws EventNotFoundException{
        List<EventVo> results = jdbcTemplate.query(
                SQL.Event.FIND_EVENT_DETAIL,
                (resultSet, i) ->{
                    EventVo eventVo = new EventVo();
                    eventVo.setEvent_id(resultSet.getInt("event_id"));
                    eventVo.setEvent_image(resultSet.getString("event_image"));
                    eventVo.setEvent_title(resultSet.getString("event_title"));
                    eventVo.setEvent_content(resultSet.getString("event_content"));
                    eventVo.setEvent_startdate(resultSet.getTimestamp("event_startdate").toLocalDateTime());
                    eventVo.setEvent_enddate(resultSet.getTimestamp("event_enddate").toLocalDateTime());
                    return eventVo;
                }, event_id);
        if(results.size() == 0){
            throw new EventNotFoundException();
        }
        else{
            return results.get(0);
        }
    }

    public EventVo findAdvertising() throws EventNotFoundException{
        List<EventVo> results = jdbcTemplate.query(
                SQL.Event.FIND_EVENT_ADVERTISING,
                (resultSet, i) -> {
                    EventVo eventVo = new EventVo();
                    eventVo.setEvent_id(resultSet.getInt("event_id"));
                    eventVo.setEvent_image(resultSet.getString("event_image"));
                    return eventVo;
                });
        if(results.size() == 0){
            throw new EventNotFoundException();
        }
        else{
            return results.get(0);
        }
    }

}
