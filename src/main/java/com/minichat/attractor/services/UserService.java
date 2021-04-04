package com.minichat.attractor.services;


import com.minichat.attractor.model.User;
import com.minichat.attractor.repostory.UserRepository;
import com.minichat.attractor.user.CrmUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<User> findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Transactional
    public User save(CrmUser crmUser) {
        User user = User.builder()
                .userName(crmUser.getUserName())
                .password(passwordEncoder.encode(crmUser.getPassword()))
                .build();
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        return userRepository.findByUserName(userName).orElseThrow(() ->
                new UsernameNotFoundException(String.format("User with username %s not found", userName)));
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public void deleteAll(){
        userRepository.deleteAll();
    }


    public Boolean existsUserName(String userName) {
        return userRepository.existsUserByUserName(userName);
    }
}
