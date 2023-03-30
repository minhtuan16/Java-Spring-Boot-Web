package com.example.demo;

import org.springframework.security.core.context.SecurityContextHolder;

import com.example.demo.model.MyUser;

public class SecurityUtils {

    public static MyUser getPrincipal() {
        return (MyUser) (SecurityContextHolder.getContext()).getAuthentication().getPrincipal();
    }
}
