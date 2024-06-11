CREATE TABLE users (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       username VARCHAR(50) NOT NULL,
                       email VARCHAR(100) NOT NULL,
                       password VARCHAR(50) NOT NULL,
                       role VARCHAR(20) NOT NULL
);

CREATE TABLE categories (
                            id INT AUTO_INCREMENT PRIMARY KEY,
                            name VARCHAR(50) NOT NULL
);

CREATE TABLE products (
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          category_id INT,
                          name VARCHAR(100) NOT NULL,
                          description TEXT,
                          price DECIMAL(10, 2) NOT NULL,
                          stock INT NOT NULL,
                          FOREIGN KEY (category_id) REFERENCES categories(id)
);

CREATE TABLE carts (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       user_id INT,
                       product_id INT,
                       quantity INT NOT NULL,
                       FOREIGN KEY (user_id) REFERENCES users(id),
                       FOREIGN KEY (product_id) REFERENCES products(id)
);

CREATE TABLE likes (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       user_id INT,
                       product_id INT,
                       FOREIGN KEY (user_id) REFERENCES users(id),
                       FOREIGN KEY (product_id) REFERENCES products(id)
);

CREATE TABLE favourites (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       user_id INT,
                       product_id INT,
                       FOREIGN KEY (user_id) REFERENCES users(id),
                       FOREIGN KEY (product_id) REFERENCES products(id)
);
