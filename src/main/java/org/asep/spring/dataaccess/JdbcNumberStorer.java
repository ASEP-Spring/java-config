package org.asep.spring.dataaccess;

import java.sql.Timestamp;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.Assert;

public class JdbcNumberStorer implements NumberStorer {

    private final JdbcTemplate jdbcTemplate;

    public JdbcNumberStorer(JdbcTemplate jdbcTemplate) {
        Assert.notNull(jdbcTemplate, "jdbcTemplate cannot be null");

        this.jdbcTemplate = jdbcTemplate;
    }

    public void storeNumber(int number) {
        jdbcTemplate.update("INSERT INTO numbers (num, create_time) VALUES (?, ?)", number, new Timestamp(System.currentTimeMillis()));
    }

    public int getMostRecentNumber() {
        return jdbcTemplate.queryForObject("SELECT num FROM numbers WHERE create_time = (SELECT MAX(create_time) FROM numbers)", Integer.class);
    }
}
