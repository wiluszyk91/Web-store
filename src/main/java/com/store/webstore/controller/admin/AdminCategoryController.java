package com.store.webstore.controller.admin;

import com.store.webstore.model.Category;
import com.store.webstore.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("admin/category")
public class AdminCategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("create")
    public ResponseEntity<Void> createCategory(@RequestBody Category category) {
        Category save = categoryService.createCateogry(category);
        return ResponseEntity.created(pathWithId(save.getId())).build();
    }

    @PutMapping("edit/{id}")
    public ResponseEntity<?> updateCategoryById(@PathVariable("id") Long id, @RequestBody Category category){
        Category currenCategory = categoryService.findOneById(id);
        currenCategory.setName(category.getName());
        currenCategory.setDescription(category.getDescription());

        categoryService.updateCategory(currenCategory);
        return new ResponseEntity<Category>(currenCategory, HttpStatus.OK);
    }

    @PutMapping("edit/{name}")
    public ResponseEntity<?> updateCategoryByName(@PathVariable("name") String name, @RequestBody Category category){
        Category currenCategory = categoryService.findOneByName(name);
        currenCategory.setName(category.getName());
        currenCategory.setDescription(category.getDescription());

        categoryService.updateCategory(currenCategory);
        return new ResponseEntity<Category>(currenCategory, HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteCategoryById(@PathVariable long id) {
        Category category = categoryService.findOneById(id);
        if (category == null) {
            return ResponseEntity.notFound().build();
        }
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }


    private URI pathWithId(Object id) {
        return MvcUriComponentsBuilder.fromController(this.getClass())
                .pathSegment(id.toString())
                .build()
                .toUri();
    }
}
