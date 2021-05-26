package com.udacity.cloudstorage.controller;


import com.udacity.cloudstorage.model.CredentialsModel;
import com.udacity.cloudstorage.model.UserModel;
import com.udacity.cloudstorage.services.CredentialService;
import com.udacity.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/credentials")
public class CredentialController {
    private final CredentialService credentialService;
    private final UserService userService;
    private String credentialSuccessMessage;
    private String credentialErrorMessage;
    private Integer insertedCredential;

    public CredentialController(CredentialService credentialService, UserService userService) {
        this.credentialService = credentialService;
        this.userService = userService;
    }

    @PostMapping
    public String createCredential(Authentication auth, @ModelAttribute CredentialsModel credentialsModel, RedirectAttributes redirectAttributes){

        UserModel user = userService.getUser(auth.getName());
        credentialsModel.setUserid(user.getUserid());

        if(credentialService.isCredentialAvailable(user.getUserid(), credentialsModel.getUrl(), credentialsModel.getUsername())) {
            insertedCredential = credentialService.addCredential(credentialsModel);
        }else {
            insertedCredential = -1;
            this.credentialErrorMessage = "This url already exist with same username";
        }
        if (insertedCredential > 0) {
            this.credentialSuccessMessage = "New credential added successfully";
            redirectAttributes.addFlashAttribute("credSuccessMessage", this.credentialSuccessMessage);
        } else {
            if(this.credentialErrorMessage == null){
                this.credentialErrorMessage = "An error occurred while adding a credential";
            }
            redirectAttributes.addFlashAttribute("credErrorMessage", this.credentialErrorMessage);
        }

        return "redirect:/home";
    }
    @PutMapping
    public String updateCredential(Authentication auth, @ModelAttribute CredentialsModel credentialsModel, RedirectAttributes redirectAttributes){

        UserModel user = userService.getUser(auth.getName());
        credentialsModel.setUserid(user.getUserid());
        Integer updatedCredential = credentialService.updateCredential(credentialsModel);

        if (updatedCredential > 0) {
            this.credentialSuccessMessage = "Credential updated successfully";
            redirectAttributes.addFlashAttribute("credSuccessMessage", this.credentialSuccessMessage);
        } else {
            this.credentialErrorMessage = "An error occurred while update the credential";
            redirectAttributes.addFlashAttribute("credErrorMessage", this.credentialErrorMessage);
        }
        return "redirect:/home";
    }

    @DeleteMapping
    public String deleteCredential(Authentication auth, @ModelAttribute CredentialsModel credentialsModel, RedirectAttributes redirectAttributes){

        UserModel user = userService.getUser(auth.getName());
        credentialsModel.setUserid(user.getUserid());
        Integer deleteCredential = credentialService.deleteCredential(credentialsModel.getCredentialid());

        if (deleteCredential > 0) {
            this.credentialSuccessMessage = "Credential deleted successfully";
            redirectAttributes.addFlashAttribute("credSuccessMessage", this.credentialSuccessMessage);
        } else {
            this.credentialErrorMessage = "An error occurred while delete the credential";
            redirectAttributes.addFlashAttribute("credErrorMessage", this.credentialErrorMessage);
        }

        return "redirect:/home";
    }
}
