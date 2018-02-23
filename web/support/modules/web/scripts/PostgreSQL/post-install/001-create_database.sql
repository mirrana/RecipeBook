-- PostgreSQL post-install script

-- Reset Data
-- DROP TABLE units_of_measurement;
-- DROP TABLE unit_types;
-- DROP TABLE foods_nutrients;
-- DROP TABLE nutrients;
-- DROP TABLE foods_brands;
-- DROP TABLE brand_names;
-- DROP TABLE foods;

-- SELECT 'drop sequence ' || c.relname || ';' FROM pg_class c WHERE (c.relkind = 'S');

-- drop sequence foods_id_seq;
-- drop sequence brand_names_id_seq;
-- drop sequence foods_brands_id_seq;
-- drop sequence unit_types_id_seq;
-- drop sequence units_of_measurement_id_seq;
-- drop sequence nutrients_id_seq;


-- Create Data Model
CREATE TABLE units_of_measurement
(
  id           INTEGER PRIMARY KEY NOT NULL,
  name         VARCHAR(50)   NOT NULL,
  abbreviation VARCHAR(10),
  type         VARCHAR(1)    NOT NULL,
  class        VARCHAR(1)    NOT NULL,
  as_metric    NUMERIC(9, 3) NOT NULL,
  CONSTRAINT units_of_measurement_abbreviation_uindex UNIQUE (abbreviation),
  CONSTRAINT units_of_measurement_name_uindex UNIQUE (name),
  CONSTRAINT units_of_measurement_check CHECK (type in ('V', 'v', 'W', 'w') AND class in ('M', 'm', 'I', 'i'))
);

CREATE SEQUENCE units_of_measurement_id_seq START WITH 1;






CREATE TABLE foods
(
  id   INTEGER PRIMARY KEY NOT NULL,
  name VARCHAR(100)        NOT NULL,
  CONSTRAINT foods_name_uindex UNIQUE (name)
);

CREATE SEQUENCE foods_id_seq START WITH 1;

INSERT INTO foods (ID, NAME) VALUES (nextval('foods_id_seq'), 'Almonds (Roasted)');
INSERT INTO foods (ID, NAME) VALUES (nextval('foods_id_seq'), 'Peanut Butter (Natural)');






CREATE TABLE brand_names
(
  id   INTEGER PRIMARY KEY NOT NULL,
  name VARCHAR(100)        NOT NULL,
  CONSTRAINT brand_names_name_uindex UNIQUE (name)
);

CREATE SEQUENCE brand_names_id_seq START WITH 1;

INSERT INTO BRAND_NAMES (ID, NAME) VALUES (nextval('brand_names_id_seq'), 'Kirkland Signature');
INSERT INTO BRAND_NAMES (ID, NAME) VALUES (nextval('brand_names_id_seq'), 'Kraft');






CREATE TABLE foods_brands
(
  id                     INTEGER PRIMARY KEY NOT NULL,
  food_id                INTEGER             NOT NULL,
  brand_id               INTEGER             NOT NULL,
  serving_weight_unit_id INTEGER,
  serving_weight_scalar  NUMERIC(9, 3),
  serving_volume_unit_id INTEGER,
  serving_volume_scalar  NUMERIC(9, 3),
  upc                    NUMERIC(12, 0),
  CONSTRAINT foods_brands_food_id_foods_fk FOREIGN KEY (food_id) REFERENCES foods (id),
  CONSTRAINT foods_brands_brand_id_brands_fk FOREIGN KEY (brand_id) REFERENCES brand_names (id),
  CONSTRAINT foods_brands_serving_volume_unit_id_uom_fk FOREIGN KEY (serving_volume_unit_id) REFERENCES units_of_measurement (id),
  CONSTRAINT foods_brands_serving_weight_unit_id_uom_fk FOREIGN KEY (serving_weight_unit_id) REFERENCES units_of_measurement (id),
  CONSTRAINT foods_brands_uindex UNIQUE (food_id, brand_id),
  CONSTRAINT foods_brands_upc_uindex UNIQUE (upc)
);

