-- CUSTOMERS
INSERT INTO CUSTOMERS(ID, FIRSTNAME, LASTNAME, CREATED, UPDATED) VALUES(SEQ_CUSTOMER_IDS.nextVal, 'Customer Fn1', 'Customer Ln1', NOW(), NOW())
INSERT INTO CUSTOMERS(ID, FIRSTNAME, LASTNAME, CREATED, UPDATED) VALUES(SEQ_CUSTOMER_IDS.nextVal, 'Customer Fn2', 'Customer Ln2', NOW(), NOW())
-- STYLISTS
INSERT INTO STYLISTS(ID, FIRSTNAME, LASTNAME, CREATED, UPDATED) VALUES(SEQ_STYLIST_IDS.nextVal, 'Stylist Fn1', 'Stylist Ln1', NOW(), NOW())
INSERT INTO STYLISTS(ID, FIRSTNAME, LASTNAME, CREATED, UPDATED) VALUES(SEQ_STYLIST_IDS.nextVal, 'Stylist Fn2', 'Stylist Ln2', NOW(), NOW())
INSERT INTO STYLISTS(ID, FIRSTNAME, LASTNAME, CREATED, UPDATED) VALUES(SEQ_STYLIST_IDS.nextVal, 'Stylist Fn3', 'Stylist Ln3', NOW(), NOW())
-- TIME_SLOTS