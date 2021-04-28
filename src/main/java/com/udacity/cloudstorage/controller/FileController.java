package com.udacity.cloudstorage.controller;

import com.udacity.cloudstorage.model.FileModel;
import com.udacity.cloudstorage.model.UserModel;
import com.udacity.cloudstorage.services.FileService;
import com.udacity.cloudstorage.services.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.Arrays;

@Controller
@RequestMapping("/files")
public class FileController {

    private final FileService fileService;
    private final UserService userService;
    private String fileSuccessMessage;
    private String fileErrorMessage;

    public FileController(FileService fileService, UserService userService) {
        this.fileService = fileService;
        this.userService = userService;
    }


    @PostMapping
    public String uploadFile(Authentication auth, @RequestParam("fileUpload") MultipartFile fileInformation, RedirectAttributes redirectAttributes) throws Exception {

        UserModel user = userService.getUser(auth.getName());


        try {

            if (fileInformation.isEmpty()){
                throw new IOException("You did not select any file");
            }

            if(!fileService.isFileExist(user.getUserid(), fileInformation.getOriginalFilename()).isEmpty()){
                throw new IOException("This file is already exist");
            }
           Integer insertedFile = fileService.addFile(fileInformation, user.getUserid());

            if (insertedFile > 0) {
                this.fileSuccessMessage = "New file uploaded successfully";
                redirectAttributes.addFlashAttribute("fileSuccessMessage", this.fileSuccessMessage);
            } else {
                this.fileErrorMessage = "An error occurred while upload a file";
                redirectAttributes.addFlashAttribute("fileErrorMessage", this.fileErrorMessage);
            }

        }catch (Exception e){

            this.fileErrorMessage = e.getMessage();
            redirectAttributes.addFlashAttribute("fileErrorMessage", this.fileErrorMessage);
        }

        return "redirect:/home";
    }


    @DeleteMapping("/delete")
    public String deleteFile(Authentication auth, @RequestParam("fileId") Integer fileId, RedirectAttributes redirectAttributes){

        UserModel user = userService.getUser(auth.getName());
        Integer deletedFile = fileService.deleteFile(fileId,user.getUserid());

        if (deletedFile > 0){
            this.fileSuccessMessage = "File deleted successfully";
            redirectAttributes.addFlashAttribute("fileSuccessMessage", this.fileSuccessMessage);
        } else {
            this.fileErrorMessage = "An error occurred while deleting the file";
            redirectAttributes.addFlashAttribute("fileErrorMessage", this.fileErrorMessage);
        }
        return "redirect:/home";
    }

    @GetMapping("/download/{fileId}")
    public ResponseEntity downloadFile(Authentication auth, @PathVariable(name = "fileId") Integer fileId, RedirectAttributes redirectAttributes) {

        UserModel user = userService.getUser(auth.getName());

        FileModel file = fileService.downloadFile(user.getUserid(), fileId);
        System.out.println("attachment; filename=\"" + file.getFilename() + "\"");
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(file.getContenttype()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file.getFiledata());
    }

}
