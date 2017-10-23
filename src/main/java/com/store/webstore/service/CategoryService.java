package com.store.webstore.service;

import com.store.webstore.model.Category;
import com.store.webstore.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category createCateogry(Category category){
        return categoryRepository.save(category);
    }

    public void updateCategory(Category category) { categoryRepository.save(category);}

    public void deleteCategory(Long id) {
        categoryRepository.delete(id);
    }

    @Transactional(readOnly = true)
    public Iterable<Category> findAll() { return categoryRepository.findAll(); }

    public Category findOneById(Long id) {
        return categoryRepository.findById(id);
    }

    public Category findOneByName(String name){
        return categoryRepository.findByName(name);
    }

}
