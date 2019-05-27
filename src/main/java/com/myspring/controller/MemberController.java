package com.myspring.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.myspring.model.member.dto.MemberDTO;
import com.myspring.service.member.MemberService;
import com.myspring.util.MediaUtils;

@Controller
@RequestMapping("/")
public class MemberController {

	@Inject
	MemberService memberService;

	@RequestMapping("sign_up.do")
	public String sign_up() {
		return "sign_up";
	}
	
	@RequestMapping("sign_in.do")
	public String sign_in(String message) {
		return "sign_in";
	}
	

	@RequestMapping("sign_in_check.do") 
	public ModelAndView sign_in_check(MemberDTO member, HttpSession session) throws Exception {
	 
		String name = memberService.loginCheck(member); 
	 
		ModelAndView mav = new ModelAndView();
	
		if(null != name) { 
			session.setAttribute("user_id", member.getUser_id());
			session.setAttribute("name", name);
			mav.addObject("message", "success");  
		}
		else {
			mav.addObject("message", "error");
		}
		
		mav.setViewName("sign_in_result");
		return mav; 
	}
	
	@RequestMapping("sign_out.do")
	public ModelAndView sign_out(HttpSession session, ModelAndView mav) {
		
		memberService.logout(session); // 세션 초기화
		mav.setViewName("sign_in_result");
		mav.addObject("message", "logout");
		
		return mav;
	}
	
	@RequestMapping("addMember.do")
	public String create(MemberDTO member, @RequestParam("profile_image") MultipartFile multipartFile, HttpServletRequest request) throws Exception {

		if(!multipartFile.isEmpty()) {
			
			String fileName = multipartFile.getOriginalFilename();
			
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
				member.setImage_name(fileName);
			}else {
				System.out.println("이미지 파일이 아닙니다.");
			}
		}else {
			System.out.println("파일이 존재하지 않거나 파일크기가 0입니다.");
		}

		memberService.addMember(member);
		return "redirect:/member_list.do";
	}
	
	@RequestMapping("edit_memberInfo.do")
	public ModelAndView editInfo_update(HttpSession session, ModelAndView mav) throws Exception {
		
		String user_id = (String) session.getAttribute("user_id");
		MemberDTO member = memberService.selectMemberInfo(user_id);
		mav.setViewName("edit_memberInfo");
		mav.addObject("member", member);

		return mav;
	}
	
	@RequestMapping("update_memberInfo.do")
	public String memberInfo_update(MemberDTO member, @RequestParam("profile_image") MultipartFile multipartFile, HttpServletRequest request) throws Exception {
		
		if(!multipartFile.isEmpty()) {
			
			String fileName = multipartFile.getOriginalFilename();
			
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
				member.setImage_name(fileName);
			}else {
				System.out.println("이미지 파일이 아닙니다.");
			}
		}else {
			System.out.println("파일이 존재하지 않거나 파일크기가 0입니다.");
		}

		memberService.updateMemberInfo(member);
		return "redirect:member_list.do";
	}
	
	@RequestMapping("userId_check.do")
	@ResponseBody
	public String userid_check(String user_id) throws Exception {
		
		String name = memberService.userIdCheck(user_id); 
		if(null != name) { 
			return "fail";
		}
		return "success";
	}
	
	@RequestMapping("profile.do")
	public ModelAndView profile(HttpSession session, String writer, ModelAndView mav) throws Exception {
	
		MemberDTO member = null;
		
		if(null == writer) {
			String user_id = (String) session.getAttribute("user_id");
			member = memberService.selectMemberInfo(user_id);
		}
		else {
			member = memberService.selectMemberInfo(writer);
		}
		
		mav.setViewName("profile");
		mav.addObject("member", member);

		return mav;
	}

	@RequestMapping("member_list.do")
	public ModelAndView uset_list() throws Exception {
		ModelAndView mav = new ModelAndView();
		
		List<MemberDTO> membersList = memberService.selectMemberList();
		mav.addObject("list", membersList);
		mav.setViewName("member_list");
		return mav;
	}
}
