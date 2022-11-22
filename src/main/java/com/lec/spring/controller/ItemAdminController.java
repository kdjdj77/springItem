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

import com.lec.spring.domain.Item;
import com.lec.spring.service.ItemAdminService;

@Controller
@RequestMapping("/admin")
public class ItemAdminController {
	@Autowired
	private ItemAdminService itemadminService;
	
	@GetMapping("/item/register")
	public String itemRegister(Model model) {
		model.addAttribute("tagList", itemadminService.getTagList());
		model.addAttribute("categoryList", itemadminService.getCategoryList());
		return "admin/registerItem";
	}
	@PostMapping("/item/registerOk")
	public String itemRegisterOk(
			@RequestParam List<MultipartFile> ifile,
			@RequestParam List<MultipartFile> cfile,
			@RequestParam List<String> colors,
			@RequestParam List<String> sizes,
			String name, String category, String tag,
			Double discount, Double price, Long stock,
			String content,
			Model model) {
		int result = 0;
		Item item = itemadminService.registerItem(name, category, tag, discount, price, stock, content, ifile, cfile);
		result = itemadminService.registerColorAndSize(item, colors, sizes);
		model.addAttribute("result", result);
		return "admin/registerOk";
	}
}
