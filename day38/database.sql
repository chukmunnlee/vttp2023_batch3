drop database if exists myuploads;

create database myuploads;

use myuploads;

create table uploads (
	id int auto_increment,
	description varchar(256),
	content mediumblob,
	media_type varchar(256),

	constraint pk_id primary key(id)
);

grant all privileges on myuploads.* to 'fred'@'%';

flush privileges;
