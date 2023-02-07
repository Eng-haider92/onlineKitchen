package studentkitchen.studentkitchen.services;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import studentkitchen.studentkitchen.model.Profile;
import studentkitchen.studentkitchen.model.Role;
import studentkitchen.studentkitchen.model.User;
import studentkitchen.studentkitchen.repository.ProfileRepository;
import studentkitchen.studentkitchen.repository.RoleRepository;
import studentkitchen.studentkitchen.repository.UserRepository;


@Service("userService")
public class UserServiceImp implements UserService{


    @Autowired
    private UserRepository userRepository;

    @Autowired
    ProfileRepository profileRepository;

    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    
    @Override
    public User findUserByEmail(String email){
        User user = userRepository.findByEmail(email);
        return user;
    }
    
    @Override
    public void saveUser(User user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        Role userRole = roleRepository.findByRole("USER");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        Profile profile = new Profile();
        profile.setUser(user);
        profile.setProfileImage("general-profile.jpg");
        profileRepository.save(profile);        
        userRepository.save(user);        
    }
}
