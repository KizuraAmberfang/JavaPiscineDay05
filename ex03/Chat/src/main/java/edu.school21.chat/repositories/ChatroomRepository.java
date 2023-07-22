package edu.school21.chat.repositories;

import java.util.Optional;

import edu.school21.chat.models.Chatroom;

public interface ChatroomRepository {
    Optional<Chatroom> findById(Long id);

    void save(Chatroom room);
}
