ALTER TABLE recruitment MODIFY COLUMN image VARCHAR(255) NULL default '';
ALTER TABLE recruitment ADD COLUMN image_name VARCHAR(255) NULL default '';
ALTER TABLE recruitment ADD COLUMN file VARCHAR(255) NULL default '';
ALTER TABLE recruitment ADD COLUMN file_name VARCHAR(255) NULL default '';
Alter TABLE recruitment DROP COLUMN saved_name;

ALTER TABLE apply MODIFY COLUMN video_link VARCHAR(255) NULL default '';


