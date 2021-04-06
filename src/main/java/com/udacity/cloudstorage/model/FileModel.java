package com.udacity.cloudstorage.model;

import java.sql.Blob;

public class FileModel {
    private Integer fileId;
    private String filename;
    private String contenttype;
    private String filesize;
    private String userid;
    private String filedata;
    private byte byteArray[];
    Blob blob ;
}
