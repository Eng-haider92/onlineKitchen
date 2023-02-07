package studentkitchen.studentkitchen.services;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import studentkitchen.studentkitchen.model.Recipe;
import studentkitchen.studentkitchen.repository.RecipeRepository;

@ExtendWith(MockitoExtension.class)
public class RecipeServiceTest {

    @Mock
    private RecipeRepository recipeRepository;

    @InjectMocks
    private RecipeServiceImp rServiceImp;

    private Recipe recipe;

    @BeforeEach
    public void setup(){
        recipe = new Recipe(1l,"hotRice",22.23, "hot rice", 30);       

    }


    @Test
    @DisplayName("JUnit test for add recipe")
    public void shouldAddRecipe(){
        given(recipeRepository.save(recipe)).willReturn(recipe);
        Recipe savedRecipe = rServiceImp.saveRecipe2(recipe);      ;
        assertThat(savedRecipe).isNotNull();
    }

    @Test
    @DisplayName("JUnit test for displays recipes")
    @Transactional
    public void shouldListAllRecipes(){
        Recipe recipe11 = new Recipe(null,"hotRice2",27, "hot rice", 30);       
        given(recipeRepository.findAll()).willReturn(List.of(recipe,recipe11));
        rServiceImp.saveRecipe2(recipe11);
        List<Recipe> recipeList = rServiceImp.findAllRecipes();

        assertThat(recipeList).isNotNull();
        assertThat(recipeList.size()).isEqualTo(2);
        
    }


    @DisplayName("JUnit test for view recipe")
    @Test
    public void shouldGetRecipe(){
        // given
        given(recipeRepository.findById(1L)).willReturn(Optional.of(recipe));

        // when
        rServiceImp.saveRecipe2(recipe);
        Recipe savedRecipe = rServiceImp.findRecipeById(recipe.getId());

        // then
        assertThat(savedRecipe).isNotNull();

    }

    @DisplayName("JUnit test for update recipe")
    @Test
    public void shouldUpdateRecipe(){
        // given - precondition or setup
        given(recipeRepository.save(recipe)).willReturn(recipe);
        recipe.setRecipeName("hotchicken");
        recipe.setId(recipe.getId());

        // when -  action or the behaviour that we are going test
        Recipe updatedRecipe = rServiceImp.saveRecipe2(recipe);

        // then - verify the output
        assertThat(updatedRecipe.getRecipeName()).isEqualTo("hotchicken");
    }


    @DisplayName("JUnit test for delete recipe")
    @Test
    public void shouldDeleteRecipe(){
        // given - precondition or setup
        long recipeId = 1L;

        willDoNothing().given(recipeRepository).deleteById(recipeId);

        // when -  action or the behaviour that we are going test
        rServiceImp.deleteRecipeById(recipeId);

        // then - verify the output
        verify(recipeRepository, times(1)).deleteById(recipeId);
    }

    
}
