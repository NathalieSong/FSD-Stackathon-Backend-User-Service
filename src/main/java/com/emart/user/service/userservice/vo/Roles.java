package com.emart.user.service.userservice.vo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.stream.Stream;

public enum Roles {
    BUYER("buyer"),
    SELLER("seller");

    private final String role;
    
    @JsonValue
    public String getRole() {
      return role;
    }

    private Roles(String role) {
        this.role = role;
    }

    @JsonCreator
    public static Roles of(final String role) {
      return Stream.of(Roles.values())
          .filter(r -> r.getRole().equalsIgnoreCase(role))
          .findFirst()
          .orElseThrow(IllegalArgumentException::new);
    }
}