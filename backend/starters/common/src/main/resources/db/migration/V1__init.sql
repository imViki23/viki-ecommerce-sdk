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
CREATE SCHEMA IF NOT EXISTS profiles;
CREATE SCHEMA IF NOT EXISTS orders;

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

-- Users
CREATE TABLE profiles.users (
    user_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name TEXT NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password TEXT NOT NULL,
    role VARCHAR(255) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL
);
CREATE TRIGGER trigger_users_updated_at
    BEFORE UPDATE ON profiles.users
    FOR EACH ROW
    EXECUTE FUNCTION update_updated_at_column();

-- Vendors
CREATE TABLE profiles.vendors (
    vendor_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name TEXT NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL
);
CREATE TRIGGER trigger_vendors_updated_at
    BEFORE UPDATE ON profiles.vendors
    FOR EACH ROW
    EXECUTE FUNCTION update_updated_at_column();

-- ORDERS (Header table for customer purchases)
CREATE TABLE orders.orders (
    order_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    user_id UUID NOT NULL,
    status VARCHAR(50) NOT NULL DEFAULT 'pending', -- e.g., pending, paid, shipped, cancelled, completed
    total_amount INT NOT NULL CHECK (total_amount >= 0), -- Stored in the smallest currency unit (e.g., cents/paise)
    shipping_address JSONB NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    CONSTRAINT fk_orders_users FOREIGN KEY (user_id) REFERENCES profiles.users(user_id)
);
CREATE TRIGGER trigger_orders_updated_at
    BEFORE UPDATE ON orders.orders
    FOR EACH ROW
    EXECUTE FUNCTION update_updated_at_column();

-- ORDER ITEMS (Line items linking orders to specific product variants and vendors)
CREATE TABLE orders.order_items (
    order_item_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    order_id UUID NOT NULL,
    variant_id UUID NOT NULL,
    vendor_id UUID NOT NULL,
    quantity INT NOT NULL CHECK (quantity > 0),
    unit_price INT NOT NULL CHECK (unit_price > 0), -- Price locked in at time of purchase
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    CONSTRAINT fk_items_orders FOREIGN KEY (order_id) REFERENCES orders.orders(order_id) ON DELETE CASCADE,
    -- References the composite primary key on the stocks/vendors table
    CONSTRAINT fk_items_stocks FOREIGN KEY (variant_id, vendor_id) REFERENCES catalog.stocks(variant_id, vendor_id)
);
CREATE TRIGGER trigger_order_items_updated_at
    BEFORE UPDATE ON orders.order_items
    FOR EACH ROW
    EXECUTE FUNCTION update_updated_at_column();

ALTER TABLE catalog.products REPLICA IDENTITY FULL;
ALTER TABLE catalog.product_variants REPLICA IDENTITY FULL;