package com.lec.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.lec.spring.service.ItemAdminService;

@Controller
@RequestMapping("/admin")
public class ItemAdminController {
	@Autowired
	private ItemAdminService itemadminservice;
	
	@GetMapping("/item/register")
	public String itemRegister(Model model) {
		model.addAttribute("tagList", itemadminservice.getTagList());
		model.addAttribute("categoryList", itemadminservice.getCategoryList());
		return "admin/registerItem";
	}
	@PostMapping("/item/registerOk")
	public String itemRegisterOk(
			@RequestParam List<MultipartFile> ifile,
			@RequestParam List<MultipartFile> cfile,
			String name, String category, String tag,
			Double discount, Double price, Long stock,
			String content,
			Model model) {
		int result = 0;
		result = itemadminservice.registerItem(name, category, tag, discount, price, stock, content, ifile, cfile);
		model.addAttribute("result", result);
		return "admin/registerOk";
	}
}
