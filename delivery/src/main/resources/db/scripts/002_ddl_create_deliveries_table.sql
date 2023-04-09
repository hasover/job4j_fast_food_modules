create table deliveries (
                              id serial primary key ,
                              order_id int,
                              address varchar(255),
                              email varchar(255),
                              order_status varchar(255),
                              delivery_driver_id int
);