package com.example.springbootproject4.Service;

import com.example.springbootproject4.ApiException.ApiException;
import com.example.springbootproject4.Model.Category;
import com.example.springbootproject4.Model.Recipe;
import com.example.springbootproject4.Model.User;
import com.example.springbootproject4.Repository.RepositoryCategory;
import com.example.springbootproject4.Repository.RepositoryRecipe;
import com.example.springbootproject4.Repository.RepositoryUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor

public class ServiceRecipe {

    private final RepositoryRecipe repositoryRecipe;

    private final RepositoryUser repositoryUser;
    private final RepositoryCategory repositoryCategory;

    public List<Recipe> getRecipe() {

        return repositoryRecipe.findAll();
    }
    public void addRecipe(Recipe recipe ) {


        repositoryRecipe.save(recipe);
    }

    public void updateRecipe(Recipe recipe, Integer id) {
        Recipe oldrecipe = repositoryRecipe.findRecipeById(id);
        if (oldrecipe == null) {
            throw new ApiException("recipe not found");
        }

        oldrecipe.setChefname(recipe.getChefname());
        oldrecipe.setType(recipe.getType());
        oldrecipe.setCooktime(recipe.getCooktime());
        oldrecipe.setIngredients(recipe.getIngredients());
        oldrecipe.setInstructions(recipe.getInstructions());
        oldrecipe.setTitel(recipe.getTitel());
        oldrecipe.setRate(recipe.getRate());
        oldrecipe.setCallories(recipe.getCallories());
        repositoryRecipe.save(oldrecipe);
    }

    public void deleteRecipe(Integer id) {
        Recipe recipe = repositoryRecipe.findRecipeById(id);
        if (recipe == null) {
            throw new ApiException("recipe not found");
        }

        repositoryRecipe.delete(recipe);
    }

    public Recipe searchByTitel(String titel) {
        Recipe recipe = repositoryRecipe.searchRecipeByTitel(titel);
        if (titel == null) {
            throw new ApiException("recipe not found");
        }
        return recipe;
    }
    public List<Recipe> searchByIngredientsContaining(String ingredients) {
        List<Recipe> recipes = repositoryRecipe.searchByIngredientsContaining(ingredients);
        if (recipes == null || recipes.isEmpty()) {
            throw new ApiException("No recipes found");
        }
        return recipes;
    }


    public List<Recipe> searchByCallories(double callorey) {
        List<Recipe> recipe = repositoryRecipe.searchRecipeByCallories(callorey);
        if (recipe == null) {
            throw new ApiException("recipe not found");
        }
        return recipe;
    }

    public List<Recipe> searchByRate(int rate) {
        List<Recipe> recipe = repositoryRecipe.searchRecipeByRate(rate);
        if (recipe == null) {
            throw new ApiException("recipe not found");
        }
        if (rate < 1 || rate > 5) {
            throw new ApiException(" invaild rate , should be between 1 to 5 ");
        }
        return recipe;
    }

    public List<Recipe> searchRecipeByCooktime(int cooktime) {
        List<Recipe> recipe = repositoryRecipe.searchRecipeByCooktime(cooktime);
        if (recipe == null ) {
            throw new ApiException("recipe not found");
        }
        return recipe;
    }
    public List<Recipe> findByType(String type) {
        List<Recipe> recipe = repositoryRecipe.findRecipeByType(type);
        if (recipe == null) {
            throw new ApiException("recipe not found");
        }
        return recipe;
    }

    public List<Recipe> searchRecipesByNotIngredient(String ingredientName) {
        List<Recipe> allRecipes = repositoryRecipe.findAll();
        allRecipes.removeIf(recipe -> recipe.getIngredients().contains(ingredientName));
        //use the removeIf method of the List to remove all recipes from the allRecipes list
        // contain the specified ingredientName.
        return allRecipes;
    }

    public void assignrecipetocategory(Integer r_id, Integer c_id) {
        Recipe recipe = repositoryRecipe.findRecipeById(r_id);
        Category category = repositoryCategory.findCategoriesById(c_id);

        if (recipe == null || category == null) {
            throw new ApiException("not found");
        }
        recipe.setCategory(category);
        repositoryRecipe.save(recipe);

    }
    public List<Recipe> checkAge(Integer id) {
        User user = repositoryUser.findUserById(id);
        if (user == null) {
            throw new ApiException("User not found");
        }
        if (user.getAge() >= 18) {
            return repositoryRecipe.findAll();
        }
        List<Recipe> recipes = repositoryRecipe.findAllByTypeIgnoreCase("kid");
        if (recipes.isEmpty()) {
            throw new ApiException("No suitable recipes found for kids");
        }
        return recipes;
    }


    public Recipe findByTitel(String title){
        Recipe recipe=repositoryRecipe.searchRecipeByTitel(title);
        if(recipe==null){
            throw new ApiException("not found");
        }
        return recipe;

    }

    public List<Recipe> findBychefname(String chefname) {
        List<Recipe> recipe = repositoryRecipe.searchRecipeByChefname(chefname);
        if (recipe == null) {
            throw new ApiException("chef not found");
        }
        return recipe;
    }


}
