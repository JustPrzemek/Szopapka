package com.powalteam.szopapka.web.repository;

import com.powalteam.szopapka.web.model.FamilyMembersView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FamilyMembersRepository extends JpaRepository<FamilyMembersView, Long> {
    @Query(value = "SELECT * FROM family_members WHERE mail = :userEmail",
            nativeQuery = true)

    List<FamilyMembersView> findByUserEmail(@Param("userEmail") String userEmail);

}
