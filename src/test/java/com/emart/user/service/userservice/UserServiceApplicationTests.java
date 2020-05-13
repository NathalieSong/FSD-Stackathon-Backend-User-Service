package com.emart.user.service.userservice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.emart.user.service.userservice.exception.EmartException;
import com.emart.user.service.userservice.service.UserService;
import com.emart.user.service.userservice.vo.BuyerRequest;
import com.emart.user.service.userservice.vo.BuyerResponse;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
class UserServiceApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private UserService userService;

	private BuyerRequest buyerReq;

	@BeforeAll
	void initBuyerRequest() {
		buyerReq = new BuyerRequest();
		buyerReq.setUsername("testbuyer");
		buyerReq.setPassword("123");
		buyerReq.setEmailId("123@testbuyer.com");
		buyerReq.setAddress("testbuyer address");
		buyerReq.setMobile("12345678");
	}

	@Test
	void buyerSignupTest() {
		BuyerResponse buyerRes = new BuyerResponse();
		try {
			buyerRes = userService.signupAsBuyer(buyerReq);
		} catch (EmartException e) {
			e.printStackTrace();
		}
		assertEquals(buyerReq.getUsername(), buyerRes.getUsername());
		assertEquals(buyerReq.getEmailId(), buyerRes.getEmailId());
		assertEquals(buyerReq.getAddress(), buyerRes.getAddress());
		assertEquals(buyerReq.getMobile(), buyerRes.getMobile());
	}

	@AfterAll
	void rmBuyerByUsername() {
		userService.deleteBuyerByUsername(buyerReq.getUsername());
	}

}
