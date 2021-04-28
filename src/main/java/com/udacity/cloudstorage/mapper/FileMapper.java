package com.udacity.cloudstorage.mapper;

import com.udacity.cloudstorage.model.FileModel;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileMapper {

    @Select("SELECT * FROM FILES WHERE userid = #{userid}")
    List<FileModel> getFiles(Integer userid);

    @Select("SELECT * FROM FILES WHERE userId = #{userId} AND fileName = #{fileName}")
    List<FileModel> isFileExist(Integer userId, String fileName);

    @Select("SELECT * FROM FILES WHERE userId = #{userId} AND fileId = #{fileId}")
    FileModel getOneFile(Integer userId, Integer fileId);

    @Insert("INSERT INTO FILES (filename, contenttype, filesize, userid, filedata) VALUES(#{filename}, #{contenttype}, #{filesize}, #{userid}, #{filedata})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    Integer insertFile(FileModel fileModel);

    @Delete("DELETE FROM FILES WHERE fileId = #{fileId}")
    Integer deleteFile(Integer fileId);
}
