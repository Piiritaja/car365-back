INSERT INTO owner (id, first_name, last_name) VALUES (random_uuid(), 'toomas', 'lastname');

INSERT INTO listing (id, body_type, brand, color, description, drive_type, engine_power, engine_size, fuel_type,
                     gearbox_type, location, mileage, model, owner, price, release_year, status, time, title)
                     VALUES (random_uuid(), 'Sedan', 'brand', 'color', 'description', 'drivetype', 100, '2.0',
                             'fueltype', 'gearbox', 'location', 100, 'model',
                             select id from owner where first_name='toomas', 3056, 2001, 'status',1600034055557,
                             'testlisting');

