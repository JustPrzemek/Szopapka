package com.powalteam.szopapka.web.api.controller;

import com.powalteam.szopapka.web.api.dto.FamilyDTO;
import com.powalteam.szopapka.web.api.dto.FamilyMembersDTO;
import com.powalteam.szopapka.web.model.Family;
import com.powalteam.szopapka.web.model.JoinFamilyRequest;
import com.powalteam.szopapka.web.service.FamilyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FamilyControllerImpl implements FamilyController {

    @Autowired
    private FamilyService familyService;

    @Override
    public Family createFamily(@ModelAttribute FamilyDTO familyDTO
    ) {
        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();
        String mail = (String) authentication.getPrincipal();
        return familyService.createFamily(familyDTO, mail);

    }

    @Override
    public FamilyMembersDTO getFamilyWithMembers() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String mail = authentication.getName();
        return familyService.getCompleteFamilyInfo(mail);
    }

    @Override
    public String joinFamilyByCode(@RequestBody JoinFamilyRequest request) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        familyService.joinFamilyByCode(userEmail, request.getFamilyCode());
        return "Successfully joined family";
    }

}
