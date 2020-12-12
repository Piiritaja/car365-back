INSERT INTO owner (id, first_name, last_name, email, phone, password, role) VALUES
(random_uuid(), 'toomas', 'lastname', 'email@email1.com', '55667788', '$2a$10$ERURRH9CxE3suR3KMskFc.peSMLNcLdF//lHE5fW.asyC57rC9Lo2', 'PREMIUM'),
('e11b7248-7fda-4534-b291-c7ceabcb510d'::uuid, 'test', 'usr', 'test@usr.com', '55667788', '$2a$10$ERURRH9CxE3suR3KMskFc.peSMLNcLdF//lHE5fW.asyC57rC9Lo2', 'USER');

INSERT INTO listing (id, body_type, brand, color, description, drive_type, engine_power, engine_size, fuel_type,
                     gearbox_type, location, mileage, model, owner, price, release_year, status, premium, time, title)
                     VALUES (random_uuid(), 'Sedan', 'brand', 'color', 'description', 'drivetype', 100, '2.0',
                             'fueltype', 'gearbox', 'location', 100, 'model',
                             select id from owner where first_name='toomas', 3056, 2001, 'status', false, 1600034055557,
                             'testlisting');

