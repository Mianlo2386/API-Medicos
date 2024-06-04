CREATE TABLE pacientes (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    documento VARCHAR(8) NOT NULL UNIQUE,
    especialidad VARCHAR(100) NOT NULL,
    calle VARCHAR(100) NOT NULL,
    distrito VARCHAR(100) NOT NULL,
    complemento VARCHAR(100),
    numero INT,
    ciudad VARCHAR(100) NOT NULL,
    PRIMARY KEY (id)
);
