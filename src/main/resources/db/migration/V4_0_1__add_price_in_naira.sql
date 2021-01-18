alter table products add column price_in_naira DECIMAL NOT NULL;

alter table products rename column price_in_naira to price_in_eur;
truncate products;
