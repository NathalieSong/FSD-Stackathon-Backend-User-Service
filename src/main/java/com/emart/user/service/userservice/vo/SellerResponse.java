package com.emart.user.service.userservice.vo;

import java.util.Date;

import lombok.Data;

@Data
public class SellerResponse {
    private String id;
    private String username;
    private String companyName;
    private String companyDescription;
    private String gstin;
    private String address;
    private String emailId;
    private String contactNumber;
    private Date createdDate;
    private boolean active;
}