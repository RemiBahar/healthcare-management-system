-- This script is run during testing, e.g. after mvn clean test or mvn clean install. 

-- 2+ records are inserted into every table as test cases are run in a random order so there will be
--an erronous failure if a test tries to delete a record that doesn't exist.

-- This script like JPA should work with any RDBMS.

INSERT INTO gender (title) VALUES ('Male'), ('Female'), ('Other');

INSERT INTO country (country_code, name) VALUES ('UK', 'United Kingdom of Great Britain and Northern Ireland'), 
('US', 'United States of America');

INSERT INTO contact_type (title) VALUES ('Work'), ('Private'), ('Relative');

INSERT INTO title (title) VALUES ('Mr'), ('Mrs'), ('Miss'), ('Ms');

INSERT INTO address_type (title) VALUES ('private'), ('work'), ('other');

INSERT INTO patient_status (status) VALUES ('pre-captured'), ('checked'), ('in-treatment'), ('for-deletion');

INSERT INTO patient (first_name, middle_name, last_name, patient_status, gender, title) VALUES ('Alan', 'Mathison', 'Turing', 1, 1, 1);

INSERT INTO patient (first_name, middle_name, last_name, patient_status, gender, title) VALUES ('Jon', 'Johnson', 'Wick', 1, 1, 1);


INSERT INTO address (street, street_number, zip_code, city, region, description, priority, patient, type, country) 
VALUES ('21', 'Park Lane', 'S7 7FI', 'Sheffield', 'South Yorkshire', 'Home', 1, 1, 1, 'UK'),
('22', 'Park Lane', 'S7 7FI', 'Sheffield', 'South Yorkshire', 'Home', 1, 1, 1, 'UK');

INSERT INTO contact(name, telephone, mobile, email, patient, type, priority) 
VALUES ('Jon Wick', '013423525225', '0129499493', 'test@hotmail.com', 1, 1, 1),
('Indiana Jones', '0129499493', '013423525225', 'test@hotmail.com', 1, 1, 1);