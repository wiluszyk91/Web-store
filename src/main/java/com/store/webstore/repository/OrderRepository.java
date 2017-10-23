package com.store.webstore.repository;

import com.store.webstore.model.Order;
import com.store.webstore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {

    @Query("select distinct o from Order o left join fetch o.user left join fetch o.orderDetails i left join fetch i.product")
    List<Order> findAll();

    @Query("select distinct o from Order o left join fetch o.user left join fetch o.orderDetails i left join fetch i.product where o.user = ?1")
    List<Order> findByUser(User user);

    @Query("select o from Order o left join fetch o.user left join fetch o.orderDetails i left join fetch i.product where o.id = ?1")
    Order findOne(Long id);
}
