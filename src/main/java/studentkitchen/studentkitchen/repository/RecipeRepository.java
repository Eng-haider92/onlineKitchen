package studentkitchen.studentkitchen.repository;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

import studentkitchen.studentkitchen.model.Category;
import studentkitchen.studentkitchen.model.Recipe;
import studentkitchen.studentkitchen.model.User;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
    
    public Recipe findByRecipeName(String name);
    public Iterable<Recipe> findAllByCategory(Category c);
    public Iterable<Recipe> findAllByUser(User u);   
    public List<Recipe> findAllByOrderByRecipePriceAsc();
    public List<Recipe> findAllByOrderByRecipeDurationAsc();
    public List<Recipe> findAllByOrderByNumberOfVisitsDesc();
    public List<Recipe> findByRecipeNameContainingIgnoreCase(String rName);
    public List<Recipe> findAll();

    
}
