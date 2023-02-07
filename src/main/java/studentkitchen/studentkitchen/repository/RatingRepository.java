package studentkitchen.studentkitchen.repository;

import org.springframework.data.repository.CrudRepository;
import studentkitchen.studentkitchen.model.Rating;
import studentkitchen.studentkitchen.model.Recipe;
import studentkitchen.studentkitchen.model.User;


public interface RatingRepository extends CrudRepository<Rating, Long> {
    public Rating findByRecipeAndUser(Recipe r, User u);
    public Iterable<Rating> findAllByRecipe(Recipe r);
}