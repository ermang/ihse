INSERT INTO STOCK (name, description, price, created_at, version) VALUES ('INGH', 'INGH', 10.0001, NOW(), 0);
INSERT INTO STOCK (name, description, price, created_at, version) VALUES ('GARAN', 'GARAN', 9.0010, NOW(), 0);
INSERT INTO STOCK (name, description, price, created_at, version) VALUES ('ISCTR', 'ISCTR', 5.0100, NOW(), 0);
INSERT INTO STOCK (name, description, price, created_at, version) VALUES ('ABCD', 'ABCD', 15.10000, NOW(), 0);

INSERT INTO EXCHANGE (name, description, live, created_at, version) VALUES ('BIST', 'BIST', false, NOW(), 0);
INSERT INTO EXCHANGE (name, description, live, created_at, version) VALUES ('NASDAQ', 'NASDAQ', false, NOW(), 0);
INSERT INTO EXCHANGE (name, description, live, created_at, version) VALUES ('DAX', 'DAX', false, NOW(), 0);

INSERT INTO STOCK_EXCHANGE_REL (exchange_id, stock_id, created_at, version) VALUES (1, 1, NOW(), 0);
