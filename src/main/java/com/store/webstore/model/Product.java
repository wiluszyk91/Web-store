package com.store.webstore.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "product")
public class Product extends NameEntity {

    @Column(name = "price")
    private Double price;

    @Column
    private String Description;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name="category_id")
    private Category category;

    @OneToMany(mappedBy="product", cascade=CascadeType.ALL)
    private List<OrderDetail> orderDetails;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
