package com.example.herewegoagain.security.service;


import com.example.herewegoagain.security.command.LoginCommand;
import com.example.herewegoagain.security.domain.User;
import com.example.herewegoagain.security.dto.LoginDTO;
import com.example.herewegoagain.security.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final JwtService jwtService;
    private final UserRepository userRepository;

    public AuthenticationServiceImpl(JwtService jwtService, UserRepository userRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    @Override
    public Optional<LoginDTO> login(LoginCommand command) {
        Optional<User> user = userRepository.findByUsername(command.getUsername());

        if (user.isEmpty() || !isMatchingPassword(command.getPassword(), user.get().getPassword())) {
            return Optional.empty();
        }

        return Optional.of(
                new LoginDTO(jwtService.createJwt(user.get()))
        );
    }

    private boolean isMatchingPassword(String rawPassword, String encryptedPassword) {
        return BCrypt.checkpw(rawPassword, encryptedPassword);
    }
}
