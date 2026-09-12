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

-- BRAND
CREATE TABLE catalog.brands (
    brand_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
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
    brand_id UUID NOT NULL REFERENCES catalog.brands(brand_id),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL
);
CREATE TRIGGER trigger_products_updated_at
    BEFORE UPDATE ON catalog.products
    FOR EACH ROW
    EXECUTE FUNCTION update_updated_at_column();

-- Variants
CREATE TABLE catalog.variants (
    variant_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    product_id UUID NOT NULL REFERENCES catalog.products (product_id),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL
);
CREATE TRIGGER trigger_variants_updated_at
    BEFORE UPDATE ON catalog.variants
    FOR EACH ROW
    EXECUTE FUNCTION update_updated_at_column();

-- Stock
CREATE TABLE catalog.stocks (
    variant_id UUID NOT NULL REFERENCES catalog.variants(variant_id),
    price NUMERIC(12, 2) NOT NULL CHECK ( price >= 0 ),
    quantity INT NOT NULL CHECK ( quantity >= 0 ),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL
);
CREATE TRIGGER trigger_stock_updated_at
    BEFORE UPDATE ON catalog.stocks
    FOR EACH ROW
    EXECUTE FUNCTION update_updated_at_column();
