package com.udacity.cloudstorage.model;


public class FileModel {

    private final Integer fileId;
    private final String filename;
    private final String contenttype;
    private final Long filesize;
    private final Integer userid;
    private final byte[] filedata;

    public FileModel(Integer fileId, String filename, String contenttype, Long filesize, Integer userid, byte[] filedata) {
        this.fileId = fileId;
        this.filename = filename;
        this.contenttype = contenttype;
        this.filesize = filesize;
        this.userid = userid;
        this.filedata = filedata;
    }

    public Integer getFileId() {
        return fileId;
    }

    public String getFilename() {
        return filename;
    }

    public String getContenttype() {
        return contenttype;
    }

    public Long getFilesize() {
        return filesize;
    }

    public Integer getUserid() {
        return userid;
    }

    public byte[] getFiledata() {
        return filedata;
    }
}
