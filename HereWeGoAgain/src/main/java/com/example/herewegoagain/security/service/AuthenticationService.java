package com.example.herewegoagain.security.service;


import com.example.herewegoagain.security.command.LoginCommand;
import com.example.herewegoagain.security.dto.LoginDTO;

import java.util.Optional;

public interface AuthenticationService {

    Optional<LoginDTO> login(LoginCommand command);

}
