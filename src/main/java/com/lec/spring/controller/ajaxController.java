package com.lec.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lec.spring.domain.ajax.TagQryList;
import com.lec.spring.service.ItemAdminService;

@RestController
@RequestMapping("/")
public class ajaxController {
	@Autowired
	private ItemAdminService itemAdminService;
	
	@GetMapping("/admin/item/data/tags")
	public TagQryList itemRegister(String category, Model model) {
		TagQryList search = itemAdminService.search(category);
		return search;
	}
}
