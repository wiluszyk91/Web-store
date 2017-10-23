package com.store.webstore.model;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "role")
public class Role extends NameEntity {

    @ManyToMany(mappedBy = "roles", cascade=CascadeType.ALL)
    private Set<User> users = new HashSet<>();

    public Set<User> getUsers() {
        return users;
    }
}
