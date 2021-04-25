package com.udacity.cloudstorage.mapper;

import com.udacity.cloudstorage.model.NoteModel;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteMapper {

    @Select("SELECT * FROM NOTES WHERE userId = #{userId}")
    public List<NoteModel> getNotes(Integer useerid);

    @Insert("INSERT INTO NOTES (noteTitle, noteDescription, userId) VALUES(#{noteTitle}, #{noteDescription}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "noteId")
    public Integer insertNote(NoteModel noteModel);

    @Delete("DELETE FROM NOTES WHERE noteId = #{noteId}")
    public Integer deleteNote(Integer noteId);

    @Update("Update NOTES SET noteTitle=#{noteTitle}, noteDescription=#{noteDescription} WHERE noteId = #{noteId}")
    public Integer updateNote(NoteModel noteModel);

}
