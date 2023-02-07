package studentkitchen.studentkitchen.repository;

import org.springframework.data.repository.CrudRepository;
import studentkitchen.studentkitchen.model.Role;



public interface RoleRepository  extends CrudRepository<Role , Integer>{

	Role findByRole(String string);
	void deleteByRole(String string);

}