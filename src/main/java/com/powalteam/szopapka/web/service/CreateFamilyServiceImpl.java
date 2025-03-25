package com.powalteam.szopapka.web.service;

import com.powalteam.szopapka.web.api.dto.FamilyDTO;
import com.powalteam.szopapka.web.model.Family;
import com.powalteam.szopapka.web.model.User;
import com.powalteam.szopapka.web.model.UserInFamily;

import com.powalteam.szopapka.web.repository.FamilyRepository;
import com.powalteam.szopapka.web.repository.UserInFamilyRepository;
import com.powalteam.szopapka.web.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class CreateFamilyServiceImpl implements CreateFamilyService {

    @Autowired
    private FamilyRepository familyRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserInFamilyRepository userInFamilyRepository;

    @Value("${file.upload-dir}")
    private String uploadDir;

    public Family createFamily(FamilyDTO familyDTO) {
        Family newFamily = new Family();
        newFamily.setFamilyName(familyDTO.getFamilyName());

        MultipartFile imageFile = familyDTO.getImage();
        if (imageFile != null && !imageFile.isEmpty()) {
            try {
                String fileName = System.currentTimeMillis() + "_" + imageFile.getOriginalFilename();
                Path filePath = Paths.get(uploadDir, fileName);
                Files.createDirectories(filePath.getParent());
                Files.write(filePath, imageFile.getBytes());
                newFamily.setImage(fileName);
            } catch (Exception e) {
                throw new RuntimeException("Błąd zapisu pliku", e);
            }
        }

        return familyRepository.save(newFamily);
    }

    @Override
    public List<User> getFamilyMembers(Long familyId) {
        return userInFamilyRepository.findByFamilyId(familyId)
                .stream()
                .map(UserInFamily::getUser)
                .collect(Collectors.toList());
    }

    @Override
    public void addUserToFamily(Long familyId, Long userId) {
        if (userInFamilyRepository.existsByUserIdAndFamilyId(userId, familyId)) {
            throw new RuntimeException("User already in family");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Family family = familyRepository.findById(familyId)
                .orElseThrow(() -> new RuntimeException("Family not found"));

        UserInFamily userInFamily = new UserInFamily();
        userInFamily.setUser(user);
        userInFamily.setFamily(family);

        userInFamilyRepository.save(userInFamily);
    }

}
