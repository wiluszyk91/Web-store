package com.store.webstore.controller.user;

import com.store.webstore.model.Product;
import com.store.webstore.service.ProductService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) { this.productService = productService; }

    @GetMapping
    public List<Product> listAllProducts() { return productService.findAll();}

    @GetMapping("searchById/{id}")
    public Product findProductById(@PathVariable Long id) {return productService.findOne(id); }

    @GetMapping("searchByName")
    public List<Product> findProductsByName(@RequestParam String name) {return productService.search(name); }

    @GetMapping("latest")
    public List<Product> find10Latest(){
        return productService.findLatest(0,10);
    }


    private URI pathWithId(Object id) {
        return MvcUriComponentsBuilder.fromController(this.getClass())
                .pathSegment(id.toString())
                .build()
                .toUri();
    }
}
