CREATE TABLE version
(
  id             SERIAL PRIMARY KEY,
  version_number INTEGER   NOT NULL,
  install_date   TIMESTAMP NOT NULL
);
CREATE UNIQUE INDEX version_version_number_uindex
  ON version (version_number);

INSERT INTO version (version_number, install_date) VALUES (1, NOW());

CREATE TABLE foods
(
  id           SERIAL PRIMARY KEY NOT NULL,
  name         VARCHAR(100)       NOT NULL,
  default_unit INT                NOT NULL
);
CREATE UNIQUE INDEX foods_name_uindex
  ON foods (name);

CREATE TABLE nutrients
(
  id     SERIAL PRIMARY KEY,
  name   VARCHAR(50) NOT NULL,
  unit   INT         NOT NULL,
  parent INT
);
CREATE UNIQUE INDEX nutrients_name_uindex
  ON nutrients (name);

CREATE TABLE unit_types
(
  id   SERIAL PRIMARY KEY,
  name VARCHAR(15) NOT NULL
);
CREATE UNIQUE INDEX unit_types_name_uindex
  ON unit_types (name);

CREATE TABLE units_of_measurement
(
  id           SERIAL PRIMARY KEY,
  name         VARCHAR(50)   NOT NULL,
  abbreviation VARCHAR(10),
  type         INT           NOT NULL,
  as_metric    NUMERIC(9, 3) NOT NULL
);
CREATE UNIQUE INDEX units_of_measurement_abbreviation_uindex
  ON units_of_measurement (abbreviation);
CREATE UNIQUE INDEX units_of_measurement_name_uindex
  ON units_of_measurement (name);

CREATE TABLE foods_nutrients
(
  food_id     INTEGER       NOT NULL,
  nutrient_id INTEGER       NOT NULL,
  value       NUMERIC(9, 3) NOT NULL,
  PRIMARY KEY (food_id, nutrient_id)
);