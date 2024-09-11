CREATE TABLE PRICES
(
    BRAND_ID   VARCHAR(10)    NOT NULL, -- Cambiado a VARCHAR
    START_DATE VARCHAR(19)    NOT NULL, -- Cambiado a VARCHAR para almacenar fecha como cadena
    END_DATE   VARCHAR(19)    NOT NULL, -- Cambiado a VARCHAR para almacenar fecha como cadena
    PRICE_LIST VARCHAR(10)    NOT NULL, -- Cambiado a VARCHAR
    PRODUCT_ID VARCHAR(10)    NOT NULL, -- Cambiado a VARCHAR
    PRIORITY   VARCHAR(10)    NOT NULL, -- Cambiado a VARCHAR
    PRICE      DECIMAL(10, 2) NOT NULL, -- Sigue siendo un número decimal
    CURR       VARCHAR(3)     NOT NULL, -- Sigue siendo una cadena (ya estaba bien)
    PRIMARY KEY (BRAND_ID, START_DATE, PRICE_LIST, PRODUCT_ID)
);