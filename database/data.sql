BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');

INSERT INTO crust (crust_name, price) VALUES ('Thin Crust', 0.0);
INSERT INTO crust (crust_name, price) VALUES ('Extra Crispy', 0.0);
INSERT INTO crust (crust_name, price) VALUES ('Pan', 0.0);
INSERT INTO crust (crust_name, price) VALUES ('Chicago Style', 1.0);

INSERT INTO sauce (sauce_name, price) VALUES ('Regular', 0.0);
INSERT INTO sauce (sauce_name, price) VALUES ('Meat Sauce', 0.0);
INSERT INTO sauce (sauce_name, price) VALUES ('Chunky', 0.0);
INSERT INTO sauce (sauce_name, price) VALUES ('White Sauce', 0.0);

INSERT INTO pizza_size (size_name, price) VALUES ('Kids', 6.0);
INSERT INTO pizza_size (size_name, price) VALUES ('Medium', 10.0);
INSERT INTO pizza_size (size_name, price) VALUES ('Large', 13.0);
INSERT INTO pizza_size (size_name, price) VALUES ('XL', 15.0);

INSERT INTO toppings (topping_name, is_premium, is_available) VALUES ('Pepperoni', false, true);
INSERT INTO toppings (topping_name, is_premium, is_available) VALUES ('Extra Cheese', false, true);
INSERT INTO toppings (topping_name, is_premium, is_available) VALUES ('Peppers', false, true);
INSERT INTO toppings (topping_name, is_premium, is_available) VALUES ('Mushroom', false, true);
INSERT INTO toppings (topping_name, is_premium, is_available) VALUES ('Sausage', false, true);
INSERT INTO toppings (topping_name, is_premium, is_available) VALUES ('Anchovies', true, false);
INSERT INTO toppings (topping_name, is_premium, is_available) VALUES ('Onion', false, false);
INSERT INTO toppings (topping_name, is_premium, is_available) VALUES ('Jalapeno', false, true);
INSERT INTO toppings (topping_name, is_premium, is_available) VALUES ('Caviar', true, true);
INSERT INTO toppings (topping_name, is_premium, is_available) VALUES ('Gold Flakes', true, true);
INSERT INTO toppings (topping_name, is_premium, is_available) VALUES ('Olives', false, true);
INSERT INTO toppings (topping_name, is_premium, is_available) VALUES ('Feta Cheese', true, true);
INSERT INTO toppings (topping_name, is_premium, is_available) VALUES ('Spinach', false, true);
INSERT INTO toppings (topping_name, is_premium, is_available) VALUES ('Tomatoes', false, true);

INSERT INTO sale (sale_status, sale_total, is_delivery, customer_address, cc_last_four)
    VALUES ('pending', 8.0, true, '101 Street Drive', 1234);
INSERT INTO sale (sale_status, sale_total, is_delivery, customer_address, cc_last_four)
    VALUES ('canceled', 28.0, true, '200 Pleasant Ave', 5678);
INSERT INTO sale (sale_status, sale_total, is_delivery, customer_address, cc_last_four)
    VALUES ('ready', 15.0, true, '99 Elm St', 9012);
INSERT INTO sale (sale_status, sale_total, is_delivery, customer_address, cc_last_four)
    VALUES ('complete', 12.0, true, '1 Big House Lane', 3456);
INSERT INTO sale (sale_status, sale_total, is_delivery, customer_address, cc_last_four)
    VALUES ('complete', 6.0, true, '33 Rocky Road', 6789);
INSERT INTO sale (sale_status, sale_total, is_delivery, customer_address, cc_last_four)
    VALUES ('complete', 20.0, false, '55 Fox-run Lane', 0123);

INSERT INTO pizza (sale_id, pizza_size_id, crust_id, price, sauce_id)
    VALUES (1, 2, 2, 10.0, 1);
INSERT INTO pizza (sale_id, pizza_size_id, crust_id, price, sauce_id)
    VALUES (2, 1, 1, 20.0, 2);
INSERT INTO pizza (sale_id, pizza_size_id, crust_id, price, sauce_id)
    VALUES (3, 3, 3, 15.0, 3);
INSERT INTO pizza (sale_id, pizza_size_id, crust_id, price, sauce_id)
    VALUES (3, 2, 2, 12.0, 4);
