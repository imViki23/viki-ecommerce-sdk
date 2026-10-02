-- Clear tables
TRUNCATE TABLE catalog.brands,
    catalog.products,
    catalog.product_variants,
    catalog.stocks,
    profiles.users,
    profiles.vendors,
    profiles.roles,
    profiles.permissions,
    profiles.role_permissions,
    orders.orders,
    orders.order_items;

CREATE EXTENSION IF NOT EXISTS pgcrypto;

-- Roles
INSERT INTO profiles.roles (role_id, name, description) VALUES
('9d3349bc-7c58-41aa-88fa-88cac3fe5f59', 'ADMIN', 'Administrator role with full access'),
('4a382935-4ce2-43eb-aaf3-7b452728886c', 'USER', 'Regular user role with limited access');

-- Permissions
INSERT INTO profiles.permissions (permission_id, name, description) VALUES
('1827d735-3db2-4a6b-9515-c4d1df422dcc', 'CREATE_PRODUCT', 'Permission to create products'),
('60a845c3-7231-4d1a-b5fc-476dddeaf2c8', 'UPDATE_PRODUCT', 'Permission to update products'),
('81dadd3b-ac43-477c-a623-0716583b3de2', 'DELETE_PRODUCT', 'Permission to delete products'),
('6e2f7d44-3405-447e-95d2-cc744cd6a1a4', 'VIEW_PRODUCT', 'Permission to view products');

-- Role-Permission Mapping
INSERT INTO profiles.role_permissions (role_id, permission_id) VALUES
('9d3349bc-7c58-41aa-88fa-88cac3fe5f59', '1827d735-3db2-4a6b-9515-c4d1df422dcc'),  -- ADMIN -> CREATE_PRODUCT
('9d3349bc-7c58-41aa-88fa-88cac3fe5f59', '60a845c3-7231-4d1a-b5fc-476dddeaf2c8'),  -- ADMIN -> UPDATE_PRODUCT
('9d3349bc-7c58-41aa-88fa-88cac3fe5f59', '81dadd3b-ac43-477c-a623-0716583b3de2'),  -- ADMIN -> DELETE_PRODUCT
('9d3349bc-7c58-41aa-88fa-88cac3fe5f59', '6e2f7d44-3405-447e-95d2-cc744cd6a1a4'),  -- ADMIN -> VIEW_PRODUCT
('4a382935-4ce2-43eb-aaf3-7b452728886c', '6e2f7d44-3405-447e-95d2-cc744cd6a1a4');  -- USER -> VIEW_PRODUCT


-- Users
INSERT INTO profiles.users (name, email, password, role) VALUES
('imviki', 'imviki@sdk.com', crypt('Test@123', gen_salt('bf', 10)), 'USER'),
('admin', 'admin@sdk.com', crypt('Test@123', gen_salt('bf', 10)), 'ADMIN');

-- Vendors
INSERT INTO profiles.vendors (vendor_id, name) VALUES
('3c7c468d-0382-4ca3-81fb-fe7d7a863b1f', 'Muthu Electronics'),
('a4a41a6f-1827-4f7c-a84c-4a61dfe5b802', 'Lakshmi Electronics');

-- Brands
INSERT INTO catalog.brands (brand_id, name) VALUES
('77f3f0cc-97d3-487d-aae4-c0cafd469d1f', 'Apple'  ),
('439e8d63-c99c-49b4-a51c-74c74193370d', 'Samsung');

-- Products
INSERT INTO catalog.products (product_id, brand_id, name, slug) VALUES
('7d2465ce-bc73-4876-bb29-1609143a3164', '77f3f0cc-97d3-487d-aae4-c0cafd469d1f', 'iPhone 17',   'iphone-17'),
('f79b926c-695a-40fb-9eab-ccb41952e237', '77f3f0cc-97d3-487d-aae4-c0cafd469d1f', 'iPhone 16',   'iphone-16'),
('6385a7d4-55a9-4940-9b10-3fe78fd30279', '439e8d63-c99c-49b4-a51c-74c74193370d', 'Samsung S25', 'samsung-s25');

-- Variants
INSERT INTO catalog.product_variants (variant_id, product_id, sku, attributes) VALUES
('1a9cafa3-912f-4478-a28c-47350fb0ebc0', '7d2465ce-bc73-4876-bb29-1609143a3164', 'IP17LVD256',    '{"color": "Lavendar", "memory": "256GB"}'::jsonb ),  -- IPhone 17 Lavendar 256 GB
('9b7e6718-1e96-4a93-9f6a-cf3014127331', '7d2465ce-bc73-4876-bb29-1609143a3164', 'IP17LVD512',    '{"color": "Lavendar", "memory": "512GB"}'::jsonb ),  -- IPhone 17 Lavendar 512 GB
('d93dcbfc-714c-4439-a3d5-c86a8ae3825f', 'f79b926c-695a-40fb-9eab-ccb41952e237', 'IP16WHT128',    '{"color": "White", "memory": "128GB"}'::jsonb    ),  -- IPhone 16 White 128 GB
('08eaad11-1d6c-4425-8336-4cfc98b968cf', '6385a7d4-55a9-4940-9b10-3fe78fd30279', 'SM25ULBLK256',  '{"color": "Black", "memory": "256GB"}'::jsonb    );  -- Samsung S25 Black 256 GB

-- Stocks
INSERT INTO catalog.stocks(vendor_id, variant_id, quantity, price) VALUES
('3c7c468d-0382-4ca3-81fb-fe7d7a863b1f', '1a9cafa3-912f-4478-a28c-47350fb0ebc0', 100, 250000),
('a4a41a6f-1827-4f7c-a84c-4a61dfe5b802', '1a9cafa3-912f-4478-a28c-47350fb0ebc0', 75, 225000),
('3c7c468d-0382-4ca3-81fb-fe7d7a863b1f', '9b7e6718-1e96-4a93-9f6a-cf3014127331', 150, 255000),
('a4a41a6f-1827-4f7c-a84c-4a61dfe5b802', '9b7e6718-1e96-4a93-9f6a-cf3014127331', 5, 235000);