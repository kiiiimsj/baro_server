package com.wantchu.wantchu_server2.dao;

import com.wantchu.wantchu_server2.business.SQL;
import com.wantchu.wantchu_server2.menu.exception.MenuNotFoundByStoreIdException;
import com.wantchu.wantchu_server2.vo.MenuVo;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MenuDao {

    private JdbcTemplate jdbcTemplate;

    public MenuDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<MenuVo> findByStoreId(int store_id) throws MenuNotFoundByStoreIdException {
        List<MenuVo> list = jdbcTemplate.query(
                SQL.Menu.FIND_BY_STORE_ID,
                (resultSet, i) -> {
                    MenuVo menuVo = new MenuVo(resultSet.getInt("menu_id"), resultSet.getInt("menu_defaultprice"), resultSet.getInt("category_id"), resultSet.getString("menu_name"), resultSet.getString("menu_info"), resultSet.getInt("store_id"));
                    return menuVo;
                }
                , store_id);
        if(list.size() == 0) {
            throw new MenuNotFoundByStoreIdException();
        } else {
            return list;
        }
    }
}
