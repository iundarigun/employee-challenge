create table department
(
    id           int           auto_increment,
    name         varchar(255)  not null,
    created_at   datetime      not null,
    updated_at   datetime      not null,
    constraint department_pk primary key (id)

);

create unique index department_name_unique01 on department (name);

create table employee
(
    id            int           auto_increment,
    name          varchar(255)  not null,
    birthday      date          not null,
    job           varchar(50)   not null,
    height        int           null,
    weight        double        null,
    description   varchar(4000) null,
    department_id int           not null,
    created_at    datetime      not null,
    updated_at    datetime      not null,
    constraint employee_pk primary key (id)
);

alter table employee  add constraint employee_department_fk
        foreign key (department_id) references department (id);


