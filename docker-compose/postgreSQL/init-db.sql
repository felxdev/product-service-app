SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

CREATE SCHEMA IF NOT EXISTS MYSCHEMA;

REVOKE ALL ON SCHEMA MYSCHEMA FROM PUBLIC;
GRANT ALL ON SCHEMA MYSCHEMA TO "user";
GRANT ALL ON SCHEMA MYSCHEMA TO PUBLIC;


CREATE TABLE IF NOT EXISTS MYSCHEMA.PRICES
(
    ID         SERIAL PRIMARY KEY NOT NULL,
    BRAND_ID   INT                NOT NULL,
    START_DATE VARCHAR(19)        NOT NULL,
    END_DATE   VARCHAR(19)        NOT NULL,
    PRICE_LIST INT                NOT NULL,
    PRODUCT_ID INT                NOT NULL,
    PRIORITY   INT                NOT NULL,
    PRICE      DECIMAL(10, 2)     NOT NULL,
    CURR       VARCHAR(3)         NOT NULL
);

CREATE INDEX prices_brand_id_index ON MYSCHEMA.PRICES USING btree (brand_id);
CREATE INDEX prices_product_id_index ON MYSCHEMA.PRICES USING btree (product_id);

INSERT INTO MYSCHEMA.PRICES (brand_id, start_date, end_date, price_list, product_id, priority,
                             price, curr)
VALUES (1, '2020-06-14 00:00:00', '2020-12-31 23:59:59', 1, 35455, 0, 35.50, 'EUR');
INSERT INTO MYSCHEMA.PRICES (brand_id, start_date, end_date, price_list, product_id, priority,
                             price, curr)
VALUES (1, '2020-06-14 15:00:00', '2020-06-14 18:30:00', 2, 35455, 1, 25.45, 'EUR');
INSERT INTO MYSCHEMA.PRICES (brand_id, start_date, end_date, price_list, product_id, priority,
                             price, curr)
VALUES (1, '2020-06-15 00:00:00', '2020-06-15 11:00:00', 3, 35455, 1, 30.50, 'EUR');
INSERT INTO MYSCHEMA.PRICES (brand_id, start_date, end_date, price_list, product_id, priority,
                             price, curr)
VALUES (1, '2020-06-15 16:00:00', '2020-12-31 23:59:59', 4, 35455, 1, 38.95, 'EUR');