package studentkitchen.studentkitchen.services;


import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import studentkitchen.studentkitchen.model.Profile;
import studentkitchen.studentkitchen.model.User;


public interface ProfileService {
    public void saveProfile( Profile profile, MultipartFile file, User user) throws IOException;    
}
