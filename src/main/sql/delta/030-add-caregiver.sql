CREATE TABLE Caregiver (
    id int generated by default as identity (start with 1),
    name varchar(255),
    primary key (id)
);

ALTER TABLE Student ADD COLUMN father_id int foreign key references Caregiver(id);
ALTER TABLE Student ADD COLUMN mother_id int foreign key references Caregiver(id);
