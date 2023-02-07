package studentkitchen.studentkitchen.controller;



import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import studentkitchen.studentkitchen.model.Category;
import studentkitchen.studentkitchen.model.Profile;
import studentkitchen.studentkitchen.model.Recipe;
import studentkitchen.studentkitchen.model.User;
import studentkitchen.studentkitchen.repository.CategoryRepository;
import studentkitchen.studentkitchen.repository.ProfileRepository;
import studentkitchen.studentkitchen.repository.RecipeRepository;
import studentkitchen.studentkitchen.services.ProfileService;
import studentkitchen.studentkitchen.services.RatingService;
import studentkitchen.studentkitchen.services.RecipeService;
import studentkitchen.studentkitchen.services.UserService;

/*###########################################################

###########################################################*/
@Controller
public class HomeController {


	@Autowired
	private RecipeService recipeService;

	@Autowired
    private UserService userService;

	@Autowired
    private ProfileService profileService;

	@Autowired
	private RatingService ratingService;

	@Autowired
	private ProfileRepository profileRepository;

	@Autowired
	private RecipeRepository recipeRepository;

	@Autowired
	private CategoryRepository categoryRepository;



	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() {
		return "landPage";
	}


	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String getHomePage(Model model, Principal principal) {


		User user = userService.findUserByEmail(principal.getName());
		Profile profile = profileRepository.findByUser(user);

		model.addAttribute("profileImage", profile.getProfileImage());
		model.addAttribute("username", user.getUsername());
		model.addAttribute("categories", categoryRepository.findAll());
		return "home";
	}




	@RequestMapping(value = "/addRecipe", method = RequestMethod.GET)
	public String getRecipeForm(Model model, Principal principal){
		User user = userService.findUserByEmail(principal.getName());
		Profile profile = profileRepository.findByUser(user);

		model.addAttribute("profileImage", profile.getProfileImage());
		model.addAttribute("recipe", new Recipe());
		model.addAttribute("categories", categoryRepository.findAll());
		model.addAttribute("username", user.getUsername());
		return "recipeForm";
	}


	@RequestMapping(value = "/editRecipe{id}", method = RequestMethod.GET)
	public String editRecipe(@PathVariable Long id, Model model, Principal principal){
		User user = userService.findUserByEmail(principal.getName());
		Profile profile = profileRepository.findByUser(user);
		Recipe recipe = recipeRepository.findById(id).get();


		model.addAttribute("profileImage", profile.getProfileImage());
		model.addAttribute("recipe", recipe);
		model.addAttribute("categories", categoryRepository.findAll());
		model.addAttribute("username", user.getUsername());
		return "recipeForm";
	}


	@RequestMapping(value = "/addRecipe", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE )
	public String addRecipe(@Validated Recipe recipe,
	                        BindingResult bindingResult,
	                        @RequestParam("categoryName") String categoryName,
	                        @RequestPart("imageFile") MultipartFile file, 
							Model model,
							Principal principal){
		            
		String recipeOwner = principal.getName();
		User user = userService.findUserByEmail(recipeOwner);
		Profile profile = profileRepository.findByUser(user);
		model.addAttribute("categories", categoryRepository.findAll());
		model.addAttribute("profileImage", profile.getProfileImage());
		model.addAttribute("username", user.getUsername());
		if (file.isEmpty()) {
            model.addAttribute("message", "Please select a file to upload.");
			return "recipeForm";
        }
		else{
			if(bindingResult.hasErrors()){
				return "recipeForm";
				}
			else{
				try {
					recipeService.saveRecipe(recipe, file, categoryName, user);
				} catch (IOException e) {
					e.printStackTrace();
				}
				return "redirect:/recipes";
				}
			}			
		
    }

	



	@RequestMapping(value = "/recipes", method = RequestMethod.GET)
	public String getAllRecipes(Model model, Principal principal) {
		User user = userService.findUserByEmail(principal.getName());
		Profile profile = profileRepository.findByUser(user);

		model.addAttribute("profileImage", profile.getProfileImage());
		model.addAttribute("username", user.getUsername());
		model.addAttribute("recipes", recipeRepository.findAll());
		model.addAttribute("categories", categoryRepository.findAll());
		return "allRecipes";
	}


	@RequestMapping(value = "/SortByPrice", method = RequestMethod.GET)
	public String sortRecipesByPrice(Model model, Principal principal) {
		User user = userService.findUserByEmail(principal.getName());
		Profile profile = profileRepository.findByUser(user);
		List<Recipe> r = recipeRepository.findAllByOrderByRecipePriceAsc();

		model.addAttribute("profileImage", profile.getProfileImage());
		model.addAttribute("username", user.getUsername());
		model.addAttribute("recipes", r);
		model.addAttribute("categories", categoryRepository.findAll());
		model.addAttribute("action", "view");
		return "recipes";
	}

	

