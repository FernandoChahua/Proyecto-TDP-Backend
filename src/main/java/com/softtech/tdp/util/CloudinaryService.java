package com.softtech.tdp.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;

import org.cloudinary.json.JSONObject;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Component;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@Component
public class CloudinaryService {
	public String uploadFile(FilePart file) throws IOException {
		Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
				"cloud_name", "powpunch",
				"api_key", "714724176192641",
				"api_secret", "YWaV0faeDKsgdofoxZFE2cAUhVE"));  
		
		File f = Files.createTempFile("temp", file.filename()).toFile();
		try {
			Map response = cloudinary.uploader().upload(f , ObjectUtils.asMap("resource_type", "auto"));
			
			JSONObject json=new JSONObject(response);
			String url=json.getString("url");		
			return url;
		}catch (IOException e) {				
			e.printStackTrace();
		}
		return null;
	}
}
