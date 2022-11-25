package com.lec.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lec.spring.domain.Item;
import com.lec.spring.repository.ContentfileRepository;
import com.lec.spring.repository.ItemRepository;

@Controller
@RequestMapping("/item")
public class ItemController {
	
	@Autowired
	private ItemRepository itemRepository;
	@Autowired
	private ContentfileRepository contentfileRepository;
	
	@GetMapping("/detail")
	public String itemDetail(Long id, Model model, Item item) {
		
		Item itemId = itemRepository.findById(id).get();
		System.out.println("itemId : "+itemId);
		
		model.addAttribute("id", id);
		model.addAttribute("itemList", itemRepository.findByIsvalidAndId(true, id));
		model.addAttribute("contentFileList", contentfileRepository.findByItem(itemId));
		
		return "item/detail";
	}
}
