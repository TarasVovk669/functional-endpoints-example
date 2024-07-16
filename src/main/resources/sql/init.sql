DROP TABLE IF EXISTS Orders;
DROP TABLE IF EXISTS Product;

-- Create Product table
CREATE TABLE Product (
                         id BIGINT PRIMARY KEY,
                         name VARCHAR(255),
                         price DECIMAL(15, 2)
);

-- Create Orders table
CREATE TABLE Orders (
                        id BIGINT PRIMARY KEY,
                        product_id BIGINT,
                        user_id BIGINT,
                        CONSTRAINT fk_product FOREIGN KEY (product_id) REFERENCES Product(id)
);

INSERT INTO Product (id, name, price) VALUES (1, 'Product 1', 10.99);
INSERT INTO Product (id, name, price) VALUES (2, 'Product 2', 15.49);
INSERT INTO Product (id, name, price) VALUES (3, 'Product 3', 20.00);
INSERT INTO Product (id, name, price) VALUES (4, 'Product 4', 25.99);
INSERT INTO Product (id, name, price) VALUES (5, 'Product 5', 30.49);
INSERT INTO Product (id, name, price) VALUES (6, 'Product 6', 35.00);
INSERT INTO Product (id, name, price) VALUES (7, 'Product 7', 40.99);
INSERT INTO Product (id, name, price) VALUES (8, 'Product 8', 45.49);
INSERT INTO Product (id, name, price) VALUES (9, 'Product 9', 50.00);
INSERT INTO Product (id, name, price) VALUES (10, 'Product 10', 55.99);
INSERT INTO Product (id, name, price) VALUES (11, 'Product 11', 60.49);
INSERT INTO Product (id, name, price) VALUES (12, 'Product 12', 65.00);
INSERT INTO Product (id, name, price) VALUES (13, 'Product 13', 70.99);
INSERT INTO Product (id, name, price) VALUES (14, 'Product 14', 75.49);
INSERT INTO Product (id, name, price) VALUES (15, 'Product 15', 80.00);
INSERT INTO Product (id, name, price) VALUES (16, 'Product 16', 85.99);
INSERT INTO Product (id, name, price) VALUES (17, 'Product 17', 90.49);
INSERT INTO Product (id, name, price) VALUES (18, 'Product 18', 95.00);
INSERT INTO Product (id, name, price) VALUES (19, 'Product 19', 100.99);
INSERT INTO Product (id, name, price) VALUES (20, 'Product 20', 105.49);
INSERT INTO Product (id, name, price) VALUES (21, 'Product 21', 110.00);
INSERT INTO Product (id, name, price) VALUES (22, 'Product 22', 115.99);
INSERT INTO Product (id, name, price) VALUES (23, 'Product 23', 120.49);
INSERT INTO Product (id, name, price) VALUES (24, 'Product 24', 125.00);
INSERT INTO Product (id, name, price) VALUES (25, 'Product 25', 130.99);
INSERT INTO Product (id, name, price) VALUES (26, 'Product 26', 135.49);
INSERT INTO Product (id, name, price) VALUES (27, 'Product 27', 140.00);
INSERT INTO Product (id, name, price) VALUES (28, 'Product 28', 145.99);
INSERT INTO Product (id, name, price) VALUES (29, 'Product 29', 150.49);
INSERT INTO Product (id, name, price) VALUES (30, 'Product 30', 155.00);
INSERT INTO Product (id, name, price) VALUES (31, 'Product 31', 160.99);
INSERT INTO Product (id, name, price) VALUES (32, 'Product 32', 165.49);
INSERT INTO Product (id, name, price) VALUES (33, 'Product 33', 170.00);
INSERT INTO Product (id, name, price) VALUES (34, 'Product 34', 175.99);
INSERT INTO Product (id, name, price) VALUES (35, 'Product 35', 180.49);
INSERT INTO Product (id, name, price) VALUES (36, 'Product 36', 185.00);
INSERT INTO Product (id, name, price) VALUES (37, 'Product 37', 190.99);
INSERT INTO Product (id, name, price) VALUES (38, 'Product 38', 195.49);
INSERT INTO Product (id, name, price) VALUES (39, 'Product 39', 200.00);
INSERT INTO Product (id, name, price) VALUES (40, 'Product 40', 205.99);
INSERT INTO Product (id, name, price) VALUES (41, 'Product 41', 210.49);
INSERT INTO Product (id, name, price) VALUES (42, 'Product 42', 215.00);
INSERT INTO Product (id, name, price) VALUES (43, 'Product 43', 220.99);
INSERT INTO Product (id, name, price) VALUES (44, 'Product 44', 225.49);
INSERT INTO Product (id, name, price) VALUES (45, 'Product 45', 230.00);
INSERT INTO Product (id, name, price) VALUES (46, 'Product 46', 235.99);
INSERT INTO Product (id, name, price) VALUES (47, 'Product 47', 240.49);
INSERT INTO Product (id, name, price) VALUES (48, 'Product 48', 245.00);
INSERT INTO Product (id, name, price) VALUES (49, 'Product 49', 250.99);
INSERT INTO Product (id, name, price) VALUES (50, 'Product 50', 255.49);

