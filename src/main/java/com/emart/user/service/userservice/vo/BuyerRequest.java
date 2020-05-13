package com.emart.user.service.userservice.vo;

import lombok.Data;

@Data
public class BuyerRequest {
    private String username;
    private String password;
    private String emailId;
    private String address;
    private String mobile;
}