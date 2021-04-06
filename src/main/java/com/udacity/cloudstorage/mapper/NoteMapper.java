package com.udacity.cloudstorage.mapper;

import com.udacity.cloudstorage.model.NoteModel;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteMapper {

    @Select("SELECT * FROM NOTES WHERE userid = #{userid}")
    List<NoteModel> getNotes(Integer useerid);

    @Insert("INSERT INTO NOTES (notetitle, notedescription, userid) VALUES(#{notetitle}, #{notedescription}, #{userid})")
    @Options(useGeneratedKeys = true, keyProperty = "noteid")
    public Integer insertNote(NoteModel noteModel);

    @Delete("DELETE FROM NOTES WHERE noteid = #{noteid}")
    public Integer deleteNote(Integer noteid);

    @Update("Update NOTES SET notetitle=#{notetitle}, notedescription=#{notedescription} WHERE noteid = #{noteid}")
    public Integer updateNote(NoteModel noteModel);

}
