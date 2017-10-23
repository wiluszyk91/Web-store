package com.store.webstore.service;

import com.store.webstore.model.Role;
import com.store.webstore.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role findByName(String name) { return roleRepository.findByName(name);}
}
