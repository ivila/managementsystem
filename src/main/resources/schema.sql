CREATE table users (
    id bigint not null primary key auto_increment,
    username varchar(32) not null,
    password varchar(255) not null,
    create_time integer not null,
    modify_time integer not null
);