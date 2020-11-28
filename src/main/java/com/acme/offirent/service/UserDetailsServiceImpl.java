package com.acme.offirent.service;

import com.acme.offirent.domain.model.Account;
import com.acme.offirent.domain.repository.AccountRepository;
import com.acme.offirent.domain.service.DefaultUserDetailsService;
import com.acme.offirent.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements DefaultUserDetailsService {

    private static final String DEFAULT_USERNAME = "john.doe@gmail.com";
    private static final List<GrantedAuthority> DEFAULT_AUTHORITIES = new ArrayList<>();

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<User> getAll() {
        return Arrays.asList(
                new User("john.doe@gmail.com",passwordEncoder.encode("password"),DEFAULT_AUTHORITIES),
                new User("john.doe@outlook.com",passwordEncoder.encode("aea"),DEFAULT_AUTHORITIES)
        );
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByEmail(username).orElseThrow(()->new ResourceNotFoundException("not found"));;
        String defaultPassword = passwordEncoder.encode(account.getPassword());
        if(account.getEmail().equals(username)){
            return new User(account.getEmail(),defaultPassword,DEFAULT_AUTHORITIES);
        }
        throw new UsernameNotFoundException(String.format("User not found with username %s",username));
    }
}
