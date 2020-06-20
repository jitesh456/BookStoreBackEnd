package com.bookstoreapp.response;

public class FileResponse {

    public String fileName;

    public String fileDownloadUri;

    public String fileType;

    public long size;

    public FileResponse(String fileName, String fileDownloadUri, String fileType, long size) {
        this.fileName = fileName;
        this.fileDownloadUri = fileDownloadUri;
        this.fileType = fileType;
        this.size = size;
    }
}
