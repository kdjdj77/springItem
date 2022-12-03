package com.lec.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lec.spring.domain.Category;
import com.lec.spring.domain.Item;
import com.lec.spring.domain.Tag;
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
	
	@GetMapping("/cart")
	public String itemCartOk(Model model) {
		model.addAttribute("cartList", itemService.cartList());
		return "item/cart";
	}
	
	@PostMapping("/cart")
	public String itemCart(Long id, Long count,
			Long gridRadios, Long gridRadios2,  Model model) {
		Item item = itemService.findByItemid(id);
		itemService.registerCart(gridRadios, gridRadios2, count, item);
		model.addAttribute("cartList", itemService.cartList());
		return "item/cart";
	}
	
	@GetMapping("/list")
	public String tagList(Long category,Long tag, Model model) {
		if(category != null) {
			Category c = itemService.getCategoryById(category);
			model.addAttribute("category", c);		
			return "item/list";
		}
		
		if(tag != null) {
			Tag t = itemService.getTagById(tag);
			model.addAttribute("tag", t);
			return "item/list";
		}
		return "item/list";
	}
	
	@GetMapping("/optionchange")
	public String GetOptionChange() {
		return "redirect:cart";
	}
	@PostMapping("/optionchange")
	public String optionChange(Long id, Long color, Long size) {
		if (color != null && size != null)
			itemService.changeOption(id, color, size);
		return "redirect:cart";
	}
	@GetMapping("/delCart")
	public String GetDelCart() {
		return "redirect:cart";
	}
	@PostMapping("/delCart")
	public String PostDelCart(Long id) {
		itemService.deleteCart(id);
		return "redirect:cart";
	}
	
	@GetMapping("/buy")
	public String getPayment(Long id, Model model) {
		
		if(id == null) {
			model.addAttribute("buyList",itemService.getCartPayment());
			itemService.deleteCartAll();
			return "item/buy";			
		}
		
		model.addAttribute("buy", itemService.getItemPayment(id));
		
		return "item/buy";
	}
	
}
