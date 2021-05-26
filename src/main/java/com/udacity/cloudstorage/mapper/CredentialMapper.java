package com.udacity.cloudstorage.mapper;

import com.udacity.cloudstorage.model.CredentialsModel;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialMapper {

    @Select("SELECT * FROM CREDENTIALS WHERE userid = #{userid}")
    public List<CredentialsModel> getCredentials(Integer userid);

    @Select("SELECT * FROM CREDENTIALS WHERE userid = #{userid} AND url = #{url} AND username = #{username}")
    public CredentialsModel getOneCredential(Integer userid, String url , String username);

    @Insert("INSERT INTO CREDENTIALS (url, username, key, password, userid) VALUES(#{url}, #{username}, #{key}, #{password}, #{userid})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialid" ,keyColumn="credentialid")
    public Integer insertCredential(CredentialsModel credentialsModel);

    @Delete("DELETE FROM CREDENTIALS WHERE credentialid = #{credentialid}")
    public Integer deleteCredential(Integer credentialid);

    @Update("Update CREDENTIALS SET username=#{username}, password=#{password}, key=#{key}, url=#{url} WHERE credentialid = #{credentialid}")
    public Integer updateCredential(CredentialsModel credentialsModel);

}
