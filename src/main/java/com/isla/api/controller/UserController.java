package com.isla.api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.isla.api.model.User;
import com.isla.api.repository.UserCollectionRepo;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/users")

public class UserController {
    private final UserCollectionRepo repo;

    public UserController(UserCollectionRepo repo) {
        this.repo = repo;
    }

    @GetMapping("")
    public List<User> findAllUsers() {
        return this.repo.findAll();
    }

    @GetMapping("/{id}")
    public User findUser(@PathVariable Integer id) {
        return this.repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found."));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/sign-up")
    public void signUp(@Valid @RequestBody User user, HttpServletResponse response) {
        this.repo.create(user);
        response.setHeader("Location", "/api/users/" + user.id());
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/update/{id}")
    public void Update(@RequestBody User user, @PathVariable Integer id, HttpServletResponse response) throws ResponseStatusException {
        if (this.repo.findById(id) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found.");
        }

        if (this.repo.update(user) == false) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed To Update User");
        }
        response.setHeader("Location", "/api/users/" + user.id());

    }

}
