CREATE FUNCTION last_upd_trig() RETURNS trigger
    LANGUAGE plpgsql AS
$$BEGIN
    NEW.last_updated := current_timestamp;
    RETURN NEW;
END;$$;

CREATE TRIGGER last_upd_trigger
    BEFORE INSERT OR UPDATE ON products
    FOR EACH ROW
EXECUTE PROCEDURE last_upd_trig();
