package com.powalteam.szopapka.web.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class MembersDTO {

    private Long userId;

    private String mail;

}
