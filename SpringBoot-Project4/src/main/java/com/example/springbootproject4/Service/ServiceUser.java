package com.example.springbootproject4.Service;

import com.example.springbootproject4.ApiException.ApiException;
import com.example.springbootproject4.Model.Recipe;
import com.example.springbootproject4.Model.User;
import com.example.springbootproject4.Repository.RepositoryRecipe;
import com.example.springbootproject4.Repository.RepositoryUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;



@Service
@RequiredArgsConstructor
public class ServiceUser {

    private final RepositoryUser repositoryUser;
    private final RepositoryRecipe repositoryRecipe;

    public List<User> getALlUsers(Integer id) {
        User user = repositoryUser.findUserById(id);
        if (user.getRole().equalsIgnoreCase("user")) {
            throw new ApiException("do not have the premonition to accesses the users ");
        }
        List<User> users = repositoryUser.findAll();
        return users;
    }

    public void AddUsers(User user) {
        repositoryUser.save(user);
    }

    public void updateUser(User user, Integer id) {
        User user1 = repositoryUser.findUserById(id);
        if (user1 == null) {
            throw new ApiException("not found");
        }
        user1.setUsername(user.getUsername());
        user1.setPassword(user.getPassword());

        repositoryUser.save(user1);
    }

    public void deleteUser(Integer idForUser) {
        User user = repositoryUser.findUserById(idForUser);
        if (user == null) {
            throw new ApiException("do not have user with this id");
        }
        repositoryUser.delete(user);
    }

    public void assighrecipetouser(Integer r_id, Integer u_id) {
        Recipe recipe = repositoryRecipe.findRecipeById(r_id);
        User user = repositoryUser.findUserById(u_id);
        if (recipe == null || user == null) {
            throw new ApiException("not found");
        }
        recipe.getUsers().add(user);
        user.getRecipes().add(recipe);

        repositoryUser.save(user);
        repositoryRecipe.save(recipe);
    }

    public double calculateAllCaloriesForUser(Integer u_id) {
        User user = repositoryUser.findUserById(u_id);
        double totalCalories = 0.0;
        for (Recipe recipe : user.getRecipes()) {
            totalCalories += recipe.getCallories();
        }
        return totalCalories;
    }

    public User checkUsernamePassword(String username, String password) {
        User user = repositoryUser.findUserByUsernameAndPassword(username, password);
        if (user == null) {
            throw new ApiException("not found");
        }
        return user;
    }



}






