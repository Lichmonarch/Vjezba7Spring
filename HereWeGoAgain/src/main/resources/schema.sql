create table if not exists hardware(
                                       index int not null auto_increment primary key,
                                       name varchar(100) not null,
                                       price int not null,
                                       type varchar(10) not null,
                                       in_stock int not null
);

create table if not exists review(
                                     index int not null auto_increment primary key,
                                     title varchar(255),
                                     body varchar(255),
                                     rating int,
                                     id_hardware int,
                                     constraint fk_hardware foreign key (id_hardware) references hardware(index) ON DELETE CASCADE
);
create table if not exists users_table  (
                                    id identity,
                                    username varchar(100) not null unique,
                                    password varchar(1000) not null
);
create table if not exists authority (
                                         id identity,
                                         authority_name varchar(100) not null unique
);
create table if not exists user_authority (
                                              user_id bigint not null,
                                              authority_id bigint not null,
                                              constraint fk_user foreign key (user_id) references users_table (id),
                                              constraint fk_authority foreign key (authority_id) references authority(id)
);
