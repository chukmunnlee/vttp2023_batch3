drop database if exists mystore;

create database mystore;

use mystore; 

create table orders (
	order_id char(8) not null,
	create_date datetime not null default now(),
	name varchar(32) not null,
	email varchar(64)  not null,
	express boolean default false,

	constraint pk_order_id primary key(order_id)
);

create table lineitems (
	item_id int auto_increment,
	order_id char(8) not null,
	item_name varchar(32) not null,
	quantity int default 1,
	unit_price decimal(8, 2),

	constraint pk_item_id primary key(item_id),
	constraint fk_order_id foreign key(order_id) references orders(order_id)
);

grant all privileges on mystore.* to 'fred'@'%';
flush privileges;
