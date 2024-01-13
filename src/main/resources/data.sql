-- Creo las categorias
INSERT INTO categoria (nombre, minimo_kg, maximo_kg) VALUES
('Mosca', 48.988, 50.802),
('Gallo', 52.163, 53.525),
('Pluma', 55.338, 57.152),
('Ligero', 58.967, 61.237),
('Welter', 63.503, 66.678),
('Mediano', 69.853, 72.562),
('Mediopesado', 76.205, 79.378),
('Pesado', 91, null);

-- Creo los entrenadores
INSERT INTO entrenador (nombre, apellido) VALUES
('Angelo', 'Manfredi'),
('Amilcar', 'Alberino'),
('Bautista', 'Sande Clop'),
('Maylin', 'Gutierrez');

-- Asigno categorias a cada entrenador
INSERT INTO entrenador_categoria VALUES
(1, 1), (1, 2),
(2, 3), (2, 4),
(3, 5), (3, 6),
(4, 7), (4, 8);




