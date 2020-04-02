package com.example.demo.mapper;


import com.example.demo.enums.UserEnums;
import com.example.demo.model.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface UserMapper {
    @Select("select * from user")
    @Results({
            @Result(property = "userSex",column = "user_sex",javaType = UserEnums.class),
            @Result(property = "nickName", column = "nick_name")
    })
    List<User> getAll();

    @Select("select * from user where id='${id}'")
    @Results({
            @Result(property = "userSex",column = "user_sex",javaType = UserEnums.class),
            @Result(property = "nickName", column = "nick_name")
    })
    User getOne(long id);

    @Insert("INSERT INTO users(userName,passWord,user_sex) VALUES(#{userName}, #{passWord}, #{userSex}")
    void insert(User user);

    @Update("UPDATE users SET userName=#{userName},nick_name=#{nickName} WHERE id =#{id}")
    void update(User user);

    @Delete("DELETE FROM users WHERE id =#{id}")
    void delete(Long id);


}
