package com.myspring.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

public class UploadFileUtils {

	public static String uploadFile(MultipartFile multipartFile, HttpServletRequest request) throws FileNotFoundException, IOException {
		
		String fileName = "";
		
		if(!multipartFile.isEmpty()) {
			
			fileName = multipartFile.getOriginalFilename();
			// 이미지 파일인가? 파일의 확장명 검사
			String formatName = fileName.substring(fileName.lastIndexOf(".")+1);
			MediaType mType = MediaUtils.getMediaType(formatName);
						
			// 이미지 파일인 경우..
			if(null != mType) {
				
				ServletContext application = request.getServletContext();
				String realPath = application.getRealPath("/upload");
				
				int index = fileName.lastIndexOf("\\");
				fileName = fileName.substring(index + 1);
				
				File file = new File(realPath, fileName);
				
				if(file.exists()) {
					fileName = System.currentTimeMillis() + "_" + fileName;
					file = new File(realPath, fileName);
				}
				
				System.out.println("업로드 경로 : "  + realPath);
				System.out.println("업로드 파일명: " + fileName);
				
				IOUtils.copy(multipartFile.getInputStream(), new FileOutputStream(file));
				//member.setImage_name(fileName);
			}else {
				System.out.println("이미지 파일이 아닙니다.");
			}
		}else {
			System.out.println("파일이 존재하지 않거나 파일크기가 0입니다.");
		}
		return fileName;
	}
}
