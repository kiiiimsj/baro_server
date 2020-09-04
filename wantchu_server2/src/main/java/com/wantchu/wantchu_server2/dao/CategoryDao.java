package com.wantchu.wantchu_server2.dao;

import com.wantchu.wantchu_server2.business.SQL;
import com.wantchu.wantchu_server2.category.exception.CategoryNotFoundByStoreIdException;
import com.wantchu.wantchu_server2.vo.CategoryVo;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.List;

@Component
public class CategoryDao {

    private JdbcTemplate jdbcTemplate;

    public CategoryDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<CategoryVo> findByStoreId(int store_id) throws CategoryNotFoundByStoreIdException{
        List<CategoryVo> list = jdbcTemplate.query(
                SQL.Category.FIND_BY_STORE_ID,
                (resultSet, i) -> {
                    CategoryVo categoryVo = new CategoryVo(resultSet.getInt("category_id"), resultSet.getInt("store_id"), resultSet.getString("category_name"));
                    return categoryVo;
                }
                , store_id);
        Iterator<CategoryVo> iterator = list.iterator();
        if(iterator.hasNext()) {
            return list;
        }
        else {
            throw new CategoryNotFoundByStoreIdException();
        }
    }
}
