INSERT into users(username, email, permissions, password, create_time, modify_time) VALUES
    ('test-admin', 'test-admin@xxx.com', 'book-view,book-edit', 'test1234', 1, 1),
    ('test-admin2', 'test-admin2@xxx.com', 'book-view,book-edit,book-delete', 'test1234', 1, 1),
    ('test-admin3', 'test-admin3@xxx.com', 'book-view,book-edit,book-delete', '16d7a4fca7442dda3ad93c9a726597e4', 1, 1),
    ('test-admin4', 'test-admin4@xxx.com', 'book-view,book-edit', '16d7a4fca7442dda3ad93c9a726597e4', 1, 1)
;

INSERT INTO books(book_name, isbn, author, operator, cover_url, create_time, modify_time) VALUES
    ('book1', 'isbn1', 'author1', 'test-admin@xxx.com', 'https://www.baidu.com/img/PCtm_d9c8750bed0b3c7d089fa7d55720d6cf.png', 1, 1),
    ('book3', 'isbn3', 'author2', 'test-admin@xxx.com', 'https://www.baidu.com/img/PCtm_d9c8750bed0b3c7d089fa7d55720d6cf.png', 1, 1),
    ('book2', null, 'author1', 'test-admin@xxx.com', 'https://www.baidu.com/img/PCtm_d9c8750bed0b3c7d089fa7d55720d6cf.png', 1, 1)
;