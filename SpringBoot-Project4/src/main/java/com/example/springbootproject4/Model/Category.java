package com.example.springbootproject4.Model;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;

@Entity
@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


   @NotEmpty(message = "name shouldn't be empty")
   @Size(min=3,message = "name should have at least 3 letters")
    private String name;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "category")
    private Set<Recipe> recipes;

}
