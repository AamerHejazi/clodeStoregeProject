package com.udacity.cloudstorage.controller;

import com.udacity.cloudstorage.model.NoteModel;
import com.udacity.cloudstorage.model.UserModel;
import com.udacity.cloudstorage.services.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.security.core.Authentication;

@Controller
@RequestMapping("/home")
public class HomeController {

    private final NoteService noteService;
    private final UserService userService;
    private final FileService fileService;
    private final CredentialService credentialService;

    public HomeController(NoteService noteService, UserService userService, FileService fileService, CredentialService credentialService) {
        this.noteService = noteService;
        this.userService = userService;
        this.fileService = fileService;
        this.credentialService = credentialService;
    }

    @GetMapping
    public String getHomePage(Authentication auth, Model model, NoteModel noteModel){
        System.out.println(noteModel.toString());
        System.out.println("Inside get home");
        UserModel user = userService.getUser(auth.getName());
        model.addAttribute("notes", noteService.getNotes(user.getUserid()));
//        model.addAttribute("files",null);
//        model.addAttribute("credentials",null);
        return "home";
    }
}
