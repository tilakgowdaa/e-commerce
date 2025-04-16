-- Insert data into the `category` table
INSERT INTO category (id, description, name) VALUES
    (1, 'Electronics items', 'Electronics'),
    (2, 'Home and Kitchen items', 'Home & Kitchen'),
    (3, 'Books and Stationery', 'Books & Stationery'),
    (4, 'Fitness and Sports items', 'Fitness & Sports'),
    (5, 'Clothing and Accessories', 'Clothing');

-- Insert data into the `product` table
INSERT INTO product (id, description, name, available_quantity, price, category_id) VALUES
    (nextval('product_seq'), 'Smartphone with 6GB RAM', 'Smartphone', 50, 699.99, 1),
    (nextval('product_seq'), 'Blender with multiple attachments', 'Blender', 30, 49.99, 2),
    (nextval('product_seq'), 'Fictional novel', 'Novel', 100, 19.99, 3),
    (nextval('product_seq'), 'Yoga mat with anti-slip surface', 'Yoga Mat', 25, 24.99, 4),
    (nextval('product_seq'), 'Casual T-shirt', 'T-shirt', 75, 15.99, 5);
