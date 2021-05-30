DROP TABLE IF EXISTS tbl_token;
create table tbl_token(
	id integer not null,
	api_token varchar(255),
	valid_to varchar(255),
	primary key(id)
);