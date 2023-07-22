package edu.school21.chat.models;

import java.sql.Timestamp;
import java.util.Objects;
import java.util.Optional;

import edu.school21.chat.repositories.NotSavedSubEntityException;

public class Message {
    Long messageID;
    User author = null;
    Chatroom room = null;
    String text = null;
    Timestamp timestamp = null;

    public Long getMessageId() {
        return (messageID);
    }

    public User getAuthor() {
        return (author);
    }

    public Chatroom getRoom() {
        return (room);
    }

    public String getText() {
        return (text);
    }

    public Timestamp getTimestamp() {
        return (timestamp);
    }

    public Message(Long id, User author, Chatroom room, String text, Timestamp messageDateTime) {
        this.messageID = id;
        this.author = author;
        this.room = room;
        this.text = text;
        this.timestamp = messageDateTime;
    }

    public void setId(Long newId) {
        this.messageID = newId;
    }

    public void setAuthor(User newAuthor) {
        this.author = newAuthor;
    }

    public void setRoom(Chatroom newRoom) {
        this.room = newRoom;
    }

    public void setText(String newText) {
        this.text = newText;
    }
    public void setTimestamp(Timestamp t) {
        this.timestamp = t;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return (true);
        }
        if (other == null) {
            return (false);
        }
        if (other.getClass() != this.getClass()) {
            return (false);
        }
        Message otherUser = (Message) other;
        if (otherUser.getMessageId() == this.messageID)
            return (true);
        return (false);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.messageID);
    }

    @Override
    public String toString() {
        return (this.messageID + " | from: " + (this.author != null ? this.author.getLogin() : author) + " | room: " + (this.room != null ? this.room.getChatName() : this.room) + " | "
                + this.timestamp + " | " + this.text + "\n");
    }

}
