package com.emart.user.service.userservice.entity;

import java.util.Date;

import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import lombok.Data;

@Data
@Entity
@Table(name = "Buyer")
public class Buyer {
    @Id
    @GenericGenerator(name = "buyer-uuid2", strategy = "uuid2")
    @GeneratedValue(generator = "buyer-uuid2")
    private String id;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "emailId")
    private String emailId;

    @Column(name = "address")
    private String address;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "createdDate")
    private Date createdDate;

    @Column(name = "active")
    private boolean active;
}