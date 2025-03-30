package com.powalteam.szopapka.web.repository;

import com.powalteam.szopapka.web.model.UserInFamily;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserInFamilyRepository extends JpaRepository<UserInFamily, Long> {

}