package com.store.webstore.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Data
@Entity
@Table(name = "category")
public class Category extends NameEntity {

    @Column(name = "description")
    private String description;
    
    @OneToMany(mappedBy="category", cascade=CascadeType.ALL,orphanRemoval = true)
    @JsonBackReference
    private Set<Product> products = new HashSet<>();
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
