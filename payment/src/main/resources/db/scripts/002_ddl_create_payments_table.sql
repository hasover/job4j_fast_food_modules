create table payments (
    id serial primary key,
    total float,
    account_id int,
    created timestamp
);