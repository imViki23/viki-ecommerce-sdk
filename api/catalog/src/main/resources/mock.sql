-- Clear tables
TRUNCATE TABLE catalog.brands,
    catalog.products,
    catalog.variants,
    catalog.stocks;

-- 1. Insert Brands
INSERT INTO catalog.brands (brand_id) VALUES
  ('77f3f0cc-97d3-487d-aae4-c0cafd469d1f'), -- Brand Apple
  ('439e8d63-c99c-49b4-a51c-74c74193370d'); -- Brand Samsung

-- 2. Insert Products
INSERT INTO catalog.products (product_id, brand_id) VALUES
  ('7d2465ce-bc73-4876-bb29-1609143a3164', '77f3f0cc-97d3-487d-aae4-c0cafd469d1f'), -- IPhone 17
  ('f79b926c-695a-40fb-9eab-ccb41952e237', '77f3f0cc-97d3-487d-aae4-c0cafd469d1f'), -- IPhone 16
  ('6385a7d4-55a9-4940-9b10-3fe78fd30279', '439e8d63-c99c-49b4-a51c-74c74193370d'); -- Samsung S25

-- 3. Insert Variants
INSERT INTO catalog.variants (variant_id, product_id) VALUES
  ('1a9cafa3-912f-4478-a28c-47350fb0ebc0', '7d2465ce-bc73-4876-bb29-1609143a3164'), -- IPhone 17 Lavendar 256 GB
  ('9b7e6718-1e96-4a93-9f6a-cf3014127331', '7d2465ce-bc73-4876-bb29-1609143a3164'), -- IPhone 17 Lavendar 512 GB
  ('d93dcbfc-714c-4439-a3d5-c86a8ae3825f', 'f79b926c-695a-40fb-9eab-ccb41952e237'), -- IPhone 16 White 128 GB
  ('08eaad11-1d6c-4425-8336-4cfc98b968cf', '6385a7d4-55a9-4940-9b10-3fe78fd30279'); -- Samsung S25 Ultra Titanium Silverblue 256 GB

-- 4. Insert Stock
INSERT INTO catalog.stocks (variant_id, price, quantity) VALUES
  ('1a9cafa3-912f-4478-a28c-47350fb0ebc0', 82900.00, 50),
  ('9b7e6718-1e96-4a93-9f6a-cf3014127331', 102900.00, 30),
  ('d93dcbfc-714c-4439-a3d5-c86a8ae3825f', 69000.00, 10),
  ('08eaad11-1d6c-4425-8336-4cfc98b968cf', 97990.00, 30);