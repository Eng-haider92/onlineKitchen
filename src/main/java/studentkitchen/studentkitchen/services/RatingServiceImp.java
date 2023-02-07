package studentkitchen.studentkitchen.services;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import studentkitchen.studentkitchen.model.Rating;
import studentkitchen.studentkitchen.model.Recipe;
import studentkitchen.studentkitchen.model.User;
import studentkitchen.studentkitchen.repository.RatingRepository;
import studentkitchen.studentkitchen.repository.RecipeRepository;


@Service
public class RatingServiceImp implements RatingService {

    @Autowired
    RatingRepository ratingRepository;

    @Autowired
    RecipeRepository recipeRepository;


    @Override
    public void updateRating(Recipe recipe, User user, float rate) {

        Rating rating = ratingRepository.findByRecipeAndUser(recipe, user);
        if(rating != null){
            rating.setId(rating.getId());
            rating.setRating(rate);
            ratingRepository.save(rating);
        }
        else{
            rating = new Rating();
            rating.setRecipe(recipe);
            rating.setUser(user);
            rating.setRating(rate);
            ratingRepository.save(rating);
        }

        updateRecipeRating(recipe);

    }


    public void updateRecipeRating(Recipe recipe){
        Iterable<Rating> ratings = ratingRepository.findAllByRecipe(recipe);
        float numberOfStars = 0;
        float counter = 0;
        Iterator<Rating> iterator = ratings.iterator();
        while(iterator.hasNext()){
            numberOfStars+= iterator.next().getRating();
            counter++;
        }

        float realRating = numberOfStars/counter;
        recipe.setId(recipe.getId());
        recipe.setNumberOfStars(realRating);
        recipeRepository.save(recipe);
    }

    
}
