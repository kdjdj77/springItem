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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.lec.spring.domain.Category;
import com.lec.spring.domain.Contentfile;
import com.lec.spring.domain.Item;
import com.lec.spring.domain.Itemfile;
import com.lec.spring.domain.Tag;
import com.lec.spring.domain.ajax.TagQryList;
import com.lec.spring.repository.CategoryRepository;
import com.lec.spring.repository.ContentfileRepository;
import com.lec.spring.repository.ItemRepository;
import com.lec.spring.repository.ItemfileRepository;
import com.lec.spring.repository.TagRepository;


@Service
public class ItemAdminService {
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private TagRepository tagRepository;
	@Autowired
	private ItemRepository itemRepository;
	@Autowired
	private ItemfileRepository itemfileRepository;
	@Autowired
	private ContentfileRepository contentfileRepository;
	@Value("${app.upload.path}")
	private String uploadDir;
	@Autowired
	ServletContext context;
	
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
	public int registerItem(String name, String category, String tag, Double discount, Double price, Long stock,
			String content, List<MultipartFile> ifile, List<MultipartFile> cfile) {
		Item item = Item.builder()
				.name(name)
				.category(categoryRepository.findById(Long.parseLong(category)).orElse(null))
				.tag(tagRepository.findById(Long.parseLong(tag)).orElse(null)).onsale(true)
				.discount(discount).price(price).stock(stock).content(content)
				.build();
		itemRepository.saveAndFlush(item);
		addItemFiles(ifile, item.getId());
		addContentFiles(cfile, item.getId());
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
}
