-- Extensions
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- Triggers
CREATE OR REPLACE FUNCTION update_updated_at_column()
RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at = CURRENT_TIMESTAMP;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- SCHEMA
CREATE SCHEMA IF NOT EXISTS catalog;
CREATE SCHEMA IF NOT EXISTS users;

-- BRAND
CREATE TABLE catalog.brands (
    brand_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name TEXT NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL
);
CREATE TRIGGER trigger_brand_updated_at
    BEFORE UPDATE ON catalog.brands
    FOR EACH ROW
    EXECUTE FUNCTION update_updated_at_column();

-- Products
CREATE TABLE catalog.products (
    product_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name TEXT NOT NULL,
    slug VARCHAR(255) NOT NULL UNIQUE,
    brand_id UUID NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    CONSTRAINT fk_products_brands FOREIGN KEY (brand_id) REFERENCES catalog.brands(brand_id)
);
CREATE TRIGGER trigger_products_updated_at
    BEFORE UPDATE ON catalog.products
    FOR EACH ROW
    EXECUTE FUNCTION update_updated_at_column();

-- Variants
CREATE TABLE catalog.product_variants (
    variant_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    sku VARCHAR(100) NOT NULL UNIQUE,
    product_id UUID NOT NULL,
    attributes JSONB NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    CONSTRAINT fk_variants_products FOREIGN KEY (product_id) REFERENCES catalog.products(product_id)
);
CREATE TRIGGER trigger_variants_updated_at
    BEFORE UPDATE ON catalog.product_variants
    FOR EACH ROW
    EXECUTE FUNCTION update_updated_at_column();

-- Stock
CREATE TABLE catalog.stocks (
    variant_id UUID NOT NULL,
    vendor_id UUID NOT NULL,
    quantity INT NOT NULL CHECK ( quantity >= 0 ),
    price INT NOT NULL CHECK ( price > 0 ),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    CONSTRAINT pk_stocks PRIMARY KEY (variant_id, vendor_id)
);
CREATE TRIGGER trigger_stocks_updated_at
    BEFORE UPDATE ON catalog.stocks
    FOR EACH ROW
    EXECUTE FUNCTION update_updated_at_column();


-- Vendors
CREATE TABLE users.vendors (
    vendor_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name TEXT NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL
);
CREATE TRIGGER trigger_vendors_updated_at
    BEFORE UPDATE ON users.vendors
    FOR EACH ROW
    EXECUTE FUNCTION update_updated_at_column();

ALTER TABLE catalog.products REPLICA IDENTITY FULL;
ALTER TABLE catalog.product_variants REPLICA IDENTITY FULL;