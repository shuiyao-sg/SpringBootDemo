CREATE TRIGGER write_surname_trigger
  AFTER INSERT
  ON person
  FOR EACH ROW
  EXECUTE PROCEDURE write_surname_function();