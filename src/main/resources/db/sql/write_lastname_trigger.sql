CREATE TRIGGER write_lastname_trigger_on_insert
  BEFORE INSERT
  ON person
  FOR EACH ROW
  EXECUTE PROCEDURE write_lastname_function();

CREATE TRIGGER write_lastname_trigger_on_update
  BEFORE UPDATE
  ON person
  FOR EACH ROW
  EXECUTE PROCEDURE write_lastname_function();