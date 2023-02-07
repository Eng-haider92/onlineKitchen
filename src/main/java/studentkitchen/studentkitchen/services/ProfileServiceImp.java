package studentkitchen.studentkitchen.services;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import studentkitchen.studentkitchen.model.Profile;
import studentkitchen.studentkitchen.model.User;
import studentkitchen.studentkitchen.repository.ProfileRepository;


@Service
public class ProfileServiceImp implements ProfileService {


    @Autowired
    ProfileRepository profileRepository;


    @Override
    public void saveProfile(Profile profile, MultipartFile file, User user) throws IOException {
        String fileName;
        if(file.isEmpty()){
            fileName = "general-profile.jpg";     
        }else{
            fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        }

            profile.setUser(user);
            profile.setProfileImage(fileName);
            profile.setId(user.getProfile().getId());
            profileRepository.save(profile);

            String uploadDirectory = "src/main/resources/static/images/profiles/";
            saveFile(uploadDirectory, fileName, file);
    }

    
    public void saveFile(String uploadDirectory,String fileName,MultipartFile file) throws IOException {

        Path path = Paths.get(uploadDirectory);

        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }

        try (InputStream inputStream = file.getInputStream()) {
            Path filePath = path.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {
            throw new IOException("Could not save image file: " + fileName, ioe);
        }

    }

    
}
