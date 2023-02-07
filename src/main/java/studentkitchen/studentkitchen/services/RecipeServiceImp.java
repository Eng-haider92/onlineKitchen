package studentkitchen.studentkitchen.services;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import studentkitchen.studentkitchen.model.Recipe;
import studentkitchen.studentkitchen.model.User;
import studentkitchen.studentkitchen.repository.CategoryRepository;
import studentkitchen.studentkitchen.repository.RecipeRepository;


@Service
public class RecipeServiceImp implements RecipeService{


    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public Recipe findRecipeByName(String name){
        Recipe r = recipeRepository.findByRecipeName(name);
        return r;
    }

    @Override
    public void saveRecipe(Recipe recipe,
                           MultipartFile file, 
                           String categoryName, 
                           User user) throws IOException{

        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

        recipe.setRecipeImage(fileName);
        recipe.setCategory(categoryRepository.findByCategoryName(categoryName));
        recipe.setUser(user);
        if(recipe.getId()!=null){
            recipe.setId(recipe.getId());
            recipe.setNumberOfVisits(recipe.getNumberOfVisits());
        }
        else{
            recipe.setNumberOfVisits(0);
        }
        recipeRepository.save(recipe);

        String uploadDirectory = "src/main/resources/static/images/recipes/" + recipe.getId();
        saveFile(uploadDirectory, fileName, file);
    }

    
    public void saveFile(String uploadDirectory,String fileName,MultipartFile file) throws IOException {

        Path path = Paths.get(uploadDirectory);

        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }

        try (InputStream inputStream = file.getInputStream()) {
            Path filePath = path.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {
            throw new IOException("Could not save image file: " + fileName, ioe);
        }

    }

    @Override
    public Recipe updateRecipeViewers(Long id) {
        Recipe currentRecipe = recipeRepository.findById(id).get();
        currentRecipe.setId(id);
        int counter = currentRecipe.getNumberOfVisits();
        counter++;
        currentRecipe.setNumberOfVisits(counter);
        recipeRepository.save(currentRecipe);
        return currentRecipe;
    }

    @Override
    public Recipe saveRecipe2(Recipe r) {
         recipeRepository.save(r);
        return r;
    }

    @Override
    public List<Recipe> findAllRecipes() {
        List<Recipe> alRecipes = recipeRepository.findAll();
        return alRecipes;
    }

    @Override
    public Recipe findRecipeById(Long id) {
        Recipe r = recipeRepository.findById(id).get();
        return r;
    }

    @Override
    public void deleteRecipeById(long id) {
          recipeRepository.deleteById(id);        
    }
   
}
