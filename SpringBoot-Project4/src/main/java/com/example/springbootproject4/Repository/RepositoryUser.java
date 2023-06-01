package com.example.springbootproject4.Repository;

import com.example.springbootproject4.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryUser extends JpaRepository<User, Integer> {

    User findUserById(Integer id);

    User findUserByAge(int age);

    User findUserByUsernameAndPassword(String username, String passwprd);
}
