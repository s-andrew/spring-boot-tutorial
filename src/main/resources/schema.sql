CREATE TABLE PERSONS(
ID BIGINT IDENTITY NOT NULL PRIMARY KEY,
FIRST_NAME VARCHAR(100),
SECOND_NAME VARCHAR(100),
BIRTHDAY DATE
);

CREATE TABLE PHONES(
ID BIGINT IDENTITY NOT NULL PRIMARY KEY,
NUMBER VARCHAR(20),
PERSON_ID BIGINT NOT NULL,
FOREIGN KEY (PERSON_ID) REFERENCES PERSONS(ID)
);


CREATE TABLE PROFESSIONS(
ID BIGINT IDENTITY NOT NULL PRIMARY KEY,
TITLE VARCHAR(100)
);


CREATE TABLE PERSONS_PROFESSIONS(
PERSON_ID BIGINT,
PROFESSION_ID BIGINT,
FOREIGN KEY (PERSON_ID) REFERENCES PERSONS(ID),
FOREIGN KEY (PROFESSION_ID) REFERENCES PROFESSIONS(ID)
);

----------------------------------------------------------------
-- SECURITY
----------------------------------------------------------------
CREATE TABLE USERS(
ID BIGINT IDENTITY NOT NULL PRIMARY KEY,
PASSWORD VARCHAR(100) NOT NULL,
USERNAME VARCHAR(100) NOT NULL
);

CREATE TABLE ROLES(
ID BIGINT IDENTITY NOT NULL PRIMARY KEY,
ROLE VARCHAR(20) NOT NULL
);

CREATE TABLE USER_ROLE(
USER_ID BIGINT NOT NULL,
ROLE_ID BIGINT NOT NULL,
FOREIGN KEY (USER_ID) REFERENCES USERS(ID),
FOREIGN KEY (ROLE_ID) REFERENCES ROLES(ID)
);