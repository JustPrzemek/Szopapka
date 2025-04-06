package com.powalteam.szopapka.web.model;

import com.google.errorprone.annotations.Immutable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name = "shopping_view")
@Entity
@NoArgsConstructor
@Immutable
public class ShoppingView {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "id_family")
    private Long idFamily;

    @Column(name = "status")
    private String status;

    @Column(name = "content")
    private String content;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "id_user")
    private Long idUser;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "family_name")
    private String familyName;

}
