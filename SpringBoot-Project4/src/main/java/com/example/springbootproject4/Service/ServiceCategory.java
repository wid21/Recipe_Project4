package com.example.springbootproject4.Service;

import com.example.springbootproject4.ApiException.ApiException;
import com.example.springbootproject4.Model.Category;
import com.example.springbootproject4.Model.Recipe;
import com.example.springbootproject4.Repository.RepositoryCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ServiceCategory {

    private final RepositoryCategory repositoryCategory;

    public List<Category> getAllCategories(){
        return repositoryCategory.findAll();
    }

    public void AddUsers(Category category,String role){
        if(isAdmin(role)){
            repositoryCategory.save(category);}
        else {
            throw new ApiException("you don't have the permission");
        }
    }

    public void updateCategory(Category category, Integer id,String role){
        if(isAdmin(role)){
            Category category1=repositoryCategory.findCategoriesById(id);
            if(category1==null){
                throw new ApiException("not found");
            }
            category1.setName(category.getName());

            repositoryCategory.save(category1);}
        else {
            throw new ApiException("you don't have the permission");
        }
    }

    public void deleteCategory(Integer id,String role) {
        if(isAdmin(role)){
            Category category = repositoryCategory.findCategoriesById(id);
            if (category == null) {
                throw new ApiException("not found");
            }
            repositoryCategory.delete(category);}
        else {
            throw new ApiException("you don't have the permission");
        }
    }

    public List<Category> getCategoryByName(String name){
        return repositoryCategory.findCategoriesByName(name);
    }

    public boolean isAdmin(String role){
        if(role.equals("admin")){
            return true;
        }
        return false;
    }




}


