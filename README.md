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
