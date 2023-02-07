package studentkitchen.studentkitchen.services;


import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import studentkitchen.studentkitchen.model.Recipe;
import studentkitchen.studentkitchen.model.User;



public interface RecipeService {
    public List<Recipe> findAllRecipes();
    public Recipe saveRecipe2(Recipe r);
    public Recipe findRecipeByName(String name);
    public void saveRecipe( Recipe recipe, 
                            MultipartFile file,
                            String category, 
                            User user) throws IOException;
    public Recipe updateRecipeViewers(Long id);
    
    public Recipe findRecipeById(Long id);

    public void deleteRecipeById(long id);
}
