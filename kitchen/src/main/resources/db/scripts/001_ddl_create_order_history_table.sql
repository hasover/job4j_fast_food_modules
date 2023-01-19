create table order_history (
    id serial primary key ,
    order_id int,
    order_status varchar(250),
    date_time timestamp default now()
);