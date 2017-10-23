package com.store.webstore.controller.admin;

import com.store.webstore.model.Category;
import com.store.webstore.model.Product;
import com.store.webstore.service.CategoryService;
import com.store.webstore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("admin/product")
public class AdminProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @PostMapping("category/searchById/{categoryId}/product/create")
    public ResponseEntity<Void> createProduct(@RequestBody Product product,@PathVariable Long categoryId) {
        Category category = categoryService.findOneById(categoryId);
        product.setCategory(category);
        Product save = productService.createProduct(product);

        return ResponseEntity.created(pathWithId(save.getId())).build();
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteCategoryById(@PathVariable Long id) {
        Product product = productService.findOne(id);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }

    private URI pathWithId(Object id) {
        return MvcUriComponentsBuilder.fromController(this.getClass())
                .pathSegment(id.toString())
                .build()
                .toUri();
    }
}
