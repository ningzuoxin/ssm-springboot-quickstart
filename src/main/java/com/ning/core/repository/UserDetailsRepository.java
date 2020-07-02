package com.ning.core.repository;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class UserDetailsRepository {

    private Map<String, UserDetails> users = new HashMap<>();

    {
        users.put("zhangsan", User.withUsername("zhangsan").password("123456").authorities("p1").build());
        users.put("lisi", User.withUsername("lisi").password("123456").authorities("p2").build());
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return users.get(username);
    }

}
