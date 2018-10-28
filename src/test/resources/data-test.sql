-- CUSTOMERS
INSERT INTO CUSTOMERS(ID, FIRSTNAME, LASTNAME, CREATED, UPDATED) VALUES(SEQ_CUSTOMER_IDS.nextVal, 'Customer Fn1', 'Customer Ln1', NOW(), NOW())
INSERT INTO CUSTOMERS(ID, FIRSTNAME, LASTNAME, CREATED, UPDATED) VALUES(SEQ_CUSTOMER_IDS.nextVal, 'Customer Fn2', 'Customer Ln2', NOW(), NOW())
-- STYLISTS
INSERT INTO STYLISTS(ID, FIRSTNAME, LASTNAME, CREATED, UPDATED) VALUES(SEQ_STYLIST_IDS.nextVal, 'Stylist Fn1', 'Stylist Ln1', NOW(), NOW())
INSERT INTO STYLISTS(ID, FIRSTNAME, LASTNAME, CREATED, UPDATED) VALUES(SEQ_STYLIST_IDS.nextVal, 'Stylist Fn2', 'Stylist Ln2', NOW(), NOW())
INSERT INTO STYLISTS(ID, FIRSTNAME, LASTNAME, CREATED, UPDATED) VALUES(SEQ_STYLIST_IDS.nextVal, 'Stylist Fn3', 'Stylist Ln3', NOW(), NOW())
INSERT INTO STYLISTS(ID, FIRSTNAME, LASTNAME, CREATED, UPDATED) VALUES(SEQ_STYLIST_IDS.nextVal, 'Stylist Fn4', 'Stylist Ln4', NOW(), NOW())
-- AVAILABILITIES
INSERT INTO AVAILABILITIES(ID, STYLIST_ID, DAY, ENCODED_TIME_SLOTS, CREATED, UPDATED) VALUES(SEQ_AVAILABILITY_IDS.nextVal, 1, PARSEDATETIME('2018-10-21', 'yyyy-MM-dd'), '0000000000000000', NOW(), NOW())
INSERT INTO AVAILABILITIES(ID, STYLIST_ID, DAY, ENCODED_TIME_SLOTS, CREATED, UPDATED) VALUES(SEQ_AVAILABILITY_IDS.nextVal, 1, PARSEDATETIME('2018-10-22', 'yyyy-MM-dd'), '1100100000000010', NOW(), NOW())
INSERT INTO AVAILABILITIES(ID, STYLIST_ID, DAY, ENCODED_TIME_SLOTS, CREATED, UPDATED) VALUES(SEQ_AVAILABILITY_IDS.nextVal, 2, PARSEDATETIME('2018-10-22', 'yyyy-MM-dd'), '1000100100000010', NOW(), NOW())
INSERT INTO AVAILABILITIES(ID, STYLIST_ID, DAY, ENCODED_TIME_SLOTS, CREATED, UPDATED) VALUES(SEQ_AVAILABILITY_IDS.nextVal, 3, PARSEDATETIME('2018-10-22', 'yyyy-MM-dd'), '1000010000000010', NOW(), NOW())
INSERT INTO AVAILABILITIES(ID, STYLIST_ID, DAY, ENCODED_TIME_SLOTS, CREATED, UPDATED) VALUES(SEQ_AVAILABILITY_IDS.nextVal, 1, PARSEDATETIME('2018-10-23', 'yyyy-MM-dd'), '1010101010101010', NOW(), NOW())
INSERT INTO AVAILABILITIES(ID, STYLIST_ID, DAY, ENCODED_TIME_SLOTS, CREATED, UPDATED) VALUES(SEQ_AVAILABILITY_IDS.nextVal, 2, PARSEDATETIME('2018-10-23', 'yyyy-MM-dd'), '0101010101010101', NOW(), NOW())
INSERT INTO AVAILABILITIES(ID, STYLIST_ID, DAY, ENCODED_TIME_SLOTS, CREATED, UPDATED) VALUES(SEQ_AVAILABILITY_IDS.nextVal, 3, PARSEDATETIME('2018-10-23', 'yyyy-MM-dd'), '1111111111111111', NOW(), NOW())
INSERT INTO AVAILABILITIES(ID, STYLIST_ID, DAY, ENCODED_TIME_SLOTS, CREATED, UPDATED) VALUES(SEQ_AVAILABILITY_IDS.nextVal, 2, PARSEDATETIME('2018-10-24', 'yyyy-MM-dd'), '0000000000000000', NOW(), NOW())