CREATE TABLE PRODUCTS(
    ID SERIAL NOT NULL PRIMARY KEY,
    MANUFACTURER_NAME TEXT NOT NULL,
    MODEL_NAME TEXT NOT NULL,
    PRICE DECIMAL NOT NULL,
    QUANTITY INT NOT NULL DEFAULT 0
);

CREATE TABLE USERS(
    ID SERIAL NOT NULL PRIMARY KEY,
    USERNAME TEXT NOT NULL UNIQUE,
    PASSWORD TEXT NOT NULL,
    ROLE TEXT NOT NULL
);

CREATE TABLE oauth_client_details
(
    client_id               VARCHAR(256) PRIMARY KEY,
    resource_ids            VARCHAR(256),
    client_secret           VARCHAR(256),
    scope                   VARCHAR(256),
    authorized_grant_types  VARCHAR(256),
    web_server_redirect_uri VARCHAR(256),
    authorities             VARCHAR(256),
    access_token_validity   INTEGER,
    refresh_token_validity  INTEGER,
    additional_information  VARCHAR(4096),
    autoapprove             VARCHAR(256)
);

