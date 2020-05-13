package com.emart.user.service.userservice.vo;

import lombok.Data;

@Data
public class SellerRequest {
    private String username;
    private String password;
    private String companyName;
    private String companyDescription;
    private String gstin;
    private String address;
    private String emailId;
    private String contactNumber;
}