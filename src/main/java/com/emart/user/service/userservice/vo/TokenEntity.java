package com.emart.user.service.userservice.vo;

import java.io.Serializable;
import lombok.Data;

@Data
public class TokenEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private String username;
    private String token;

    public TokenEntity(String username, String token) {
        this.username = username;
        this.token = token;
    }
}