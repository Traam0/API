package com.isla.api.repository;

import com.isla.api.model.Role;
import com.isla.api.model.Status;
import com.isla.api.model.User;

import jakarta.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserCollectionRepo {
    private final ArrayList<User> users = new ArrayList<>();

    public UserCollectionRepo() {
    }

    public List<User> findAll() {
        return this.users;
    }

    public Optional<User> findById(Integer id) {
        return this.users.stream().filter(u -> u.id().equals(id)).findFirst();
    }

    public void create(User user) {
        this.users.add(user);
    }

    public boolean update(User user) {
        var existingUser = this.findById(user.id());
        if (existingUser.equals(null)) {
            return false;
        }

        this.users.set(this.users.indexOf(existingUser.get()), user);
        return true;
    }

    @PostConstruct()
    private void inti() {
        User user = new User(1, "Traam", "Traam@gmail.com", "admin", LocalDateTime.now(), null, Role.ADMIN,
                Status.ACTIF);

        this.users.add(user);
    }
}
