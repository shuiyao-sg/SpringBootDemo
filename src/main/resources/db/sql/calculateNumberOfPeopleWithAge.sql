--create or replace function calculateNumberOfPeopleWithAge (age int) returns int as
--$$
--begin
--    return select count(*) from person where person.age = age;
--end;
--$$
--language plpgsql;

drop function calculateNumberOfPeopleWithAge;

create or replace function calculateNumberOfPeopleWithAge (inputAge int) returns int as

'
begin
    return count(*) from person where person.age = inputAge;
end;
'

language plpgsql;

--create or replace function calculateNumberOfPeopleWithAge (age int) returns query as
--
--'
--begin
--    return select count(*) from person where person.age = age;
--end;
--'
--
--language plpgsql;