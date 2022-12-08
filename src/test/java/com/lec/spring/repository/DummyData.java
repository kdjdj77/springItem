package com.lec.spring.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.lec.spring.domain.Authority;
import com.lec.spring.domain.Buy;
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
	private TagRepository tagRepository;
	@Autowired
	private ItemRepository itemRepository;
	@Autowired
	private LikeRepository likeRepository;
	@Autowired
	private ReviewRepository reviewRepository;
	@Autowired
	private SizeRepository sizeRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	@Test
	void init() {
		System.out.println("[init]");
// 권한
		Authority auth_member = Authority.builder().name("ROLE_MEMBER").build();
		Authority auth_admin = Authority.builder().name("ROLE_ADMIN").build();
		
		auth_member = authorityRepository.saveAndFlush(auth_member);
		auth_admin = authorityRepository.saveAndFlush(auth_admin);
// 유저
		User user1 = User.builder()
				.username("USER1").password(passwordEncoder.encode("1234"))
				.name("회원1").phonenum("01011111111").email("111@gmail.com")
				.address1("64841").address2("경기도 평택시").address3("101-1503").point(200L).build();		
		User user2 = User.builder()
				.username("USER2").password(passwordEncoder.encode("1234"))
				.name("회원2").phonenum("01022222222").email("222@gmail.com")
				.address1("46815").address2("경기도 하남시").address3("103-203").point(50L).build();
		User admin = User.builder()
				.username("ADMIN").password(passwordEncoder.encode("1234"))
				.name("관리자").phonenum("01076767676").email("asdf@gmail.com")
				.address1("98754").address2("서울특별시 강서구").address3("107-1105").point(400L).build();
		
		user1.addAuthority(auth_member); user2.addAuthority(auth_member); admin.addAuthority(auth_admin);
		user1 = userRepository.save(user1); user2 = userRepository.save(user2); 
		admin = userRepository.save(admin);
// 카테고리		
		Category cg1 = Category.builder().name("아우터").build(); cg1 = categoryRepository.save(cg1);
		Category cg2 = Category.builder().name("상의").build(); cg2 = categoryRepository.save(cg2);
		Category cg5 = Category.builder().name("셔츠/블라우스").build(); cg5 = categoryRepository.save(cg5);
		Category cg6 = Category.builder().name("트레이닝").build(); cg6 = categoryRepository.save(cg6);
		Category cg7 = Category.builder().name("베이직").build(); cg7 = categoryRepository.save(cg7);
		Category cg8 = Category.builder().name("원피스").build(); cg8 = categoryRepository.save(cg8);
		Category cg9 = Category.builder().name("스커트").build(); cg9 = categoryRepository.save(cg9);
		Category cg3 = Category.builder().name("팬츠").build(); cg3 = categoryRepository.save(cg3);
		Category cg10 = Category.builder().name("가방").build(); cg10 = categoryRepository.save(cg10);
		Category cg4 = Category.builder().name("신발").build(); cg4 = categoryRepository.save(cg4);
		Category cg11 = Category.builder().name("악세사리").build(); cg11 = categoryRepository.save(cg11);
// 태그
		Tag t1 = Tag.builder().category(cg1).name("가디건/조끼").build(); t1 = tagRepository.save(t1);
		Tag t2 = Tag.builder().category(cg1).name("야상/점퍼").build(); t2 = tagRepository.save(t2);
		Tag t3 = Tag.builder().category(cg1).name("자켓/코트").build(); t3 = tagRepository.save(t3);
		Tag t4 = Tag.builder().category(cg1).name("패딩").build(); t4 = tagRepository.save(t4);
		Tag t5 = Tag.builder().category(cg1).name("플리스").build(); t5 = tagRepository.save(t5);
		
		Tag t6 = Tag.builder().category(cg2).name("긴팔티셔츠").build(); t6 = tagRepository.save(t6);
		Tag t7 = Tag.builder().category(cg2).name("맨투맨").build(); t7 = tagRepository.save(t7);
		Tag t8 = Tag.builder().category(cg2).name("후드").build(); t8 = tagRepository.save(t8);
		Tag t9 = Tag.builder().category(cg2).name("반팔/민소매티셔츠").build(); t9 = tagRepository.save(t9);
		Tag t10 = Tag.builder().category(cg2).name("니트").build(); t10 = tagRepository.save(t10);
		
		Tag t11 = Tag.builder().category(cg3).name("청바지").build(); t11 = tagRepository.save(t11);
		Tag t12 = Tag.builder().category(cg3).name("롱팬츠").build(); t12 = tagRepository.save(t12);
		Tag t13 = Tag.builder().category(cg3).name("면바지").build(); t13 = tagRepository.save(t13);
		Tag t14 = Tag.builder().category(cg3).name("슬랙스").build(); t14 = tagRepository.save(t14);
		Tag t15 = Tag.builder().category(cg3).name("레깅스").build(); t15 = tagRepository.save(t15);
		Tag t16 = Tag.builder().category(cg3).name("숏팬츠").build(); t16 = tagRepository.save(t16);
		
		Tag t17 = Tag.builder().category(cg4).name("운동화/단화").build(); t17 = tagRepository.save(t17);
		Tag t18 = Tag.builder().category(cg4).name("구두/워커").build(); t18 = tagRepository.save(t18);
		Tag t19 = Tag.builder().category(cg4).name("샌들/슬리퍼/장화").build(); t19 = tagRepository.save(t19);
		
		Tag t20 = Tag.builder().category(cg5).name(cg5.getName()).build(); t20 = tagRepository.save(t20);
		Tag t21 = Tag.builder().category(cg6).name(cg6.getName()).build(); t21 = tagRepository.save(t21);
		Tag t22 = Tag.builder().category(cg7).name(cg7.getName()).build(); t22 = tagRepository.save(t22);
		Tag t23 = Tag.builder().category(cg8).name(cg8.getName()).build(); t23 = tagRepository.save(t23);
		Tag t24 = Tag.builder().category(cg9).name(cg9.getName()).build(); t24 = tagRepository.save(t24);
		
		Tag t25 = Tag.builder().category(cg10).name("백팩/스쿨백").build(); t25 = tagRepository.save(t25);
		Tag t26 = Tag.builder().category(cg10).name("크로스/토트백").build(); t26 = tagRepository.save(t26);
		
		Tag t27 = Tag.builder().category(cg11).name("주얼리").build(); t27 = tagRepository.save(t27);
		Tag t28 = Tag.builder().category(cg11).name("모자/벨트").build(); t28 = tagRepository.save(t28);
		Tag t29 = Tag.builder().category(cg11).name("양말/스타킹").build(); t29 = tagRepository.save(t29);
// 상품
		Item i1 = Item.builder()
				.name("후드 레이어드 패딩점퍼").discount(1D).content("푹신해요").price(27500L)
				.stock(3L).reviewcnt(3L).avgstar(4.6D).sell(5L).likecnt(3L).tag(t8)
				.category(t8.getCategory()).build();
		Item i2 = Item.builder()
				.name("누빔 데일리 캐주얼 박시 패딩").discount(10D).content("따뜻해요").price(13000L)
				.stock(5L).reviewcnt(3L).avgstar(4.3D).sell(9L).likecnt(2L).tag(t4)
				.category(t4.getCategory()).build();
		Item i3 = Item.builder()
				.name("터닝 도톰 케이블 니트 집업 가디건").discount(10D).content("따뜻해요").price(33000L)
				.stock(23L).reviewcnt(3L).avgstar(3.5D).sell(3L).likecnt(1L).tag(t10)
				.category(t10.getCategory()).build();
		
		i1 = itemRepository.save(i1); i2 = itemRepository.save(i2); i3 = itemRepository.save(i3);
// 색상		
		Color c1 = Color.builder().item(i1).name("red").build(); colorRepository.save(c1);
		Color c2 = Color.builder().item(i1).name("blue").build(); colorRepository.save(c2);
		Color c3 = Color.builder().item(i1).name("purple").build(); colorRepository.save(c3);
		Color c4 = Color.builder().item(i2).name("green").build(); colorRepository.save(c4);
		Color c5 = Color.builder().item(i2).name("black").build(); colorRepository.save(c5);
		Color c6 = Color.builder().item(i2).name("white").build(); colorRepository.save(c6);
		Color c7 = Color.builder().item(i3).name("sky").build(); colorRepository.save(c7);
		Color c8 = Color.builder().item(i3).name("gray").build(); colorRepository.save(c8);
		Color c9 = Color.builder().item(i3).name("pink").build(); colorRepository.save(c9);
// 사이즈		
		Size s1 = Size.builder().item(i1).name("M").build(); sizeRepository.save(s1);
		Size s2 = Size.builder().item(i1).name("XL").build(); sizeRepository.save(s2);
		Size s3 = Size.builder().item(i1).name("L").build(); sizeRepository.save(s3);
		Size s4 = Size.builder().item(i2).name("250").build(); sizeRepository.save(s4);
		Size s5 = Size.builder().item(i2).name("240").build(); sizeRepository.save(s5);
		Size s6 = Size.builder().item(i2).name("260").build(); sizeRepository.save(s6);
		Size s7 = Size.builder().item(i3).name("L").build(); sizeRepository.save(s7);
		Size s8 = Size.builder().item(i3).name("XL").build(); sizeRepository.save(s8);
		Size s9 = Size.builder().item(i3).name("M").build(); sizeRepository.save(s9);
// 상품이미지
		Itemfile if1 = Itemfile.builder().file("item1.jpg").source("item1.jpg").item(i1).build();
		Itemfile if2 = Itemfile.builder().file("item2.jpg").source("item2.jpg").item(i1).build();
		Itemfile if3 = Itemfile.builder().file("item3.jpg").source("item3.jpg").item(i1).build();
		Itemfile if4 = Itemfile.builder().file("item4.jpg").source("item4.jpg").item(i2).build();
		Itemfile if5 = Itemfile.builder().file("item5.jpg").source("item5.jpg").item(i2).build();
		Itemfile if6 = Itemfile.builder().file("item6.jpg").source("item6.jpg").item(i2).build();
		Itemfile if7 = Itemfile.builder().file("item7.jpg").source("item7.jpg").item(i3).build();
		Itemfile if8 = Itemfile.builder().file("item8.jpg").source("item8.jpg").item(i3).build();
		Itemfile if9 = Itemfile.builder().file("item9.jpg").source("item9.jpg").item(i3).build();
		
		itemfileRepository.save(if1); itemfileRepository.save(if2); itemfileRepository.save(if3);
		itemfileRepository.save(if4); itemfileRepository.save(if5); itemfileRepository.save(if6);
		itemfileRepository.save(if7); itemfileRepository.save(if8); itemfileRepository.save(if9);
// 상품내용이미지		
		Contentfile cf1 = Contentfile.builder().file("contentitem1.jpg").source("contentitem1.jpg").item(i1).build();
		Contentfile cf2 = Contentfile.builder().file("contentitem2.jpg").source("contentitem2.jpg").item(i1).build();
		Contentfile cf3 = Contentfile.builder().file("contentitem3.jpg").source("contentitem3.jpg").item(i1).build();
		Contentfile cf4 = Contentfile.builder().file("contentitem4.jpg").source("contentitem4.jpg").item(i2).build();
		Contentfile cf5 = Contentfile.builder().file("contentitem5.jpg").source("contentitem5.jpg").item(i2).build();
		Contentfile cf6 = Contentfile.builder().file("contentitem6.jpg").source("contentitem6.jpg").item(i2).build();
		Contentfile cf7 = Contentfile.builder().file("contentitem7.jpg").source("contentitem7.jpg").item(i3).build();
		Contentfile cf8 = Contentfile.builder().file("contentitem8.jpg").source("contentitem8.jpg").item(i3).build();
		Contentfile cf9 = Contentfile.builder().file("contentitem9.jpg").source("contentitem9.jpg").item(i3).build();
		
		contentfileRepository.save(cf1); contentfileRepository.save(cf2); contentfileRepository.save(cf3); 
		contentfileRepository.save(cf4); contentfileRepository.save(cf5); contentfileRepository.save(cf6);
		contentfileRepository.save(cf7); contentfileRepository.save(cf8); contentfileRepository.save(cf9);
// 댓글
		User[] us = {user1, user1, user1, user1, user1, user1, user1, user1, user2, user2,
					user2, user2, user2, user2, user2, user2, user2, user2};
		Item[] it = {i1, i1, i1, i2, i2, i2, i3, i3, i3, i1, 
					i1, i1, i2, i2, i2, i3, i3, i3};
		String[] co = {
				"소라색 러버들에게 강력 추천하는 패딩이에요!! 안에 하얀색 후드집업과 붙어있어서 훨씬 스타일리쉬하게 코디 가능하답니다:) 색감 진짜 예뻐서 더 추워지면 겨울 내내 착용하고 다닐 것 같아요! 핏도 색감도 정말 인생 패딩이라 만족하는 제품이랍니다;)",
				"뒷쪽 후드도 있는 귀여운 패딩 점퍼! :)\r\n부해보여서 어깡만들어주는 패딩이 많은데 요 아이는 그렇지 않아서 너무 좋았어요ㅠㅠ 잠구거나 풀어서 착용해도 모두 핏 예쁘고 후드도 있어서 추울 때 딱입니당 ♥",
				"숏패딩 정말 갖고 싶었는데 드디어ㅜ♡ 갖게 됐네요 패딩 구매하면서 내심 부해보이지는 않을까?,따뜻할까? 걱정 많이 했는데 전혀 부해보이지 않고 따뜻함을 넘어서 어쩔때는 실내에선 덥다 느낄 정도로 따뜻합니다ㅎㅎ 진짜 너무 만족스러운 패딩이에요",
				"입고지연 인한 배송 늦게 왔습니다 집에 없어서 주문했어요 그리고 한번 입어봤는데 사이즈 잘 맞아요 ! 팔이 들었는데 작지 않네요 너무 좋아요 ㅋㅋㅋ",
				"조아요 ㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎ",
				"외출할 때 가볍게 입을 누빔 패딩을 찾다 발견했어요! 사진이랑 똑같고, 제가 상체에 살이 없는 편이라 약간 넉넉하게 입고 있는데도 부해보이지 않아서 좋아요 ㅎㅎ 목이 허전해서 목도리까지 매면 이번 겨울 따뜻하게 보낼 수 있을 것 같아요!",
				"후드집업은 너무 편해보이고 코트는 너무 포멀해서 애매할때 입기 좋은 아우터에요! 손목과 밑단이 쫀쫀해서 아방한 핏잡기 좋아요! 지퍼 올리는 정도에 따라서 카라넥이나 목폴라로 연출할 수 있는점이 좋아용",
				"스쿨룩으로 딱 좋은 니트 집업 가디건이랍니다! 도톰하지만 무겁지 않아서 좋아요 ~ 그레이 색상이라 무난해서 어느 하의든 잘 어울리는 것 같아요! :)",
				"옷에서 비닐을 뜯음과 동시에 공장? 냄새가 좀 심했습니다 ㅠㅠ 그러나 다른 쇼핑몰보다 싼 가격에 구매한 것 같아 만족합니다!! 그리고 가디건 자체는 두툼해서 좋았습니다!",
				"짱임버요!! 데일리로 좋아요 색상도 원하던색이에요 ㅎㅎ 고민하시는 분들 언능 사세요!!!!! 후드있어서 너무 좋아요",
				"색감 넘예.. 후드티 레이어드 된 거 너무 이뻐요!! 코디하기도 너무 편하고 아무거나 입어도 잘 어울려서 좋아요ㅎㅎ 앞으로 자주 입을 것 같아요!",
				"세상에!엄청 도톰따뜻해요! 색깔도 너무너무 마음에 들고 엄마도 잘샀다고 하시네요! 진짜 엄청 마음에들어요! 블랙도 샀는데 두개사길 잘했네요!",
				"가볍고 따뜻해요~저번주인가 살짝 추웠을때 얇은 옷위에 걸치고 나갔는데 하나도 안추웠어요~^^추워지면 목폴라입고 입으면 거뜬할 거 같아요~~",
				"사이즈를 고민하다가 L사이즈햇는데요 더큰사이즈 시켯어도 괜찬앗을거갇아요 화면에서보는거랑 실물이랑똑같습니다 옷두 따뜻하고 핏도이뻐요 화면에서보는거랑 실제로입어보니 생각햇던것보다 더 따뜻한거같아요 다른컬러들도 탐나네요 포장 이쁘게해주셔서감사합니다",
				"그렇게 두껍지도 않고 또 그렇게 얇지도 않은 그런 아우터입니다 간편하게 입기 너무 좋아요 !! 이너만 다르게 입으면 가볍게도, 엄청 따뜻하게도 입을 수 있어요 요즘 맨날 입고 다닙니다 ㅎㅎ 사이즈도 너무 좋아용",
				"옷이 꽤 도톰해서 안에 목폴라티입고 하면 단독으로 입을 수도 있겠다는 생각을 했어요 그리고 약간 오버핏일줄 알았는데 생각보다 팔이나 총기장이 딱 맞아요 그래도 원했던 디자인이라 잘입으려고요!!",
				"스타일도 이쁜데 따뜻하기까지 해용~ 매일 입고 다녀요 ㅋㅋㅋ",
				"예쁘고 가격대비 짱입니다",
		};
		Double[] st = {2D, 5D, 3D, 4D, 4D, 2D, 2D, 1D, 3D, 2D,
						1D, 3D, 2D, 1D, 3D, 2D, 1D, 3D};
		for(int i = 0; i < us.length; i++) {
			Review rv = Review.builder().user(us[i]).item(it[i]).content(co[i]).star(st[i]).build();
			reviewRepository.save(rv);
		}
// 좋아요
		Like l1 = Like.builder().user(user1).item(i1).build(); likeRepository.save(l1);
		Like l2 = Like.builder().user(user2).item(i1).build(); likeRepository.save(l2);
		Like l3 = Like.builder().user(admin).item(i1).build(); likeRepository.save(l3);
		Like l4 = Like.builder().user(user1).item(i2).build(); likeRepository.save(l4);
		Like l5 = Like.builder().user(user2).item(i2).build(); likeRepository.save(l5);
		Like l6 = Like.builder().user(user1).item(i3).build(); likeRepository.save(l6);
// 구매내역
		Buy b1 = Buy.builder().user(user1).item(i1).count(1L).color(c1)
				.size(s1).build(); buyRepository.save(b1);
		Buy b2 = Buy.builder().user(user1).item(i2).count(2L).color(c4)
				.size(s4).build(); buyRepository.save(b2);
		Buy b3 = Buy.builder().user(user1).item(i3).count(1L).color(c7)
				.size(s7).build(); buyRepository.save(b3);
		Buy b4 = Buy.builder().user(user2).item(i1).count(2L).color(c1)
				.size(s1).build(); buyRepository.save(b4);
		Buy b5 = Buy.builder().user(user2).item(i2).count(3L).color(c4)
				.size(s4).build(); buyRepository.save(b5);
		Buy b6 = Buy.builder().user(user2).item(i3).count(5L).color(c7)
				.size(s7).build(); buyRepository.save(b6);
		
// 상품 (추가)
		Item i4 = Item.builder()
				.name("키아나 심플 카라 기모 맨투맨").discount(11D).content("상품소개").price(27200L)
				.stock(30L).tag(t7).category(t7.getCategory()).build();
		Item i5 = Item.builder()
				.name("주디 기모 반집업 카라넥 크롭 맨투맨").discount(10D).content("상품소개").price(13100L)
				.stock(35L).tag(t7).category(t7.getCategory()).build();
		Item i6 = Item.builder()
				.name("캔디 체크 플리스 맨투맨").discount(15D).content("상품소개").price(33500L)
				.stock(23L).tag(t7).category(t7.getCategory()).build();
		Item i7 = Item.builder()
				.name("자수 기모 박시핏 맨투맨").discount(13D).content("상품소개").price(27600L)
				.stock(38L).tag(t7).category(t7.getCategory()).build();
		Item i8 = Item.builder()
				.name("시그널 반집업 세미크롭 맨투맨").discount(20D).content("상품소개").price(13800L)
				.stock(24L).tag(t7).category(t7.getCategory()).build();
		Item i9 = Item.builder()
				.name("매니큐어 레터링 양기모 맨투맨").discount(13D).content("상품소개").price(43000L)
				.stock(27L).tag(t7).category(t7.getCategory()).build();
		Item i10 = Item.builder()
				.name("하티 레터링 기모 반집업").discount(5D).content("상품소개").price(37500L)
				.stock(15L).tag(t7).category(t7.getCategory()).build();
		Item i11 = Item.builder()
				.name("산타모니카 기모 레터링 맨투맨").discount(10D).content("상품소개").price(23000L)
				.stock(34L).tag(t7).category(t7.getCategory()).build();
		Item i12 = Item.builder()
				.name("레이지 레터링 기모 맨투맨").discount(15D).content("상품소개").price(13000L)
				.stock(27L).tag(t7).category(t7.getCategory()).build();
		Item i13 = Item.builder()
				.name("(기모)헌팅턴 레터링 맨투맨").discount(12D).content("상품소개").price(27900L)
				.stock(16L).tag(t7).category(t7.getCategory()).build();
		Item i14 = Item.builder()
				.name("프랑스 레터링 양기모 맨투맨").discount(7D).content("상품소개").price(16000L)
				.stock(19L).tag(t7).category(t7.getCategory()).build();
		Item i15 = Item.builder()
				.name("빌리지 레터링 프린팅 맨투맨").discount(10D).content("상품소개").price(18000L)
				.stock(24L).tag(t7).category(t7.getCategory()).build();
		Item i16 = Item.builder()
				.name("가브리엘 레터링 박시핏 맨투맨").discount(15D).content("상품소개").price(12500L)
				.stock(20L).tag(t7).category(t7.getCategory()).build();
		Item i17 = Item.builder()
				.name("헤이브 크롭 플리스 맨투맨").discount(20D).content("상품소개").price(13700L)
				.stock(36L).tag(t7).category(t7.getCategory()).build();
		Item i18 = Item.builder()
				.name("(양기모)캐나다 데일리 세미크롭 맨투맨").discount(10D).content("상품소개").price(33800L)
				.stock(18L).tag(t7).category(t7.getCategory()).build();
		Item i19 = Item.builder()
				.name("(9컬러)나인 오버핏 베이직 무지 맨투맨").discount(16D).content("상품소개").price(36800L)
				.stock(21L).tag(t7).category(t7.getCategory()).build();
		Item i20 = Item.builder()
				.name("(기모추가)피어스 캐주얼 심플 레터링 맨투맨").discount(5D).content("상품소개").price(23800L)
				.stock(22L).tag(t7).category(t7.getCategory()).build();
		Item i21 = Item.builder()
				.name("코넬 배색 기모 맨투맨").discount(10D).content("상품소개").price(13800L)
				.stock(15L).tag(t7).category(t7.getCategory()).build();
		
		i4 = itemRepository.save(i4); 	i5 = itemRepository.save(i5); 	i6 = itemRepository.save(i6);
		i7 = itemRepository.save(i7); 	i8 = itemRepository.save(i8); 	i9 = itemRepository.save(i9);
		i10 = itemRepository.save(i10); i11 = itemRepository.save(i11); i12 = itemRepository.save(i12);
		i13 = itemRepository.save(i13); i14 = itemRepository.save(i14); i15 = itemRepository.save(i15);
		i16 = itemRepository.save(i16); i17 = itemRepository.save(i17); i18 = itemRepository.save(i18);
		i19 = itemRepository.save(i19); i20 = itemRepository.save(i20); i21 = itemRepository.save(i21);
		
		Color c10 = Color.builder().item(i4).name("red").build(); colorRepository.save(c10);
		Color c11 = Color.builder().item(i5).name("blue").build(); colorRepository.save(c11);
		Color c12 = Color.builder().item(i6).name("purple").build(); colorRepository.save(c12);
		Color c13 = Color.builder().item(i7).name("green").build(); colorRepository.save(c13);
		Color c14 = Color.builder().item(i8).name("black").build(); colorRepository.save(c14);
		Color c15 = Color.builder().item(i9).name("white").build(); colorRepository.save(c15);
		Color c16 = Color.builder().item(i10).name("sky").build(); colorRepository.save(c16);
		Color c17 = Color.builder().item(i11).name("gray").build(); colorRepository.save(c17);
		Color c18 = Color.builder().item(i12).name("pink").build(); colorRepository.save(c18);
		Color c19 = Color.builder().item(i13).name("red").build(); colorRepository.save(c19);
		Color c20 = Color.builder().item(i14).name("blue").build(); colorRepository.save(c20);
		Color c21 = Color.builder().item(i15).name("purple").build(); colorRepository.save(c21);
		Color c22 = Color.builder().item(i16).name("green").build(); colorRepository.save(c22);
		Color c23 = Color.builder().item(i17).name("black").build(); colorRepository.save(c23);
		Color c24 = Color.builder().item(i18).name("white").build(); colorRepository.save(c24);
		Color c25 = Color.builder().item(i19).name("green").build(); colorRepository.save(c25);
		Color c26 = Color.builder().item(i20).name("black").build(); colorRepository.save(c26);
		Color c27 = Color.builder().item(i21).name("white").build(); colorRepository.save(c27);
	
		Size s10 = Size.builder().item(i4).name("ONE SIZE").build(); sizeRepository.save(s10);
		Size s11 = Size.builder().item(i5).name("ONE SIZE").build(); sizeRepository.save(s11);
		Size s12 = Size.builder().item(i6).name("ONE SIZE").build(); sizeRepository.save(s12);
		Size s13 = Size.builder().item(i7).name("ONE SIZE").build(); sizeRepository.save(s13);
		Size s14 = Size.builder().item(i8).name("ONE SIZE").build(); sizeRepository.save(s14);
		Size s15 = Size.builder().item(i9).name("ONE SIZE").build(); sizeRepository.save(s15);
		Size s16 = Size.builder().item(i10).name("ONE SIZE").build(); sizeRepository.save(s16);
		Size s17 = Size.builder().item(i11).name("ONE SIZE").build(); sizeRepository.save(s17);
		Size s18 = Size.builder().item(i12).name("ONE SIZE").build(); sizeRepository.save(s18);
		Size s19 = Size.builder().item(i13).name("ONE SIZE").build(); sizeRepository.save(s19);
		Size s20 = Size.builder().item(i14).name("ONE SIZE").build(); sizeRepository.save(s20);
		Size s21 = Size.builder().item(i15).name("ONE SIZE").build(); sizeRepository.save(s21);
		Size s22 = Size.builder().item(i16).name("ONE SIZE").build(); sizeRepository.save(s22);
		Size s23 = Size.builder().item(i17).name("ONE SIZE").build(); sizeRepository.save(s23);
		Size s24 = Size.builder().item(i18).name("ONE SIZE").build(); sizeRepository.save(s24);
		Size s25 = Size.builder().item(i19).name("ONE SIZE").build(); sizeRepository.save(s25);
		Size s26 = Size.builder().item(i20).name("ONE SIZE").build(); sizeRepository.save(s26);
		Size s27 = Size.builder().item(i21).name("ONE SIZE").build(); sizeRepository.save(s27);

		Itemfile if10 = Itemfile.builder().file("item10.jpg").source("item10.jpg").item(i4).build();
		Itemfile if11 = Itemfile.builder().file("item11.jpg").source("item11.jpg").item(i5).build();
		Itemfile if12 = Itemfile.builder().file("item12.jpg").source("item12.jpg").item(i6).build();
		Itemfile if13 = Itemfile.builder().file("item13.jpg").source("item13.jpg").item(i7).build();
		Itemfile if14 = Itemfile.builder().file("item14.jpg").source("item14.jpg").item(i8).build();
		Itemfile if15 = Itemfile.builder().file("item15.jpg").source("item15.jpg").item(i9).build();
		Itemfile if16 = Itemfile.builder().file("item16.jpg").source("item16.jpg").item(i10).build();
		Itemfile if17 = Itemfile.builder().file("item17.jpg").source("item17.jpg").item(i11).build();
		Itemfile if18 = Itemfile.builder().file("item18.jpg").source("item18.jpg").item(i12).build();
		Itemfile if19 = Itemfile.builder().file("item19.jpg").source("item19.jpg").item(i13).build();
		Itemfile if20 = Itemfile.builder().file("item20.jpg").source("item20.jpg").item(i14).build();
		Itemfile if21 = Itemfile.builder().file("item21.jpg").source("item21.jpg").item(i15).build();
		Itemfile if22 = Itemfile.builder().file("item22.jpg").source("item22.jpg").item(i16).build();
		Itemfile if23 = Itemfile.builder().file("item23.jpg").source("item23.jpg").item(i17).build();
		Itemfile if24 = Itemfile.builder().file("item24.jpg").source("item24.jpg").item(i18).build();
		Itemfile if25 = Itemfile.builder().file("item25.jpg").source("item25.jpg").item(i19).build();
		Itemfile if26 = Itemfile.builder().file("item26.jpg").source("item26.jpg").item(i20).build();
		Itemfile if27 = Itemfile.builder().file("item27.jpg").source("item27.jpg").item(i21).build();
		
		itemfileRepository.save(if10); itemfileRepository.save(if11); itemfileRepository.save(if12);
		itemfileRepository.save(if13); itemfileRepository.save(if14); itemfileRepository.save(if15);
		itemfileRepository.save(if16); itemfileRepository.save(if17); itemfileRepository.save(if18);
		itemfileRepository.save(if19); itemfileRepository.save(if20); itemfileRepository.save(if21);
		itemfileRepository.save(if22); itemfileRepository.save(if23); itemfileRepository.save(if24);
		itemfileRepository.save(if25); itemfileRepository.save(if26); itemfileRepository.save(if27);
	
		Contentfile cf10 = Contentfile.builder().file("contentitem10.jpg").source("contentitem10.jpg").item(i4).build();
		Contentfile cf11 = Contentfile.builder().file("contentitem11.jpg").source("contentitem11.jpg").item(i5).build();
		Contentfile cf12 = Contentfile.builder().file("contentitem12.jpg").source("contentitem12.jpg").item(i6).build();
		Contentfile cf13 = Contentfile.builder().file("contentitem13.jpg").source("contentitem13.jpg").item(i7).build();
		Contentfile cf14 = Contentfile.builder().file("contentitem14.jpg").source("contentitem14.jpg").item(i8).build();
		Contentfile cf15 = Contentfile.builder().file("contentitem15.jpg").source("contentitem15.jpg").item(i9).build();
		Contentfile cf16 = Contentfile.builder().file("contentitem16.jpg").source("contentitem16.jpg").item(i10).build();
		Contentfile cf17 = Contentfile.builder().file("contentitem17.jpg").source("contentitem17.jpg").item(i11).build();
		Contentfile cf18 = Contentfile.builder().file("contentitem18.jpg").source("contentitem18.jpg").item(i12).build();
		Contentfile cf19 = Contentfile.builder().file("contentitem19.jpg").source("contentitem19.jpg").item(i13).build();
		Contentfile cf20 = Contentfile.builder().file("contentitem20.jpg").source("contentitem20.jpg").item(i14).build();
		Contentfile cf21 = Contentfile.builder().file("contentitem21.jpg").source("contentitem21.jpg").item(i15).build();
		Contentfile cf22 = Contentfile.builder().file("contentitem22.jpg").source("contentitem22.jpg").item(i16).build();
		Contentfile cf23 = Contentfile.builder().file("contentitem23.jpg").source("contentitem23.jpg").item(i17).build();
		Contentfile cf24 = Contentfile.builder().file("contentitem24.jpg").source("contentitem24.jpg").item(i18).build();
		Contentfile cf25 = Contentfile.builder().file("contentitem25.jpg").source("contentitem25.jpg").item(i19).build();
		Contentfile cf26 = Contentfile.builder().file("contentitem26.jpg").source("contentitem26.jpg").item(i20).build();
		Contentfile cf27 = Contentfile.builder().file("contentitem27.jpg").source("contentitem27.jpg").item(i21).build();
		
		contentfileRepository.save(cf10); contentfileRepository.save(cf11); contentfileRepository.save(cf12); 
		contentfileRepository.save(cf13); contentfileRepository.save(cf14); contentfileRepository.save(cf15);
		contentfileRepository.save(cf16); contentfileRepository.save(cf17); contentfileRepository.save(cf18);
		contentfileRepository.save(cf19); contentfileRepository.save(cf20); contentfileRepository.save(cf21); 
		contentfileRepository.save(cf22); contentfileRepository.save(cf23); contentfileRepository.save(cf24);
		contentfileRepository.save(cf25); contentfileRepository.save(cf26); contentfileRepository.save(cf27);
	}
}