	@RequestMapping(value = "/SortByDuration", method = RequestMethod.GET)
	public String sortRecipesByDuration(Model model, Principal principal) {
		User user = userService.findUserByEmail(principal.getName());
		Profile profile = profileRepository.findByUser(user);
		List<Recipe> r = recipeRepository.findAllByOrderByRecipeDurationAsc();

		model.addAttribute("profileImage", profile.getProfileImage());
		model.addAttribute("username", user.getUsername());
		model.addAttribute("recipes", r);
		model.addAttribute("categories", categoryRepository.findAll());
		model.addAttribute("action", "view");
		return "recipes";
	}

	@RequestMapping(value = "/SortByPopular", method = RequestMethod.GET)
	public String sortRecipesByPopular(Model model, Principal principal) {
		User user = userService.findUserByEmail(principal.getName());
		Profile profile = profileRepository.findByUser(user);
		List<Recipe> r = recipeRepository.findAllByOrderByNumberOfVisitsDesc();

		model.addAttribute("profileImage", profile.getProfileImage());
		model.addAttribute("username", user.getUsername());
		model.addAttribute("recipes", r);
		model.addAttribute("categories", categoryRepository.findAll());
		model.addAttribute("action", "view");
		return "recipes";
	}


	@RequestMapping(value = "/FilterByRice", method = RequestMethod.GET)
	public String filterByRice(Model model, Principal principal) {
		User user = userService.findUserByEmail(principal.getName());
		Profile profile = profileRepository.findByUser(user);
		List<Recipe> r = recipeRepository.findByRecipeNameContainingIgnoreCase("rice");

		model.addAttribute("profileImage", profile.getProfileImage());
		model.addAttribute("username", user.getUsername());
		model.addAttribute("recipes", r);
		model.addAttribute("categories", categoryRepository.findAll());
		return "allRecipes";
	}

	@RequestMapping(value = "/FilterByChicken", method = RequestMethod.GET)
	public String filterByChicken(Model model, Principal principal) {
		User user = userService.findUserByEmail(principal.getName());
		Profile profile = profileRepository.findByUser(user);
		List<Recipe> r = recipeRepository.findByRecipeNameContainingIgnoreCase("chicken");

		model.addAttribute("profileImage", profile.getProfileImage());
		model.addAttribute("username", user.getUsername());
		model.addAttribute("recipes", r);
		model.addAttribute("categories", categoryRepository.findAll());
		return "allRecipes";
	}

	@RequestMapping(value = "/FilterBySalad", method = RequestMethod.GET)
	public String filterBySalad(Model model, Principal principal) {
		User user = userService.findUserByEmail(principal.getName());
		Profile profile = profileRepository.findByUser(user);
		List<Recipe> r = recipeRepository.findByRecipeNameContainingIgnoreCase("salad");

		model.addAttribute("profileImage", profile.getProfileImage());
		model.addAttribute("username", user.getUsername());
		model.addAttribute("recipes", r);
		model.addAttribute("categories", categoryRepository.findAll());
		return "allRecipes";
	}

	@RequestMapping(value = "/FilterByFish", method = RequestMethod.GET)
	public String FilterByFish(Model model, Principal principal) {
		User user = userService.findUserByEmail(principal.getName());
		Profile profile = profileRepository.findByUser(user);
		List<Recipe> r = recipeRepository.findByRecipeNameContainingIgnoreCase("fish");

		model.addAttribute("profileImage", profile.getProfileImage());
		model.addAttribute("username", user.getUsername());
		model.addAttribute("recipes", r);
		model.addAttribute("categories", categoryRepository.findAll());
		return "allRecipes";
	}



	@RequestMapping(value = "/myRecipes", method = RequestMethod.GET)
	public String getMyRecipes(Model model, Principal principal) {
		User user = userService.findUserByEmail(principal.getName());
		Profile profile = profileRepository.findByUser(user);

		model.addAttribute("profileImage", profile.getProfileImage());
		model.addAttribute("username", user.getUsername());
		model.addAttribute("recipes", recipeRepository.findAllByUser(user));
		model.addAttribute("categories", categoryRepository.findAll());
		model.addAttribute("action", "own");

		return "recipes";
	}


	
	@RequestMapping(value = "/favRecipe", method = RequestMethod.GET)
	public String getFavRecipes(Model model, Principal principal) {
		User user = userService.findUserByEmail(principal.getName());
		Profile profile = profileRepository.findByUser(user);
		Iterable<Recipe> favRecipes = profile.getRecipe();
		
		model.addAttribute("profileImage", profile.getProfileImage());
		model.addAttribute("username", user.getUsername());
		model.addAttribute("recipes", favRecipes);
		model.addAttribute("categories", categoryRepository.findAll());
		model.addAttribute("action", "fav");
		return "recipes";
	}


