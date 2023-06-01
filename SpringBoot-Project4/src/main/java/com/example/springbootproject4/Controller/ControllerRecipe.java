package com.example.springbootproject4.Controller;

import com.example.springbootproject4.ApiResponse.ApiResponse;
import com.example.springbootproject4.Model.Recipe;
import com.example.springbootproject4.Service.ServiceRecipe;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("api/v1/recipe")
@RequiredArgsConstructor
public class ControllerRecipe {

    private final ServiceRecipe serviceRecipe;

    @GetMapping("/get")
    public ResponseEntity getAllRecipes() {
        List<Recipe> recipes = serviceRecipe.getRecipe();
        return ResponseEntity.status(200).body(recipes);
    }

    @PostMapping("/add")
    public ResponseEntity addRecipes(@Valid @RequestBody Recipe recipe) {
        serviceRecipe.addRecipe(recipe);
        return ResponseEntity.status(200).body(new ApiResponse("recipe added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateRecipe(@Valid @RequestBody Recipe recipe, @PathVariable Integer id) {
        serviceRecipe.updateRecipe(recipe, id);
        return ResponseEntity.status(200).body("recipe Updated");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteRecipe(@PathVariable Integer id) {
        serviceRecipe.deleteRecipe(id);
        return ResponseEntity.status(200).body("recipe deleted");
    }

    @GetMapping("/get-titel/{titel}")
    public ResponseEntity searchbytitel(@PathVariable String titel) {
        Recipe recipe = serviceRecipe.searchByTitel(titel);
        return ResponseEntity.status(200).body(recipe);
    }

    @GetMapping("/get-ingredient/{ingredient}")
    public ResponseEntity searchbyingredient(@PathVariable String ingredient ) {
        List<Recipe> recipe = serviceRecipe.searchByIngredientsContaining(ingredient);
        return ResponseEntity.status(200).body(recipe);
    }

    @GetMapping("/get-callory/{callory}")
    public ResponseEntity searchByCallories(@PathVariable double callory) {
        List<Recipe> recipe = serviceRecipe.searchByCallories(callory);
        return ResponseEntity.status(200).body(recipe);
    }

    @GetMapping("/get-rate/{rate}")
    public ResponseEntity searchByRate(@PathVariable int rate) {
        List<Recipe> recipe = serviceRecipe.searchByRate(rate);
        return ResponseEntity.status(200).body(recipe);
    }

    @GetMapping("/get-cooktime/{cooktime}")
    public ResponseEntity searchRecipeByCooktime(@PathVariable int cooktime) {
        List<Recipe> recipe = serviceRecipe.searchRecipeByCooktime(cooktime);
        return ResponseEntity.status(200).body(recipe);
    }

    @GetMapping("/get-type/{type}")
    public ResponseEntity getbytype(@PathVariable String type) {
        List<Recipe> recipe = serviceRecipe.findByType(type);
        return ResponseEntity.status(200).body(recipe);
    }

    @GetMapping("/get-not/{ingredientName}")
     public ResponseEntity getnoting(@PathVariable String ingredientName) {
       List<Recipe> recipe = serviceRecipe.searchRecipesByNotIngredient(ingredientName);
        return ResponseEntity.status(200).body(recipe);
    }
    @PutMapping("/addrectocate/{r_id}/{c_id}")
    public ResponseEntity getCourserTeacher(@PathVariable Integer r_id , @PathVariable Integer c_id) {
        serviceRecipe.assignrecipetocategory(r_id, c_id);
        return ResponseEntity.status(200).body(new ApiResponse("Recipe added to category "));
    }

    @GetMapping("/get-age/{id}")
    public ResponseEntity getbyage(@PathVariable Integer id) {
        List<Recipe> recipe = serviceRecipe.checkAge(id);
        return ResponseEntity.status(200).body(recipe);
    }


    @GetMapping("/get-chef/{chefname}")
    public ResponseEntity findAllBychefnameT(@PathVariable String chefname){
        List<Recipe> find= serviceRecipe.findBychefname(chefname);
        return ResponseEntity.status(200).body(find);
    }
}
