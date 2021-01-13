create table warehouses(
                           id serial not null primary key,
                           name text not null unique
);

create table warehouses_products(
                                    warehouse_id serial references warehouses(id) on update cascade on delete cascade,
                                    product_id serial references products(id) on update cascade on delete cascade,
                                    quantity int not null default 0,
                                    constraint warehouses_products_primary_key primary key (warehouse_id, product_id)
);

insert into warehouses(name) values('First');

insert into warehouses_products(warehouse_id, product_id, quantity)
select 1, id, quantity from products;

alter table products drop column quantity;
