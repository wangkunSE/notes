package com.soul.dao;

import com.soul.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

/***
 * @author wangkun1
 * @version 2018/1/12 
 */
@Repository
public class UserDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public User findUserByUserName(String userName){

        String sql = "SELECT * from user WHERE loginusername = ?";
        final User user = new User();
        jdbcTemplate.query(sql,new Object[]{userName}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                user.setId(resultSet.getString("email"));
                user.setUserName(resultSet.getString("loginusername"));
                user.setPhone(resultSet.getString("phonenumber"));
            }
        });
        return user;
    }
}
