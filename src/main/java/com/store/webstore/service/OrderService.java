package com.store.webstore.service;

import com.store.webstore.model.Order;
import com.store.webstore.model.OrderDetail;
import com.store.webstore.model.User;
import com.store.webstore.repository.OrderRepository;
import com.store.webstore.repository.ProductRepository;
import com.store.webstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    Iterable<Order> findAll(){return orderRepository.findAll(); }

    List<Order> findByUser(User user) {return orderRepository.findByUser(user); }

    Order findOneById(Long id) {return  orderRepository.findOne(id); }

    public long countAll(){return orderRepository.count(); }

    public void delete(Long orderId) { orderRepository.delete(orderId);}

    public void save(Order order) {
        User currentUser = userRepository.findByEmail(order.getUser().getEmail());

        Order dbOrder = new Order();
        dbOrder.setName(order.getName());
        dbOrder.setUser(currentUser);

        OrderDetail detail;
        for(OrderDetail od :order.getOrderDetails()) {
            detail = new OrderDetail();
            detail.setOrder(dbOrder);
            detail.setProduct(productRepository.findOne(od.getProduct().getId()));
            detail.setQuantity(od.getQuantity());
            detail.setPrice(od.getPrice());
            dbOrder.addOrderDetail(detail);
        }
        dbOrder = orderRepository.save(dbOrder);
    }

}
