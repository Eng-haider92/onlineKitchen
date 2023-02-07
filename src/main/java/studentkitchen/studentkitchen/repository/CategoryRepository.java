package studentkitchen.studentkitchen.repository;

import org.springframework.data.repository.CrudRepository;
import studentkitchen.studentkitchen.model.Category;

public interface CategoryRepository extends CrudRepository<Category, Long>{
    public Category findByCategoryName(String name);

}
