package com.example.ClassService.services;

import com.example.ClassService.models.LoginResponse;
import com.example.ClassService.models.Role;
import com.example.ClassService.models.User;
import com.example.ClassService.repository.RoleRepository;
import com.example.ClassService.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;
    private final RoleRepository roleRepository;

    public ResponseEntity<User> register(String username, String password) {
        String encryptedPassword = passwordEncoder.encode(password);

        Role userRole = roleRepository.findByAuthority("USER").get();

        Set<Role> authorities = new HashSet<>();

        authorities.add(userRole);

        return ResponseEntity.ok(userRepository.save( new User(0L, username, encryptedPassword, authorities) ));
    }

    public ResponseEntity<LoginResponse> login (String username, String password) {
        try{
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            String token = tokenService.generateJwt(auth);

            return ResponseEntity.ok(new LoginResponse(userRepository.findByUsername(username).get(), token));

        } catch(AuthenticationException e){
            return ResponseEntity.ok(new LoginResponse(null, ""));
        }
    }
}
