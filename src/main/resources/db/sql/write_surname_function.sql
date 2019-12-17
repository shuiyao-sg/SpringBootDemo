CREATE OR REPLACE FUNCTION write_surname_function()
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