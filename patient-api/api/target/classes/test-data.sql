INSERT INTO gender (title) VALUES ('Male'), ('Female'), ('Other');
INSERT INTO country (country_code, name) VALUES ('UK', 'United Kingdom of Great Britain and Northern Ireland');
INSERT INTO contact_type (title) VALUES ('Work'), ('Private'), ('Relative');
INSERT INTO title (title) VALUES ('Mr'), ('Mrs'), ('Miss'), ('Ms');
INSERT INTO address_type (title) VALUES ('private', 'work', 'other');
INSERT INTO patient_status (status) VALUES ('pre-captured'), ('checked'), ('in-treatment'), ('for-deletion');
INSERT INTO patient (first_name, middle_name, last_name, patient_status, gender, title) VALUES ('Alan', 'Mathison', 'Turing', 1, 1, 1);

INSERT INTO address (street, street_number, zip_code, city, region, description, priority, patient, type, country) 
VALUES ('21', 'Park Lane', 'S7 7FI', 'Sheffield', 'South Yorkshire', 'Home', 1, 1, 1, 'UK');

INSERT INTO contact(name, telephone, mobile, email, patient, type, priority) 
VALUES ('Jon Wick', '013423525225', '0129499493', 'test@hotmail.com', 1, 1, 1);