package com.powalteam.szopapka.web.service;

import com.powalteam.szopapka.web.api.dto.FamilyDTO;
import com.powalteam.szopapka.web.api.dto.FamilyMembersDTO;
import com.powalteam.szopapka.web.model.Family;

import java.util.List;

public interface FamilyService {

    public Family createFamily(FamilyDTO familyDTO, String userMail);

    FamilyMembersDTO getCompleteFamilyInfo(String userMail, String familyName);

    List<FamilyMembersDTO> getAllFamiliesWithMembers(String userMail);

    void joinFamilyByCode(String userEmail, String familyCode) throws Exception;
}
