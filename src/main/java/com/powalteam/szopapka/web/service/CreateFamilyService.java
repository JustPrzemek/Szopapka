package com.powalteam.szopapka.web.service;

import com.powalteam.szopapka.web.api.dto.FamilyDTO;
import com.powalteam.szopapka.web.model.Family;
import com.powalteam.szopapka.web.model.User;

import java.util.List;

public interface CreateFamilyService {
    Family createFamily(FamilyDTO familyDTO);
    List<User> getFamilyMembers(Long familyId);
    void addUserToFamily(Long familyId, Long userId);
}
