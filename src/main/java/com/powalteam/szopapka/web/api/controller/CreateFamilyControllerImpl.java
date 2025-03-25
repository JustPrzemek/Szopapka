package com.powalteam.szopapka.web.api.controller;

import com.powalteam.szopapka.web.api.dto.FamilyDTO;
import com.powalteam.szopapka.web.model.Family;
import com.powalteam.szopapka.web.service.CreateFamilyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CreateFamilyControllerImpl implements CreateFamilyController {

    @Autowired
    private CreateFamilyService createFamilyService;

    @Override
    public Family createFamily(@ModelAttribute FamilyDTO familyDTO) {
        return createFamilyService.createFamily(familyDTO);
    }

}