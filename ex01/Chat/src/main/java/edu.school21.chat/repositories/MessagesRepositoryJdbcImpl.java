package edu.school21.chat.repositories;

import java.util.Optional;

import edu.school21.chat.models.Message;

public class MessagesRepositoryJdbcImpl implements MessagesRepository {

    @Override
    public Optional<Message> findById(Long id) {
        Message msg = null;
        return (Optional.ofNullable(msg));
    }
}
