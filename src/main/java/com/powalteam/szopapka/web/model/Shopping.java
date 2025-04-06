package com.powalteam.szopapka.web.model;

import com.powalteam.szopapka.web.api.dto.ShoppingDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "shopping")
public class Shopping extends ShoppingDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_family")
    private Long idFamily;

    @Column(name = "content")
    private String content;

    @Column(name = "status")
    private String status;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "id_user")
    private Long idUser;

}
