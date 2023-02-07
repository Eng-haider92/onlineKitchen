package studentkitchen.studentkitchen.services;


import studentkitchen.studentkitchen.model.Recipe;
import studentkitchen.studentkitchen.model.User;


public interface RatingService {
    public void updateRating(Recipe r, User u, float rate);
}
