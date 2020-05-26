package com.emart.user.service.userservice.controller;

import javax.servlet.http.HttpServletRequest;

import com.emart.user.service.userservice.exception.EmartException;
import com.emart.user.service.userservice.exception.ExceptionEnums;
import com.emart.user.service.userservice.jwtsecurity.JwtTokenUtil;
import com.emart.user.service.userservice.jwtsecurity.JwtUserDetailsService;
import com.emart.user.service.userservice.service.RedisService;
import com.emart.user.service.userservice.vo.JwtResponse;
import com.emart.user.service.userservice.vo.JwtUser;
import com.emart.user.service.userservice.vo.LoginRequest;
import com.emart.user.service.userservice.vo.Roles;
import com.emart.user.service.userservice.vo.TokenRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class JwtAuthenticationController {
    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    private RedisService redisService;

    @PostMapping("/buyer")
    public ResponseEntity<?> checkBuyerByToken(@RequestBody TokenRequest tokenReq) throws EmartException {
        if (checkRoleByTokenAndRole(tokenReq.getToken(), Roles.BUYER)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            throw new EmartException(ExceptionEnums.INSUFFICIENT_AUTH);
        }
    }

    @PostMapping("/seller")
    public ResponseEntity<?> checkSellerByToken(@RequestBody TokenRequest tokenReq) throws EmartException {
        if (checkRoleByTokenAndRole(tokenReq.getToken(), Roles.SELLER)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            throw new EmartException(ExceptionEnums.INSUFFICIENT_AUTH);
        }
    }

    private boolean checkRoleByTokenAndRole(String token, Roles role) {
        String userRole = "";
        final String username = jwtTokenUtil.getUsernameFromToken(token);
        if (username != null) {
            final JwtUser userDetails = userDetailsService.loadUserByUsername(username);
            if (jwtTokenUtil.validateToken(token, userDetails)) {
                userRole = userDetails.getRole();
            }
        }
        return Roles.BUYER.getRole().equalsIgnoreCase(userRole) ? true : false;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) throws Exception {
        Authentication authentication = authenticate(loginRequest.getUsername(), loginRequest.getPassword());
        final JwtUser userDetails = userDetailsService.loadUserByUsername(loginRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        SecurityContextHolder.getContext()
                .setAuthentication(authentication);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    private Authentication authenticate(String username, String password) throws Exception {
        try {
            return authManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        String username = jwtTokenUtil.getUsernameFromRequest(request);
        if (username != null && !username.isEmpty()) {
            redisService.rmToken(username);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}