INSERT INTO pizza (sale_id, pizza_size_id, crust_id, price, sauce_id)
    VALUES (4, 4, 1, 14.0, 1);

INSERT INTO pizza_topping (pizza_id, topping_id) VALUES (1,1);
INSERT INTO pizza_topping (pizza_id, topping_id) VALUES (1,2);
INSERT INTO pizza_topping (pizza_id, topping_id) VALUES (1,3);
INSERT INTO pizza_topping (pizza_id, topping_id) VALUES (2,2);
INSERT INTO pizza_topping (pizza_id, topping_id) VALUES (3,2);
INSERT INTO pizza_topping (pizza_id, topping_id) VALUES (4,1);
INSERT INTO pizza_topping (pizza_id, topping_id) VALUES (4,5);
INSERT INTO pizza_topping (pizza_id, topping_id) VALUES (5,10);
INSERT INTO pizza_topping (pizza_id, topping_id) VALUES (5,9);
INSERT INTO pizza_topping (pizza_id, topping_id) VALUES (5,8);
INSERT INTO pizza_topping (pizza_id, topping_id) VALUES (5,6);

INSERT INTO specialty_pizza (pizza_name, crust_id, sauce_id, price, pizza_size_id, is_available, description, image, is_square_cut)
    VALUES('Veggie Pizza', 1, 2, 10.0, 3, true, 'This is our staple veggie pizza covered in a plethora of garden goodies. Just waiting for you to take a bite.', 'veggie-pizza.jpg', false );
INSERT INTO specialty_pizza (pizza_name, crust_id, sauce_id, price, pizza_size_id, is_available, description, image, is_square_cut)
    VALUES('Meat Lovers Pizza', 2, 3, 20.0, 2, true, 'All the meats we have, all for you. Take a look or better yet, take a bite!', 'meat-pizza.jpg', false);
INSERT INTO specialty_pizza (pizza_name, crust_id, sauce_id, price, pizza_size_id, is_available, description, image, is_square_cut)
    VALUES('Meat Haters Pizza', 3, 1, 12.0, 1, true, 'None of the meats we have, all for you. Take a look or better yet, take a bite!', 'cheese-pizza.jpg', false);
INSERT INTO specialty_pizza (pizza_name, crust_id, sauce_id, price, pizza_size_id, is_available, description, image, is_square_cut)
    VALUES('Deluxe', 2, 3, 100.0, 4, true, 'Deluxe means only the best - Enjoy our fancy schmancy topping laden deluxe pizza!', 'deluxe-pizza.jpg', false);
INSERT INTO specialty_pizza (pizza_name, crust_id, sauce_id, price, pizza_size_id, is_available, description, image, is_square_cut)
    VALUES('Greek Pizza', 1, 4, 16.00, 4, true, 'Tomatoes, feta cheese, black olives and spinach.', 'greek-pizza.jpg', false);
INSERT INTO specialty_pizza (pizza_name, crust_id, sauce_id, price, pizza_size_id, is_available, description, image, is_square_cut)
    VALUES('Supreme', 4, 2, 18.00, 4, true, 'Classic supreme with pepperoni, sausage, green peppers, mushrooms, onions, and olives.', 'supreme-pizza.jpg', false);

INSERT INTO specialty_topping (specialty_id, topping_id) VALUES (1,2);
INSERT INTO specialty_topping (specialty_id, topping_id) VALUES (1,3);
INSERT INTO specialty_topping (specialty_id, topping_id) VALUES (1,4);
INSERT INTO specialty_topping (specialty_id, topping_id) VALUES (1,5);
INSERT INTO specialty_topping (specialty_id, topping_id) VALUES (1,6);
INSERT INTO specialty_topping (specialty_id, topping_id) VALUES (2,1);
INSERT INTO specialty_topping (specialty_id, topping_id) VALUES (2,5);
INSERT INTO specialty_topping (specialty_id, topping_id) VALUES (2,6);
INSERT INTO specialty_topping (specialty_id, topping_id) VALUES (3,2);
INSERT INTO specialty_topping (specialty_id, topping_id) VALUES (4,10);
INSERT INTO specialty_topping (specialty_id, topping_id) VALUES (4,9);

COMMIT TRANSACTION;
