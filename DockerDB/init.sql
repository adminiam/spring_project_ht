CREATE DATABASE IF NOT EXISTS taskManagement;

USE taskManagement;

CREATE TABLE IF NOT EXISTS users (
  idUser INT PRIMARY KEY,
  userName VARCHAR(255),
    idTask INT
    );
