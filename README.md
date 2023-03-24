# Spring Project - springItem

팀원 : 김대진, 진하륜

주제 : 의류 쇼핑몰

사이트 : http://localhost:8083/home

★처음 프로젝트 실행 시 해야 할 것

DB 설정 mysql -u root -p (비밀번호 입력)

  CREATE DATABASE springitem default CHARACTER SET UTF8;
  CREATE USER 'springitem'@'localhost' IDENTIFIED BY '1234';
  GRANT ALL PRIVILEGES ON springitem.* TO 'springitem'@'localhost';
  FLUSH PRIVILEGES;

dbeaver 연결

  database이름 : springitem
  
  user이름 : springitem
  
  password : 1234

  allowPublicKeyRetrieval=true

테이블 + 더미데이터 생성

  application.properties 에서 spring.jpa.hibernate.ddl-auto=create 로 수정
  
  src/test/java/com/lec/repository/DummyData.java 를 JUnitTest로 실행
  
  application.properties 에서 spring.jpa.hibernate.ddl-auto=update 로 수정
  
  
  
  
  
# BIMBO
# JPA Repository / Spring Security (BcryptPasswordEncoder) / Kakao,Naver,Google 로그인 / 전화번호 인증 / 결제

<p align="">
<img width="500" alt="erd8" src="https://user-images.githubusercontent.com/51112376/227442484-890a00ba-e459-4e24-a54c-387a2e733453.png">

### 📌 프로젝트 기술스택
- 에디터 : Eclipse
- 개발 툴 : SpringBoot 2.7.5
- 자바 : JAVA 11
- 빌드 : Maven 3.0
- 서버 : localhost
- 데이터베이스 : MySql
- 필수 라이브러리 : SpringBoot Web, MySQL, Spring Data JPA, Lombok, Spring Security, jstl

## 구현 완료
**Function** | **완료** | 
:------------ | :-------------|  
**회원가입 / 로그인 구현** | :heavy_check_mark: | 
**Validator** | :heavy_check_mark: | 
**문자인증** | :heavy_check_mark: |  
**Spring Security** | :heavy_check_mark: |  
**BcryptPasswordEncoder 비밀번호 암호화** | :heavy_check_mark: |  
**커뮤니티 구현 (댓글)** | :heavy_check_mark: |  
**상품 관리(추가, 삭제, 수정, 리스트, 재고)** | :heavy_check_mark: |  
**결제 시스템** | :heavy_check_mark: | 
**상품목록 페이징** | :heavy_check_mark: | 
**Header, Footer(홈, 검색, 좋아요, 구매내역, 마이페이지, 장바구니)** | :heavy_check_mark: | 
**마이페이지 구현** | :heavy_check_mark: | 
**검색결과 페이지 구현(상품상세로 이동, 좋아요 설정 가능)** | :heavy_check_mark: | 
**카테고리별 분류** | :heavy_check_mark: |
**정렬(신상품, 좋아요, 판매량, 가격낮은순)** | :heavy_check_mark: | 
**관리자 페이지(연/월수익, 회원수, 상품수, 수익 그래프, 상품관리, 배송관리)** | :heavy_check_mark: |
**화면 UI 개발 (헤더, 풋터, 회원가입, 로그인, home, 검색리스트, 카테고리별, 상품상세, 결제, 장바구니, 결제내역)** | :heavy_check_mark: | 
**google, kakao, naver API 로그인 구현** | :heavy_check_mark: |  
**웹 서버** | :heavy_check_mark: |  
**비동기(ajax)** | :heavy_check_mark: |  



# ERD 다이어그램
<p align="center">
<img width="700" alt="erd8" src="https://user-images.githubusercontent.com/51112376/227445709-d60733bf-8cd2-4e08-9fb8-0f6d1d4d5cf4.png">


## 🔽 URL

| METHOD  | URI                                | 기능 |
| ------- | ---------------------------------- |--------------------------- |
| REQUEST | /home | 홈페이지 | 
| POST    | /auth | 로그인한 유저 권한 | 
| GET     | /user/isexistid | 아이디 중복여부 |
| POST    | /user/login | 로그인 |
| POST    | /user/register | 회원가입 |
| REQUEST | /user/rejectAuth | 권한 거부 |
| GET     | /user/userinfo | 유저정보 |
| GET     | /user/update | 유저정보수정 페이지 |
| POST    | /user/updateOk | 유저정보수정 |
| GET     | /user/delete | 유저 삭제(비활성화) |
| GET     | /user/updatePassword | 비밀번호 변경 페이지 |
| POST    | /user/updatePasswordOk | 비밀번호 변경 |
| POST    | /user/apiLogin | API를 사용한 로그인 |
| GET     | /user/phoneCheck | 휴대폰 인증번호 |
| GET     | /item/setcount | 장바구니 상품개수 변경 |
| GET     | /item/getprice | 장바구니의 총 가격 |
| GET     | /item/data/likecontrol | 상품 좋아요 관리 |
| POST    | /item/registerReview | 상품댓글등록 |
| GET     | /item/reviewlist | 상품댓글리스트 |
| POST    | /item/deleteReview | 상품댓글삭제 |
| GET     | /item/detail | 상품상세 |
| GET     | /item/cart | 장바구니 목록 |
| POST    | /item/cart | 장바구니 상품추가 |
| GET     | /item/list | 카테고리/태그 상품목록 |
| GET     | /item/orderByList | 상품 정렬 |
| POST    | /item/optionchange | 상품옵션변경 |
| POST    | /item/delCart | 장바구니 삭제 |
| GET     | /item/buy | 구매목록 |
| POST    | /item/buy | 장바구니 구매 |
| POST    | /item/buydirect | 직접구매 |
| GET     | /item/search | 검색결과 목록(페이징, 정렬) |
| GET     | /item/like | 좋아요 표시한 상품 목록 |
| GET     | /admin/item/data/tags | 카테고리 목록 | 
| GET     | /admin/item/register | 상품등록 | 
| POST    | /admin/item/registerOk | 상품등록확인 | 
| GET     | /admin/item/update | 상품수정 | 
| POST    | /admin/item/updateOk | 상품수정확인 | 
| GET     | /admin/item/list | 상품관리리스트 | 
| GET     | /admin/item/delete | 상품삭제 | 
| GET     | /admin/item/manage | 관리자 페이지 | 
| GET     | /admin/buy/delivery | 배송확인 | 



