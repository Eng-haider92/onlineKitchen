package studentkitchen.studentkitchen.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "rating")
public class Rating implements Serializable{

    private Long id;
    private Recipe recipe;
    private User user;
    private float rating;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "rating_id")
    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    public Recipe getRecipe(){
        return recipe;
    }

    public void setRecipe(Recipe r){
        this.recipe = r;
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    public User getUser(){
        return user;
    }

    public void setUser(User u){
        this.user = u;
    }



    @Column(name = "rating")
    public float getRating(){
        return rating;
    }

    public void setRating(float r){
        this.rating = r;
    }
    
}
