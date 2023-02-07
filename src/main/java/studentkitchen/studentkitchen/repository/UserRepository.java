package studentkitchen.studentkitchen.repository;

import org.springframework.data.repository.CrudRepository;
import studentkitchen.studentkitchen.model.User;


public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);
	User findByUsername(String name);
    
}
