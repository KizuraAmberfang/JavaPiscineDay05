package edu.school21.chat.models;

import java.util.List;
import java.util.Objects;

public class Chatroom {
    long chatID;
    String chatName;
    User owner;
    List<Message> messages;

    public Chatroom(long id, String name, User own, List<Message> msgs) {
        this.chatID = id;
        this.chatName = name;
        this.owner = own;
        this.messages = msgs;
    }

    public long getChatId() {
        return (this.chatID);
    }

    public String getChatName() {
        return (this.chatName);
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
        Chatroom otherUser = (Chatroom) other;
        if (otherUser.getChatId() == this.chatID)
            return (true);
        return (false);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.chatID);
    }

    @Override
    public String toString() {
        return (this.chatID + " | " + this.chatName + " | " + this.owner.getLogin());
    }
}
