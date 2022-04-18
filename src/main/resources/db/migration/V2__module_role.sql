CREATE TABLE gira_group (
	ID VARCHAR ( 36 ) NOT NULL,
	created_at TIMESTAMP,
	created_by VARCHAR ( 36 ),
	last_modified_at TIMESTAMP,
	last_modified_by VARCHAR ( 36 ),
	VERSION int4 NOT NULL,
	name VARCHAR ( 100 ),
	description VARCHAR ( 255 ),
	PRIMARY KEY ( ID ) 
);

CREATE TABLE gira_role (
	ID VARCHAR ( 36 ) NOT NULL,
	created_at TIMESTAMP,
	created_by VARCHAR ( 36 ),
	last_modified_at TIMESTAMP,
	last_modified_by VARCHAR ( 36 ),
	VERSION int4 NOT NULL,
	name VARCHAR ( 100 ),
	description VARCHAR ( 255 ),
	PRIMARY KEY ( ID ) 
);


CREATE TABLE gira_group_role ( 
    group_id VARCHAR ( 36 ) NOT NULL, 
    role_id VARCHAR ( 36 ) NOT NULL, 
    PRIMARY KEY ( group_id, role_id ) 
);

ALTER TABLE
IF
	EXISTS gira_group_role ADD CONSTRAINT FK_GROUP_ROLE_ROLE FOREIGN KEY ( role_id ) REFERENCES gira_role;
ALTER TABLE
IF
	EXISTS gira_group_role ADD CONSTRAINT FK_GROUP_ROLE_GROUP FOREIGN KEY ( group_id ) REFERENCES gira_group;



CREATE TABLE gira_group_user ( 
	group_id 			VARCHAR ( 36 ) NOT NULL, 
	user_id 			VARCHAR ( 36 ) NOT NULL, 
	PRIMARY KEY ( group_id, user_id ) 
);

ALTER TABLE IF EXISTS gira_group_user 
	ADD CONSTRAINT FK_GROUP_ROLE_USER FOREIGN KEY ( user_id ) REFERENCES gira_user(id);
ALTER TABLE IF EXISTS gira_group_user 
	ADD CONSTRAINT FK_GROUP_ROLE_GROUP FOREIGN KEY ( group_id ) REFERENCES gira_group(id);


CREATE TABLE gira_program (
	ID 					VARCHAR ( 36 ) NOT NULL,
	created_at 			TIMESTAMP,
	created_by 			VARCHAR ( 36 ),
	last_modified_at 	TIMESTAMP,
	last_modified_by 	VARCHAR ( 36 ),
	VERSION 			INT4 NOT NULL,
	name 				VARCHAR(255) NOT NULL UNIQUE,
	program_type		VARCHAR(50) NOT NULL,
	module				VARCHAR(50) NOT NULL,
	description			VARCHAR(255) NOT NULL,
    identity_key        VARCHAR(100) NOT NULL,
	PRIMARY KEY ( ID ) 
);


CREATE TABLE gira_role_program ( 
	role_id 			VARCHAR ( 36 ) NOT NULL, 
	program_id 			VARCHAR ( 36 ) NOT NULL, 
	PRIMARY KEY ( role_id, program_id ) 
);

ALTER TABLE IF EXISTS gira_role_program 
	ADD CONSTRAINT FK_ROLE FOREIGN KEY ( role_id ) REFERENCES gira_role(id);
ALTER TABLE IF EXISTS gira_role_program 
	ADD CONSTRAINT FK_PROGRAM FOREIGN KEY ( program_id ) REFERENCES gira_program(id);