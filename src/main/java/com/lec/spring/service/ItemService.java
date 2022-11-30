package com.lec.spring.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lec.spring.domain.Cart;
import com.lec.spring.domain.Category;
import com.lec.spring.domain.Color;
import com.lec.spring.domain.Contentfile;
import com.lec.spring.domain.Item;
import com.lec.spring.domain.Size;
import com.lec.spring.domain.Tag;
import com.lec.spring.domain.User;
import com.lec.spring.domain.ajax.QryItemCount;
import com.lec.spring.domain.ajax.QryTagList;
import com.lec.spring.domain.ajax.QryTotalPrice;
import com.lec.spring.repository.CartRepository;
import com.lec.spring.repository.CategoryRepository;
import com.lec.spring.repository.ColorRepository;
import com.lec.spring.repository.ContentfileRepository;
import com.lec.spring.repository.ItemRepository;
import com.lec.spring.repository.SizeRepository;
import com.lec.spring.repository.TagRepository;
import com.lec.spring.repository.UserRepository;
import com.lec.spring.util.U;

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
	@Autowired
	private ContentfileRepository contentfileRepository;
	@Autowired
	private SizeRepository sizeRepository;
	@Autowired
	private ColorRepository colorRepository;
	@Autowired
	private CartRepository cartRepository;
	
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
		return itemRepository.findTop30ByIsvalidOrderByIdDesc(true);
	}
	
	@Transactional
	public Item findByisvalidItem(Long id){
		return itemRepository.findByIsvalidAndId(true, id);
	}
	
	@Transactional
	public Item findByItemid(Long id) {
		return itemRepository.findById(id).get();
	}
	
	@Transactional
	public List<Contentfile> getItemContentfile(Item id) {
		return contentfileRepository.findByItem(id);
	}
	
	@Transactional
	public List<Size> getItemSize(Item id) {
		return sizeRepository.findByItem(id);
	}
	
	@Transactional
	public List<Color> getItemColor(Item id) {
		return colorRepository.findByItem(id);
	}
	
	@Transactional
	public void registerCart(Long color_id, Long size_id, Long count, Item id) {
		User user = U.getLoggedUser();
		Cart cart = new Cart();
		
		cart.setItem(id);
		cart.setUser(user);
		cart.setColor(colorRepository.findById(color_id).orElse(null));
		cart.setSize(sizeRepository.findById(size_id).orElse(null));
		cart.setCount(count);
		cartRepository.saveAndFlush(cart);
	}
	
	public List<Cart> cartList(){
		User user = U.getLoggedUser();
		
		return cartRepository.findByUser(user);
	}

	public void changeOption(Long id, Long color, Long size) {
		Cart cart = cartRepository.findById(id).orElse(null);
		cart.setColor(colorRepository.findById(color).orElse(null));
		cart.setSize(sizeRepository.findById(size).orElse(null));
		cartRepository.saveAndFlush(cart);
	}

	public void deleteCart(Long id) {
		Cart c = cartRepository.findById(id).orElse(null);
		cartRepository.delete(c);
	}

	public QryItemCount setCountDB(Long id, Long cnt) {
		QryItemCount list = new QryItemCount();
		Cart cart = cartRepository.findById(id).orElse(null);
		cart.setCount(cnt);
		cartRepository.saveAndFlush(cart);
		list.setCount(1);
		list.setData(cnt);
		list.setStatus("OK");
		return list;
	}

	public QryTotalPrice getTotalPrice() {
		QryTotalPrice list = new QryTotalPrice();
		List<Cart> carts = cartRepository.findByUser(U.getLoggedUser());
		Long sum = 0L;
		for(Cart c : carts) {
			sum += c.getCount() * Math.round(c.getItem().getPrice() - c.getItem().getPrice() * c.getItem().getDiscount() / 100);
		}
		list.setCount(1);
		list.setData(sum);
		list.setStatus("OK");
		return list;
	}
	
}
