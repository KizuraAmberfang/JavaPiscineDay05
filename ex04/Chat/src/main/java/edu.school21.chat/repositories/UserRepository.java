package edu.school21.chat.repositories;

import java.util.Optional;

import edu.school21.chat.models.User;

public interface UserRepository {
    List<User> findAll(int page, int size);
}
