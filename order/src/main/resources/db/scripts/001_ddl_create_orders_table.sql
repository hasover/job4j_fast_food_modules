
create table orders (
                              id serial primary key ,
                              status varchar(150),
                              payment_id int,
                              kitchen_id int,
                              delivery_id int
);