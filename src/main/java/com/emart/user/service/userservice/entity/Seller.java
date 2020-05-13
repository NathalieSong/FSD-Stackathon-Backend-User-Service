package com.emart.user.service.userservice.entity;

import java.util.Date;

import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import lombok.Data;

@Data
@Entity
@Table(name = "Seller")
public class Seller {
    @Id
    @GenericGenerator(name = "seller-uuid2", strategy = "uuid2")
    @GeneratedValue(generator = "seller-uuid2")
    private String id;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "companyName")
    private String companyName;

    @Column(name = "companyDescription")
    private String companyDescription;

    @Column(name = "gstin")
    private String gstin;

    @Column(name = "address")
    private String address;

    @Column(name = "emailId")
    private String emailId;

    @Column(name = "contactNumber")
    private String contactNumber;

    @Column(name = "createdDate")
    private Date createdDate;

    @Column(name = "active")
    private boolean active;
}