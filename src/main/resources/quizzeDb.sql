-- ==============================================================
-- DB creation script for MySQL
-- user: root
-- password:1234
-- ==============================================================

SET NAMES utf8;

DROP DATABASE IF EXISTS quizdb;
CREATE DATABASE quizdb CHARACTER SET utf8 COLLATE utf8_bin;

USE quizdb;
-- --------------------------------------------------------------
-- ROLES
-- users roles
-- --------------------------------------------------------------

DROP TABLE IF EXISTS roles;

CREATE TABLE roles
(

-- id has the INTEGER type (other name is INT), it is the primary key
    id   INTEGER     NOT NULL PRIMARY KEY,

-- name has the VARCHAR type - a string with a variable length
-- names values should not be repeated (UNIQUE)
    name VARCHAR(10) NOT NULL UNIQUE
);

-- this two commands insert data into roles table
-- --------------------------------------------------------------
-- ATTENTION!!!
-- we use ENUM as the Role entity, so the numeration must started 
-- from 0 with the step equaled to 1
-- --------------------------------------------------------------
INSERT INTO roles
VALUES (0, 'admin');
INSERT INTO roles
VALUES (1, 'client');

-- --------------------------------------------------------------
-- LOGIN
-- --------------------------------------------------------------
DROP TABLE IF EXISTS user_account;

CREATE TABLE user_account
(
    id                      BIGINT             NOT NULL auto_increment PRIMARY KEY,
    user_name               VARCHAR(20) UNIQUE NOT NULL,
    password                VARCHAR(100)        NOT NULL,
    password_salt           VARCHAR(100),
    password_hash_algorithm VARCHAR(20),
-- this declaration contains the foreign key constraint	
-- role_id in users table is associated with id in roles table
-- role_id of user = id of role
    role_id                 INTEGER            NOT NULL,
    INDEX rol_ind (role_id),
    FOREIGN KEY (role_id) REFERENCES roles (id)
-- removing a row with some ID from roles table implies removing 
-- all rows from users table for which ROLES_ID=ID
-- default value is ON DELETE RESTRICT, it means you cannot remove
-- row with some ID from the roles table if there are rows in 
-- users table with ROLES_ID=ID
        ON DELETE CASCADE
-- the same as previous but updating is used insted deleting
        ON UPDATE RESTRICT
);

INSERT INTO user_account
VALUES (DEFAULT, 'admin', '123', null, null, 0);
INSERT INTO user_account
VALUES (DEFAULT, 'ivani', '123', null, null, 1);
INSERT INTO user_account
VALUES (DEFAULT, 'petrp', '123', null, null, 1);

-- --------------------------------------------------------------
-- USERS
-- --------------------------------------------------------------
DROP TABLE IF EXISTS users;

CREATE TABLE users
(
    id          BIGINT      NOT NULL auto_increment PRIMARY KEY,
    first_name  VARCHAR(20) NOT NULL,
    last_name   VARCHAR(20) NOT NULL,
-- this declaration of status of user Active true, not activ - false;	
    user_status BOOLEAN DEFAULT TRUE,
-- this declaration contains the foreign key constraint	
-- account_id in users table is associated with id in users table
-- account_id of user = id of user account
    account_id  BIGINT      NOT NULL,
    INDEX acc_ind (account_id),
    FOREIGN KEY (account_id) REFERENCES user_account (id)
-- removing a row with some ID from roles table implies removing 
-- all rows from users table for which ROLES_ID=ID
-- default value is ON DELETE RESTRICT, it means you cannot remove
-- row with some ID from the roles table if there are rows in 
-- users table with ACCOUNT_ID=ID
        ON DELETE CASCADE
-- the same as previous but updating is used insted deleting
        ON UPDATE RESTRICT
);

INSERT INTO users
VALUES (DEFAULT, 'Anatol', 'Ageev', DEFAULT, 1);

INSERT INTO users
VALUES (DEFAULT, 'Ivan', 'Ivanov', DEFAULT, 2);

