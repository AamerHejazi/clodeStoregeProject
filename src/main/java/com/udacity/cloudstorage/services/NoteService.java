package com.udacity.cloudstorage.services;

import com.udacity.cloudstorage.mapper.NoteMapper;
import com.udacity.cloudstorage.model.NoteModel;
import org.springframework.stereotype.Service;

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

    public void addNote(NoteModel note){
        noteMapper.insertNote(note);
    }

    public void updateNote(NoteModel noteModel){
        noteMapper.updateNote(noteModel);
    }

    public void deleteNote(Integer noteId){
        noteMapper.deleteNote(noteId);
    }
}
