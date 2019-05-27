package com.myspring.controller;

import java.io.FileInputStream;
import java.io.InputStream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


import com.myspring.util.MediaUtils;
import com.myspring.util.UploadFileUtils;


@Controller
@RequestMapping("/upload/*")
public class UploadController {

	@ResponseBody
	@RequestMapping("displayFile.do")
	public ResponseEntity<byte[]> displayFile(String fileName, HttpServletRequest request) throws Exception{
		
		InputStream in = null;
		ResponseEntity<byte[]> entity = null;
		
		try {
			// 파일의 확장자 검사
			String formatName = fileName.substring(fileName.lastIndexOf(".")+1);
			MediaType mType = MediaUtils.getMediaType(formatName);
			
			// 헤더를 생성
			HttpHeaders headers = new HttpHeaders();
			
			ServletContext application = request.getServletContext();
			String uploadPath = application.getRealPath("/upload/");
			
			in = new FileInputStream(uploadPath + fileName);
			
			// 이미지 파일인 경우
			if(null != mType) {
				headers.setContentType(mType);
			}
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.OK);
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			
			if(null != in) {
				
				in.close();
			}
		}
		return entity;
	}
	
	@RequestMapping(value="/upload/uploadAjax", method=RequestMethod.POST, produces="text/plain;charset=utf-8")
	@ResponseBody
	public ResponseEntity<String> uploadAjax(MultipartFile multipartFile, HttpServletRequest request) throws Exception{
		
		// 파일 정보를 로그에 출력
		System.out.println("originalName:" + multipartFile.getOriginalFilename());
		System.out.println("size" + multipartFile.getSize());
		System.out.println("contentType:" + multipartFile.getContentType());
		return new ResponseEntity<String>(UploadFileUtils.uploadFile(multipartFile, request), HttpStatus.OK);
	}
}
