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
CREATE TABLE unit_types
(
  id   INTEGER PRIMARY KEY NOT NULL,
  name VARCHAR(15) NOT NULL,
  CONSTRAINT unit_types_name_uindex UNIQUE (name)
);

CREATE SEQUENCE unit_types_id_seq START WITH 1;






CREATE TABLE units_of_measurement
(
  id           INTEGER PRIMARY KEY NOT NULL,
  name         VARCHAR(50)   NOT NULL,
  abbreviation VARCHAR(10),
  type_id      INTEGER       NOT NULL,
  as_metric    NUMERIC(9, 3) NOT NULL,
  CONSTRAINT units_of_measurement_abbreviation_uindex UNIQUE (abbreviation),
  CONSTRAINT units_of_measurement_name_uindex UNIQUE (name),
  CONSTRAINT units_of_measurement_type_id_unit_types FOREIGN KEY (type_id) REFERENCES unit_types (id)
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

CREATE TABLE INGREDIENT_TYPES
(
  ID INTEGER PRIMARY KEY NOT NULL,
  NAME VARCHAR(50) NOT NULL
);

-- INGREDIENT_ID can reference one of many tables, depending on the value for INGREDIENT_TYPE.
-- For example, an ingredient can be one of the following:
--   1. An atomic food item, such as a carrot or potato or milk,
--   2. A prepared food item such as a can of cream of mushroom soup,
--   3. Another recipe.
CREATE TABLE RECIPE_INGREDIENTS
(
  RECIPE_ID       INTEGER NOT NULL,
  INGREDIENT_TYPE INTEGER NOT NULL,
  INGREDIENT_ID   INTEGER NOT NULL,
  CONSTRAINT RECIPE_INGREDIENTS_PK PRIMARY KEY (RECIPE_ID, INGREDIENT_TYPE, INGREDIENT_ID),
  CONSTRAINT RECIPE_INGREDIENTS_RECIPE_FK FOREIGN KEY (RECIPE_ID) REFERENCES RECIPES (ID),
  CONSTRAINT RECIPE_INGREDIENTS_INGREDIENT_TYPE_FK FOREIGN KEY (INGREDIENT_TYPE) REFERENCES INGREDIENT_TYPES (ID)
)
  