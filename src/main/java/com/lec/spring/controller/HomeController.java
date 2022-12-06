package com.lec.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lec.spring.service.ItemAdminService;
import com.lec.spring.service.ItemService;

@Controller
@RequestMapping("/")
public class HomeController {
	
	@Autowired
	private ItemService itemService;
	@Autowired
	private ItemAdminService itemAdminService;
	
	@RequestMapping("/home")
	public void home(Model model) {
		model.addAttribute("itemList", itemService.itemList());
		model.addAttribute("likeList", itemAdminService.getLikeList());
	}
	
	// 현재 로그인한(인증된) 정보 Authentication 보기
	@RequestMapping("/auth")
	@ResponseBody
	public Authentication auth() {
		return SecurityContextHolder.getContext().getAuthentication();
	}
	
}
