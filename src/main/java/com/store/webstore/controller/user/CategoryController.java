package com.store.webstore.controller.user;

import com.store.webstore.model.Category;
import com.store.webstore.model.Product;
import com.store.webstore.service.CategoryService;
import com.store.webstore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<Iterable<Category>> listAllCategories(){
        Iterable<Category> categories = categoryService.findAll();
        return new ResponseEntity<Iterable<Category>>(categories,HttpStatus.OK);
    }

    @GetMapping("{id}/products")
    public ResponseEntity<List<Product>> findAllProductsInCategory(@PathVariable("id") Long categoryId) {
        if(categoryId == null) {
            return new ResponseEntity<List<Product>>((List<Product>) null,HttpStatus.NOT_FOUND);
        } else {
            List<Product> products = productService.findByCategoryId(categoryId);
            return new ResponseEntity<List<Product>>(products,HttpStatus.OK);
        }
    }

    @GetMapping("searchById/{id}")
    public Category findCategoryById(@PathVariable Long id){ return categoryService.findOneById(id);}

    @GetMapping("searchByName")
    public Category findCategoryByName(@RequestParam String name) {return categoryService.findOneByName(name);}



    private URI pathWithId(Object id) {
        return MvcUriComponentsBuilder.fromController(this.getClass())
                .pathSegment(id.toString())
                .build()
                .toUri();
    }
}
