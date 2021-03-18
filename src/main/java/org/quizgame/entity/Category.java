package org.quizgame.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Category {
    @Id @GeneratedValue
    private Long id;

    @NotBlank
    @Size(max = 128)
    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<SubCategory> subCategories;

    public Category(){
        this.subCategories = new ArrayList<>();
    };

    public List<SubCategory> getSubCategories() {
        return subCategories;
    }

    public void setSubCategory(SubCategory subCategory) {
        this.subCategories.add(subCategory);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
