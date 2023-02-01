package com.zc.managementsystem.user.dao;

import com.zc.managementsystem.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_CREATE_TIME = "create_time";
    private static final String COLUMN_MODIFY_TIME = "modify_time";
    private static final String SELECT_BY_USER_NAME_SQL = "SELECT * FROM users WHERE email = ?";

    public User getUserByEmail(String email) {
        User user = new User();
        jdbcTemplate.query(SELECT_BY_USER_NAME_SQL, new Object[]{email}, new RowCallbackHandler() {
            public void processRow(ResultSet rs) throws SQLException {
                user.setId(rs.getLong(COLUMN_ID));
                user.setUserName(rs.getString(COLUMN_USERNAME));
                user.setPassword(rs.getString(COLUMN_PASSWORD));
                user.setCreateTime(rs.getInt(COLUMN_CREATE_TIME));
                user.setModifyTime(rs.getInt(COLUMN_MODIFY_TIME));
                user.setEmail(rs.getString(COLUMN_EMAIL));
            }
        });
        if (user.getId() == 0) {
            return null;
        }
        return user;
    }
}
