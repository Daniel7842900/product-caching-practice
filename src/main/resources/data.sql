INSERT INTO products (id, name, category, price, updated_at) VALUES
-- snacks (12)
('11111111-1111-1111-1111-111111111111', 'Kettle Chips', 'snacks', 3.99, now()),
('22222222-2222-2222-2222-222222222222', 'Chocolate Bar', 'snacks', 1.49, now()),
('77777777-7777-7777-7777-777777777701', 'Pretzels', 'snacks', 2.49, now()),
('77777777-7777-7777-7777-777777777702', 'Trail Mix', 'snacks', 4.99, now()),
('77777777-7777-7777-7777-777777777703', 'Granola Bar', 'snacks', 1.29, now()),
('77777777-7777-7777-7777-777777777704', 'Popcorn', 'snacks', 2.99, now()),
('77777777-7777-7777-7777-777777777705', 'Protein Bar', 'snacks', 2.79, now()),
('77777777-7777-7777-7777-777777777706', 'Rice Crackers', 'snacks', 3.49, now()),
('77777777-7777-7777-7777-777777777707', 'Cheese Puffs', 'snacks', 2.59, now()),
('77777777-7777-7777-7777-777777777708', 'Tortilla Chips', 'snacks', 3.19, now()),
('77777777-7777-7777-7777-777777777709', 'Snack Mix', 'snacks', 4.29, now()),
('77777777-7777-7777-7777-777777777710', 'Veggie Chips', 'snacks', 3.89, now()),

-- drinks (7)
('33333333-3333-3333-3333-333333333333', 'Almond Milk', 'drinks', 4.29, now()),
('44444444-4444-4444-4444-444444444444', 'Orange Juice', 'drinks', 3.79, now()),
('88888888-8888-8888-8888-888888888801', 'Sparkling Water', 'drinks', 1.19, now()),
('88888888-8888-8888-8888-888888888802', 'Green Tea', 'drinks', 2.49, now()),
('88888888-8888-8888-8888-888888888803', 'Cold Brew Coffee', 'drinks', 3.99, now()),
('88888888-8888-8888-8888-888888888804', 'Sports Drink', 'drinks', 2.29, now()),
('88888888-8888-8888-8888-888888888805', 'Iced Tea', 'drinks', 2.19, now()),

-- household (6)
('55555555-5555-5555-5555-555555555555', 'Dish Soap', 'household', 2.99, now()),
('66666666-6666-6666-6666-666666666666', 'Paper Towels', 'household', 6.49, now()),
('99999999-9999-9999-9999-999999999901', 'Laundry Detergent', 'household', 9.99, now()),
('99999999-9999-9999-9999-999999999902', 'Trash Bags', 'household', 5.49, now()),
('99999999-9999-9999-9999-999999999903', 'All-Purpose Cleaner', 'household', 3.79, now()),
('99999999-9999-9999-9999-999999999904', 'Sponges', 'household', 2.29, now())

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
