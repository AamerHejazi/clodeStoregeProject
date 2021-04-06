package com.udacity.cloudstorage.controller;

import com.udacity.cloudstorage.model.NoteModel;
import com.udacity.cloudstorage.model.UserModel;
import com.udacity.cloudstorage.services.CredentialService;
import com.udacity.cloudstorage.services.FileService;
import com.udacity.cloudstorage.services.NoteService;
import com.udacity.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/notes")
public class NoteController {

    private final NoteService noteService;
    private final UserService userService;
    private final FileService fileService;
    private final CredentialService credentialService;

    public NoteController(NoteService noteService, UserService userService, FileService fileService, CredentialService credentialService) {

        this.noteService = noteService;
        this.userService = userService;
        this.fileService = fileService;
        this.credentialService = credentialService;
    }

    @PostMapping
    public String postView(Authentication auth, @ModelAttribute("noteModel") NoteModel noteModel, Model model) {

        UserModel user = userService.getUser(auth.getName());
        noteModel.setUserid(user.getUserid());

        noteService.addNote(noteModel);
        model.addAttribute("notes", this.noteService.getNotes(user.getUserid()));
//        model.addAttribute("files", this.fileService.getFiles(user.getUserId()));
//        model.addAttribute("credentials", this.credentialService.getCredentials(user.getUserId()));
        return "home";
    }

    @DeleteMapping("/delete/{noteId}")
    public String deleteNote(Authentication auth, @PathVariable(value = "noteId") Integer noteId, Model model) {
        this.noteService.deleteNote(noteId);
        UserModel user = userService.getUser(auth.getName());
        model.addAttribute("notes", this.noteService.getNotes(user.getUserid()));
//        model.addAttribute("files", this.fileService.getFiles(user.getUserId()));
//        model.addAttribute("credentials", this.credentialService.getCredentials(user.getUserId()));
        return "home";
    }

    @PostMapping("/update/{noteid}")
    public String updateNote(Authentication auth, @PathVariable(value = "noteid")  String noteid, Model model,
                             @ModelAttribute("noteModel") NoteModel noteModel) {

        this.noteService.updateNote(noteModel);
        UserModel user = userService.getUser(auth.getName());
        model.addAttribute("notes", this.noteService.getNotes(user.getUserid()));
//        model.addAttribute("files", this.fileService.getFiles(user.getUserId()));
//        model.addAttribute("credentials", this.credentialService.getCredentials(user.getUserId()));
        return "home";
    }

}
