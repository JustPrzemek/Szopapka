package com.powalteam.szopapka.web.repository;

import com.powalteam.szopapka.web.model.FamilyMembersView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FamilyMembersRepository extends JpaRepository<FamilyMembersView, Long> {
    List<FamilyMembersView> findByMail(String mail);

    @Query("SELECT fm FROM FamilyMembersView fm WHERE fm.familyId = :familyId")
    List<FamilyMembersView> findByFamilyId(@Param("familyId") Long familyId);
}
