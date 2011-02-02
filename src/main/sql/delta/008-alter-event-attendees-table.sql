DROP TABLE EventAttendees;
CREATE TABLE EventAttendees(
event_id int foreign key references Event(event_id),
id int foreign key references Student(id),
PRIMARY KEY (event_id, id)
);