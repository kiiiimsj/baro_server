package com.wantchu.wantchu_server2.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class ManageDao {

    private JdbcTemplate jdbcTemplate;

    public ManageDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    //추가
}
