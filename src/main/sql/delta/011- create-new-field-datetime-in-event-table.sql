ALTER TABLE Event ADD COLUMN datetime TIMESTAMP
ALTER TABLE Event DROP COLUMN event_date
ALTER TABLE Event DROP COLUMN event_time