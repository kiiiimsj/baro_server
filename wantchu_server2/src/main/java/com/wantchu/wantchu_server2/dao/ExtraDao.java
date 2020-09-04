package com.wantchu.wantchu_server2.dao;

import com.wantchu.wantchu_server2.business.SQL;
import com.wantchu.wantchu_server2.extra.exception.ExtraNotFoundByMenuIdException;
import com.wantchu.wantchu_server2.vo.ExtraVo;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExtraDao {

    private JdbcTemplate jdbcTemplate;

    public ExtraDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<ExtraVo> findByMenuId(int menu_id) throws ExtraNotFoundByMenuIdException {
        List<ExtraVo> list = jdbcTemplate.query(
                SQL.Extra.FIND_LIST_BY_MENU_ID,
                (resultSet, i) -> {
                    ExtraVo extraVo = new ExtraVo();
                    extraVo.setExtra_id(resultSet.getInt("extra_id"));
                    extraVo.setExtra_price(resultSet.getInt("extra_price"));
                    extraVo.setExtra_name(resultSet.getString("extra_name"));
                    extraVo.setExtra_maxcount(resultSet.getInt("extra_maxcount"));
                    if(resultSet.getString("extra_group") == null) {
                        extraVo.setExtra_group("null");
                    }
                    else {
                        extraVo.setExtra_group(resultSet.getString("extra_group"));
                    }
                    return extraVo;
                }
                , menu_id);
        if(list.size() == 0) {
            throw new ExtraNotFoundByMenuIdException();
        } else {
            return list;
        }
    }
}
