create database ecommerce_db;
use ecommerce_db;

create table products (
product_id int auto_increment primary key, name varchar(150) not null, category varchar(80), price decimal(10,2) not null, stock_quantity int not null default 0, added_on timestamp default current_timestamp
);

create table customers (
customer_id   int auto_increment primary key,
name varchar(100) not null, email varchar(120) unique not null, phone varchar(15), address varchar(255)
);

create table orders (
order_id int auto_increment primary key, customer_id int not null, order_date timestamp default current_timestamp, total_amount decimal(10,2) not null default 0.00, status varchar(20) not null default 'PLACED',  -- PLACED, SHIPPED, DELIVERED, CANCELLED
constraint fk_order_customer foreign key (customer_id)
references customers(customer_id)
on delete cascade
);

create table order_items (
order_item_id  int auto_increment primary key, order_id int not null, product_id int not null, quantity int not null, unit_price decimal(10,2) not null,
constraint fk_item_order foreign key (order_id) references orders(order_id)
on delete cascade, constraint fk_item_product foreign key (product_id)
references products(product_id)
);
