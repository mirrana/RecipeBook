DO $$
DECLARE
  weight_type         BIGINT;
  volume_type         BIGINT;
  qty_type            BIGINT;
  gram                BIGINT;
  milligram           BIGINT;
  kilogram            BIGINT;
  ounce               BIGINT;
  pound               BIGINT;
  litre               BIGINT;
  millilitre          BIGINT;
  gallon              BIGINT;
  quart               BIGINT;
  pint                BIGINT;
  cup                 BIGINT;
  fl_ounce            BIGINT;
  tbsp                BIGINT;
  tsp                 BIGINT;
  qty                 BIGINT;
  pkg                 BIGINT;
  doz                 BIGINT;
  pct                 BIGINT;
  food                BIGINT;
  calories            BIGINT;
  fat                 BIGINT;
  saturated_fat       BIGINT;
  polyunsaturated_fat BIGINT;
  monounsaturated_fat BIGINT;
  trans_fat           BIGINT;
  cholesterol         BIGINT;
  sodium              BIGINT;
  potassium           BIGINT;
  carbs               BIGINT;
  fiber               BIGINT;
  sugar               BIGINT;
  protein             BIGINT;
  vitamin_a           BIGINT;
  vitamin_c           BIGINT;
  calcium             BIGINT;
  iron                BIGINT;

