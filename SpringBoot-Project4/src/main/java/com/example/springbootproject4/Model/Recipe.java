package com.example.springbootproject4.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id ;

    @NotEmpty(message = "chef name should not be empty ")
    @Column(columnDefinition ="varchar(15) not null" )
    private String chefname;

    @NotEmpty(message = "type should not be empty ")
    @Column(columnDefinition = "varchar(10) not null check(type='kid' or type='adult')")
    @Pattern(regexp = "(kid|adult)")
    private String type; ;

    @NotEmpty(message = "titel should not be empty ")
    @Column(columnDefinition = "varchar(50) not null")
    private String titel;

    @ElementCollection
    @NotEmpty(message = "ingredients should not be empty ")
    @Column(columnDefinition = "varchar(50) not null")
    private List<String> ingredients;

    @NotEmpty(message = "instructions should not be empty ")
    @Column(columnDefinition = "varchar(300) not null")
    private String instructions;

    @NotNull(message = "cooktime should not be empty ")
    @Column(columnDefinition ="int not null" )
    private int cooktime;

    @NotNull(message = "callories should not be empty")
    @Column(columnDefinition ="double not null" )
    private double callories;

    private int rate;


    @ManyToOne
    @JoinColumn(name="category_id" ,referencedColumnName = "id")
    @JsonIgnore
    private Category category;

    @ManyToMany
    @JsonIgnore
    private Set<User> users;








}
