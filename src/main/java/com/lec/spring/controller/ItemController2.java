package com.lec.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lec.spring.service.ItemAdminService;
import com.lec.spring.util.U;

@Controller
@RequestMapping("/item")
public class ItemController2 {

	@Autowired
	private ItemAdminService itemAdminService;
	
	@GetMapping("/search")
	public String searchList(Integer page, String search, Integer sort, Model model) {
		// sort 0:최신순, 1:좋아요순, 2:판매량순, 3:낮은가격순
		if (search.equals("") || search == null) search = "";
		if (sort == null) sort = 0;
		model.addAttribute("itemList", itemAdminService.getSearchList(page, search, sort, model));
		model.addAttribute("search", search);
		model.addAttribute("sort", sort);
		model.addAttribute("likeList", itemAdminService.getLikeList());
		return "item/search";
	}
	@GetMapping("/like")
	public String likeList(Model model) {
		model.addAttribute("likeList", itemAdminService.getLikeList());
		return "item/like";
	}
}
