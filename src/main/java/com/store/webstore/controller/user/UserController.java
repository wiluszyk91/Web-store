package com.store.webstore.controller.user;

import com.store.webstore.model.User;
import com.store.webstore.service.RoleService;
import com.store.webstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("login")
    public ResponseEntity<User> login(@RequestBody User user) {
        return new ResponseEntity<User>(userService.chceckLogin(user), HttpStatus.CREATED);
    }

    @PostMapping("register")
    public ResponseEntity<Boolean> register(@RequestBody User user) {
        User dbUser = new User();
        dbUser.setName(user.getName());
        dbUser.setEmail(user.getEmail());
        dbUser.setSurname(user.getSurname());
        dbUser.setTelephone(user.getTelephone());
        dbUser.setPassword(passwordEncoder.encode(user.getPassword()));
        return new ResponseEntity<Boolean> (userService.register(dbUser),HttpStatus.CREATED);
    }
}
