package org.example.myblog.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.example.myblog.exception.InvalidTokenException;
import org.example.myblog.security.AuthService;
import org.example.myblog.security.ExternalProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/auth")
@RestController
public class AuthRestController {

    final ExternalProperties externalProperties;
    final AuthService authService;

    @PostMapping("")
    public ResponseEntity<Void> access(HttpServletRequest request) {
        String accessToken = null;
        String prefix = externalProperties.getTokenPrefix();
        String refreshToken = request.getHeader(externalProperties.getRefreshKey());

        System.out.println("AuthRestController RefreshToken: " + refreshToken);

        if(refreshToken == null || !refreshToken.startsWith(prefix) || refreshToken.equals(prefix)) {
            throw new InvalidTokenException("No Prefix");
        }

        refreshToken = refreshToken.substring(prefix.length());
        accessToken = prefix + authService.issueAccessToken(refreshToken);

        return ResponseEntity.status(HttpStatus.OK).header(externalProperties.getAccessKey(), accessToken).build();
    }
}
