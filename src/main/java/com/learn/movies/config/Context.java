package com.learn.movies.config;

import com.learn.movies.entity.User;
import com.learn.movies.objects.UserPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class Context {

    public static User getUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null && authentication.getPrincipal() != null)
            return ((UserPrincipal) authentication.getPrincipal()).getUser();
        return null;
    }
}
