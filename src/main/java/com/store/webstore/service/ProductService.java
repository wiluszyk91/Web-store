package com.store.webstore.service;

import com.store.webstore.model.Product;
import com.store.webstore.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public void delete(Long id) {
        productRepository.delete(id);
    }

    @Transactional(readOnly = true)
    public List<Product> findAll() { return productRepository.findAll();}

    @Transactional(readOnly = true)
    public List<Product> findByCategoryId(Long categoryId) { return productRepository.findByCategoryId(categoryId); }

    @Transactional(readOnly = true)
    public Product findOne(Long id) {
        return productRepository.findOne(id);
    }

    @Transactional(readOnly = true)
    public Product findOneWithCategory(Long id) { return productRepository.findOneWithCategory(id);}

    @Transactional(readOnly = true)
    public List<Product> findLatest(int page, int size) {
        return productRepository.findTop10ByOrderByIdDesc();
    }

    @Transactional(readOnly = true)
    public List<Product> search(String keyword) {
        return productRepository.findByNameContaining(keyword);
    }

    @Transactional(readOnly = true)
    public long countAll() {
        return productRepository.count();
    }


}
