package com.powalteam.szopapka.web.model;

import com.google.errorprone.annotations.Immutable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "family_members")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Immutable
public class FamilyMembers {

    @Id
    @Column(name = "family_name")
    private String familyName;

    @Column(name= "image")
    private String image;

    @Column(name = "mail")
    private String mail;
}
