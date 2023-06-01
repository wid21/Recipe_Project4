package com.example.springbootproject4.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.constraints.*;
import lombok.*;
import java.util.Set;
@Entity
@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "role shouldn't be null")
   // @Column(columnDefinition = "varchar(15) check(role='user' or role='admin')")
    //@Pattern(regexp = "(user|admin)")
    private String role;

    @NotEmpty(message = "name shouldn't be empty")
    @Size(min = 5,message = "username should have at least 3 letters")
    private String username;

    @NotNull(message = "password shouldn't be empty")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d).+$", message = "Password must contain both letters and digits")
    private String password;

    @NotNull(message = "email shouldn't be null")
    @Email(message = "invalid email")
    @Column(columnDefinition = "varchar(50) not null unique")
    private String email;

    @NotNull(message = "age shouldn't be empty")
    private Integer age;

    @ManyToMany(mappedBy = "users")
    private Set<Recipe> recipes;


}
