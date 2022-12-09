package com.lec.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lec.spring.domain.Item;
import com.lec.spring.domain.ajax.QryItemCount;
import com.lec.spring.domain.ajax.QryResult;
import com.lec.spring.domain.ajax.QryReviewList;
import com.lec.spring.domain.ajax.QryTagList;
import com.lec.spring.domain.ajax.QryTotalPrice;
import com.lec.spring.service.ItemAdminService;
import com.lec.spring.service.ItemService;

@RestController
@RequestMapping("/")
public class ajaxController {
	@Autowired
	private ItemAdminService itemAdminService;
	@Autowired
	private ItemService itemService;
	
	@GetMapping("/admin/item/data/tags")
	public QryTagList itemRegister(String category) {
		QryTagList search = itemAdminService.search(category);
		return search;
	}
	@GetMapping("/item/setcount")
	public QryItemCount SetCount(Long id, Long count) {
		QryItemCount data = itemService.setCountDB(id, count);
		return data;
	}
	@GetMapping("/item/getprice")
	public QryTotalPrice getPrice() {
		QryTotalPrice data = itemService.getTotalPrice();
		return data;
	}
	@GetMapping("/item/data/likecontrol")
	public QryResult likecontrol(Long itemId) {
		return itemAdminService.likecontrol(itemId);
	}
	
	// review *********************************************************
	@GetMapping("/list")
	public QryReviewList list(Item itemId) {
		System.out.println("itemid = "+itemId);
		return itemService.reviewList(itemId);
	}
	// ****************************************************************
}
