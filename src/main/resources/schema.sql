CREATE table users (
    id bigint not null auto_increment,
    username varchar(32) not null,
    email varchar(255) not null,
    password varchar(255) not null,
    create_time bigint not null,
    modify_time bigint not null,
    primary key (id)
);

CREATE unique index unique_email ON users(email);

CREATE table books (
    id bigint not null primary key auto_increment,
    isbn varchar(255),
    book_name varchar(255) not null,
    cover_url varchar(255) not null,
    author varchar(255) not null,
    operator varchar(255) not null,
    create_time bigint not null,
    modify_time bigint not null
);