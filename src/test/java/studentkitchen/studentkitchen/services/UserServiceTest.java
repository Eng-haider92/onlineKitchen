package studentkitchen.studentkitchen.services;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

import javax.transaction.Transactional;

import studentkitchen.studentkitchen.model.User;
import studentkitchen.studentkitchen.repository.UserRepository;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;



    
    @Test
    @Transactional
    public void saveUserTesting(){
        User user = new User(null,"ram22", "ram@email.com", "12345", 1);       
        userRepository.save(user);
        User foundUser = userService.findUserByEmail("ram@email.com");
        assertThat(foundUser).usingRecursiveComparison().ignoringFields("user_id").isEqualTo(user);
    }

    @Test
    public void findUserByEmail(){
        User user = new User(null,"ram22", "ram2@email.com", "12345", 1);       
        userRepository.save(user);
        User userFound = userService.findUserByEmail("ram2@email.com");

        assertThat(userFound.getEmail()).isEqualTo(user.getEmail());
    }

    
}
