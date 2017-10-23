package com.store.webstore.model;

import lombok.Data;

import javax.persistence.*;
import java.util.*;

@Data
@Entity
@Table(name = "orders")
public class Order extends NameEntity{

        @Temporal(TemporalType.TIMESTAMP)
        @Column(name = "created")
        private Date created;

        @OneToMany(mappedBy="order", cascade=CascadeType.ALL)
        private Set<OrderDetail> orderDetails = new HashSet<>();

        @ManyToOne
        @JoinColumn(name="account_id")
        private User user;

        public Set<OrderDetail> getOrderDetails() {
                return orderDetails;
        }

        public void setOrderDetails(Set<OrderDetail> orderDetails) {
                this.orderDetails = orderDetails;
        }

        public User getUser() {
                return user;
        }

        public void setUser(User user) {
                this.user = user;
        }

        public Date getCreated() {
                return created;
        }

        public void setCreated(Date created) {
                this.created = created;
        }

        public void addOrderDetail(OrderDetail orderDetail) { orderDetails.add(orderDetail);}
}