INSERT INTO Orders (id, product_id, user_id) VALUES (1, 1, 1001);
INSERT INTO Orders (id, product_id, user_id) VALUES (2, 2, 1002);
INSERT INTO Orders (id, product_id, user_id) VALUES (3, 3, 1003);
INSERT INTO Orders (id, product_id, user_id) VALUES (4, 4, 1004);
INSERT INTO Orders (id, product_id, user_id) VALUES (5, 5, 1005);
INSERT INTO Orders (id, product_id, user_id) VALUES (6, 6, 1006);
INSERT INTO Orders (id, product_id, user_id) VALUES (7, 7, 1007);
INSERT INTO Orders (id, product_id, user_id) VALUES (8, 8, 1008);
INSERT INTO Orders (id, product_id, user_id) VALUES (9, 9, 1009);
INSERT INTO Orders (id, product_id, user_id) VALUES (10, 10, 1010);
INSERT INTO Orders (id, product_id, user_id) VALUES (11, 11, 1011);
INSERT INTO Orders (id, product_id, user_id) VALUES (12, 12, 1012);
INSERT INTO Orders (id, product_id, user_id) VALUES (13, 13, 1013);
INSERT INTO Orders (id, product_id, user_id) VALUES (14, 14, 1014);
INSERT INTO Orders (id, product_id, user_id) VALUES (15, 15, 1015);
INSERT INTO Orders (id, product_id, user_id) VALUES (16, 16, 1016);
INSERT INTO Orders (id, product_id, user_id) VALUES (17, 17, 1017);
INSERT INTO Orders (id, product_id, user_id) VALUES (18, 18, 1018);
INSERT INTO Orders (id, product_id, user_id) VALUES (19, 19, 1019);
INSERT INTO Orders (id, product_id, user_id) VALUES (20, 20, 1020);
INSERT INTO Orders (id, product_id, user_id) VALUES (21, 21, 1021);
INSERT INTO Orders (id, product_id, user_id) VALUES (22, 22, 1022);
INSERT INTO Orders (id, product_id, user_id) VALUES (23, 23, 1023);
INSERT INTO Orders (id, product_id, user_id) VALUES (24, 24, 1024);
INSERT INTO Orders (id, product_id, user_id) VALUES (25, 25, 1025);
INSERT INTO Orders (id, product_id, user_id) VALUES (26, 26, 1026);
INSERT INTO Orders (id, product_id, user_id) VALUES (27, 27, 1027);
INSERT INTO Orders (id, product_id, user_id) VALUES (28, 28, 1028);
INSERT INTO Orders (id, product_id, user_id) VALUES (29, 29, 1029);
INSERT INTO Orders (id, product_id, user_id) VALUES (30, 30, 1030);
INSERT INTO Orders (id, product_id, user_id) VALUES (31, 31, 1031);
INSERT INTO Orders (id, product_id, user_id) VALUES (32, 32, 1032);
INSERT INTO Orders (id, product_id, user_id) VALUES (33, 33, 1033);
INSERT INTO Orders (id, product_id, user_id) VALUES (34, 34, 1034);
INSERT INTO Orders (id, product_id, user_id) VALUES (35, 35, 1035);
INSERT INTO Orders (id, product_id, user_id) VALUES (36, 36, 1036);
INSERT INTO Orders (id, product_id, user_id) VALUES (37, 37, 1037);
INSERT INTO Orders (id, product_id, user_id) VALUES (38, 38, 1038);
INSERT INTO Orders (id, product_id, user_id) VALUES (39, 39, 1039);
INSERT INTO Orders (id, product_id, user_id) VALUES (40, 40, 1040);
INSERT INTO Orders (id, product_id, user_id) VALUES (41, 41, 1041);
INSERT INTO Orders (id, product_id, user_id) VALUES (42, 42, 1042);
INSERT INTO Orders (id, product_id, user_id) VALUES (43, 43, 1043);
INSERT INTO Orders (id, product_id, user_id) VALUES (44, 44, 1044);
INSERT INTO Orders (id, product_id, user_id) VALUES (45, 45, 1045);
INSERT INTO Orders (id, product_id, user_id) VALUES (46, 46, 1046);
INSERT INTO Orders (id, product_id, user_id) VALUES (47, 47, 1047);
INSERT INTO Orders (id, product_id, user_id) VALUES (48, 48, 1048);
INSERT INTO Orders (id, product_id, user_id) VALUES (49, 49, 1049);
INSERT INTO Orders (id, product_id, user_id) VALUES (50, 50, 1050);