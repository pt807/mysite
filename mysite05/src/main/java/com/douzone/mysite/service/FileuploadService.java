package com.douzone.mysite.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.mysite.exception.FileUploadServiceException;

@Service
@PropertySource("classpath:com/douzone/mysite/web/fileupload.properties")
public class FileuploadService {
	
	@Autowired
	private Environment env;

	public String restore(MultipartFile file) {
		String url = null;

		try {
			File uploadDirectory = new File(env.getProperty("fileupload.uploadLocation"));
			if (!uploadDirectory.exists()) {
				uploadDirectory.mkdir();
			}

			if (file.isEmpty()) {
				return url;
			}

			String originFilename = file.getOriginalFilename();
			String extName = originFilename.substring(originFilename.lastIndexOf(".") + 1);
			String saveFilename = generateSaveFilename(extName);
			Long filesize = file.getSize();

			System.out.println("################" + originFilename);
			System.out.println("################" + saveFilename);
			System.out.println("################" + filesize);

			byte[] data = file.getBytes();
			OutputStream os = new FileOutputStream(env.getProperty("fileupload.uploadLocation") + "/" + saveFilename);
			os.write(data);
			os.close();

			url = env.getProperty("fileupload.resourceUrl") + "/" + saveFilename;
		} catch (IOException e) {
			throw new FileUploadServiceException(e.toString());
		}
		return url;
	}

	private String generateSaveFilename(String extName) {
		String filename = "";

		Calendar calendar = Calendar.getInstance();
		filename += calendar.get(calendar.YEAR);
		filename += calendar.get(calendar.MONTH);
		filename += calendar.get(calendar.DATE);
		filename += calendar.get(calendar.HOUR);
		filename += calendar.get(calendar.MINUTE);
		filename += calendar.get(calendar.SECOND);
		filename += calendar.get(calendar.MILLISECOND);
		filename += ("." + extName);

		return filename;
	}

}
