CREATE TABLE Persona (
        idPersona int NOT NULL AUTO_INCREMENT,
        nombre varchar(45) NOT NULL,
        genero varchar(9) NOT NULL,
        edad varchar(2) NOT NULL,
        identificacion varchar(8) NOT NULL,
        direccion varchar(45) NOT NULL,
        telefono varchar(9) NOT NULL,
        PRIMARY KEY (idPersona)
    );

CREATE TABLE Cliente (
        idCliente int NOT NULL AUTO_INCREMENT,
        password varchar(45) NOT NULL,
        idPersona int NOT NULL,
        estadoCliente binary NOT NULL,
        PRIMARY KEY (idCliente),
        CONSTRAINT FK_3 FOREIGN KEY (idPersona) REFERENCES Persona (idPersona)
    );

CREATE TABLE Cuenta (
        idCuenta int NOT NULL AUTO_INCREMENT,
        idCliente int NOT NULL,
        numeroCuenta varchar(6) NOT NULL,
        tipoCuenta varchar(15) NOT NULL,
        saldo int NOT NULL,
        estadoCuenta binary NOT NULL,
        PRIMARY KEY (idCuenta),
        CONSTRAINT FK_1 FOREIGN KEY (idCliente) REFERENCES Cliente (idCliente)
    );

CREATE TABLE Movimientos (
        idMovimiento int NOT NULL AUTO_INCREMENT,
        fecha date NOT NULL,
        idCuenta int NOT NULL,
        tipoMovimiento varchar(45) NOT NULL,
        valor int NOT NULL,
        saldoInicial int NOT NULL,
        saldoFinal int NOT NULL,
        PRIMARY KEY (idMovimiento),
        CONSTRAINT FK_2 FOREIGN KEY (idCuenta) REFERENCES Cuenta (idCuenta)
    );