BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS sale;
DROP TABLE IF EXISTS sauce;
DROP TABLE IF EXISTS pizza_size;
DROP TABLE IF EXISTS crust;
DROP TABLE IF EXISTS toppings;
DROP TABLE IF EXISTS pizza_topping;
DROP TABLE IF EXISTS specialty_pizza;
DROP TABLE IF EXISTS specialty_topping;


CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);


BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS sale;
DROP TABLE IF EXISTS sauce;
DROP TABLE IF EXISTS pizza_size;
DROP TABLE IF EXISTS crust;
DROP TABLE IF EXISTS toppings;
DROP TABLE IF EXISTS pizza_topping;
DROP TABLE IF EXISTS specialty_pizza;
DROP TABLE IF EXISTS specialty_topping;


CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);



CREATE TABLE sale (
    sale_id SERIAL NOT NULL,
    sale_status varchar(25),
    sale_total numeric(13,2),
    is_delivery boolean,
    customer_address varchar(50),
    cc_last_four int,
    CONSTRAINT PK_sale PRIMARY KEY (sale_id)
    );

CREATE TABLE sauce (
    sauce_id SERIAL,
    sauce_name varchar(50) NOT NULL UNIQUE,
    price NUMERIC,
    CONSTRAINT PK_sauce PRIMARY KEY (sauce_id)
 );

CREATE TABLE pizza_size (
    pizza_size_id SERIAL NOT NULL,
    size_name varchar(50) NOT NULL UNIQUE,
    price NUMERIC,
    CONSTRAINT PK_pizza_size PRIMARY KEY (pizza_size_id)
);

CREATE TABLE crust (
    crust_id SERIAL,
    crust_name varchar(50) NOT NULL UNIQUE,
    price NUMERIC,
    CONSTRAINT PK_crust PRIMARY KEY (crust_id)
);

CREATE TABLE toppings(
    topping_id SERIAL NOT NULL,
    topping_name varchar(30) NOT NULL,
    is_premium boolean NOT NULL,
    is_available boolean NOT NULL,
    CONSTRAINT PK_topping_id PRIMARY KEY (topping_id)
);



CREATE TABLE pizza (
    pizza_id SERIAL NOT NULL,
    sale_id int NOT NULL,
    crust_id int NOT NULL,
    sauce_id int NOT NULL,
    pizza_size_id int NOT NULL,
    price numeric not NULL,
    is_square_cut boolean DEFAULT FALSE,
    CONSTRAINT PK_pizza_id PRIMARY KEY (pizza_id),
    CONSTRAINT FK_sale_id FOREIGN KEY (sale_id) REFERENCES sale (sale_id),
    CONSTRAINT FK_crust_id FOREIGN KEY (crust_id) REFERENCES crust (crust_id),
    CONSTRAINT FK_sauce_id FOREIGN KEY (sauce_id) REFERENCES sauce (sauce_id),
    CONSTRAINT FK_pizza_size_id FOREIGN KEY (pizza_size_id) REFERENCES pizza_size (pizza_size_id)
    );

CREATE TABLE pizza_topping(
        pizza_id int NOT NULL,
        topping_id int NOT NULL,
        CONSTRAINT FK_pizza_id FOREIGN KEY (pizza_id) REFERENCES pizza (pizza_id),
        CONSTRAINT FK_topping_id FOREIGN KEY (topping_id) REFERENCES toppings (topping_id)
        );

CREATE TABLE specialty_pizza (
    specialty_id SERIAL NOT NULL,
    pizza_name varchar(50) NOT NULL,
    crust_id int NOT NULL,
    sauce_id int NOT NULL,
    pizza_size_id int NOT NULL,
    price numeric not NULL,
    is_available boolean NOT NULL,
    description varchar(200),
    image varchar(100),
    is_square_cut boolean NOT NULL,
    CONSTRAINT PK_specialty_id PRIMARY KEY (specialty_id),
    CONSTRAINT FK_crust_id FOREIGN KEY (crust_id) REFERENCES crust (crust_id),
    CONSTRAINT FK_sauce_id FOREIGN KEY (sauce_id) REFERENCES sauce (sauce_id),
    CONSTRAINT FK_pizza_size_id FOREIGN KEY (pizza_size_id) REFERENCES pizza_size (pizza_size_id)
    );

CREATE TABLE specialty_topping(
   specialty_id int NOT NULL,
    topping_id int NOT NULL,
    CONSTRAINT FK_specialty_id FOREIGN KEY (specialty_id) REFERENCES specialty_pizza (specialty_id),
    CONSTRAINT FK_topping_id FOREIGN KEY (topping_id) REFERENCES toppings (topping_id)
    );

COMMIT TRANSACTION;


