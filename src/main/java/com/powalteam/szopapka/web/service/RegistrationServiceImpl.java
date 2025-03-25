package com.powalteam.szopapka.web.service;

import com.powalteam.szopapka.web.api.dto.UserDTO;
import com.powalteam.szopapka.web.model.User;
import com.powalteam.szopapka.web.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User RegisterUser(UserDTO userDto) {

        User newUser = new User();
        newUser.setEmail(userDto.getMail());
        return userRepository.save(newUser);


    }
}
