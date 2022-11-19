package com.lec.spring.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.lec.spring.domain.Authority;
import com.lec.spring.domain.Buy;
import com.lec.spring.domain.Cart;
import com.lec.spring.domain.Category;
import com.lec.spring.domain.Color;
import com.lec.spring.domain.Contentfile;
import com.lec.spring.domain.Item;
import com.lec.spring.domain.Itemfile;
import com.lec.spring.domain.Like;
import com.lec.spring.domain.Review;
import com.lec.spring.domain.Size;
import com.lec.spring.domain.Tag;
import com.lec.spring.domain.User;

@SpringBootTest
public class DummyData {

	@Autowired
	private AuthorityRepository authorityRepository;
	@Autowired
	private BuyRepository buyRepository;
	@Autowired
	private CartRepository cartRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private ColorRepository colorRepository;
	@Autowired
	private ItemfileRepository itemfileRepository;
	@Autowired
	private ContentfileRepository contentfileRepository;
	@Autowired
	private ItemRepository itemRepository;
	@Autowired
	private LikeRepository likeRepository;
	@Autowired
	private ReviewRepository reviewRepository;
	@Autowired
	private SizeRepository sizeRepository;
	@Autowired
	private TagRepository tagRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	@Test
	void init() {
		System.out.println("[init]");
		
		Authority auth_member = Authority.builder()
				.name("ROLE_MEMBER")
				.build();
		Authority auth_admin = Authority.builder()
				.name("ROLE_ADMIN")
				.build();
		
		auth_member = authorityRepository.saveAndFlush(auth_member);
		auth_admin = authorityRepository.saveAndFlush(auth_admin);
		
		User user1 = User.builder()
				.username("USER1")
//				.password("1234")  // 나중에 Security 적용하면 PasswordEncoder사용해야함.
				.password(passwordEncoder.encode("1234"))
				.name("회원1")
				.phonenum("01011111111")
				.email("111@gmail.com")
				.address("경기도 평택시")
				.point(200L)
				.build();		
		User user2 = User.builder()
				.username("USER2")
				.password(passwordEncoder.encode("1234"))
				.name("회원2")
				.phonenum("01022222222")
				.email("222@gmail.com")
				.address("경기도 하남시")
				.point(50L)
				.build();
		User admin = User.builder()
				.username("ADMIN")
				.password(passwordEncoder.encode("1234"))
				.name("관리자")
				.phonenum("01076767676")
				.email("asdf@gmail.com")
				.address("서울특별시 강서구")
				.point(400L)
				.build();
		
		user1.addAuthority(auth_member);
		user2.addAuthority(auth_member);
		admin.addAuthority(auth_admin);
		
		user1 = userRepository.save(user1);
		user2 = userRepository.save(user2);
		admin = userRepository.save(admin);
		
		userRepository.findAll().forEach(System.out::println);
		
		
		Item i1 = registerItem("후드 레이", true, 0D, "푹신해요", 27500D, 3L, 3L, 2D, 5L, 1L);
		Item i2 = registerItem("누빔데일리", true, 10D, "따뜻해요", 13000D, 5L, 3L, 3D, 9L, 1L);
		Item i3 = registerItem("터닝 도톰 케이블", true, 10D, "따뜻해요", 33000D, 23L, 3L, 5D, 3L, 6L);
		
		itemRepository.save(i1);
		itemRepository.save(i2);
		itemRepository.save(i3);
		
		Color c1 = Color.builder().item(i1).color("red").build();
		Color c2 = Color.builder().item(i1).color("blue").build();
		Color c3 = Color.builder().item(i1).color("purple").build();
		Color c4 = Color.builder().item(i2).color("green").build();
		Color c5 = Color.builder().item(i2).color("black").build();
		Color c6 = Color.builder().item(i2).color("white").build();
		Color c7 = Color.builder().item(i3).color("sky").build();
		Color c8 = Color.builder().item(i3).color("gray").build();
		Color c9 = Color.builder().item(i3).color("pink").build();
		
		colorRepository.save(c1);
		colorRepository.save(c2);
		colorRepository.save(c3);
		colorRepository.save(c4);
		colorRepository.save(c5);
		colorRepository.save(c6);
		colorRepository.save(c7);
		colorRepository.save(c8);
		colorRepository.save(c9);
		
		Size s1 = Size.builder().item(i1).name("M").build();
		Size s2 = Size.builder().item(i1).name("XL").build();
		Size s3 = Size.builder().item(i1).name("L").build();
		Size s4 = Size.builder().item(i2).name("250").build();
		Size s5 = Size.builder().item(i2).name("240").build();
		Size s6 = Size.builder().item(i2).name("260").build();
		Size s7 = Size.builder().item(i3).name("L").build();
		Size s8 = Size.builder().item(i3).name("XL").build();
		Size s9 = Size.builder().item(i3).name("M").build();
		
		sizeRepository.save(s1);
		sizeRepository.save(s2);
		sizeRepository.save(s3);
		sizeRepository.save(s4);
		sizeRepository.save(s5);
		sizeRepository.save(s6);
		sizeRepository.save(s7);
		sizeRepository.save(s8);
		sizeRepository.save(s9);
		
		Category cg1 = Category.builder().item(i1).name("상의").build();
		Category cg2 = Category.builder().item(i2).name("신발").build();
		Category cg3 = Category.builder().item(i3).name("하의").build();
		
		categoryRepository.save(cg1);
		categoryRepository.save(cg2);
		categoryRepository.save(cg3);
		
		Tag t1 = Tag.builder().category(cg1).name("반팔").build();
		Tag t2 = Tag.builder().category(cg1).name("후드티").build();
		Tag t3 = Tag.builder().category(cg1).name("아우터").build();
		Tag t4 = Tag.builder().category(cg2).name("운동").build();
		Tag t5 = Tag.builder().category(cg2).name("샌들").build();
		Tag t6 = Tag.builder().category(cg2).name("슬르피").build();
		Tag t7 = Tag.builder().category(cg3).name("청바지").build();
		Tag t8 = Tag.builder().category(cg3).name("반바지").build();
		Tag t9 = Tag.builder().category(cg3).name("면바지").build();
		
		tagRepository.save(t1);
		tagRepository.save(t2);
		tagRepository.save(t3);
		tagRepository.save(t4);
		tagRepository.save(t5);
		tagRepository.save(t6);
		tagRepository.save(t7);
		tagRepository.save(t8);
		tagRepository.save(t9);
		
		Itemfile if1 = Itemfile.builder().file("item1.jpg").source("item1.jpg").item(i1).build();
		Itemfile if2 = Itemfile.builder().file("item2.jpg").source("item2.jpg").item(i1).build();
		Itemfile if3 = Itemfile.builder().file("item3.jpg").source("item3.jpg").item(i1).build();
		Itemfile if4 = Itemfile.builder().file("item4.jpg").source("item4.jpg").item(i2).build();
		Itemfile if5 = Itemfile.builder().file("item5.jpg").source("item5.jpg").item(i2).build();
		Itemfile if6 = Itemfile.builder().file("item6.jpg").source("item6.jpg").item(i2).build();
		Itemfile if7 = Itemfile.builder().file("item7.jpg").source("item7.jpg").item(i3).build();
		Itemfile if8 = Itemfile.builder().file("item8.jpg").source("item8.jpg").item(i3).build();
		Itemfile if9 = Itemfile.builder().file("item9.jpg").source("item9.jpg").item(i3).build();
		
		itemfileRepository.save(if1);
		itemfileRepository.save(if2);
		itemfileRepository.save(if3);
		itemfileRepository.save(if4);
		itemfileRepository.save(if5);
		itemfileRepository.save(if6);
		itemfileRepository.save(if7);
		itemfileRepository.save(if8);
		itemfileRepository.save(if9);
		
		Contentfile cf1 = Contentfile.builder().file("contentitem1.jpg").source("contentitem1.jpg").item(i1).build();
		Contentfile cf2 = Contentfile.builder().file("contentitem2.jpg").source("contentitem2.jpg").item(i1).build();
		Contentfile cf3 = Contentfile.builder().file("contentitem3.jpg").source("contentitem3.jpg").item(i1).build();
		Contentfile cf4 = Contentfile.builder().file("contentitem4.jpg").source("contentitem4.jpg").item(i2).build();
		Contentfile cf5 = Contentfile.builder().file("contentitem5.jpg").source("contentitem5.jpg").item(i2).build();
		Contentfile cf6 = Contentfile.builder().file("contentitem6.jpg").source("contentitem6.jpg").item(i2).build();
		Contentfile cf7 = Contentfile.builder().file("contentitem7.jpg").source("contentitem7.jpg").item(i3).build();
		Contentfile cf8 = Contentfile.builder().file("contentitem8.jpg").source("contentitem8.jpg").item(i3).build();
		Contentfile cf9 = Contentfile.builder().file("contentitem9.jpg").source("contentitem9.jpg").item(i3).build();
		
		contentfileRepository.save(cf1);
		contentfileRepository.save(cf2);
		contentfileRepository.save(cf3);
		contentfileRepository.save(cf4);
		contentfileRepository.save(cf5);
		contentfileRepository.save(cf6);
		contentfileRepository.save(cf7);
		contentfileRepository.save(cf8);
		contentfileRepository.save(cf9);
		
		Review rv1 = Review.builder().user(user1).item(i1).content("이뻐요").star(2.0D).build();
		Review rv2 = Review.builder().user(user1).item(i1).content("좋아요").star(5.0D).build();
		Review rv3 = Review.builder().user(user1).item(i1).content("괜찮아요").star(3.0D).build();
		Review rv4 = Review.builder().user(user1).item(i2).content("나쁘지않아요").star(4.0D).build();
		Review rv5 = Review.builder().user(user1).item(i2).content("이뻐요").star(4.0D).build();
		Review rv6 = Review.builder().user(user1).item(i2).content("핏굿요").star(2.0D).build();
		Review rv7 = Review.builder().user(user1).item(i3).content("짱이에요").star(2.0D).build();
		Review rv8 = Review.builder().user(user1).item(i3).content("가격이싸요").star(1.0D).build();
		Review rv9 = Review.builder().user(user2).item(i3).content("쫌별로에요").star(3.0D).build();
	
		Review rv10 = Review.builder().user(user2).item(i1).content("짱이에요").star(2.0D).build();
		Review rv11 = Review.builder().user(user2).item(i1).content("가격이싸요").star(1.0D).build();
		Review rv12 = Review.builder().user(user2).item(i1).content("쫌별로에요").star(3.0D).build();
		Review rv13 = Review.builder().user(user2).item(i2).content("짱이에요").star(2.0D).build();
		Review rv14 = Review.builder().user(user2).item(i2).content("가격이싸요").star(1.0D).build();
		Review rv15 = Review.builder().user(user2).item(i2).content("쫌별로에요").star(3.0D).build();
		Review rv16 = Review.builder().user(user2).item(i3).content("짱이에요").star(2.0D).build();
		Review rv17 = Review.builder().user(user2).item(i3).content("가격이싸요").star(1.0D).build();
		Review rv18 = Review.builder().user(user2).item(i3).content("쫌별로에요").star(3.0D).build();
	
		reviewRepository.save(rv1);
		reviewRepository.save(rv2);
		reviewRepository.save(rv3);
		reviewRepository.save(rv4);
		reviewRepository.save(rv5);
		reviewRepository.save(rv6);
		reviewRepository.save(rv7);
		reviewRepository.save(rv8);
		reviewRepository.save(rv9);
		reviewRepository.save(rv10);
		reviewRepository.save(rv11);
		reviewRepository.save(rv12);
		reviewRepository.save(rv13);
		reviewRepository.save(rv14);
		reviewRepository.save(rv15);
		reviewRepository.save(rv16);
		reviewRepository.save(rv17);
		reviewRepository.save(rv18);
		
		Cart ct1 = Cart.builder().user(user1).item(i1).count(2L).build();
		Cart ct2 = Cart.builder().user(user1).item(i2).count(4L).build();
		Cart ct3 = Cart.builder().user(user1).item(i3).count(1L).build();
		Cart ct4 = Cart.builder().user(user2).item(i1).count(3L).build();
		Cart ct5 = Cart.builder().user(user2).item(i2).count(5L).build();
		Cart ct6 = Cart.builder().user(user2).item(i3).count(7L).build();
		
		cartRepository.save(ct1);
		cartRepository.save(ct2);
		cartRepository.save(ct3);
		cartRepository.save(ct4);
		cartRepository.save(ct5);
		cartRepository.save(ct6);
		
		Buy b1 = Buy.builder().user(user1).item(i1).count(1L).build();
		Buy b2 = Buy.builder().user(user1).item(i2).count(2L).build();
		Buy b3 = Buy.builder().user(user1).item(i3).count(1L).build();
		Buy b4 = Buy.builder().user(user2).item(i1).count(2L).build();
		Buy b5 = Buy.builder().user(user2).item(i2).count(3L).build();
		Buy b6 = Buy.builder().user(user2).item(i3).count(5L).build();
		buyRepository.save(b1);
		buyRepository.save(b2);
		buyRepository.save(b3);
		buyRepository.save(b4);
		buyRepository.save(b5);
		buyRepository.save(b6);
		
		Like l1 = Like.builder().user(user1).item(i1).build();
		Like l2 = Like.builder().user(user1).item(i2).build();
		Like l3 = Like.builder().user(user2).item(i1).build();
		Like l4 = Like.builder().user(user2).item(i3).build();
		
		likeRepository.save(l1);
		likeRepository.save(l2);
		likeRepository.save(l3);
		likeRepository.save(l4);
	}
	public static Item registerItem(String q, Boolean w, Double e, String r, Double t, Long y, Long u, Double i, Long o, Long p) {
		Item a = Item.builder().name(q).onsale(w).discount(e).content(r).price(t).stock(y).reviewCnt(u).avgstar(i).sell(o).like(p).build();
		return a;
	}
}
