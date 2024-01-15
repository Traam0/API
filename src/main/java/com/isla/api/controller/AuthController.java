package com.isla.api.controller;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.isla.api.dto.ApiErrorResponse;
import com.isla.api.dto.Credentials;
import com.isla.api.dto.TokenPayload;
import com.isla.api.model.User;
import com.isla.api.repository.UserRepository;
import com.isla.api.services.JwtService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/api/auth", method = { RequestMethod.POST })
@CrossOrigin
public class AuthController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<Object> Login(@RequestBody @Valid Credentials credentials, HttpServletResponse Response) {

        try {
            Optional<User> result = this.userRepository.findByUsername(credentials.username());

            if (result.isPresent() == false) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiErrorResponse("User does not or no longer exists."));
            }

            User user = result.get();

            if (!user.getPassword().equals(credentials.password())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiErrorResponse("Invalid Credentials"));
            }

            Cookie AtsCookie = new Cookie("ATS",
                    jwtService.SignToken(new TokenPayload(user.getId(), user.getVersion(), user.getRole())));
            AtsCookie.setHttpOnly(true);
            AtsCookie.setSecure(true);
            AtsCookie.setMaxAge(((int) TimeUnit.HOURS.toSeconds(1)));

            Response.addCookie(AtsCookie);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }

    }

    @PostMapping("/sign-up")
    public ResponseEntity<Object> postMethodName(@RequestBody User user) {

        return null;
    }

}
