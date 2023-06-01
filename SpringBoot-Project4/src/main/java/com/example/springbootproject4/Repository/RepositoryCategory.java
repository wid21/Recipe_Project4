package com.example.springbootproject4.Repository;


import com.example.springbootproject4.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositoryCategory extends JpaRepository<Category,Integer> {

    Category findCategoriesById(Integer id);

    List<Category > findCategoriesByName(String name);
    Category findCategoryByName(String name);
}
