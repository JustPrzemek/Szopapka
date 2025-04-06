package com.powalteam.szopapka.web.repository;

import com.powalteam.szopapka.web.model.Shopping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingRepository extends JpaRepository<Shopping, Long> {

}
