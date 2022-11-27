package com.lec.spring.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.lec.spring.domain.Category;
import com.lec.spring.domain.Color;
import com.lec.spring.domain.Contentfile;
import com.lec.spring.domain.Item;
import com.lec.spring.domain.Itemfile;
import com.lec.spring.domain.Size;
import com.lec.spring.domain.Tag;
import com.lec.spring.domain.User;
import com.lec.spring.domain.ajax.TagQryList;
import com.lec.spring.repository.CategoryRepository;
import com.lec.spring.repository.ColorRepository;
import com.lec.spring.repository.ContentfileRepository;
import com.lec.spring.repository.ItemRepository;
import com.lec.spring.repository.ItemfileRepository;
import com.lec.spring.repository.SizeRepository;
import com.lec.spring.repository.TagRepository;
import com.lec.spring.repository.UserRepository;
import com.lec.spring.util.C;
import com.lec.spring.util.U;


@Service
public class ItemAdminService {
	@Autowired private CategoryRepository categoryRepository;
	@Autowired private TagRepository tagRepository;
	@Autowired private ItemRepository itemRepository;
	@Autowired private ItemfileRepository itemfileRepository;
	@Autowired private ContentfileRepository contentfileRepository;
	@Autowired private ColorRepository colorRepository;
	@Autowired private SizeRepository sizeRepository;
	@Autowired ServletContext context;
	@Value("${app.upload.path}") private String uploadDir;
	
	public List<Tag> getTagList() {
		return tagRepository.findAll();
	}
	public List<Category> getCategoryList() {
		return categoryRepository.findAll();
	}
	public List<Tag> getTagListByCat(String name) {
		Category cat = categoryRepository.findByName(name);
		return tagRepository.findByCategory(cat);
	}
	public TagQryList search(String category) {
		Long cat_id = Long.parseLong(category);
		TagQryList list = new TagQryList();
		Category cat = categoryRepository.findById(cat_id).orElse(null);
		List<Tag> tags = tagRepository.findByCategory(cat);
		list.setCount(tags.size());
		list.setList(tags);		
		list.setStatus("OK");
		return list;
	}
	public Item registerItem(Item item, String category, String tag, 
			List<MultipartFile> ifile, List<MultipartFile> cfile) {
		item.setCategory(categoryRepository.findById(Long.parseLong(category)).orElse(null));
		item.setTag(tagRepository.findById(Long.parseLong(tag)).orElse(null));
		item = itemRepository.saveAndFlush(item);
		addItemFiles(ifile, item.getId());
		addContentFiles(cfile, item.getId());
		return item;
	}
	public Item updateItem(String id, Item item, String category, String tag, 
			List<MultipartFile> ifile, List<MultipartFile> cfile) {
		Item i = itemRepository.findById(Long.parseLong(id)).orElse(null); 
		i.setCategory(categoryRepository.findById(Long.parseLong(category)).orElse(null));
		i.setTag(tagRepository.findById(Long.parseLong(tag)).orElse(null));
		i.setDiscount(item.getDiscount());
		i.setName(item.getName());
		i.setStock(item.getStock());
		i.setPrice(item.getPrice());
		i.setContent(item.getContent());
		itemRepository.saveAndFlush(i);
		addItemFiles(ifile, i.getId());
		addContentFiles(cfile, i.getId());
		return i;
	}
	public int registerColorAndSize(Item item, List<String> colors, List<String> sizes) {
		if (colors != null)
		for(String c : colors) {
			if (c.trim().equals("")) continue;
			Color co = Color.builder().name(c).item(item).build();
			colorRepository.saveAndFlush(co);
		}
		if (sizes != null)
		for(String s : sizes) {
			if (s.trim().equals("")) continue;
			Size si = Size.builder().name(s).item(item).build();
			sizeRepository.saveAndFlush(si);
		}
		return 1;
	}
	public void deleteColorAndSize(List<String> delcolors, List<String> delsizes) {
		if (delcolors != null)
		for(String c : delcolors) {
			Color co = colorRepository.findById(Long.parseLong(c)).orElse(null);
			co.setIsvalid(false);
			colorRepository.saveAndFlush(co);
		}
		if (delsizes != null)
		for(String s : delsizes) {
			Size si = sizeRepository.findById(Long.parseLong(s)).orElse(null);
			si.setIsvalid(false);
			sizeRepository.saveAndFlush(si);
		}
	}
	public Item getItemById(String id) {
		Long lid = Long.parseLong(id);
		return itemRepository.findById(lid).orElse(null);
	}
	public int deletefile(Item item, List<String> delifiles, List<String> delcfiles) {
		if (delifiles != null) for(String i : delifiles) {
			deliFile(itemfileRepository.findById(Long.parseLong(i)).orElse(null));
			itemfileRepository.deleteById(Long.parseLong(i));
		}
		if (delcfiles != null) for(String c : delcfiles) {
			delcFile(contentfileRepository.findById(Long.parseLong(c)).orElse(null));
			contentfileRepository.deleteById(Long.parseLong(c));
		}
		
		return 1;
	}
	public void getSearchItem(String search, Pageable pageable, Integer page, Model model) {
		HttpSession session = U.getSession();
		Integer adminPages = (Integer)session.getAttribute("adminPages");
		if(adminPages == null) adminPages = C.LIST_PAGES;
		Integer pageRows = (Integer)session.getAttribute("pageRows");
		if(pageRows == null) pageRows = C.PAGE_ROWS;
		session.setAttribute("page", page);
		Page<Item> pageItems = itemRepository.findByIsvalidAndNameContaining(true, search, PageRequest.of(page - 1, pageRows, Sort.by(Order.desc("id"))));
	
		long cnt = pageItems.getTotalElements();   // 글 목록 전체의 개수
		int totalPage = pageItems.getTotalPages(); //총 몇 '페이지' 분량인가?
		if(page > totalPage) page = totalPage;   // 페이지 보정

		// [페이징] 에 표시할 '시작페이지' 와 '마지막페이지' 계산
		int startPage = ((int)((page - 1) / adminPages) * adminPages) + 1;
		int endPage = startPage + adminPages - 1;
		if (endPage >= totalPage) endPage = totalPage;
		
		model.addAttribute("cnt", cnt);  // 전체 글 개수
		model.addAttribute("page", page); // 현재 페이지
		model.addAttribute("totalPage", totalPage);  // 총 '페이지' 수
		
		// [페이징]
		model.addAttribute("url", U.getRequest().getRequestURI());  // 목록 url
		model.addAttribute("startPage", startPage);  // [페이징] 에 표시할 시작 페이지
		model.addAttribute("endPage", endPage);   // [페이징] 에 표시할 마지막 페이지
		model.addAttribute("itemList", pageItems.getContent());
	}
	public int deleteItemById(String id) {
		Long lid = Long.parseLong(id);
		Item item = itemRepository.findById(lid).orElse(null);
		item.setIsvalid(false);
		itemRepository.saveAndFlush(item);
		return 1;
	}
	
	
//file//////////////////////////////////////////////////////////////////////////////
	private int addItemFiles(List<MultipartFile> files, Long id) {
		if(files == null) return 0;
		for(MultipartFile e : files) {
			Itemfile file = upload(e);
			if(file != null) { 
				file.setItem(itemRepository.findById(id).orElse(null)); 
				file = itemfileRepository.save(file); 
			}
		} return 1;
	}
	private Itemfile upload(MultipartFile multipartFile) {
		Itemfile attachment = null;
		String originalFilename = multipartFile.getOriginalFilename();
		if(originalFilename == null || originalFilename.length() == 0) return null;
        String sourceName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        String fileName = sourceName;
        File file = new File(context.getRealPath(uploadDir) + File.separator + sourceName);
        if(file.exists()) {
        	int pos = fileName.lastIndexOf(".");
        	if(pos > -1) {
	        	String name = fileName.substring(0, pos);
	        	String ext = fileName.substring(pos + 1);
	        	fileName = name + "_" + System.currentTimeMillis() + "." + ext;
        	} else fileName += "_" + System.currentTimeMillis();
        }
		Path copyOfLocation = Paths.get(context.getRealPath(uploadDir) + File.separator + fileName);
        try {Files.copy(multipartFile.getInputStream(), copyOfLocation, StandardCopyOption.REPLACE_EXISTING);} 
        catch (IOException e) {e.printStackTrace();}	
        attachment = Itemfile.builder().file(fileName) .source(sourceName).build();
		return attachment;
	}
	public void setItemImage(List<Itemfile> fileList) {
		String realPath = context.getRealPath(uploadDir);
		for(Itemfile fileDto : fileList) {
			BufferedImage imgData = null;
			File f = new File(realPath, fileDto.getFile());
			try {imgData = ImageIO.read(f);} 
			catch (IOException e) {System.out.println("파일존재안함: " + f.getAbsolutePath() + " [" + e.getMessage() + "]");}
			if(imgData != null) fileDto.setImage(true);
		}
	}
	
