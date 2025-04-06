package com.powalteam.szopapka.web.repository;

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

    @Query("SELECT fm.mail FROM FamilyMembersView fm " +
            "WHERE fm.familyCode = :familyCode")
    List<String> findMemberEmailsByFamilyCode(@Param("familyCode") String familyCode);

    Optional<Family> findByFamilyCode(String familyCode);

    boolean existsByFamilyCode(String code);

}
