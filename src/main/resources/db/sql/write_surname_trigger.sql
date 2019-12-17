CREATE TRIGGER write_surname_trigger_on_insert
  AFTER INSERT
  ON person
  FOR EACH ROW
  EXECUTE PROCEDURE write_surname_function_on_insert();

CREATE TRIGGER write_surname_trigger_on_update
  AFTER UPDATE
  ON person
  FOR EACH ROW
  EXECUTE PROCEDURE write_surname_function_on_update();