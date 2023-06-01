package com.example.springbootproject4.Controller;

import com.example.springbootproject4.Model.Category;
import com.example.springbootproject4.Service.ServiceCategory;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/category")
@RequiredArgsConstructor
public class ControllerCategory {

    private final ServiceCategory serviceCategory;
    @GetMapping("/get")
    public ResponseEntity getAllCategories(){
        List<Category> user=serviceCategory.getAllCategories();
        return ResponseEntity.status(200).body(user);
    }
    @PostMapping("/add/{role}")
    public ResponseEntity AddCategory(@Valid @RequestBody Category category,@PathVariable String role){
        serviceCategory.AddUsers(category,role);
        return ResponseEntity.status(200).body("category added");
    }
    @PutMapping("/update/{id}/{role}")
    public ResponseEntity updateCategory(@Valid @RequestBody Category category,@PathVariable Integer id,@PathVariable String role){
        serviceCategory.updateCategory(category,id,role);
        return ResponseEntity.status(200).body("user updated");
    }
    @DeleteMapping("/delete/{id}/{role}")
    public ResponseEntity deleteCategory(@PathVariable Integer id,@PathVariable String role){
        serviceCategory.deleteCategory(id,role);
        return ResponseEntity.status(200).body("category deleted");
    }

    @GetMapping("/get-name/{name}")
    public ResponseEntity getCategoryByName(@PathVariable String name){
        List<Category> categories=serviceCategory.getCategoryByName(name);
        return ResponseEntity.status(200).body(categories);
    }
}



