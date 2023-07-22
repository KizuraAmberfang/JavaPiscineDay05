DROP SCHEMA IF EXISTS chat CASCADE;

CREATE SCHEMA IF NOT EXISTS chat;

CREATE TABLE IF NOT EXISTS chat.user (
    user_id SERIAL PRIMARY KEY,
    login text UNIQUE NOT NULL,
    password text NOT NULL
);

CREATE TABLE IF NOT EXISTS chat.chatroom (
    chat_id SERIAL PRIMARY KEY,
    chat_name text UNIQUE NOT NULL,
    chat_owner INT NOT NULL,
    FOREIGN KEY (chat_owner) REFERENCES chat.user (user_id)
);

CREATE TABLE IF NOT EXISTS chat.message (
    message_id SERIAL PRIMARY KEY,
    author INT,
    room INT,
    text_message text,
    data_time TIMESTAMP, 
    FOREIGN KEY (author) REFERENCES chat.user (user_id),
    FOREIGN KEY (room) REFERENCES chat.chatroom (chat_id)
);