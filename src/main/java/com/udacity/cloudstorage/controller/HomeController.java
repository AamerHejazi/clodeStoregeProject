package com.udacity.cloudstorage.controller;

import com.udacity.cloudstorage.model.UserModel;
import com.udacity.cloudstorage.services.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

    private final NoteService noteService;
    private final UserService userService;
    private final FileService fileService;
    private final CredentialService credentialService;
    private final EncryptionService encryptionService;

    public HomeController(NoteService noteService, UserService userService, FileService fileService, CredentialService credentialService, EncryptionService encryptionService) {
        this.noteService = noteService;
        this.userService = userService;
        this.fileService = fileService;
        this.credentialService = credentialService;
        this.encryptionService = encryptionService;
    }

    @GetMapping
    public String getHomePage(Authentication auth, Model model) {

        UserModel user = userService.getUser(auth.getName());
        model.addAttribute("encryptionService", encryptionService);
        model.addAttribute("notes", noteService.getNotes(user.getUserid()));
        //model.addAttribute("files",null);
        model.addAttribute("userCredentials",credentialService.getCredentials(user.getUserid()));
        return "home";
    }
}
