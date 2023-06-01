package com.example.springbootproject4.Controller;

import com.example.springbootproject4.ApiException.ApiException;
import com.example.springbootproject4.Model.Recipe;
import com.example.springbootproject4.Model.User;
import com.example.springbootproject4.Repository.RepositoryRecipe;
import com.example.springbootproject4.Repository.RepositoryUser;
import com.example.springbootproject4.Service.ServiceRecipe;
import com.example.springbootproject4.Service.ServiceUser;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class ControllerUser {

    private final ServiceUser serviceUser;
    private final ServiceRecipe serviceRecipe;




    @GetMapping("/get-users/{id}")
    public ResponseEntity getALlMyUsers(@PathVariable Integer id) {
        List<User> users = serviceUser.getALlUsers(id);
        return ResponseEntity.status(200).body(users);
    }

    @PostMapping("/add")
    public ResponseEntity AddUsers(@Valid @RequestBody User user){
        serviceUser.AddUsers(user);
        return ResponseEntity.status(200).body("user added");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@Valid @RequestBody User user, @PathVariable Integer id){
        serviceUser.updateUser(user,id);
        return ResponseEntity.status(200).body("user updated");
    }

     @DeleteMapping("/delete/{id}")
      public ResponseEntity deleteuser(@PathVariable Integer id ){
        serviceUser.deleteUser(id);
        return ResponseEntity.status(200).body("user deleted");
}

    @PutMapping("/getcs/{r_id}/{u_id}")
    public ResponseEntity assigrecipetouser(@PathVariable Integer r_id, @PathVariable Integer u_id) {
        serviceUser.assighrecipetouser(r_id, u_id);
        return ResponseEntity.status(200).body("Recipe student ");

    }

    @GetMapping("/get/{u_id}")
    public ResponseEntity getuserCallory(@PathVariable Integer u_id){
        double cal=serviceUser.calculateAllCaloriesForUser(u_id);
        return ResponseEntity.status(200).body( "The total calories for all user recipe is " + cal);
    }

    @GetMapping("/login/{username}/{password}")
    public ResponseEntity check(@PathVariable String username,@PathVariable String password){
        User user=serviceUser.checkUsernamePassword(username,password);
        return ResponseEntity.status(200).body(user);
    }
    @PostMapping("/add-recipe")
    public ResponseEntity AddRecipe(@Valid @RequestBody Recipe recipe){
        serviceRecipe.addRecipe(recipe);
        return ResponseEntity.status(200).body("recipe added");
    }



}