CREATE SEQUENCE foods_brands_id_seq START WITH 1;

-- Ensure that the unit IDs are valid for their expected types.
CREATE OR REPLACE FUNCTION verify_unit_types() RETURNS TRIGGER AS $$
DECLARE
  weight_type_id INTEGER;
  volume_type_id INTEGER;
BEGIN
  weight_type_id = (SELECT id FROM unit_types WHERE lower(name) = 'weight');
  volume_type_id = (SELECT id FROM unit_types WHERE lower(name) = 'volume');
  
  IF (weight_type_id <> (SELECT type_id FROM units_of_measurement WHERE id = NEW.serving_weight_unit_id)) THEN
    RAISE EXCEPTION 'Invalid weight unit (%)', (SELECT name FROM units_of_measurement WHERE id = NEW.serving_weight_unit_id);
  END IF;

  IF (volume_type_id <> (SELECT type_id FROM units_of_measurement WHERE id = NEW.serving_volume_unit_id)) THEN
    RAISE EXCEPTION 'Invalid volume unit (%)', (SELECT name FROM units_of_measurement WHERE id = NEW.serving_volume_unit_id);
  END IF;  
  
  RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER tr_foods_brands_verify_unit_types
BEFORE INSERT OR UPDATE ON foods_brands
FOR EACH ROW
EXECUTE PROCEDURE verify_unit_types();






CREATE TABLE nutrients
(
  id     INTEGER PRIMARY KEY NOT NULL,
  name   VARCHAR(50) NOT NULL,
  unit   INTEGER     NOT NULL,
  CONSTRAINT nutrients_name_uindex UNIQUE (name)
);

CREATE SEQUENCE nutrients_id_seq START WITH 1;






CREATE TABLE foods_nutrients
(
  foods_brands_id INTEGER       NOT NULL,
  nutrient_id     INTEGER       NOT NULL,
  scalar          NUMERIC(9, 3) NOT NULL,
  PRIMARY KEY (foods_brands_id, nutrient_id),
  CONSTRAINT foods_nutrients_foods_brands_id_foods_brands_fk FOREIGN KEY (foods_brands_id) REFERENCES foods_brands (id),
  CONSTRAINT foods_nutrients_nutrient_id_nutrients_fk FOREIGN KEY (nutrient_id) REFERENCES nutrients (id)
);







SELECT nextval('nutrients_id_seq');















CREATE TABLE VERSION
(
  VERSION_NUMBER INTEGER      NOT NULL,
  INSTALL_DATE   TIMESTAMP(6) NOT NULL
);

INSERT INTO VERSION (VERSION_NUMBER, INSTALL_DATE) VALUES (1, current_timestamp);



-- Recipes
CREATE TABLE RECIPES
(
  ID          INTEGER PRIMARY KEY NOT NULL,
  NAME        VARCHAR(100)        NOT NULL,
  DESCRIPTION VARCHAR(100)        NOT NULL,
  SERVINGS    INTEGER             NOT NULL,
  PREP_TIME   INTEGER, -- Minutes
  COOK_TIME   INTEGER -- Minutes
);






-- INGREDIENT_ID can reference one of many tables, depending on the unit for INGREDIENT_TYPE.
-- For example, an ingredient can be one of the following:
--   1. An atomic food item, such as a carrot or potato or milk,
--   2. A prepared food item such as a can of cream of mushroom soup,
--   3. Another recipe.
CREATE TABLE RECIPE_INGREDIENTS
(
  RECIPE_ID       INTEGER NOT NULL,
  INGREDIENT_ID   INTEGER NOT NULL,
  INGREDIENT_TYPE VARCHAR(1) NOT NULL,
  CONSTRAINT RECIPE_INGREDIENTS_PK PRIMARY KEY (RECIPE_ID, INGREDIENT_TYPE, INGREDIENT_ID),
  CONSTRAINT RECIPE_INGREDIENTS_RECIPE_FK FOREIGN KEY (RECIPE_ID) REFERENCES RECIPES (ID)
);






