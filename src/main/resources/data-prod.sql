-- CUSTOMERS
INSERT INTO CUSTOMERS(ID, FIRSTNAME, LASTNAME, CREATED, UPDATED) VALUES(SEQ_CUSTOMER_IDS.nextVal, 'John', 'Doe', NOW(), NOW())
INSERT INTO CUSTOMERS(ID, FIRSTNAME, LASTNAME, CREATED, UPDATED) VALUES(SEQ_CUSTOMER_IDS.nextVal, 'Max', 'Mustermann', NOW(), NOW())
-- STYLISTS
INSERT INTO STYLISTS(ID, FIRSTNAME, LASTNAME, CREATED, UPDATED) VALUES(SEQ_STYLIST_IDS.nextVal, 'Calvin', 'Klein', NOW(), NOW())
INSERT INTO STYLISTS(ID, FIRSTNAME, LASTNAME, CREATED, UPDATED) VALUES(SEQ_STYLIST_IDS.nextVal, 'Tommy', 'Hilfiger', NOW(), NOW())
INSERT INTO STYLISTS(ID, FIRSTNAME, LASTNAME, CREATED, UPDATED) VALUES(SEQ_STYLIST_IDS.nextVal, 'Giorgio', 'Armani', NOW(), NOW())
-- STYLIST_AVAILABILITIES
INSERT INTO STYLIST_AVAILABILITIES(ID, STYLIST_ID, DAY, ENCODED_TIME_SLOTS, CREATED, UPDATED) VALUES(SEQ_STYLIST_AVAILABILITY_IDS.nextVal, 1, PARSEDATETIME('2018-10-28', 'yyyy-MM-dd'), '0010000000000000', NOW(), NOW())
INSERT INTO STYLIST_AVAILABILITIES(ID, STYLIST_ID, DAY, ENCODED_TIME_SLOTS, CREATED, UPDATED) VALUES(SEQ_STYLIST_AVAILABILITY_IDS.nextVal, 1, PARSEDATETIME('2018-10-29', 'yyyy-MM-dd'), '0010000000000000', NOW(), NOW())
INSERT INTO STYLIST_AVAILABILITIES(ID, STYLIST_ID, DAY, ENCODED_TIME_SLOTS, CREATED, UPDATED) VALUES(SEQ_STYLIST_AVAILABILITY_IDS.nextVal, 2, PARSEDATETIME('2018-10-29', 'yyyy-MM-dd'), '1110000000000000', NOW(), NOW())
INSERT INTO STYLIST_AVAILABILITIES(ID, STYLIST_ID, DAY, ENCODED_TIME_SLOTS, CREATED, UPDATED) VALUES(SEQ_STYLIST_AVAILABILITY_IDS.nextVal, 3, PARSEDATETIME('2018-10-29', 'yyyy-MM-dd'), '0110000000000000', NOW(), NOW())
INSERT INTO STYLIST_AVAILABILITIES(ID, STYLIST_ID, DAY, ENCODED_TIME_SLOTS, CREATED, UPDATED) VALUES(SEQ_STYLIST_AVAILABILITY_IDS.nextVal, 1, PARSEDATETIME('2018-10-30', 'yyyy-MM-dd'), '0000000000000000', NOW(), NOW())
INSERT INTO STYLIST_AVAILABILITIES(ID, STYLIST_ID, DAY, ENCODED_TIME_SLOTS, CREATED, UPDATED) VALUES(SEQ_STYLIST_AVAILABILITY_IDS.nextVal, 2, PARSEDATETIME('2018-10-30', 'yyyy-MM-dd'), '1111111111111111', NOW(), NOW())
INSERT INTO STYLIST_AVAILABILITIES(ID, STYLIST_ID, DAY, ENCODED_TIME_SLOTS, CREATED, UPDATED) VALUES(SEQ_STYLIST_AVAILABILITY_IDS.nextVal, 3, PARSEDATETIME('2018-11-01', 'yyyy-MM-dd'), '0000000000000000', NOW(), NOW())