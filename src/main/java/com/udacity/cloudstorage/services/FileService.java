package com.udacity.cloudstorage.services;

import com.udacity.cloudstorage.mapper.FileMapper;
import com.udacity.cloudstorage.model.FileModel;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class FileService {
    private  final FileMapper fileMapper;

    // Constructor
    public FileService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    public List<FileModel> getAllFiles(Integer userId){
        return fileMapper.getFiles(userId);
    }

    public List<FileModel> isFileExist(Integer userId, String fileName){
        return fileMapper.isFileExist(userId, fileName);
    }

    public FileModel downloadFile(Integer userId, Integer fileId){
        return fileMapper.getOneFile(userId, fileId);
    }

    public Integer addFile(MultipartFile file, Integer userId) throws Exception {
       return fileMapper.insertFile(new FileModel(null, file.getOriginalFilename(), file.getContentType(), file.getSize(), userId, file.getBytes()));
    }

    public Integer deleteFile(Integer fileId, Integer userId){
        return fileMapper.deleteFile(fileId);
    }



}
