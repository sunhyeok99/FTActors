ALTER TABLE report ADD COLUMN created_at timestamp NOT NULL DEFAULT current_timestamp;
ALTER TABLE report ADD COLUMN updated_at timestamp NOT NULL DEFAULT current_timestamp;

ALTER TABLE report MODIFY COLUMN image_link VARCHAR(255) NULL default '';