package com.store.webstore.repository;

import com.store.webstore.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {

    Category findById(Long id);

    Category findByName(String name);




}