	private int addContentFiles(List<MultipartFile> files, Long id) {
		if(files == null) return 0;
		for(MultipartFile e : files) {
			Contentfile file = upload2(e);
			if(file != null) { 
				file.setItem(itemRepository.findById(id).orElse(null)); 
				file = contentfileRepository.save(file); 
			}
		} return 1;
	}
	private Contentfile upload2(MultipartFile multipartFile) {
		Contentfile attachment = null;
		String originalFilename = multipartFile.getOriginalFilename();
		if(originalFilename == null || originalFilename.length() == 0) return null;
        String sourceName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        String fileName = sourceName;
        File file = new File(context.getRealPath(uploadDir) + File.separator + sourceName);
        if(file.exists()) {
        	int pos = fileName.lastIndexOf(".");
        	if(pos > -1) {
	        	String name = fileName.substring(0, pos);
	        	String ext = fileName.substring(pos + 1);
	        	fileName = name + "_" + System.currentTimeMillis() + "." + ext;
        	} else fileName += "_" + System.currentTimeMillis();
        }
		Path copyOfLocation = Paths.get(context.getRealPath(uploadDir) + File.separator + fileName);
        try {Files.copy(multipartFile.getInputStream(), copyOfLocation, StandardCopyOption.REPLACE_EXISTING);} 
        catch (IOException e) {e.printStackTrace();}	
        attachment = Contentfile.builder().file(fileName).source(sourceName).build();
		return attachment;
	}
	public void setContentImage(List<Contentfile> fileList) {
		String realPath = context.getRealPath(uploadDir);
		for(Contentfile fileDto : fileList) {
			BufferedImage imgData = null;
			File f = new File(realPath, fileDto.getFile());
			try {imgData = ImageIO.read(f);} 
			catch (IOException e) {System.out.println("파일존재안함: " + f.getAbsolutePath() + " [" + e.getMessage() + "]");}
			if(imgData != null) fileDto.setImage(true);
		}
	}
	private void deliFile(Itemfile file) {
		if (file == null) return;
		String saveDirectory = context.getRealPath(uploadDir);
		File f = new File(saveDirectory, file.getFile());
		if (f.exists()) f.delete();
	}
	private void delcFile(Contentfile file) {
		if (file == null) return;
		String saveDirectory = context.getRealPath(uploadDir);
		File f = new File(saveDirectory, file.getFile());
		if (f.exists()) f.delete();
	}
}
