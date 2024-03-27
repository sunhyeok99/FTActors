
ALTER TABLE recruitment ADD COLUMN image_name VARCHAR(255) NULL default '';
ALTER TABLE recruitment ADD COLUMN file_name VARCHAR(255) NULL default '';
Alter TABLE recruitment DROP COLUMN saved_name;


