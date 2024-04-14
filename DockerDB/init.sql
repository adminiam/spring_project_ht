CREATE DATABASE IF NOT EXISTS taskManagement;

USE taskManagement;

CREATE TABLE IF NOT EXISTS users
(
    id_user   INT PRIMARY KEY,
    user_name VARCHAR(255),
    id_task   INT
);

INSERT INTO `users` (id_user, user_name, id_task)
VALUES ('1', 'Dan', '1');

CREATE TABLE users_tasks
(
    user_id_user  INT NOT NULL,
    tasks_id_task INT NOT NULL,
    PRIMARY KEY (user_id_user, tasks_id_task)
);

CREATE TABLE `tasks`
(
    id_task    INT         NOT NULL,
    PRIMARY KEY (id_task),
    name       VARCHAR(45) NULL,
    description VARCHAR(45) NULL,
    deadline    VARCHAR(45) NULL,
    priority    VARCHAR(45) NULL,
    status VARCHAR(45) NULL
);
