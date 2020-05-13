package com.emart.user.service.userservice.repository;

import com.emart.user.service.userservice.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller, String> {
    Seller findByUsername(String username);
}