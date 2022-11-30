package com.lec.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lec.spring.domain.Category;
import com.lec.spring.domain.Item;
import com.lec.spring.service.ItemService;

@Controller
@RequestMapping("/item")
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	@GetMapping("/detail")
	public String itemDetail(String id, Model model) {
		
		Long lId = Long.parseLong(id);
		model.addAttribute("item", itemService.findByisvalidItem(lId));
		return "item/detail";
	}
	
	@PostMapping("/cartOk")
	public String itemCartOk(Long id, String color, String size,
			Long gridRadios, Long gridRadios2,  Model model) {
		Item itemId = itemService.findByItemid(id);
		itemService.registerCart(gridRadios, gridRadios2, itemId);
		return "redirect:/item/cart" ;
	}
	
	@GetMapping("/cart")
	public String itemCart(Model model) {
		
		model.addAttribute("cartList", itemService.cartList());
		
		return "item/cart";
	}
	
	@GetMapping("/list")
	public String categoryOfTag(Category cate, Model model) {
		
		return "item/list";
	}
	
	
}
