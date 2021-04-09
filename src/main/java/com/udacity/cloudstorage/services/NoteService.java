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

    public Integer addNote(NoteModel note){
        System.out.println(note.getNoteid());
        System.out.println(note.getNotetitle());
        System.out.println(note.getNotedescription());
        return noteMapper.insertNote(note);
    }

    public Integer updateNote(NoteModel note){
       return noteMapper.updateNote(new NoteModel(note.getNoteid(), note.getNotetitle(), note.getNotedescription(), note.getUserid()));
    }

    public int deleteNote(Integer noteId){
        return noteMapper.deleteNote(noteId);
    }
}
