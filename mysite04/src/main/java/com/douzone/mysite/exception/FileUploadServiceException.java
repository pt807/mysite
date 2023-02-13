package com.douzone.mysite.exception;

public class FileUploadServiceException extends RuntimeException {

	public FileUploadServiceException(String message) {
		super(message);
	}

	public FileUploadServiceException() {
		super("FileUpload Exception Occurs");
	}
}
