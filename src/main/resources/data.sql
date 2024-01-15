-- Creo los entrenadores
INSERT INTO entrenador (nombre, apellido) VALUES
('Angelo', 'Manfredi'),
('Amilcar', 'Alberino'),
('Bautista', 'Sande Clop'),
('Maylin', 'Gutierrez');

-- Creo las categorias
INSERT INTO categoria (nombre, minimo_kg, maximo_kg, entrenador_id) VALUES
('Mosca', 48.988, 50.802, 1),
('Gallo', 52.163, 53.525, 1),
('Pluma', 55.338, 57.152, 2),
('Ligero', 58.967, 61.237, 2),
('Welter', 63.503, 66.678, 3),
('Mediano', 69.853, 72.562, 3),
('Mediopesado', 76.205, 79.378, 4),
('Pesado', 91, null, 4) ;





