INSERT INTO chat.user (login, password)
VALUES ('alfa', 'alfa'),
       ('beta', 'beta'),
       ('gamma', 'gamma'),
       ('delta', 'delta'),
       ('epsilon', 'epsilon');

INSERT INTO chat.chatroom (chat_name, chat_owner)
VALUES  ('yi', 1),
        ('er', 2),
        ('san', 1),
        ('si', 5),
        ('wu', 3);

INSERT INTO chat.message (author, room, text_message, data_time)
VALUES  (1, 3, 'This text was print by alfa in chat san', now()),
        (1, 2, 'This text was print by alfa in chat er', now()),
        (4, 1, 'This text was print by delta in chat yi', now()),
        (4, 5, 'This text was print by delta in chat wu', now()),
        (4, 4, 'This text was print by delta in chat si', now());