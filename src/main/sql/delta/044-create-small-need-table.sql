CREATE TABLE SmallNeed (
	ID int generated by default as identity (start with 1),
    ITEM_NAME varchar(1000),
    COST decimal,
    COMMENT varchar(1000),
	primary key (ID)
);

