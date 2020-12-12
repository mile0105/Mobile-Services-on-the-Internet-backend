CREATE EXTENSION moddatetime;

CREATE TRIGGER product_last_update
    BEFORE UPDATE OR INSERT ON products
    FOR EACH ROW EXECUTE PROCEDURE moddatetime(last_updated)
