package com.store.webstore.service;

import com.store.webstore.model.User;
import com.store.webstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public User findOne(Long id) {
        return userRepository.findOne(id);
    }

    @Transactional(readOnly = true)
    public long countAll() {
        return userRepository.count();
    }

    @Transactional
    public void delete(Long id) {
        userRepository.delete(id);
    }

    public User chceckLogin(User user) {
        User temp = null;
        User dbUser = userRepository.findByEmail(user.getEmail());

        if(dbUser != null && passwordEncoder.matches(user.getPassword(),dbUser.getPassword())){
            temp = new User();
            temp.setId(dbUser.getId());
            temp.setName(dbUser.getName());
            temp.setEmail(dbUser.getEmail());
        }
        return temp;
    }

    public boolean register(User user) {
        if(userRepository.findByEmail(user.getEmail())!=null) {
            return false;
        }
        userRepository.save(user);
        return true;
    }

}