INSERT INTO users
VALUES (DEFAULT, 'Петр', 'Петрров', DEFAULT, 3);

-- --------------------------------------------------------------
-- LOCALE
-- locale for db
-- --------------------------------------------------------------
DROP TABLE IF EXISTS locale;

CREATE TABLE locale
(
    -- language_code contains ru, en, etc.
    language_code VARCHAR(20) UNIQUE NOT NULL,
    -- will contanes name of the language like English, Русский
    name          VARCHAR(20)        NOT NULL
);

INSERT INTO locale
VALUES ('en', 'English');
INSERT INTO locale
VALUES ('ru', 'Русский');

-- --------------------------------------------------------------
-- SUBJECT
-- --------------------------------------------------------------

DROP TABLE IF EXISTS subject;

CREATE TABLE subject
(
    id           BIGINT       NOT NULL auto_increment PRIMARY KEY,
    default_name VARCHAR(100) NOT NULL
);

INSERT INTO subject
VALUES (DEFAULT, 'Theory DB');
INSERT INTO subject
VALUES (DEFAULT, 'Java');

-- --------------------------------------------------------------
-- SUBJECT LOCALE
-- --------------------------------------------------------------

DROP TABLE IF EXISTS subject_locale;

CREATE TABLE subject_locale
(
    subject_id   BIGINT       NOT NULL,
    lang_ind     VARCHAR(20)  NOT NULL,
    subject_name VARCHAR(100) NOT NULL,

    FOREIGN KEY (lang_ind) REFERENCES locale (language_code)
        ON DELETE CASCADE
        ON UPDATE RESTRICT,
    FOREIGN KEY (subject_id) REFERENCES subject (id)
        ON DELETE CASCADE
        ON UPDATE RESTRICT
);

INSERT INTO subject_locale
VALUES (1, 'en', 'Theory DB');
INSERT INTO subject_locale
VALUES (1, 'ru', 'Теория баз данных');
INSERT INTO subject_locale
VALUES (2, 'en', 'Java');
INSERT INTO subject_locale
VALUES (2, 'ru', 'Язык програмирования Java');


-- --------------------------------------------------------------
-- TEST
-- --------------------------------------------------------------

DROP TABLE IF EXISTS tests;

CREATE TABLE tests
(
    id              BIGINT  NOT NULL auto_increment PRIMARY KEY,

    difficulty      INTEGER NOT NULL,
    min_to_complete INTEGER NOT NULL DEFAULT 30,
    subject_id      BIGINT  NOT NULL,
    INDEX lang_ind (subject_id),
    FOREIGN KEY (subject_id) REFERENCES subject (id)
        ON DELETE CASCADE
        ON UPDATE RESTRICT
);

INSERT INTO tests
VALUES (DEFAULT, 30, 15, 1);
INSERT INTO tests
VALUES (DEFAULT, 40, 25, 1);
INSERT INTO tests
VALUES (DEFAULT, 50, 35, 1);
INSERT INTO tests
VALUES (DEFAULT, 30, 20, 2);
INSERT INTO tests
VALUES (DEFAULT, 40, 35, 2);
INSERT INTO tests
VALUES (DEFAULT, 50, 30, 2);

-- --------------------------------------------------------------
-- TEST LOCALE
-- --------------------------------------------------------------

DROP TABLE IF EXISTS tests_locale;

CREATE TABLE tests_locale
(
    test_id   BIGINT       NOT NULL,
    lang_ind  VARCHAR(20)  NOT NULL,
    test_name VARCHAR(200) NOT NULL,

    FOREIGN KEY (lang_ind) REFERENCES locale (language_code)
        ON DELETE CASCADE
        ON UPDATE RESTRICT,
    FOREIGN KEY (test_id) REFERENCES tests (id)
        ON DELETE CASCADE
        ON UPDATE RESTRICT
);

