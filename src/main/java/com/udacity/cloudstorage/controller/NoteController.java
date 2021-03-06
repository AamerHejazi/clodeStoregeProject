package com.udacity.cloudstorage.controller;

import com.udacity.cloudstorage.model.NoteModel;
import com.udacity.cloudstorage.model.UserModel;
import com.udacity.cloudstorage.services.NoteService;
import com.udacity.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.SQLException;

@Controller
@RequestMapping("/notes")
public class NoteController {

    private final NoteService noteService;
    private final UserService userService;
    private String noteSuccessMessage;
    private String noteErrorMessage;
    private Integer insertedNote;

    public NoteController(NoteService noteService, UserService userService) {
        this.noteService = noteService;
        this.userService = userService;
    }

    @PostMapping
    public String createNote(Authentication auth, @ModelAttribute NoteModel noteModel, RedirectAttributes redirectAttributes) throws SQLException {

        UserModel user = userService.getUser(auth.getName());
        noteModel.setUserId(user.getUserid());

        try {
            if(noteService.isNoteAvailable(user.getUserid(), noteModel.getNoteTitle(), noteModel.getNoteDescription())){
                insertedNote = noteService.addNote(noteModel);
            }else {
                insertedNote = -1;
                this.noteErrorMessage = "Note already exist";
            }


        } catch (Exception e) {
            e.getLocalizedMessage();
            insertedNote = -1;
            this.noteErrorMessage = "Note description exceed 1000 characters";
        } finally {
            if (insertedNote > 0) {
                this.noteSuccessMessage = "New note added successfully";
                // model.addAttribute("noteSuccessMessage" , this.noteSuccessMessage);
                redirectAttributes.addFlashAttribute("noteSuccessMessage", this.noteSuccessMessage);
            } else {
                if (this.noteErrorMessage == null) {
                    this.noteErrorMessage = "An error occurred while adding a note";
                }
                redirectAttributes.addFlashAttribute("noteErrorMessage", this.noteErrorMessage);
            }
        }

        return "redirect:/home";
    }


    @PutMapping
    public String updateNote(@ModelAttribute NoteModel noteModel, Authentication auth, RedirectAttributes redirectAttributes) {

        UserModel user = userService.getUser(auth.getName());
        noteModel.setUserId(user.getUserid());
        Integer updatedNotes = this.noteService.updateNote(noteModel);

        if (updatedNotes > 0) {
            this.noteSuccessMessage = "Note updated successfully";
            redirectAttributes.addFlashAttribute("noteSuccessMessage", this.noteSuccessMessage);
        } else {
            this.noteErrorMessage = "An error occurred while update the note";
            redirectAttributes.addFlashAttribute("noteErrorMessage", this.noteErrorMessage);
        }
        return "redirect:/home";
    }

    @DeleteMapping
    public String deleteNote(@ModelAttribute NoteModel noteModel, Authentication auth, RedirectAttributes redirectAttributes) {

        UserModel user = userService.getUser(auth.getName());
        noteModel.setUserId(user.getUserid());
        Integer deletedNotes = this.noteService.deleteNote(noteModel.getNoteId());

        if (deletedNotes > 0) {
            this.noteSuccessMessage = "Note deleted successfully";
            redirectAttributes.addFlashAttribute("noteSuccessMessage", this.noteSuccessMessage);
        } else {
            this.noteErrorMessage = "An error occurred while delete the note";
            redirectAttributes.addFlashAttribute("noteErrorMessage", this.noteErrorMessage);
        }
//
        return "redirect:/home";
    }

}
