package com.udacity.cloudstorage.services;

import com.udacity.cloudstorage.mapper.FileMapper;
import com.udacity.cloudstorage.model.FileModel;
import com.udacity.cloudstorage.model.NoteModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileService {
    private  final FileMapper fileMapper;

    public FileService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    public List<FileModel> getAllfiles(Integer userId){
        return fileMapper.getFiles(userId);
    }
    public void addFile(FileModel file){
        fileMapper.insertFile(file);
    }

    public void deleteFile(Integer fileId){
        fileMapper.deleteFile(fileId);
    }
}
