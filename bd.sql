drop table if exists bill cascade;
drop table if exists car cascade;
drop table if exists car_brand cascade;
drop table if exists color cascade;
drop table if exists m_order cascade;
drop table if exists m_roles cascade;
drop table if exists m_user cascade;
drop table if exists model_car cascade;
drop table if exists worker cascade;


/* роли для пользователя*/
drop table if exists m_roles cascade;
create table if not exists "m_roles"
(
    id   serial  not null PRIMARY KEY,
    name varchar not null
);

/* пользователи*/
drop table if exists m_user cascade;
create table if not exists "m_user"
(
    id         serial       not null Primary key,
    first_name varchar(50)  not null,
    last_name  varchar(100) not null,
    login      varchar(50)  not null,
    pass       varchar(100) not null,
    created    Date         not null,
    id_role    bigint       not null
        constraint m_user_id_role_fk
            references m_roles,
    email      varchar(100) not null,
    phone      varchar(13)  not null,
    city       varchar(100) not null

);

/* марка машины*/
drop table if exists car_brand cascade;
create table if not exists car_brand
(
    id         serial      not null PRIMARY KEY,
    name       varchar(50) not null,
    price_hour float       not null
);

/*цвет авто*/
drop table if exists color cascade;
create table if not exists color
(
    id    serial      not null PRIMARY KEY,
    color varchar(50) not null
);

/*модели машины*/
drop table if exists model_car cascade;
create table if not exists model_car
(
    id              serial       not null PRIMARY KEY,
    name            varchar(50)  not null,
    engine_capacity bigint       not null,
    data            int          not null,
    vin             varchar(100) not null,
    id_color        int          not null
        constraint model_car_id_color_fk
            references color,
    id_car          bigint       not null
        constraint model_car_id_car_fk
            references car_brand
);

/*рабочие*/
drop table if exists worker cascade;
create table if not exists worker
(
    id          serial       not null PRIMARY KEY,
    first_name  varchar(50)  not null,
    last_name   varchar(100) not null,
    id_position bigint       not null
        constraint worker_id_position_fk
            references m_roles,
    id_user     bigint       not null
        constraint worker_id_user_fk
            references m_user
);

/*заказ*/
drop table if exists "m_order" cascade;
create table if not exists m_order
(
    id           serial not null PRIMARY KEY,
    id_user      bigint not null
        constraint m_order_id_user_fk
            references m_user,
    id_car       bigint not null
        constraint m_order_id_car_fk
            references car_brand,
    id_worker    bigint not null
        constraint m_order_id_worker_fk
            references worker,
    rental_start time   not null,
    rental_end   time   not null
);

/*статус заказа*/
drop table if exists bill cascade;
create table if not exists bill
(
    id       serial not null PRIMARY KEY,
    id_order bigint not null
        constraint bill_id_order_fk
            references m_order,
    status   bool   not null
);

INSERT INTO color (color)
VALUES ('WHITE');
INSERT INTO color (color)
VALUES ('BLACK');
INSERT INTO color (color)
VALUES ('ORANGE');
INSERT INTO color (color)
VALUES ('PINK');
INSERT INTO color (color)
VALUES ('GRAY');
INSERT INTO color (color)
VALUES ('GREEN');

insert into car_brand (name, price_hour)
VALUES ('audi', 76.88);
insert into model_car (name, engine_capacity, data, vin, id_color, id_car)
VALUES ('A4', 2000, 2016, 4458896644886, 1, 1);
insert into model_car (name, engine_capacity, data, vin, id_color, id_car)
VALUES ('A6', 2500, 2015, 4458681245858, 2, 1);
insert into model_car (name, engine_capacity, data, vin, id_color, id_car)
VALUES ('A6', 3200, 2017, 4458681245858, 1, 1);
insert into model_car (name, engine_capacity, data,vin, id_color, id_car)
VALUES ('A8', 3200, 2018, 4458681245858, 2, 1);

insert into car_brand (name, price_hour)
VALUES ('Honda', 54.88);
insert into model_car (name, engine_capacity, data, vin, id_color, id_car)
VALUES ('S2000', 3200, 2000, 4458896644886, 3, 2);
insert into model_car (name, engine_capacity, data, vin, id_color, id_car)
VALUES ('ACCORD', 2500, 2015, 4458681245858, 2, 2);
insert into model_car (name, engine_capacity, data, vin, id_color, id_car)
VALUES ('CIVIC', 1600, 2017, 4458681245858, 2, 2);
insert into model_car (name, engine_capacity, data,vin, id_color, id_car)
VALUES ('PILOT', 3200, 2015, 4458681245858, 4, 2);

insert into car_brand (name, price_hour)
VALUES ('Nissan', 150.99);
insert into model_car (name, engine_capacity, data, vin, id_color, id_car)
VALUES ('GT-R', 4400, 2018, 4458896644886, 3, 3);
insert into model_car (name, engine_capacity, data, vin, id_color, id_car)
VALUES ('PIXO', 1000, 2012, 4458681245858, 2, 3);

insert into car_brand (name, price_hour)
VALUES ('LEXUS', 370.99);
insert into model_car (name, engine_capacity, data, vin, id_color, id_car)
VALUES ('ES', 3200, 2018, 4458896644886, 2, 4);
insert into model_car (name, engine_capacity, data, vin, id_color, id_car)
VALUES ('GX', 1000, 2019, 4458681245858, 2, 4);

INSERT INTO m_roles (id, name)
VALUES (1, 'ADMIN');
INSERT INTO m_roles (id, name)
VALUES (2, 'USER');
INSERT INTO m_roles (id, name)
VALUES (3, 'SELLER');

INSERT INTO m_user (first_name, last_name, login, pass, created, id_role, email, phone, city)
VALUES ('ADMIN', 'ADMIN', 'admin', 'admin', '2017-01-01', 1, 'dolbik@mail.ru', '+375293803981', 'Minsk');
INSERT INTO m_user (first_name, last_name, login, pass, created, id_role, email, phone, city)
VALUES ('Victor', 'Mironov', 'victorMin', '123', '2017-01-01', 3, 'vicMir@mail.ru', '+375295558878', 'Minsk');


INSERT INTO worker (first_name, last_name, id_position, id_user)
VALUES ('Victor', 'Mironov', 3, 2);

