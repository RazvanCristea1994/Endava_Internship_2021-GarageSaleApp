INSERT INTO category (name)
VALUES ('Laptop'), ('PC'), ('Mobile Phone'), ('Tablet'), ('TV'), ('Screen'), ('Smart Watch'), ('Accessories');

INSERT INTO asset (category_id, price, quantity) VALUES (1, 199, 5);
INSERT INTO issue (asset_id, asset_issue) VALUES (1, "Broken screen");

INSERT INTO asset (category_id, price, quantity) VALUES (1, 99, 10);
INSERT INTO issue (asset_id, asset_issue) VALUES (2, "Missing enter button");
INSERT INTO issue (asset_id, asset_issue) VALUES (2, "Missing space button");

INSERT INTO asset (category_id, price, quantity) VALUES (1, 299, 15);
INSERT INTO issue (asset_id, asset_issue) VALUES (3, "Some scratches on the touchpad");

INSERT INTO asset (category_id, price, quantity) VALUES (2, 399, 20);
INSERT INTO issue (asset_id, asset_issue) VALUES (4, "No issues");

INSERT INTO asset (category_id, price, quantity) VALUES (2, 159, 25);
INSERT INTO issue (asset_id, asset_issue) VALUES (5, "Very old hardware. Works pretty slow.");

INSERT INTO asset (category_id, price, quantity) VALUES (3, 159, 10);
INSERT INTO issue (asset_id, asset_issue) VALUES (6, "Scratches on the screen");

INSERT INTO asset (category_id, price, quantity) VALUES (3, 129, 5);
INSERT INTO issue (asset_id, asset_issue) VALUES (7, "Scratches on the screen and on the back");

INSERT INTO asset (category_id, price, quantity) VALUES (4, 209, 10);
INSERT INTO issue (asset_id, asset_issue) VALUES (8, "Scratches on the screen and on the back");

INSERT INTO asset (category_id, price, quantity) VALUES (4, 229, 20);
INSERT INTO issue (asset_id, asset_issue) VALUES (9, "Scratches on the screen");

INSERT INTO asset (category_id, price, quantity) VALUES (5, 329, 10);
INSERT INTO issue (asset_id, asset_issue) VALUES (10, "No issues");

INSERT INTO asset (category_id, price, quantity) VALUES (6, 129, 15);
INSERT INTO issue (asset_id, asset_issue) VALUES (11, "No issues");

INSERT INTO asset (category_id, price, quantity) VALUES (7, 59, 5);
INSERT INTO issue (asset_id, asset_issue) VALUES (12, "Scratches on the screen");

INSERT INTO asset (category_id, price, quantity) VALUES (7, 39, 5);
INSERT INTO issue (asset_id, asset_issue) VALUES (13, "Missing bracelet");