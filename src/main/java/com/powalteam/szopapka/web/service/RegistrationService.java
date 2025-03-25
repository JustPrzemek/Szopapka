package com.powalteam.szopapka.web.service;

import com.powalteam.szopapka.web.api.dto.UserDTO;
import com.powalteam.szopapka.web.model.User;

public interface RegistrationService {

    public User RegisterUser(UserDTO userDto);
}
