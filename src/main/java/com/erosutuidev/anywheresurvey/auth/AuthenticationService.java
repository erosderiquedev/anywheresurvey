/**
 * Author: Eros Derique Utui
 * User:erosderiquedev
 * Date:06/03/2025
 * Time:10:39
 */
package com.erosutuidev.anywheresurvey.auth;

import com.erosutuidev.anywheresurvey.auth.dto.AuthenticationRequest;
import com.erosutuidev.anywheresurvey.auth.dto.AuthenticationResponse;
import com.erosutuidev.anywheresurvey.auth.dto.RegisterRequest;
import com.erosutuidev.anywheresurvey.token.Token;
import com.erosutuidev.anywheresurvey.token.TokenRepository;
import com.erosutuidev.anywheresurvey.token.TokenType;
import com.erosutuidev.anywheresurvey.user.Role;
import com.erosutuidev.anywheresurvey.user.User;
import com.erosutuidev.anywheresurvey.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final TokenRepository tokenRepository;
    
    public AuthenticationResponse register(RegisterRequest request) {
         var user = User.builder()
                 .firstname(request.getFirstName())
                 .lastname(request.getLastName())
                 .email(request.getEmail())
                 .password(passwordEncoder.encode(request.getPassword()))
                 .role(Role.GESTOR)
                 .build();
         var savedUser = userRepository.save(user);
         var jwtToken = jwtService.generateToken(user);
        saveUserToken(savedUser, jwtToken);
        return AuthenticationResponse.builder()
                 .token(jwtToken)
                 .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(
                  request.getEmail(),
                  request.getPassword()
          )
        );
        var user = userRepository.findByEmail(request.getEmail()).orElseThrow(
                () -> new UsernameNotFoundException("User not found")
        );
        var jwtToken = jwtService.generateToken(user);
        revokeAlUserTokens(user);
        saveUserToken(user, jwtToken);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    private void revokeAlUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllByValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty()) {
            return;
        }
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();

        tokenRepository.save(token);
    }
}
