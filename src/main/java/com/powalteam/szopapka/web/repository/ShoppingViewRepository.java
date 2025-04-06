package com.powalteam.szopapka.web.repository;

import com.powalteam.szopapka.web.model.ShoppingView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoppingViewRepository extends JpaRepository<ShoppingView, Long> {
    List<ShoppingView> findByIdFamily(Long idFamily);
}