package studentkitchen.studentkitchen.model;


import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.OneToMany;



@Entity
@Table(name = "category")
public class Category {

    private Long id;
    private String categoryName;
    private Set<Recipe> recipe;


    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    @Column(name = "category_id")
    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    @OneToMany(mappedBy = "category")
    public Set<Recipe> getRecipe(){
         return recipe;
    }
    
    public void setRecipe(Set<Recipe> r){
        this.recipe = r;
    }

    @Column(name = "category_name")
    public String getCategoryName(){
        return categoryName;
    }

    public void setCategoryName(String cName){
        this.categoryName = cName;
    }

    
}
