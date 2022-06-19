package com.tv.tvapi.service;

import com.tv.tvapi.model.User;
import com.tv.tvapi.repository.UserRepository;
import com.tv.tvapi.model.MyUserDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MyUserDetailService implements UserDetailsService {

    private final UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> optional = userRepo.findByUsernameOrEmail(s, s);
        if (optional.isEmpty())
            throw new UsernameNotFoundException("Could not find user");
        User user = optional.get();
        MyUserDetail myUserDetail = new MyUserDetail(user);
        return myUserDetail;
    }
}