INSERT INTO tests_locale
VALUES (1, 'en', 'Elementaey SQL');
INSERT INTO tests_locale
VALUES (1, 'ru', 'Начальный SQL');
INSERT INTO tests_locale
VALUES (2, 'en', 'Advince SQL');
INSERT INTO tests_locale
VALUES (2, 'ru', 'Продвинутый SQL');
INSERT INTO tests_locale
VALUES (3, 'en', 'Pro SQL');
INSERT INTO tests_locale
VALUES (3, 'ru', 'Профессиональный SQL');
INSERT INTO tests_locale
VALUES (4, 'en', 'Elementaey Java');
INSERT INTO tests_locale
VALUES (4, 'ru', 'Начальный уровень Java');
INSERT INTO tests_locale
VALUES (5, 'en', 'Advince Java');
INSERT INTO tests_locale
VALUES (5, 'ru', 'Продвинутый Java');
INSERT INTO tests_locale
VALUES (6, 'en', 'Pro Java');
INSERT INTO tests_locale
VALUES (6, 'ru', 'Профессиональный уровень Java');

-- --------------------------------------------------------------
-- QUESTIONS
-- --------------------------------------------------------------

DROP TABLE IF EXISTS questions;

CREATE TABLE questions
(
    id      BIGINT NOT NULL auto_increment PRIMARY KEY,
    test_id BIGINT NOT NULL,
--	num_correct_answers INTEGER DEFAULT 1,
    INDEX test_ind (test_id),
    FOREIGN KEY (test_id) REFERENCES tests (id)
        ON DELETE CASCADE
        ON UPDATE RESTRICT
);

INSERT INTO questions
VALUES (DEFAULT, 1);
INSERT INTO questions
VALUES (DEFAULT, 1);
INSERT INTO questions
VALUES (DEFAULT, 1);
INSERT INTO questions
VALUES (DEFAULT, 1);
INSERT INTO questions
VALUES (DEFAULT, 1);
INSERT INTO questions
VALUES (DEFAULT, 2);
INSERT INTO questions
VALUES (DEFAULT, 2);
INSERT INTO questions
VALUES (DEFAULT, 3);
INSERT INTO questions
VALUES (DEFAULT, 3);
INSERT INTO questions
VALUES (DEFAULT, 4);
INSERT INTO questions
VALUES (DEFAULT, 4);
INSERT INTO questions
VALUES (DEFAULT, 4);
INSERT INTO questions
VALUES (DEFAULT, 4);
INSERT INTO questions
VALUES (DEFAULT, 4);
INSERT INTO questions
VALUES (DEFAULT, 5);
INSERT INTO questions
VALUES (DEFAULT, 5);
INSERT INTO questions
VALUES (DEFAULT, 6);
INSERT INTO questions
VALUES (DEFAULT, 6);

-- --------------------------------------------------------------
-- QUESTIONS LOCALE
-- --------------------------------------------------------------

DROP TABLE IF EXISTS questions_locale;

CREATE TABLE questions_locale
(
    question_id   BIGINT      NOT NULL,
    lang_ind      VARCHAR(20) NOT NULL,
    question_text VARCHAR(1000),
    FOREIGN KEY (lang_ind) REFERENCES locale (language_code)
        ON DELETE CASCADE
        ON UPDATE RESTRICT,
    FOREIGN KEY (question_id) REFERENCES questions (id)
        ON DELETE CASCADE
        ON UPDATE RESTRICT

);

