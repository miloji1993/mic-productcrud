INSERT INTO users (username, password) VALUES ('mercadona', '$2a$10$EDpI5mW/brIf4aw5SsQYnOBsCgc2pD8SjM/.rePyG9HYVHcywYDdC');

INSERT INTO suppliers(id, name) VALUES (8437008, 'Hacendado');
INSERT INTO suppliers(id, name) VALUES (8437000, 'Colgate');

INSERT INTO products(id, ean, name, destination, supplier_id) VALUES (45905, '8437008459059', 'JABON DE MANOS', 'MERCADONA_OFFICE', 8437008);