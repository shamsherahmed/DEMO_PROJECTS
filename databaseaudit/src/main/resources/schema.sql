CREATE TABLE user (
   id int PRIMARY KEY NOT NULL,
   name VARCHAR(255),
   mobile VARCHAR(255),
   email VARCHAR(255),
   created_by VARCHAR(255),
   created_date TIMESTAMP,
   last_modified_by VARCHAR(255),
   last_modified_date TIMESTAMP
);