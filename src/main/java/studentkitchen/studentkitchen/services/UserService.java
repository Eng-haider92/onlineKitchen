package studentkitchen.studentkitchen.services;


import studentkitchen.studentkitchen.model.User;


public interface UserService {
    public User findUserByEmail(String email);
    public void saveUser(User user);    
}
