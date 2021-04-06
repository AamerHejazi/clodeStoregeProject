package com.udacity.cloudstorage.mapper;

import com.udacity.cloudstorage.model.CredentialsModel;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialMapper {

    @Select("SELECT * FROM CREDENTIALS WHERE userid = #{userid}")
    List<CredentialMapper> getCredentials(Integer userid);

    @Insert("INSERT INTO CREDENTIALS (url, username, key, password, userid) VALUES(#{url}, #{username}, #{key}, #{password}, #{userid})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialid")
    Integer insert(CredentialsModel credentialsModel);

    @Delete("DELETE FROM CREDENTIALS WHERE credentialid = #{credentialid}")
    Integer deleteNote(Integer credentialid);

    @Update("Update CREDENTIALS SET username=#{username}, password=#{password} WHERE credentialid = #{credentialid}")
    Integer updateNote(CredentialsModel credentialsModel, Integer credentialid);

}
