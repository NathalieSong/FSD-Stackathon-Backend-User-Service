package com.emart.user.service.userservice.controller;

import com.emart.user.service.userservice.exception.EmartException;
import com.emart.user.service.userservice.service.UserService;
import com.emart.user.service.userservice.vo.BuyerRequest;
import com.emart.user.service.userservice.vo.BuyerResponse;
import com.emart.user.service.userservice.vo.SellerRequest;
import com.emart.user.service.userservice.vo.SellerResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/signup/buyer")
    public ResponseEntity<BuyerResponse> signupAsBuyer(@RequestBody BuyerRequest buyer) throws EmartException {
        return new ResponseEntity<BuyerResponse>(
            userService.signupAsBuyer(buyer),
            HttpStatus.CREATED
        );
    }

    @PostMapping("/signup/seller")
    public ResponseEntity<SellerResponse> signupAsSeller(@RequestBody SellerRequest seller) throws EmartException {
        return new ResponseEntity<SellerResponse>(
            userService.signupAsSeller(seller),
            HttpStatus.CREATED
        );
    }
}