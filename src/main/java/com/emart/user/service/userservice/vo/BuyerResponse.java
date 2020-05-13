package com.emart.user.service.userservice.vo;

import java.util.Date;

import lombok.Data;

@Data
public class BuyerResponse {
    private String id;
    private String username;
    private String emailId;
    private String address;
    private String mobile;
    private Date createdDate;
    private boolean active;
}