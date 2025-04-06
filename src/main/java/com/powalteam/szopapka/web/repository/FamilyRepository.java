package com.powalteam.szopapka.web.repository;

import com.powalteam.szopapka.web.api.dto.FamilyMembersDTO;
import com.powalteam.szopapka.web.model.Family;
import com.powalteam.szopapka.web.model.FamilyMembersView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FamilyRepository extends JpaRepository<Family, Long> {
    Optional<Family> findByFamilyName(String familyName);
    Optional<Family> findByFamilyCode(String familyCode);
    boolean existsByFamilyCode(String familyCode);
}