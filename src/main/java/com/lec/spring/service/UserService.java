package com.lec.spring.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lec.spring.config.PrincipalDetailService;
import com.lec.spring.domain.Authority;
import com.lec.spring.domain.User;
import com.lec.spring.repository.AuthorityRepository;
import com.lec.spring.repository.UserRepository;

import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

@Service
public class UserService {
	
	private UserRepository userRepository;
	private AuthorityRepository authorityRepository;
	
	@Autowired
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Autowired
	public void setAuthorityRepository(AuthorityRepository authorityRepository) {
		this.authorityRepository = authorityRepository;
	}
	
	@Autowired
	private PasswordEncoder passwordEncoder; // 회원가입등에서 사용

	public UserService() {
		System.out.println(getClass().getName() + "() 생성");
	}
	
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
	// 특정 id 의 User 의 Authority (들)을 리턴
	public List<Authority> selectAuthoritiesById(Long id){
		
		User user = userRepository.findById(id).orElse(null);
		
		if(user != null) {
			return user.getAuthorities();
		}
		
		return new ArrayList<>();
	}
	
	// 주어진 username 의 회원이 존재하는지 확인 (회원가입등에서 활용)
	public boolean isExist(String username) {
		User user = userRepository.findByUsername(username);
		if(user != null) return true;
		
		return false;
	}

	// 회원등록
	public int register(User user) {
		user.setUsername(user.getUsername().toUpperCase()); // 대문자로 저장
		user.setPassword(passwordEncoder.encode(user.getPassword())); // password는 암호화해서
		user = userRepository.save(user); // 저장
		
		// 신규회원은 ROLE_MEMBER 권한 기본적으로 부여하기
		Authority auth = authorityRepository.findByName("ROLE_MEMBER");
		user.addAuthority(auth);
		userRepository.save(user);
		
		return 1;
	}

	public int updateUser(String id, String name, String phonenum, String email, String address1, String address2, String address3) {
		User user = userRepository.findById(Long.parseLong(id)).orElse(null);
		user.setName(name);
		user.setPhonenum(phonenum);
		user.setEmail(email);
		user.setAddress1(address1);
		user.setAddress2(address2);
		user.setAddress3(address3);
		userRepository.saveAndFlush(user);
		return 1;
	}

	public int deleteUser(String id) {
		User user = userRepository.findById(Long.parseLong(id)).orElse(null);
		user.setIsvalid(false);
		userRepository.saveAndFlush(user);
		return 1;
	}
	public int updatePassword(String id, String password, String re_password) {
		if (!password.equals(re_password)) return 0;
		User user = userRepository.findById(Long.parseLong(id)).orElse(null);
		user.setPassword(passwordEncoder.encode(password));
		userRepository.saveAndFlush(user);
		return 1;
	}

	public void certifiedPhoneNumber(String userPhoneNumber, int randomNumber) {
		String api_key = "NCS9MOT5VQD4KWR1";
	    String api_secret = "VXKUTHFDZKGQL7XDLY2IJPKJQXWPJUJN";
	    Message coolsms = new Message(api_key, api_secret);

	    // 4 params(to, from, type, text) are mandatory. must be filled
	    HashMap<String, String> params = new HashMap<String, String>();
	    params.put("to", userPhoneNumber);    // 수신전화번호
	    params.put("from", "01055350934");    // 발신전화번호. 테스트시에는 발신,수신 둘다 본인 번호로 하면 됨
	    params.put("type", "SMS");
	    params.put("text", "BIMBO 인증번호는" + "["+randomNumber+"]" + "입니다."); // 문자 내용 입력
	    params.put("app_version", "test app 1.2"); // application name and version

	    User user = new User();
	    user.setRandom(userPhoneNumber);
	    try {
	        JSONObject obj = (JSONObject) coolsms.send(params);
	        System.out.println(obj.toString());
	    } catch (CoolsmsException e) {
	    	System.out.println(e.getMessage());
	    	System.out.println(e.getCode());
	    }
	}
}
















