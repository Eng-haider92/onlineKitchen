package studentkitchen.studentkitchen.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import studentkitchen.studentkitchen.services.RecipeService;
import studentkitchen.studentkitchen.services.UserService;
import studentkitchen.studentkitchen.model.Recipe;
import studentkitchen.studentkitchen.model.User;


@RestController
public class ResController {

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private UserService userService;

    @GetMapping("/api/recipe/{id}")
    public String getRecipe(@PathVariable Long id){
        Recipe recipe = recipeService.findRecipeById(id);
        return recipe.toString();
    }

    @GetMapping("/api/recipes")
    public String getAllRecipes(){
        List<Recipe> recipes = recipeService.findAllRecipes();
        return recipes.toString();
    }

    
    @DeleteMapping("/api/delete/{id}")
    public void deleteRecipe(@PathVariable Long id){
        recipeService.deleteRecipeById(id);
    }

    
    @PutMapping("/api/updateRecipe/{id}")
    public String updateRecipe(@RequestBody Recipe recipe,@PathVariable Long id){
        Recipe r = recipeService.findRecipeById(id);
        r.setId(r.getId());
        r.setRecipeName(recipe.getRecipeName());
        r.setRecipeDescription(recipe.getRecipeDescription());
        r.setRecipePrice(recipe.getRecipePrice());
        recipeService.saveRecipe2(r);

        return r.toString();
    }


    @PostMapping("/api/users/addUser")
    public void addUser(@RequestBody User user){
           userService.saveUser(user);
        }
}
