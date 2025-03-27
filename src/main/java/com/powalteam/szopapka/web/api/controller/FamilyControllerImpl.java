package com.powalteam.szopapka.web.api.controller;

import com.powalteam.szopapka.web.api.dto.FamilyDTO;
import com.powalteam.szopapka.web.api.dto.FamilyMembersDTO;
import com.powalteam.szopapka.web.model.Family;
import com.powalteam.szopapka.web.model.FamilyMembers;
import com.powalteam.szopapka.web.service.FamilyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FamilyControllerImpl implements FamilyController {

    @Autowired
    private FamilyService familyService;

    @Override
    public Family createFamily(@ModelAttribute FamilyDTO familyDTO
    ) {
        return familyService.createFamily(familyDTO);
    }

    public List<FamilyMembersDTO> getFamily() {
        return familyService.getFamilyWithMembers();
    }

}