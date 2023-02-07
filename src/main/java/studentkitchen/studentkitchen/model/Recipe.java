package studentkitchen.studentkitchen.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;


import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;



@Entity
@Table(name="recipe")
public class Recipe {

    private Long id;
    private String recipeName;
    private double recipePrice;
    private String recipeDescription;
    private double recipeDuration;
    private String recipeImage;
    private String recipePreperation;
    private int numberOfVisits;
    private float numberOfStars;
    private Category category;
    private User user;
    private Set<Rating> rating;

    public Recipe(Long id, String recipeName, double recipePrice,
                  String recipeDescription, double recipeDuration){
        
        this.id = id;
        this.recipeName = recipeName;
        this.recipePrice = recipePrice;
        this.recipePreperation = recipeDescription;
        this.recipeDuration = recipeDuration;
        

    }

    public Recipe(){

    }



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "recipe_id")
    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    @Column(name = "recipe_name")
    public String getRecipeName(){
        return recipeName;
    }

    public void setRecipeName(String rName){
        this.recipeName = rName;       
    }

    @Column(name = "recipe_price")
    public double getRecipePrice(){
        return recipePrice;
    }

    public void setRecipePrice(double rPrice){
        this.recipePrice = rPrice;        
    }

    @Column(name = "recipe_duration")
    public double getRecipeDuration(){
        return recipeDuration;
    }

    public void setRecipeDuration(double rDur){
        this.recipeDuration = rDur;        
    }

    @Column(name = "recipe_image")
    public String getRecipeImage(){
        return recipeImage;
    }

    public void setRecipeImage(String img){
        this.recipeImage = img;        
    }

    @Column(name = "recipe_description")
    public String getRecipeDescription(){
        return recipeDescription;
    }

    public void setRecipeDescription(String rDesc){
        this.recipeDescription = rDesc;        
    }

    @Column(name = "recipe_preperation")
    public String getRecipePreperation(){
        return recipePreperation;
    }

    public void setRecipePreperation(String rPrep){
        this.recipePreperation = rPrep;        
    }

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "category_id")
    public Category getCategory(){
        return category;
    }

    public void setCategory(Category c){
        this.category = c;
    }

    @Column(name = "number_of_visits")
    public int getNumberOfVisits(){
        return numberOfVisits;
    }

    public void setNumberOfVisits(int v){
        this.numberOfVisits = v;
    }

    @Column(name = "number_of_stars")
    public float getNumberOfStars(){
        return numberOfStars;
    }

    public void setNumberOfStars(float v){
        this.numberOfStars = v;
    }

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    public User getUser(){
        return user;
    }

    public void setUser(User u){
        this.user = u;
    }

    @OneToMany(mappedBy = "recipe")
    public Set<Rating> getRating(){
        return rating;
    }

    public void setRating(Set<Rating> r){
        this.rating = r;
    }


   @Override
   public String toString() {
       return "Recipe{" + "\r\n" + "id=" + this.id + "\r\n"+ ", name='" + this.recipeName +
                  '\'' + "\r\n"+ ", price='" + this.recipePrice + '\'' +
                  '\'' + "\r\n"+ ", duration='" + this.recipeDuration + '\'' + 
                  '\'' + "\r\n"+ ", description='" + this.recipeDescription + '\'' +
                  '\'' + "\r\n"+ ", preperation='" + this.recipePreperation + '\'' +
                  '\'' + "\r\n"+ ", number of visits='" + this.numberOfVisits + '\'' +
                  '\'' + "\r\n"+ ", Rating='" + this.numberOfStars + '\'' +
                  '\'' + "\r\n"+ ", category='" + this.category.getCategoryName() + '\'' +
                  '\'' + "\r\n"+  ", owner='" + this.user.getUsername() + '\'' + "\r\n"+ '}';
  }
  
}
