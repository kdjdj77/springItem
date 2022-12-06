package com.lec.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lec.spring.domain.Cart;
import com.lec.spring.domain.Category;
import com.lec.spring.domain.Item;
import com.lec.spring.domain.Tag;
import com.lec.spring.domain.User;
import com.lec.spring.service.ItemService;
import com.lec.spring.util.U;

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
	public String itemCart(Long id, Long cnt,
			Long col, Long siz,  Model model) {
		Item item = itemService.findByItemid(id);
		itemService.registerCart(col, siz, cnt, item);
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
	
	@GetMapping("/orderByList")
	public String orderByList(Long category, Long orby, Model model) {
		if(orby == 0) { // 최신순
			Category c = itemService.latestList(category);
			model.addAttribute("category", c);
			return "item/orderByList";
		}
		if(orby == 1) { // 좋아요순
			Category c = itemService.likeList(category);
			model.addAttribute("category", c);
			return "item/orderByList";
		}
		if(orby == 2) { // 판매량순
			Category c = itemService.salesList(category);
			model.addAttribute("category", c);
			return "item/orderByList";
		}
		if(orby == 3) { // 낮은가격순
			Category c = itemService.lowpriceList(category);
			model.addAttribute("category", c);
			return "item/orderByList";
		}
		return "item/orderByList";
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
	public String buyGet(Model model) {
		model.addAttribute("buyList", itemService.getBuyList());
		return "item/buy";
	}
	@PostMapping("/buy")
	public String getPayment(Model model) {
		int result = 0;
		result = itemService.getCartPayment();
		itemService.deleteCartAll();
		model.addAttribute("result", result);
		return "item/buyOk";
	}
	@GetMapping("/buydirect")
	public String buyDirectGet(Long id) {
		return "redirect:detail?id=" + id;
	}
	@PostMapping("/buydirect")
	public String getDirectPayment(Long id, Long col2, Long siz2, Long cnt2, Model model) {
		int result = 0;
		result = itemService.directCart(id, col2, siz2, cnt2);
		model.addAttribute("result", result);
		return "item/buyOk";
	}	
}
