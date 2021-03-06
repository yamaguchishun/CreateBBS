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
		update_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP)


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
		update_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP)


create table 
	yamaguchi_shun.divisions(
		id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
		name varchar(20),
		insert_date TIMESTAMP,
		update_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP)

INSERT INTO 
	yamaguchi_shun.users (
		account,
		name,
		password,
		division_id,
		branch_id,
		is_Working
		)VALUES(
			'admin01',
			'±',
			'admin01',
			1,
			1,
			0),
			('admin02',
			'îñZLeB',
			'admin02',
			2,
			1,
			0),
			('admin02',
			'îñZLeB',
			'admin02',
			2,
			1,
			0),
			('xX·A',
			'xX·A',
			'shitenchou',
			3,
			2,
			0),
			('ÐõA',
			'ÐõA',
			'shaina',
			4,
			2,
			0)
		
		
INSERT INTO 
	yamaguchi_shun.division (
		name) value('±'),
					('îñZLeB'),
					('xX·'),
					('Ðõ')
		
INSERT INTO 
	yamaguchi_shun.branches (
		name) value('{Ð'),
					('xXA'),
					('xXB'),
					('xXC')
