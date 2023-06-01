package com.example.springbootproject4.Repository;

import com.example.springbootproject4.Model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.DataTruncation;
import java.util.List;

@Repository
public interface RepositoryRecipe extends JpaRepository<Recipe,Integer> {

    Recipe findRecipeById(Integer id);

 List <Recipe> searchRecipeByChefname(String chefname);
    List<Recipe> searchByIngredientsContaining(String ingredients);
    Recipe searchRecipeByTitel(String titel);
    List<Recipe> findAllByTypeIgnoreCase(String type);

    @Query("select c from Recipe c where c.callories >= ?1")
    List<Recipe> searchRecipeByCallories(double callories);
    @Query("select r from Recipe r where r.rate =?1")
    List<Recipe> searchRecipeByRate (int rate);

    List<Recipe> searchRecipeByCooktime(int cooktime);

    List<Recipe> findRecipeByType(String type);


    List<Recipe> findAllByTitel(String title );
}
