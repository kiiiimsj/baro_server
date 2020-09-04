package com.wantchu.wantchu_server2.dao;

import com.wantchu.wantchu_server2.business.SQL;
import com.wantchu.wantchu_server2.type.exception.TypeNotFoundException;
import com.wantchu.wantchu_server2.vo.TypeVo;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TypeDao {

    private final JdbcTemplate jdbcTemplate;

    public TypeDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<TypeVo> findAll() throws TypeNotFoundException {
        List<TypeVo> list = jdbcTemplate.query(
                SQL.Type.FIND_ALL,
                (resultSet, i) -> {
                    TypeVo typeVo = TypeVo.builder()
                            .type_code(resultSet.getString("type_code"))
                            .type_name(resultSet.getString("type_name"))
                            .type_image(resultSet.getString("type_image") == null ? "default.png" : resultSet.getString("type_image"))
                            .build();
                    return typeVo;
                }
        );
        if(list.size() == 0) {
            throw new TypeNotFoundException();
        } else {
            return list;
        }
    }
}
