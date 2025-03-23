package com.powalteam.szopapka.web.api.controller;

import com.powalteam.szopapka.web.api.dto.FamilyDTO;
import com.powalteam.szopapka.web.model.Family;
import com.powalteam.szopapka.web.model.User;
import com.powalteam.szopapka.web.service.CreateFamilyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CreateFamilyControllerImpl implements CreateFamilyController {

    @Autowired
    private CreateFamilyService createFamilyService;

    @Override
    public Family createFamily(FamilyDTO familyDTO) {
        return createFamilyService.createFamily(familyDTO);
    }

    @Override
    public List<User> getFamilyMembers(@PathVariable Long familyId) {
        return createFamilyService.getFamilyMembers(familyId);
    }

    @Override
    public void addUserToFamily(@PathVariable Long familyId, @PathVariable Long userId) {
        createFamilyService.addUserToFamily(familyId, userId);
    }
}