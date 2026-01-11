INSERT INTO products (id, name, category, price, updated_at) VALUES
('11111111-1111-1111-1111-111111111111', 'Kettle Chips', 'snacks', 3.99, now()),
('22222222-2222-2222-2222-222222222222', 'Chocolate Bar', 'snacks', 1.49, now()),
('33333333-3333-3333-3333-333333333333', 'Almond Milk', 'drinks', 4.29, now()),
('44444444-4444-4444-4444-444444444444', 'Orange Juice', 'drinks', 3.79, now()),
('55555555-5555-5555-5555-555555555555', 'Dish Soap', 'household', 2.99, now()),
('66666666-6666-6666-6666-666666666666', 'Paper Towels', 'household', 6.49, now())
ON CONFLICT (id) DO NOTHING;

-- extra volume for paging tests
INSERT INTO products (id, name, category, price, updated_at)
SELECT
    gen_random_uuid(),
    'Snack Item ' || g,
    'snacks',
    round((random() * 5 + 1)::numeric, 2),
    now()
FROM generate_series(1, 50) g
WHERE NOT EXISTS (SELECT 1 FROM products);
