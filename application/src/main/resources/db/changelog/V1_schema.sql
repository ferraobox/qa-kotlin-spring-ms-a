CREATE TABLE cousine (
                         id INT NOT NULL AUTO_INCREMENT,
                         name VARCHAR(100) NOT NULL,
                         UNIQUE (name),
                         PRIMARY KEY (id)
);

INSERT INTO cousine (name) VALUES ('Chinese');
INSERT INTO cousine (name) VALUES ('Pizza');
INSERT INTO cousine (name) VALUES ('Sushi');
INSERT INTO cousine (name) VALUES ('Vietnamese');

CREATE TABLE store (
                       id INT NOT NULL AUTO_INCREMENT,
                       name VARCHAR(100) NOT NULL,
                       address VARCHAR(300) NOT NULL,
                       cousine_id INT NOT NULL,
                       UNIQUE(name),
                       PRIMARY KEY (id),
                       FOREIGN KEY (cousine_id) REFERENCES cousine (id)
);

SET @COUSINE_ID = SELECT id FROM cousine WHERE name = 'Chinese';

INSERT INTO store (name, address, cousine_id)
VALUES ('Hai Shang', '2991 Pembina Hwy, Winnipeg, Manitoba R3T 2H5, Canada', @COUSINE_ID);

SET @COUSINE_ID = SELECT id FROM cousine WHERE name = 'Pizza';

INSERT INTO store (name, address, cousine_id)
VALUES ('Za Pizza Bistro', 'E-1220 St Mary s Rd, Winnipeg, Manitoba R2M 3V6, Canada', @COUSINE_ID);

CREATE TABLE product (
                         id INT NOT NULL AUTO_INCREMENT,
                         name VARCHAR(100) NOT NULL,
                         description VARCHAR(300) NOT NULL,
                         price DECIMAL(19, 2) NOT NULL,
                         store_id INT NOT NULL,
                         UNIQUE (name, store_id),
                         PRIMARY KEY (id),
                         FOREIGN KEY (store_id) REFERENCES store (id)
);

SET @STORE_ID = SELECT id FROM store WHERE name = 'Hai Shang';

INSERT INTO product (name, description, price, store_id)
VALUES ('Shrimp Tempura', 'Fresh shrimp battered and deep fried until golden brown', 10.95, @STORE_ID);

CREATE TABLE customer (
                          id INT NOT NULL AUTO_INCREMENT,
                          name VARCHAR(50) NOT NULL,
                          email VARCHAR(100) NOT NULL,
                          address VARCHAR(200) NOT NULL,
                          password VARCHAR(100) NOT NULL,
                          UNIQUE (email),
                          PRIMARY KEY (id)
);

INSERT INTO customer (name, email, address, password)
VALUES ('carlos ferrao', 'carlosferrao@spring.com', 'addressfake', '12345');

CREATE TABLE orders (
                        id INT NOT NULL AUTO_INCREMENT,
                        customer_id int NOT NULL,
                        store_id int NOT NULL,
                        total DECIMAL(19, 2) NOT NULL,
                        status VARCHAR(100) NOT NULL,
                        created_at DATETIME NOT NULL,
                        updated_at DATETIME NOT NULL,
                        PRIMARY KEY (id),
                        FOREIGN KEY (customer_id) REFERENCES customer (id),
                        FOREIGN KEY (store_id) REFERENCES store (id)
);

CREATE TABLE order_item (
                            id INT NOT NULL AUTO_INCREMENT,
                            order_id int NOT NULL,
                            product_id int not null,
                            quantity int NOT NULL,
                            price DECIMAL(19, 2) NOT NULL,
                            total DECIMAL(19, 2) NOT NULL,
                            PRIMARY KEY (id),
                            FOREIGN KEY (order_id) REFERENCES orders (id),
                            FOREIGN KEY (product_id) REFERENCES product (id)
);
