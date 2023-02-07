package studentkitchen.studentkitchen.repository;

import org.springframework.data.repository.CrudRepository;
import studentkitchen.studentkitchen.model.Profile;
import studentkitchen.studentkitchen.model.User;

public interface ProfileRepository extends CrudRepository<Profile, Long> {
    public Profile findByUser(User user);    
}
