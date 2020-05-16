create table users
(
    id         bigserial not null
        constraint users_pk
            primary key,
    name       varchar,
    surname    varchar,
    photo_url  varchar,
    last_login timestamp
);

alter table users
    owner to zqkbvaxaenesqq;

create unique index users_id_uindex
    on users (id);

-------------------------------
create table message_content
(
    id        bigserial not null
        constraint message_content_pk
            primary key,
    content   varchar,
    date_time timestamp
);

alter table message_content
    owner to zqkbvaxaenesqq;

create unique index message_content_id_uindex
    on message_content (id);

-------------------------------

create table message_manager
(
    id                 bigserial not null
        constraint message_manager_pk
            primary key,
    from_id            integer
        constraint from_id_fk
            references users,
    to_id              integer
        constraint to_id_fk
            references users,
    message_content_id integer
        constraint mes_man_mess_con_fk
            references message_content
);

alter table message_manager
    owner to zqkbvaxaenesqq;

create unique index message_manager_id_uindex
    on message_manager (id);

-------------------------------

create table user_likes
(
    id     bigserial not null
        constraint user_likes_id_seq_pk
            primary key,
    "from" integer
        constraint user_likes_from_users_id_fk
            references users,
    "to"   integer
        constraint user_likes_to_users_id_fk
            references users
);

alter table user_likes
    owner to zqkbvaxaenesqq;

create unique index user_likes_id_seq_id_uindex
    on user_likes (id);

-------------------------------

create table user_unlikes
(
    id     bigserial not null
        constraint user_unlikes_pk
            primary key,
    "from" integer
        constraint user_unlikes_users_id_fk
            references users,
    "to"   integer
        constraint "user_unlikes_users id_fk"
            references users
);

alter table user_unlikes
    owner to zqkbvaxaenesqq;

create unique index user_unlikes_id_uindex
    on user_unlikes (id);