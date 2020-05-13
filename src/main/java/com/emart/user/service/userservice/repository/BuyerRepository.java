package com.emart.user.service.userservice.repository;

import java.util.List;

import com.emart.user.service.userservice.entity.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuyerRepository extends JpaRepository<Buyer, String> {
    Buyer findByUsername(String username);
}