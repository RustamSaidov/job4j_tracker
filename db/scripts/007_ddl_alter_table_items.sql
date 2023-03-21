alter table items drop COLUMN created;
alter table items add column created TIMESTAMP WITHOUT TIME ZONE DEFAULT now();