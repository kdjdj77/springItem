package com.lec.spring.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lec.spring.config.PrincipalDetailService;
import com.lec.spring.domain.User;
import com.lec.spring.domain.UserValidator;
import com.lec.spring.repository.UserRepository;
import com.lec.spring.service.ItemAdminService;
import com.lec.spring.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired private UserService userService;
	@Autowired private UserRepository userRepository;
	@Autowired private PrincipalDetailService principalDetailService;

	public UserController() {
		System.out.println(getClass().getName() + "() 생성");
	}
	
	@GetMapping("/login")
	public void login() {}
	
	@PostMapping("/login")
	public void loginOk() {}
	
	@GetMapping("/register")
	public void register() {}
	
	@PostMapping("/register")
	public String registerOk(@Valid User user
			, BindingResult result // ← validator 가 유효성 검사를 한 결과가 담긴 객체
			, Model model //★ BindingResult 보다 Model 은 뒤에 있어야 한다. 순서중요! 아니면 에러남
			, RedirectAttributes redirectAttrs // redirect 될 페이지에 넘겨줄 값들을 담을 객체
			) {
		
		// 이미 중독된 아이디(username) 이 있다면
		if(userService.isExist(user.getUsername())) {
			result.rejectValue("username", "이미 존재하는 아이디(username) 입니다");
		}
		
		if (user.getAddress1() == null) user.setAddress1("-");
		if (user.getAddress2() == null) user.setAddress2("-");
		if (user.getAddress3() == null) user.setAddress3("-");
		
		// 에러가 있었다면 redirect 한다.
		if(result.hasErrors()) {
			redirectAttrs.addFlashAttribute("username", user.getUsername());
			redirectAttrs.addFlashAttribute("name", user.getName());
			
			List<FieldError> errList = result.getFieldErrors();
			for(FieldError err : errList) {
				// 가장 처음에 발견된 에러를 담아보낸다. (그래서 break 썻음)
				redirectAttrs.addFlashAttribute("error", err.getCode());
				break;
			}
			
			return "redirect:/user/register"; // 원래 폼으로 redirect 하기.
		}
		
		// 에러가 없었으면 회원등록 진행
		int cnt = userService.register(user);
		
		// api로그인이라면 바로 로그인까지 실행
		if (user.getProvider().equals("api")) {
			model.addAttribute("username", user.getUsername());
			return "/user/apiLogin";
		}
				
		model.addAttribute("result", cnt);
		return "/user/registerOk";
		
	}
	
	@RequestMapping("/rejectAuth")
	public String rejectAuth() {
		return "common/rejectAuth";
	}
	
	// 이 컨트롤러 클래스의 handler 에서  폼 데이터 를 바인딩할때 검증하는 Validator 객체 지정
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(new UserValidator());
	}
	
	@GetMapping("/userinfo")
	public String userinfo() {
		return "user/userinfo";
	}
	@GetMapping("/update")
	public String userUpdate() {
		return "user/update";
	}
	@GetMapping("/updateOk")
	public String userUpdateGet() { return "redirect:update"; }
	@PostMapping("/updateOk")
	public String userUpdateOk(
			String id, String name, String phonenum, 
			String email, String address1, String address2, String address3, Model model) {
		int result = 0;
		result = userService.updateUser(id, name, phonenum, email, address1, address2, address3);
		//수정된 유저정보 principal에 업데이트
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userAccount = (UserDetails) authentication.getPrincipal();
	    SecurityContextHolder.getContext().setAuthentication(
	    		createNewAuthentication(authentication,userAccount.getUsername())
	    );
		model.addAttribute("result", result);
		return "user/updateOk";
	}
	protected Authentication createNewAuthentication(Authentication currentAuth, String username) {
	    UserDetails newPrincipal = principalDetailService.loadUserByUsername(username);
	    UsernamePasswordAuthenticationToken newAuth = 
	    		new UsernamePasswordAuthenticationToken(
	    				newPrincipal, 
	    				currentAuth.getCredentials(), 
	    				newPrincipal.getAuthorities()
	    		);
	    newAuth.setDetails(currentAuth.getDetails());
	    return newAuth;
	}
	@GetMapping("/delete")
	public String deleteUser(String id, Model model) {
		int result = 0;
		result = userService.deleteUser(id);
		model.addAttribute("result", result);
		return "user/deleteOk";
	}
	@GetMapping("/updatePassword")
	public String updatePassword() {
		return "user/updatePassword";
	}
	@GetMapping("/updatePasswordOk")
	public String updatePasswordGet() { return "redirect:updatePassword"; }
	@PostMapping("/updatePasswordOk")
	public String updatePasswordOk(String id, String chkpassword,
			String password, String re_password, Model model) {
		int result = 0;
		if (!isValidPassword(id, chkpassword)) {
			model.addAttribute("result", result);
			return "user/updatePasswordOk";
		}
		result = userService.updatePassword(id, password, re_password);
		model.addAttribute("result", result);
		return "user/updatePasswordOk";
	}
	protected boolean isValidPassword(String id, String chkpassword) {
		User user = userRepository.findById(Long.parseLong(id)).orElse(null);
		return principalDetailService.checkMemberPassword(chkpassword, user.getUsername());
	}
	@PostMapping("/apiLogin")
	public String apiLogin(
			String id, String name, 
			String phonenum, String email, Model model) {
		model.addAttribute("username", id);
		
		if (userService.isExist(id)) return "/user/apiLogin";
		
		model.addAttribute("name", name);
		if (phonenum != null) model.addAttribute("phonenum", phonenum.replaceAll("-", ""));
		else model.addAttribute("phonenum", "00000000000");
		if (email != null) model.addAttribute("email", email);
		else model.addAttribute("email", "test@test.com");
		model.addAttribute("address", "-");
		return "/user/apiRegister";
	}
	@GetMapping("/naverOK")
	public String naverOk() {
		return "/common/naverOK";
	}
	@RequestMapping(value = "/phoneCheck", method = RequestMethod.GET)
	@ResponseBody
	public String sendSMS(@RequestParam("phone") String userPhoneNumber) { // 휴대폰 문자보내기
		int randomNumber = (int)((Math.random()* (9999 - 1000 + 1)) + 1000);//난수 생성

		userService.certifiedPhoneNumber(userPhoneNumber,randomNumber);
		
		return Integer.toString(randomNumber);
	}
}