BEGIN
  --   @formatter:off
  -- Unit Types
  INSERT INTO unit_types (name) VALUES ('Weight') RETURNING id INTO weight_type;
  INSERT INTO unit_types (name) VALUES ('Volume') RETURNING id INTO volume_type;
  INSERT INTO unit_types (name) VALUES ('Quantity') RETURNING id INTO qty_type;

  -- Units of Measurement
  INSERT INTO units_of_measurement (name, abbreviation, type, as_metric) VALUES ('Gram', 'g', weight_type, 1) RETURNING id INTO gram;
  INSERT INTO units_of_measurement (name, abbreviation, type, as_metric) VALUES ('Milligram', 'mg', weight_type, .001) RETURNING id INTO milligram;
  INSERT INTO units_of_measurement (name, abbreviation, type, as_metric) VALUES ('Kilogram', 'kg', weight_type, 1000) RETURNING id INTO kilogram;
  INSERT INTO units_of_measurement (name, abbreviation, type, as_metric) VALUES ('Ounce', 'oz', weight_type, 28.3495) RETURNING id INTO ounce;
  INSERT INTO units_of_measurement (name, abbreviation, type, as_metric) VALUES ('Pound', 'lb', weight_type, 453.592) RETURNING id INTO pound;
  INSERT INTO units_of_measurement (name, abbreviation, type, as_metric) VALUES ('Litre', 'l', volume_type, 1) RETURNING id INTO litre;
  INSERT INTO units_of_measurement (name, abbreviation, type, as_metric) VALUES ('Millilitre', 'ml', volume_type, .001) RETURNING id INTO millilitre;
  INSERT INTO units_of_measurement (name, abbreviation, type, as_metric) VALUES ('Gallon', 'gal', volume_type, 4.54609) RETURNING id INTO gallon;
  INSERT INTO units_of_measurement (name, abbreviation, type, as_metric) VALUES ('Quart', 'qt', volume_type, 1.13652) RETURNING id INTO quart;
  INSERT INTO units_of_measurement (name, abbreviation, type, as_metric) VALUES ('Pint', 'pt', volume_type, 0.568261) RETURNING id INTO pint;
  INSERT INTO units_of_measurement (name, abbreviation, type, as_metric) VALUES ('Cup', 'c', volume_type, 0.284131) RETURNING id INTO cup;
  INSERT INTO units_of_measurement (name, abbreviation, type, as_metric) VALUES ('Fluid Ounce', 'fl. oz', volume_type, 0.0284131) RETURNING id INTO fl_ounce;
  INSERT INTO units_of_measurement (name, abbreviation, type, as_metric) VALUES ('Tablespoon', 'tbsp', volume_type, 0.01775821) RETURNING id INTO tbsp;
  INSERT INTO units_of_measurement (name, abbreviation, type, as_metric) VALUES ('Teaspoon', 'tsp', volume_type, 0.00591939) RETURNING id INTO tsp;
  INSERT INTO units_of_measurement (name, abbreviation, type, as_metric) VALUES ('Quantity', '', qty_type, 1) RETURNING id INTO qty;
  INSERT INTO units_of_measurement (name, abbreviation, type, as_metric) VALUES ('Package', 'pkg', qty_type, 1) RETURNING id INTO pkg;
  INSERT INTO units_of_measurement (name, abbreviation, type, as_metric) VALUES ('Dozen', 'doz', qty_type, 12) RETURNING id INTO doz;
  INSERT INTO units_of_measurement (name, abbreviation, type, as_metric) VALUES ('Percent', '%', qty_type, 1) RETURNING id INTO pct;

  -- Nutrients
  INSERT INTO nutrients (name, unit) VALUES ('Calories', qty) RETURNING id INTO calories;
  INSERT INTO nutrients (name, unit) VALUES ('Total Fat', gram) RETURNING id INTO fat;
  INSERT INTO nutrients (name, unit, parent) VALUES ('Saturated Fat', gram, fat) RETURNING id INTO saturated_fat;
  INSERT INTO nutrients (name, unit, parent) VALUES ('Monounsaturated Fat', gram, fat) RETURNING id INTO polyunsaturated_fat;
  INSERT INTO nutrients (name, unit, parent) VALUES ('Polyunsaturated Fat', gram, fat) RETURNING id INTO monounsaturated_fat;
  INSERT INTO nutrients (name, unit, parent) VALUES ('Trans Fat', gram, fat) RETURNING id INTO trans_fat;
  INSERT INTO nutrients (name, unit) VALUES ('Cholesterol', milligram) RETURNING id INTO cholesterol;
  INSERT INTO nutrients (name, unit) VALUES ('Sodium', milligram) RETURNING id INTO sodium;
  INSERT INTO nutrients (name, unit) VALUES ('Potassium', milligram) RETURNING id INTO potassium;
  INSERT INTO nutrients (name, unit) VALUES ('Total Carbohydrates', gram) RETURNING id INTO carbs;
  INSERT INTO nutrients (name, unit, parent) VALUES ('Dietary Fiber', gram, carbs) RETURNING id INTO fiber;
  INSERT INTO nutrients (name, unit, parent) VALUES ('Sugar', gram, carbs) RETURNING id INTO sugar;
  INSERT INTO nutrients (name, unit) VALUES ('Protein', gram) RETURNING id INTO protein;
  INSERT INTO nutrients (name, unit) VALUES ('Vitamin A', pct) RETURNING id INTO vitamin_a;
  INSERT INTO nutrients (name, unit) VALUES ('Vitamin C', pct) RETURNING id INTO vitamin_c;
  INSERT INTO nutrients (name, unit) VALUES ('Calcium', pct) RETURNING id INTO calcium;
  INSERT INTO nutrients (name, unit) VALUES ('Iron', pct) RETURNING id INTO iron;

  -- Foods
  --------

  -- Banana
  INSERT INTO foods (name, default_unit) VALUES ('Banana', gram) RETURNING id INTO food;
  INSERT INTO foods_nutrients (food_id, nutrient_id, scalar) VALUES (food,calories,0.3);
  INSERT INTO foods_nutrients (food_id, nutrient_id, scalar) VALUES (food,fat,0.3);
  INSERT INTO foods_nutrients (food_id, nutrient_id, scalar) VALUES (food,saturated_fat,0.1);
  INSERT INTO foods_nutrients (food_id, nutrient_id, scalar) VALUES (food,polyunsaturated_fat,0.1);
  INSERT INTO foods_nutrients (food_id, nutrient_id, scalar) VALUES (food,sodium,1);
  INSERT INTO foods_nutrients (food_id, nutrient_id, scalar) VALUES (food,potassium,358);
  INSERT INTO foods_nutrients (food_id, nutrient_id, scalar) VALUES (food,carbs,23);
  INSERT INTO foods_nutrients (food_id, nutrient_id, scalar) VALUES (food,fiber,2.6);
  INSERT INTO foods_nutrients (food_id, nutrient_id, scalar) VALUES (food,sugar,12);
  INSERT INTO foods_nutrients (food_id, nutrient_id, scalar) VALUES (food,protein,1.1);
  INSERT INTO foods_nutrients (food_id, nutrient_id, scalar) VALUES (food,vitamin_a,1);
  INSERT INTO foods_nutrients (food_id, nutrient_id, scalar) VALUES (food,vitamin_c,14);
  INSERT INTO foods_nutrients (food_id, nutrient_id, scalar) VALUES (food,iron,1);

  --   @formatter:on
END $$;
