package com.powalteam.szopapka.web.service;

import com.powalteam.szopapka.web.api.dto.FamilyDTO;
import com.powalteam.szopapka.web.api.dto.FamilyMembersDTO;
import com.powalteam.szopapka.web.api.mapper.FamilyMembersMapper;
import com.powalteam.szopapka.web.model.Family;

import com.powalteam.szopapka.web.model.FamilyMembersView;
import com.powalteam.szopapka.web.model.User;
import com.powalteam.szopapka.web.model.UserInFamily;
import com.powalteam.szopapka.web.repository.FamilyMembersRepository;
import com.powalteam.szopapka.web.repository.FamilyRepository;
import com.powalteam.szopapka.web.repository.UserInFamilyRepository;
import com.powalteam.szopapka.web.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;


@Service
public class FamilyServiceImpl implements FamilyService {

    @Autowired
    private FamilyRepository familyRepository;

    @Autowired
    private FamilyMembersRepository familyMembersRepository;

    @Autowired
    private FamilyMembersMapper familyMembersMapper;

    @Autowired
    private UserInFamilyRepository userInFamilyRepository;

    @Autowired
    private UserRepository userRepository;

    @Value("${file.upload-dir}")
    private String uploadDir;

    public Family createFamily(FamilyDTO familyDTO, String userMail) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();

        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

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
                throw new RuntimeException("Error File", e);
            }
        }
        newFamily.setFamilyCode(generateFamilyCode());

        Family savedFamily = familyRepository.save(newFamily);

        UserInFamily userInFamily = new UserInFamily();
        userInFamily.setUserId(user.getId());
        userInFamily.setFamilyId(savedFamily.getId());
        userInFamilyRepository.save(userInFamily);

        return savedFamily;


    }
    private String generateFamilyCode() {
        return UUID.randomUUID().toString().substring(0, 5).toUpperCase();
    }

    @Override
    public List<FamilyMembersDTO> getFamilyWithMembers(String userEmail) {
        List<FamilyMembersView> members = familyMembersRepository.findByUserEmail(userEmail);
        return familyMembersMapper.toDtoList(members);
    }
}



