package com.udacity.cloudstorage.mapper;

import com.udacity.cloudstorage.model.UserModel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM USERS WHERE username = #{username}")
    UserModel getUser(String username);

    @Select("SELECT * FROM USERS WHERE username = #{userid}")
    UserModel getUserById(Integer userid);

    @Insert("INSERT INTO USERS (username, salt, password, firstname, lastname) VALUES(#{username}, #{salt}, #{password}, #{firstname}, #{lastname})")
    @Options(useGeneratedKeys = true, keyProperty = "userid")
    Integer insertUser(UserModel userModel);
}