CREATE TABLE USERS
(
  USER_ID INTEGER NOT NULL,
  USER_NAME VARCHAR(50) NOT NULL,
  CONSTRAINT USERS_PK PRIMARY KEY (USER_ID)
);

INSERT INTO USERS (USER_ID, USER_NAME) VALUES (1, 'sarah');






CREATE TABLE USER_MEALS
(
  MEAL_ID     INTEGER     NOT NULL,
  USER_ID     INTEGER     NOT NULL,
  MEAL_NUMBER INTEGER     NOT NULL,
  MEAL_NAME   VARCHAR(50) NOT NULL,
  CONSTRAINT USER_MEALS_PK PRIMARY KEY (MEAL_ID),
  CONSTRAINT USER_MEALS_USERS_FK FOREIGN KEY (USER_ID) REFERENCES USERS (USER_ID),
  CONSTRAINT USER_MEALS_MEAL_NUMBER_UK UNIQUE (USER_ID, MEAL_NUMBER),
  CONSTRAINT USER_MEALS_MEAL_NAME_UK UNIQUE (USER_ID, MEAL_NAME)
);






CREATE TABLE DIARY
(
  DIARY_ID INTEGER NOT NULL,
  DATE DATE NOT NULL,
  MEAL_ID INTEGER NOT NULL,
  ITEM_ID INTEGER NOT NULL,
  ITEM_TYPE VARCHAR(1) NOT NULL,
  SERVINGS NUMERIC(9, 3),
  CONSTRAINT DIARY_PK PRIMARY KEY (DIARY_ID),
  CONSTRAINT DIARY_USER_MEALS_FK FOREIGN KEY (MEAL_ID) REFERENCES USER_MEALS (MEAL_ID)
);

  
  
  
  
  
  
  
  
--------------------------------------------------
-- CNF Data
--------------------------------------------------

CREATE TABLE CNF_FOOD_GROUP
(
  FOOD_GROUP_ID INT PRIMARY KEY,
  FOOD_GROUP_CODE INT,
  FOOD_GROUP_NAME VARCHAR(200),
  FOOD_GROUP_NAME_F VARCHAR(200)
);

CREATE TABLE CNF_FOOD_SOURCE
(
  FOOD_SOURCE_ID INT PRIMARY KEY,
  FOOD_SOURCE_CODE INT,
  FOOD_SOURCE_DESCRIPTION VARCHAR(200),
  FOOD_SOURCE_DESCRIPTION_F VARCHAR(200)
);

CREATE TABLE CNF_FOOD_NAME
(
  FOOD_ID                  INT PRIMARY KEY,
  FOOD_CODE                INT,
  FOOD_GROUP_ID            INT,
  FOOD_SOURCE_ID           INT,
  FOOD_DESCRIPTION         VARCHAR(255),
  FOOD_DESCRIPTION_F       VARCHAR(255),
  COUNTRY_CODE             INT,
  FOOD_DATE_OF_ENTRY       DATE,
  FOOD_DATE_OF_PUBLICATION DATE,
  SCIENTIFIC_NAME          VARCHAR(100),
  CONSTRAINT FK_FOOD_NAME_FOOD_GROUP FOREIGN KEY (FOOD_GROUP_ID) REFERENCES CNF_FOOD_GROUP (FOOD_GROUP_ID),
  CONSTRAINT FK_FOOD_NAME_FOOD_SOURCE FOREIGN KEY (FOOD_SOURCE_ID) REFERENCES CNF_FOOD_SOURCE (FOOD_SOURCE_ID)
);

CREATE TABLE CNF_NUTRIENT_NAME
(
  NUTRIENT_NAME_ID INT PRIMARY KEY,
  NUTRIENT_CODE INT,
  NUTRIENT_SYMBOL VARCHAR(10),
  UNIT VARCHAR(8),
  NUTRIENT_NAME VARCHAR(200),
  NUTRIENT_NAME_F VARCHAR(200),
  TAGNAME VARCHAR(20),
  NUTRIENT_DECIMALS INT
);