INSERT INTO questions_locale
VALUES (1, 'en', 'Qustion 1');
INSERT INTO questions_locale
VALUES (2, 'en', 'Qustion 2');
INSERT INTO questions_locale
VALUES (3, 'en', 'Qustion 3');
INSERT INTO questions_locale
VALUES (4, 'en', 'Qustion 3');
INSERT INTO questions_locale
VALUES (5, 'en', 'Qustion 3');
INSERT INTO questions_locale
VALUES (1, 'ru', 'Вопрос 1');
INSERT INTO questions_locale
VALUES (2, 'ru', 'Вопрос 2');
INSERT INTO questions_locale
VALUES (3, 'ru', 'Вопрос 3');
INSERT INTO questions_locale
VALUES (4, 'ru', 'Вопрос 3');
INSERT INTO questions_locale
VALUES (5, 'ru', 'Вопрос 3');
INSERT INTO questions_locale
VALUES (6, 'ru', 'Вопрос DB 1');
INSERT INTO questions_locale
VALUES (7, 'ru', 'Вопрос DB 2');
INSERT INTO questions_locale
VALUES (6, 'en', 'Qustion DB 1');
INSERT INTO questions_locale
VALUES (7, 'en', 'Qustion DB 2');
INSERT INTO questions_locale
VALUES (8, 'en', 'Qustion DB 1');
INSERT INTO questions_locale
VALUES (9, 'en', 'Qustion DB 2');
INSERT INTO questions_locale
VALUES (8, 'ru', 'Вопрос DB 1');
INSERT INTO questions_locale
VALUES (9, 'ru', 'Вопрос DB 2');
INSERT INTO questions_locale
VALUES (10, 'en', 'Qustion Java 1');
INSERT INTO questions_locale
VALUES (11, 'en', 'Qustion Java 1');
INSERT INTO questions_locale
VALUES (12, 'en', 'Qustion Java 1');
INSERT INTO questions_locale
VALUES (13, 'en', 'Qustion Java 1');

INSERT INTO questions_locale
VALUES (14, 'en', 'Qustion Java 1');

INSERT INTO questions_locale
VALUES (11, 'ru', 'Вопрос Java 1');
INSERT INTO questions_locale
VALUES (12, 'ru', 'Вопрос Java 1');
INSERT INTO questions_locale
VALUES (13, 'ru', 'Вопрос Java 1');
INSERT INTO questions_locale
VALUES (14, 'ru', 'Вопрос Java 1');

INSERT INTO questions_locale
VALUES (10, 'ru', 'Вопрос Java 1');
INSERT INTO questions_locale
VALUES (15, 'en', 'Qustion Java 1');
INSERT INTO questions_locale
VALUES (16, 'en', 'Qustion Java 1');
INSERT INTO questions_locale
VALUES (15, 'ru', 'Вопрос Java 1');
INSERT INTO questions_locale
VALUES (16, 'ru', 'Вопрос Java 1');
INSERT INTO questions_locale
VALUES (17, 'en', 'Qustion Java 1');
INSERT INTO questions_locale
VALUES (18, 'en', 'Qustion Java 1');
INSERT INTO questions_locale
VALUES (17, 'ru', 'Вопрос Java 1');
INSERT INTO questions_locale
VALUES (18, 'ru', 'Вопрос Java 1');


-- --------------------------------------------------------------
-- ANSWERS
-- --------------------------------------------------------------

DROP TABLE IF EXISTS answers;

CREATE TABLE answers
(
    id          BIGINT NOT NULL auto_increment PRIMARY KEY,
    question_id BIGINT NOT NULL,
    is_correct  BOOLEAN DEFAULT FALSE,
--	num_correct INTEGER DEFAULT 1,
    INDEX quest_ind (question_id),
    FOREIGN KEY (question_id) REFERENCES questions (id)
        ON DELETE CASCADE
        ON UPDATE RESTRICT
);

INSERT INTO answers
VALUES (DEFAULT, 1, DEFAULT);
INSERT INTO answers
VALUES (DEFAULT, 1, TRUE);
INSERT INTO answers
VALUES (DEFAULT, 1, DEFAULT);
INSERT INTO answers
VALUES (DEFAULT, 1, DEFAULT);
INSERT INTO answers
VALUES (DEFAULT, 2, DEFAULT);
INSERT INTO answers
VALUES (DEFAULT, 2, TRUE);
INSERT INTO answers
VALUES (DEFAULT, 2, DEFAULT);
INSERT INTO answers
VALUES (DEFAULT, 2, TRUE);
INSERT INTO answers
VALUES (DEFAULT, 3, DEFAULT);
INSERT INTO answers
VALUES (DEFAULT, 3, TRUE);
INSERT INTO answers
VALUES (DEFAULT, 3, DEFAULT);
INSERT INTO answers
VALUES (DEFAULT, 3, DEFAULT);



