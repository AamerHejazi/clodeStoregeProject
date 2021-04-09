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
    public String createNote(Authentication auth,@ModelAttribute("noteModel")  NoteModel noteModel, Model model) {

        System.out.println(noteModel.toString());
        System.out.println("Inside create home");

        UserModel user = userService.getUser(auth.getName());
        noteModel.setUserid(user.getUserid());

        noteService.addNote(noteModel);
        model.addAttribute("notes", this.noteService.getNotes(user.getUserid()));
//        model.addAttribute("files", this.fileService.getFiles(user.getUserId()));
//        model.addAttribute("credentials", this.credentialService.getCredentials(user.getUserId()));
        return "redirect:/home";
    }


    @PutMapping
    public String updateNote(@ModelAttribute NoteModel noteModel, Authentication auth, Model model) {
        System.out.println("I am inside update function");
        System.out.println(noteModel.getNotedescription());
        System.out.println(noteModel.getNotetitle());
        System.out.println(noteModel.getNoteid());

        UserModel user = userService.getUser(auth.getName());
        noteModel.setUserid(user.getUserid());
        this.noteService.updateNote(noteModel);
        model.addAttribute("notes", this.noteService.getNotes(user.getUserid()));
//        model.addAttribute("files", this.fileService.getFiles(user.getUserId()));
//        model.addAttribute("credentials", this.credentialService.getCredentials(user.getUserId()));
        return "redirect:/home";
    }

//    @DeleteMapping
//    public String deleteNote(Authentication auth, Model model) {
//        //this.noteService.deleteNote(noteId);
//        UserModel user = userService.getUser(auth.getName());
//        model.addAttribute("notes", this.noteService.getNotes(user.getUserid()));
////        model.addAttribute("files", this.fileService.getFiles(user.getUserId()));
////        model.addAttribute("credentials", this.credentialService.getCredentials(user.getUserId()));
//        return "home";
//    }

}
