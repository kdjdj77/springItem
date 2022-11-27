package com.lec.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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
	@GetMapping("/item/registerOk")
	public String itemRegisterGet() { return "redirect:register"; }
	@PostMapping("/item/registerOk")
	public String itemRegisterOk(
			@RequestParam(required=false) List<MultipartFile> ifile, 
			@RequestParam(required=false) List<MultipartFile> cfile,
			@RequestParam List<String> rcolors, @RequestParam List<String> rsizes,
			Item itemParam, String category, String tag, Model model) {
		int result = 0;
		Item item = itemadminService.registerItem(itemParam, category, tag, ifile, cfile);
		result = itemadminService.registerColorAndSize(item, rcolors, rsizes);
		model.addAttribute("result", result);
		model.addAttribute("id", item.getId());
		return "admin/registerOk";
	}
	@GetMapping("/item/update")
	public String itemUpdate(String id, Model model) {
		model.addAttribute("item", itemadminService.getItemById(id));
		model.addAttribute("categoryList", itemadminService.getCategoryList());
		return "admin/updateItem";
	}
	@GetMapping("/item/updateOk")
	public String itemUpdateGet() { return "redirect:update"; }
	@PostMapping("/item/updateOk")
	public String itemUpdateOk(
			@RequestParam(required=false) List<MultipartFile> ifile, 
			@RequestParam(required=false) List<MultipartFile> cfile,
			@RequestParam(required=false) List<String> delifile, 
			@RequestParam(required=false) List<String> delcfile,
			@RequestParam(required=false) List<String> rcolors, 
			@RequestParam(required=false) List<String> rsizes,
			@RequestParam(required=false) List<String> delcolors, 
			@RequestParam(required=false) List<String> delsizes,
			Item itemParam, String id, String category, String tag, Model model) {
		int result = 0;
		itemParam.setId(Long.parseLong(id));
		itemadminService.deleteColorAndSize(delcolors, delsizes);
		itemadminService.deletefile(itemParam, delifile, delcfile);
		itemadminService.updateItem(id, itemParam, category, tag, ifile, cfile);
		result = itemadminService.registerColorAndSize(itemParam, rcolors, rsizes);
		model.addAttribute("result", result);
		model.addAttribute("id", itemParam.getId());
		return "admin/registerOk";
	}
	@GetMapping("/item/list")
	public String itemList(String search, Pageable pageable, 
			Integer page, Model model) {
		if(page == null) page = 1;
		if(page < 1) page = 1;
		if (search == null) search = "";
		model.addAttribute("search", search);
		itemadminService.getSearchItem(search, pageable, page, model);
		return "admin/list";		
	}
	@GetMapping("/item/delete")
	public String itemDelete(String id, Model model) {
		int result = 0;
		result = itemadminService.deleteItemById(id);
		model.addAttribute("result", result);
		return "admin/deleteOk";
	}
}