-- --------------------------------------------------------------
-- ANSWERS LOCALE
-- --------------------------------------------------------------

DROP TABLE IF EXISTS answers_locale;

CREATE TABLE answers_locale
(
    answer_id   BIGINT      NOT NULL,
    lang_ind    VARCHAR(20) NOT NULL,
    answer_text VARCHAR(1000),
    FOREIGN KEY (lang_ind) REFERENCES locale (language_code)
        ON DELETE CASCADE
        ON UPDATE RESTRICT,
    FOREIGN KEY (answer_id) REFERENCES answers (id)
        ON DELETE CASCADE
        ON UPDATE RESTRICT

);

INSERT INTO answers_locale
VALUES (1, 'en', 'Answer 1');
INSERT INTO answers_locale
VALUES (2, 'en', 'Answer 2');
INSERT INTO answers_locale
VALUES (3, 'en', 'Answer 3');
INSERT INTO answers_locale
VALUES (4, 'en', 'Answer 4');
INSERT INTO answers_locale
VALUES (5, 'en', 'Answer 1');
INSERT INTO answers_locale
VALUES (6, 'en', 'Answer 2');
INSERT INTO answers_locale
VALUES (7, 'en', 'Answer 3');
INSERT INTO answers_locale
VALUES (8, 'en', 'Answer 4');
INSERT INTO answers_locale
VALUES (9, 'en', 'Answer 1');
INSERT INTO answers_locale
VALUES (10, 'en', 'Answer 2');
INSERT INTO answers_locale
VALUES (11, 'en', 'Answer 3');
INSERT INTO answers_locale
VALUES (12, 'en', 'Answer 4');
INSERT INTO answers_locale
VALUES (1, 'ru', 'Ответ 1');
INSERT INTO answers_locale
VALUES (2, 'ru', 'Ответ 2');
INSERT INTO answers_locale
VALUES (3, 'ru', 'Ответ 3');
INSERT INTO answers_locale
VALUES (4, 'ru', 'Ответ 4');
INSERT INTO answers_locale
VALUES (5, 'ru', 'Ответ 1');
INSERT INTO answers_locale
VALUES (6, 'ru', 'Ответ 2');
INSERT INTO answers_locale
VALUES (7, 'ru', 'Ответ 3');
INSERT INTO answers_locale
VALUES (8, 'ru', 'Ответ 4');
INSERT INTO answers_locale
VALUES (9, 'ru', 'Ответ 1');
INSERT INTO answers_locale
VALUES (10, 'ru', 'Ответ 2');
INSERT INTO answers_locale
VALUES (11, 'ru', 'Ответ 3');
INSERT INTO answers_locale
VALUES (12, 'ru', 'Ответ 4');


-- --------------------------------------------------------------
-- USERS_RESULT
-- --------------------------------------------------------------

DROP TABLE IF EXISTS users_results;

CREATE TABLE users_results
(
    id              BIGINT NOT NULL auto_increment PRIMARY KEY,
    user_id         BIGINT NOT NULL,
    test_id         BIGINT NOT NULL,
    evaluation      INTEGER,
    date_evaluation DATE DEFAULT (CURRENT_DATE),
    INDEX quest_ind (user_id),
    FOREIGN KEY (user_id) REFERENCES user_account (id)
        ON DELETE CASCADE
        ON UPDATE RESTRICT
);

INSERT INTO users_results
VALUES (DEFAULT, 1, 1, 100, DEFAULT);
INSERT INTO users_results
VALUES (DEFAULT, 1, 2, 100, DEFAULT);
