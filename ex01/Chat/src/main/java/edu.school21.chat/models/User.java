package edu.school21.chat.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {
    private long userID;
    private String login;
    private String password;
    private List<Chatroom> createdRoom = new ArrayList<Chatroom>();
    private List<Chatroom> chatRoom = new ArrayList<Chatroom>();

    public User(long id, String log, String pwd, List<Chatroom> crRoom, List<Chatroom> chRoom) {
        this.userID = id;
        this.login = log;
        this.password = pwd;
        this.createdRoom = crRoom;
        this.chatRoom = chRoom;
    }

    public long getUserId() {
        return (userID);
    }

    public String getLogin() {
        return (login);
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
        User otherUser = (User) other;
        if (otherUser.getUserId() == this.userID)
            return (true);
        return (false);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.userID);
    }

    @Override
    public String toString() {
        return (this.userID + " " + this.login);
    }
}