CREATE TABLE CNF_NUTRIENT_SOURCE
(
  NUTRIENT_SOURCE_ID INT PRIMARY KEY,
  NUTRIENT_SOURCE_CODE INT,
  NUTRIENT_SOURCE_DESCRIPTION VARCHAR(200),
  NUTRIENT_SOURCE_DESCRIPTION_F VARCHAR(200)
);

CREATE TABLE CNF_NUTRIENT_AMOUNT
(
  FOOD_ID INT PRIMARY KEY,
  NUTRIENT_NAME_ID INT,
  NUTRIENT_VALUE NUMERIC(12,5),
  STANDARD_ERROR NUMERIC(8,4),
  NUMBER_OF_OBSERVATIONS INT,
  NUTRIENT_SOURCE_ID INT,
  NUTRIENT_DATE_ENTRY DATE,
  CONSTRAINT FK_NUTRIENT_AMOUNT_NUTRIENT_NAME FOREIGN KEY (NUTRIENT_NAME_ID) REFERENCES CNF_NUTRIENT_NAME (NUTRIENT_NAME_ID),
  CONSTRAINT FK_NUTRIENT_AMOUNT_NUTRIENT_SOURCE FOREIGN KEY (NUTRIENT_SOURCE_ID) REFERENCES CNF_NUTRIENT_SOURCE (NUTRIENT_SOURCE_ID)
);

CREATE TABLE CNF_MEASURE_NAME
(
  MEASURE_ID INT PRIMARY KEY,
  MEASURE_NAME VARCHAR(200),
  MEASURE_NAME_F VARCHAR(200)
);

CREATE TABLE CNF_CONVERSION_FACTOR
(
  FOOD_ID INT,
  MEASURE_ID INT,
  CONVERSION_FACTOR_VALUE INT,
  CONVERSION_FACTOR_DATE_OF_ENTRY DATE,
  CONSTRAINT FK_CONVERSION_FACTOR_FOOD_NAME FOREIGN KEY (FOOD_ID) REFERENCES CNF_FOOD_NAME (FOOD_ID),
  CONSTRAINT FK_CONVERSION_FACTOR_MEASURE_NAME FOREIGN KEY (MEASURE_ID) REFERENCES CNF_MEASURE_NAME (MEASURE_ID)
);

CREATE TABLE CNF_REFUSE_NAME
(
  REFUSE_ID INT PRIMARY KEY,
  REFUSE_NAME VARCHAR(200),
  REFUSE_NAME_F VARCHAR(200)
);
  
CREATE TABLE CNF_REFUSE_AMOUNT
(
  FOOD_ID INT,
  REFUSE_ID INT,
  REFUSE_AMOUNT NUMERIC(9,5),
  REFUSE_DATE_OF_ENTRY DATE,
  CONSTRAINT FK_REFUSE_AMOUNT_FOOD_NAME FOREIGN KEY (FOOD_ID) REFERENCES CNF_FOOD_NAME (FOOD_ID),
  CONSTRAINT FK_REFUSE_AMOUNT_REFUSE_NAME FOREIGN KEY (REFUSE_ID) REFERENCES CNF_REFUSE_NAME (REFUSE_ID)
);

CREATE TABLE CNF_YIELD_NAME
(
  YIELD_ID INT PRIMARY KEY,
  YIELD_NAME VARCHAR(200),
  YIELD_NAME_F VARCHAR(200)
);

CREATE TABLE CNF_YIELD_AMOUNT
(
  FOOD_ID INT,
  YIELD_ID INT,
  YIELD_AMOUNT NUMERIC(9,5),
  YIELD_DATE_OF_ENTRY DATE,
  CONSTRAINT FK_YIELD_AMOUNT_FOOD_NAME FOREIGN KEY (FOOD_ID) REFERENCES CNF_FOOD_NAME (FOOD_ID),
  CONSTRAINT FK_YIELD_AMOUNT_YIELD_NAME FOREIGN KEY (YIELD_ID) REFERENCES CNF_YIELD_NAME (YIELD_ID)
);