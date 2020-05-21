
-------------------------------------------------------------------------------------------------------------------------

create table users
(
    id         bigserial not null
        constraint users_pk
            primary key,
    name       varchar,
    surname    varchar,
    photo_url  varchar,
    last_login timestamp,
    email      varchar,
    password   varchar
);

alter table users
    owner to zqkbvaxaenesqq;

create unique index users_id_uindex
    on users (id);

create unique index users_email_uindex
    on users (email);

-------------------------------------------------------------------------------------------------------------------------

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

-------------------------------------------------------------------------------------------------------------------------

create table message_manager
(
    id                 bigserial not null
        constraint message_manager_pk
            primary key,
    from_id            integer
        constraint from_id_fk
            references users
            on delete cascade,
    to_id              integer
        constraint to_id_fk
            references users
            on delete cascade,
    message_content_id integer
        constraint mes_man_mess_con_fk
            references message_content
            on delete cascade
);

alter table message_manager
    owner to zqkbvaxaenesqq;

create unique index message_manager_id_uindex
    on message_manager (id);

-------------------------------------------------------------------------------------------------------------------------

create table like_dislike
(
    id           bigserial not null,
    from_user_id integer   not null
        constraint from_id_fk
            references users
            on delete set null,
    to_user_id   integer   not null
        constraint to_id_fk
            references users
            on delete set null,
    is_like      boolean,
    constraint like_dislike_pk
        primary key (from_user_id, to_user_id)
);

alter table like_dislike
    owner to zqkbvaxaenesqq;

create unique index like_dislike_id_uindex
    on like_dislike (id);

-------------------------------------------------------------------------------------------------------------------------
