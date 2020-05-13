package com.emart.user.service.userservice.service;

import java.util.Date;

import com.emart.user.service.userservice.entity.Buyer;
import com.emart.user.service.userservice.entity.Seller;
import com.emart.user.service.userservice.exception.EmartException;
import com.emart.user.service.userservice.exception.ExceptionEnums;
import com.emart.user.service.userservice.repository.*;
import com.emart.user.service.userservice.vo.BuyerRequest;
import com.emart.user.service.userservice.vo.BuyerResponse;
import com.emart.user.service.userservice.vo.JwtUser;
import com.emart.user.service.userservice.vo.Roles;
import com.emart.user.service.userservice.vo.SellerRequest;
import com.emart.user.service.userservice.vo.SellerResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private BuyerRepository buyerRepo;

    @Autowired
    private SellerRepository sellerRepo;

    public JwtUser getJwtUserByUsernameAndRole(String username, String role) {
        JwtUser user = new JwtUser();
        user.setUsername(username);
        user.setRole(role);
        switch (Roles.of(role)) {
            case BUYER:
            Buyer buyer = buyerRepo.findByUsername(username);
            if (buyer == null) {
                return null;
            }
            user.setPassword(buyer.getPassword());
            return user;
            case SELLER:
            Seller seller = sellerRepo.findByUsername(username);
            if (seller == null) {
                return null;
            }
            user.setPassword(seller.getPassword());
            return user;
            default:
            return null;
        }
    }

	public BuyerResponse signupAsBuyer(BuyerRequest buyer) throws EmartException {
        if ( existBuyer(buyer.getUsername()) ) {
            throw new EmartException(ExceptionEnums.USERNAME_ALREADY_EXISTED);
        }
		return toBuyerResponse(
            buyerRepo.save(toBuyer(buyer))
        );
    }

    public SellerResponse signupAsSeller(SellerRequest seller) throws EmartException {
        if ( existSeller(seller.getUsername()) ) {
            throw new EmartException(ExceptionEnums.USERNAME_ALREADY_EXISTED);
        }
		return toSellerResponse(
            sellerRepo.save(toSeller(seller))
        );
	}
    
    private boolean existBuyer(String username) {
        Buyer buyer = buyerRepo.findByUsername(username);
        return !(buyer == null);
    }

    private boolean existSeller(String username) {
        Seller seller = sellerRepo.findByUsername(username);
        return !(seller == null);
    }

    private Buyer toBuyer(BuyerRequest buyerReq) {
        Buyer buyer = new Buyer();
        BeanUtils.copyProperties(buyerReq, buyer);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        buyer.setPassword(encoder.encode(buyer.getPassword()));
        buyer.setCreatedDate(new Date());
        buyer.setActive(true);
        return buyer;
    }

    private BuyerResponse toBuyerResponse(Buyer buyer) {
        BuyerResponse buyerRes = new BuyerResponse();
        BeanUtils.copyProperties(buyer, buyerRes);
        return buyerRes;
    }

    private Seller toSeller(SellerRequest sellerReq) {
        Seller seller = new Seller();
        BeanUtils.copyProperties(sellerReq, seller);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        seller.setPassword(encoder.encode(seller.getPassword()));
        seller.setCreatedDate(new Date());
        seller.setActive(true);
        return seller;
    }

    private SellerResponse toSellerResponse(Seller seller) {
        SellerResponse sellerRes = new SellerResponse();
        BeanUtils.copyProperties(seller, sellerRes);
        return sellerRes;
    }
}