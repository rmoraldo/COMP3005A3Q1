create table if not exists Students (
	student_id			serial,
	first_name			text		not null,
	last_name			text		not null,
	email				text		not null		unique,
	enrollment_date		date,
	primary key (student_id)
);

-- default data from assignment
INSERT INTO students (first_name, last_name, email, enrollment_date) VALUES
('John', 'Doe', 'john.doe@example.com', '2023-09-01'),
('Jane', 'Smith', 'jane.smith@example.com', '2023-09-01'),
('Jim', 'Beam', 'jim.beam@example.com', '2023-09-02');