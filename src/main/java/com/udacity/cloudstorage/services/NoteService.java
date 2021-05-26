package com.udacity.cloudstorage.services;

import com.udacity.cloudstorage.mapper.NoteMapper;
import com.udacity.cloudstorage.model.NoteModel;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class NoteService {

    private final NoteMapper noteMapper;

    public NoteService(NoteMapper noteMapper) {
        this.noteMapper = noteMapper;
    }

    public List <NoteModel> getNotes(Integer userId){
        return noteMapper.getNotes(userId);
    }

    public boolean isNoteAvailable(Integer userId, String noteTitle, String noteDescription){
        return noteMapper.getOneNote(userId, noteTitle, noteDescription) == null;
    }

    public Integer addNote(NoteModel note){
        return noteMapper.insertNote(note);
    }

    public Integer updateNote(NoteModel note){
       return noteMapper.updateNote(new NoteModel(note.getNoteId(), note.getNoteTitle(), note.getNoteDescription(), note.getUserId()));
    }

    public Integer deleteNote(Integer noteId){
        return noteMapper.deleteNote(noteId);
    }
}