	@RequestMapping(value = "/addToFavorite{id}", method = RequestMethod.GET)
	public String addRecipeToFavrite(@PathVariable Long id, Principal principal, Model model){		
		User user = userService.findUserByEmail(principal.getName());
		Profile profile = profileRepository.findByUser(user);
		Recipe recipe = recipeRepository.findById(id).get();
		profile.getRecipe().add(recipe);
		profile.setRecipe(profile.getRecipe());
		profile.setId(profile.getId());
		profileRepository.save(profile);

		model.addAttribute("success", "recipe has beed added to favorite");
		return "redirect:/favRecipe";
	}

	@RequestMapping(value = "/removeFav{id}", method = RequestMethod.GET)
	public String removeRecipeToFavrite(@PathVariable Long id, Principal principal, Model model){		
		User user = userService.findUserByEmail(principal.getName());
		Profile profile = profileRepository.findByUser(user);
		Recipe recipe = recipeRepository.findById(id).get();
		profile.getRecipe().remove(recipe);
		profile.setRecipe(profile.getRecipe());
		profile.setId(profile.getId());
		profileRepository.save(profile);

		model.addAttribute("success", "recipe has beed added to favorite");
		return "redirect:/favRecipe";
	}




	@RequestMapping(value = "/rateRecipe{id}", method = RequestMethod.POST)
	public String rateRecipe(@PathVariable Long id,
	         	             @RequestParam(name = "rating") String rating,
							 Principal principal, Model model){		
		User user = userService.findUserByEmail(principal.getName());
		Recipe recipe = recipeRepository.findById(id).get();
		float rate = Float.valueOf(rating);
		ratingService.updateRating(recipe, user, rate);

		model.addAttribute("success", "recipe has been rated!");
		return "redirect:/favRecipe";
	}


	@RequestMapping(value = "/recipesDetails{id}", method = RequestMethod.GET)
	public String getRecipeDetails(@PathVariable Long id, Model model, Principal principal) {
		User user = userService.findUserByEmail(principal.getName());
		Profile profile = profileRepository.findByUser(user);
		Recipe currentRecipe = recipeService.updateRecipeViewers(id);


		model.addAttribute("profileImage", profile.getProfileImage());
		model.addAttribute("recipe", currentRecipe);
		model.addAttribute("categoryName", currentRecipe.getCategory().getCategoryName());
		model.addAttribute("username", user.getUsername());
		model.addAttribute("categories", categoryRepository.findAll());
		return "details";
	}

	@RequestMapping(value = "/deleteRecipe{id}", method = RequestMethod.GET)
	public String deleteRecipe(@PathVariable Long id, Model model, Principal principal) {
		Recipe currentRecipe = recipeRepository.findById(id).get();
		recipeRepository.delete(currentRecipe);
		
		return "redirect:/myRecipes";
	}




	@RequestMapping(value = "/category{id}", method = RequestMethod.GET)
	public String getRecipesByCategory(@PathVariable Long id, Model model, Principal principal) {
		User user = userService.findUserByEmail(principal.getName());
		Optional<Category> g = categoryRepository.findById(id);
		Profile profile = profileRepository.findByUser(user);

		model.addAttribute("profileImage", profile.getProfileImage());
		model.addAttribute("recipes", recipeRepository.findAllByCategory(g.get()));
		model.addAttribute("username", user.getUsername());
		model.addAttribute("categories", categoryRepository.findAll());
		model.addAttribute("action", "view");
		return "recipes";
	}




	@RequestMapping(value = "/editProfile", method = RequestMethod.GET)
	public String getProfileForm(Model model, Principal principal){
		User user = userService.findUserByEmail(principal.getName());
		Profile profile = profileRepository.findByUser(user);

		model.addAttribute("profileImage", profile.getProfileImage());
		model.addAttribute("profile", profile);
		model.addAttribute("categories", categoryRepository.findAll());
		model.addAttribute("username", user.getUsername());
		return "profileForm";
	}

	


	@RequestMapping(value = "/editProfile", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE )
	public String updateProfile(@Valid Profile profile,
	                            @RequestPart("imageFile") MultipartFile file, 
							    Principal principal){      
		User user = userService.findUserByEmail(principal.getName());
        try {
			profileService.saveProfile(profile, file, user);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "redirect:/home";
	}


	@RequestMapping(value = "/viewProfile", method = RequestMethod.GET)
	public String viewProfile(Principal principal, Model model){      
		User user = userService.findUserByEmail(principal.getName());
		Profile profile = profileRepository.findByUser(user);

		model.addAttribute("profileImage", profile.getProfileImage());
		model.addAttribute("profile", profile);
		model.addAttribute("username", user.getUsername());
		model.addAttribute("categories", categoryRepository.findAll());
		return "profile";
	}
		

}