package com.li.dao;

import com.li.domain.User;
import com.li.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

//操作数据库中User表中的类
public class UserDao {
    //登陆方法
    ////声明JDBCTemplate对象共用
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDatasource());
    public User login(User loginUser){
        try {
            //1.编写sql
            String sql = "select * from user where username = ? and password = ?";
            //2.调用query方法
            User user = jdbcTemplate.queryForObject(sql,
                    new BeanPropertyRowMapper<User>(User.class),
                    loginUser.getUsername(), loginUser.getPassword());
            //第一个参数sql语句，
            //第二个参数RowMapper，一般使用BeanPropertyRowMapper实现类，可以完成JavaBean的自动封装
            //BeanPropertyRowMapper<类型>(类型.class)
            //第三四个参数是填补sql语句的？参数

            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
