package com.powalteam.szopapka.web.service;

import com.powalteam.szopapka.web.api.dto.FamilyDTO;
import com.powalteam.szopapka.web.api.dto.FamilyMembersDTO;
import com.powalteam.szopapka.web.api.mapper.FamilyMembersMapper;
import com.powalteam.szopapka.web.model.Family;

import com.powalteam.szopapka.web.repository.FamilyMembersRepository;
import com.powalteam.szopapka.web.repository.FamilyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;


@Service
public class FamilyServiceImpl implements FamilyService {

    @Autowired
    private FamilyRepository familyRepository;

    @Autowired
    private FamilyMembersRepository familyMembersRepository;

    @Autowired
    private FamilyMembersMapper familyMembersMapper;

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
    public List<FamilyMembersDTO> getFamilyWithMembers() {
        return familyMembersRepository.findAll().stream()
                .map(familyMembersMapper::toDTO)
                .collect(Collectors.toList());
    }
}


