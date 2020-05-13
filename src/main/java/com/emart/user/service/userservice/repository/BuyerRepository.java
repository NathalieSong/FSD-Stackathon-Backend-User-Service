package com.emart.user.service.userservice.repository;

import com.emart.user.service.userservice.entity.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface BuyerRepository extends JpaRepository<Buyer, String> {
    Buyer findByUsername(String username);

    @Modifying
    @Query("delete from Buyer b where b.username = :username")
    void deleteByUsername(String username);
}