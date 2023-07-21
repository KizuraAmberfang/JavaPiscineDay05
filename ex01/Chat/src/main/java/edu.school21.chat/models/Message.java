package edu.school21.chat.models;

import java.sql.Timestamp;
import java.util.Objects;

public class Message {
    long messageID;
    User author;
    Chatroom room;
    String text;
    Timestamp timestamp;

    public long getMessageId() {
        return (this.messageID);
    }

    public Message(long id, User author, Chatroom room, String text, Timestamp messageDateTime) {
        this.messageID = id;
        this.author = author;
        this.room = room;
        this.text = text;
        this.timestamp = messageDateTime;
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
        return (this.messageID + " | from: " + this.author.getLogin() + " | room: " + this.room.getChatName() + " | "
                + this.timestamp + " | " + this.text + "\n");
    }

}
