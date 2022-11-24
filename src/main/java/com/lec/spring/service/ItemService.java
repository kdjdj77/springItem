package com.lec.spring.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lec.spring.domain.Category;
import com.lec.spring.domain.Item;
import com.lec.spring.domain.Tag;
import com.lec.spring.repository.CategoryRepository;
import com.lec.spring.repository.ItemRepository;
import com.lec.spring.repository.TagRepository;
import com.lec.spring.repository.UserRepository;

@Service
public class ItemService {
	
	@Autowired
	private ItemRepository itemRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private TagRepository tagRepository;
	
	@Transactional
	public List<Category> categoryList() {
		return categoryRepository.findAll();
	}
	
	@Transactional
	public List<Tag> tagList() {
		return tagRepository.findAll();
	}
	
	@Transactional
	public List<Item> itemList() {
		return itemRepository.findAll();
	}
}
