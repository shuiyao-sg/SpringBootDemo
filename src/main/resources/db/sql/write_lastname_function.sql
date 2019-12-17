CREATE OR REPLACE FUNCTION write_lastname_function()
  RETURNS trigger AS

'
BEGIN
   IF NEW.last_name IS NULL THEN
       UPDATE person SET last_name = NEW.surname;
            WHERE id = NEW.id;
   END IF;

   RETURN NEW;
END;
'

LANGUAGE PLPGSQL;