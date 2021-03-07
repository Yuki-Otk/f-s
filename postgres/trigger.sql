DROP TABLE operand;
DROP TABLE answer;

CREATE TABLE operand(
                        id SERIAL,
                        operand1 INTEGER,
                        operand2 INTEGER
);

CREATE TABLE answer(
                       operand_id INTEGER,
                       quotient INTEGER,
                       remainder INTEGER
);

CREATE OR REPLACE FUNCTION div_proc() RETURNS trigger AS $div_proc$
BEGIN
        IF new.operand1 = 10 THEN
            RAISE 'not support operand1 is %',new.operand1;
end if;
INSERT INTO  answer VALUES(new.id,div(new.operand1,new.operand2), mod(NEW.operand1, NEW.operand2));
RETURN new;
end;
    $div_proc$
LANGUAGE plpgsql;

CREATE TRIGGER div_trg AFTER INSERT ON operand FOR  EACH ROW EXECUTE FUNCTION div_proc();

INSERT INTO operand(operand1, operand2)  VALUES (6,2);
INSERT INTO operand(operand1, operand2)  VALUES (5,2);
-- operand1が10の場合は例外発生
INSERT INTO operand(operand1, operand2)  VALUES (10,2);