package com.powalteam.szopapka.web.repository;

import com.powalteam.szopapka.web.model.Family;
import com.powalteam.szopapka.web.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FamilyRepository extends JpaRepository<Family, Long> {

}
