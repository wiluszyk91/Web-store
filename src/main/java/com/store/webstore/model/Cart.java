package com.store.webstore.model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("session")
public class Cart {

    List<OrderDetail> orderDetails;

    public Cart() { orderDetails = new ArrayList<>(); }

    public int getCount() { return orderDetails.size(); }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public Double getTotal() {
        Double total = null;
        for(OrderDetail orderDetail : orderDetails) {
            total += (orderDetail.getPrice());
        }
        return total;
    }

}
