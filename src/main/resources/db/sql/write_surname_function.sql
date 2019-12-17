CREATE OR REPLACE FUNCTION write_surname_function_on_insert()
  RETURNS trigger AS
'
BEGIN
   IF NEW.surname IS NULL THEN
       UPDATE person SET surname = NEW.last_name
            WHERE id = NEW.id;
   END IF;

   RETURN NEW;
END;
'
LANGUAGE PLPGSQL;

CREATE OR REPLACE FUNCTION write_surname_function_on_update()
  RETURNS trigger AS
'
BEGIN
   IF OLD.surname != NEW.last_name THEN
       UPDATE person SET surname = NEW.last_name
            WHERE id = NEW.id;
   END IF;

   RETURN NEW;
END;
'
LANGUAGE PLPGSQL;