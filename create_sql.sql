create table 
	yamaguchi_shun.users (
		id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
		account VARCHAR(20),
		password VARCHAR(255),
		name VARCHAR(10),
		branch_id INT,
		division_id INT,
		is_working TINYINT UNSIGNED,
		insert_date TIMESTAMP,
		update_date CURRENT_TIMESTAMP)


create table 
	yamaguchi_shun.posts (
		id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
		user_id INT,
		subject VARCHAR(30) NOTNULL,
		category varchar(10) NOTNULL,
		text VARCHAR(1000) NOTNULL,
		branch_id INT,
		division_id INT,
		insert_date TIMESTAMP)


create table 
	yamaguchi_shun.comments (
		id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
		user_id INT,
		post_id INT,
		text VARCHAR(500) NOTNULL,
		branch_id INT,
		division_id INT,
		insert_date TIMESTAMP) 


create table 
	yamaguchi_shun_.branches(
		id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
		name varchar(20),
		insert_date TIMESTAMP,
		update_date CURRENT_TIMESTAMP)


create table 
	yamaguchi_shun.divisions(
		id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
		name varchar(20),
		insert_date TIMESTAMP,
		update_date CURRENT_TIMESTAMP)

