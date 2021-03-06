CREATE SCHEMA MED_JOURNALS;
SET SCHEMA MED_JOURNALS;

CREATE TABLE USERS (
		ID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) CONSTRAINT USER_PK PRIMARY KEY,
		EMAIL VARCHAR(128) NOT NULL,
		JOURNAL_NAME VARCHAR(256),
		PASSWORD VARCHAR(20) NOT NULL
);

CREATE TABLE ISSUES (
		ID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) CONSTRAINT ISSUE_PK PRIMARY KEY,
		USER_ID INTEGER NOT NULL CONSTRAINT USER_FOREIGN_KEY REFERENCES USERS,
		NAME VARCHAR(256) NOT NULL,
		CONTENT BLOB NOT NULL
);

CREATE TABLE SUBSCRIPTIONS (
		ID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) CONSTRAINT SUBSCRIPTION_PK PRIMARY KEY,
		SUBSCRIBER_USER_ID INTEGER NOT NULL CONSTRAINT SUBSCRIBER_FOREIGN_KEY REFERENCES USERS,
		PUBLISHER_USER_ID INTEGER NOT NULL CONSTRAINT PUBLISHER_FOREIGN_KEY REFERENCES USERS
);
