package com.lec.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lec.spring.domain.Category;
import com.lec.spring.service.ItemService;

@Controller
@RequestMapping("/item")
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	@GetMapping("/list")
	public String categoryList(Model model) {
		
		List<Category> list = itemService.categoryList();

		model.addAttribute("categoryList", list);
		System.out.println(list);
		
		return "/item/list";
	}
}
