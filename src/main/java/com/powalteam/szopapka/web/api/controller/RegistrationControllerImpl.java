package com.powalteam.szopapka.web.api.controller;

import com.powalteam.szopapka.web.api.dto.UserDTO;
import com.powalteam.szopapka.web.model.User;
import com.powalteam.szopapka.web.service.RegistrationService;
import com.powalteam.szopapka.web.service.RegistrationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RegistrationControllerImpl implements RegistrationController {

    @Autowired
    private RegistrationServiceImpl registrationService;

    @Override
    public User registerUser(UserDTO userDto) {

        return registrationService.RegisterUser(userDto);
    }
}
