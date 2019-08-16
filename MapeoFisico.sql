CREATE DATABASE CodeNBugs;
USE CodeNBugs;

CREATE TABLE Precios(
codigo VARCHAR (25) NOT NULL,
nombre VARCHAR (25) NOT NULL,
precio DOUBLE NOT NULL,
CONSTRAINT PK_PRECIO PRIMARY KEY(codigo)
);

CREATE TABLE Cliente(
casillero INT(5) NOT NULL,
nit VARCHAR(13) NOT NULL default 'c/f',
nombres VARCHAR(25) NOT NULL,
apellidos VARCHAR(25) NOT NULL,
direccion VARCHAR(20),
correoElectronico VARCHAR(35),
CONSTRAINT PK_CLIENTE PRIMARY KEY(casillero)
);


CREATE TABLE Factura(
idFactura INT(4) NOT NULL AUTO_INCREMENT,
casilleroCliente INT(5) NOT NULL,
direccionCliente VARCHAR(20),
total DOUBLE NOT NULL,
fechaYHora DATETIME NOT NULL,
nombre VARCHAR(40), 
CONSTRAINT PK_FACTURA PRIMARY KEY (idFactura),
CONSTRAINT FK_CLIENTE_FACTURA FOREIGN KEY (casilleroCliente)
	REFERENCES Cliente(casillero)
	ON UPDATE CASCADE);

CREATE TABLE TiposDeEmpleados(
tipo INT NOT NULL,
puesto VARCHAR(13) NOT NULL,
CONSTRAINT PK_TIPO_EMPLEADO PRIMARY KEY(tipo)
);

CREATE TABLE Empleado(
cui CHAR(13) NOT NULL,
nombres VARCHAR(25) NOT NULL,
apellidos VARCHAR(25) NOT NULL,
salario DOUBLE NOT NULL,
direccion VARCHAR(50),
correoElectronico VARCHAR(35),
estado BOOLEAN NOT NULL,
tipo INT NOT NULL,
fechaDeContratacion DATE NOT NULL,
password VARCHAR(10) NOT NULL,
CONSTRAINT PK_EMPLEADO PRIMARY KEY (cui));
ALTER TABLE Empleado ADD CONSTRAINT FK_TIPO_EMPLEADO FOREIGN KEY (tipo)
	REFERENCES TiposDeEmpleados(tipo)
	ON UPDATE CASCADE
);

CREATE TABLE Destino(
idDestino INT(4) NOT NULL AUTO_INCREMENT,
precio DOUBLE NOT NULL,
nombre VARCHAR(20) NOT NULL,
CONSTRAINT PK_DESTINO PRIMARY KEY (idDestino)
);

/*ALTER TABLE Destino AUTO_INCREMENT = 100000;*/

CREATE TABLE Ruta(
idRuta INT(4) NOT NULL PRIMARY KEY AUTO_INCREMENT,
nombre VARCHAR(20) NOT NULL,
idDestino INT(4) NOT NULL,
cantidadDePaquetesRegistrados INT NOT NULL,
CONSTRAINT PK_RUTA PRIMARY KEY (idRuta));

ALTER TABLE Ruta ADD CONSTRAINT FK_DESTINO FOREIGN KEY (idDestino)
	REFERENCES Destino(idDestino);
ALTER TABLE Ruta AUTO_INCREMENT=1000;

CREATE TABLE PuntoDeControl(
idPunto INT(5) NOT NULL AUTO_INCREMENT,
nombre VARCHAR(15) NOT NULL,
idRuta INT(4) NOT NULL,
cui CHAR(13) NOT NULL,
capacidad INT NOT NULL,
tarifaDeOperacion VARCHAR (25) NOT NULL,
CONSTRAINT PK_PUNTO PRIMARY KEY (idPunto),
CONSTRAINT FK_EMPLEADO FOREIGN KEY(cui)
	REFERENCES Empleado(cui)
	ON UPDATE CASCADE,
CONSTRAINT FK_PRECIO FOREIGN KEY (tarifaDeOperacion)
    REFERENCES Precios(codigo)
    ON UPDATE CASCADE
CONSTRAINT FK_RUTA FOREIGN KEY(idRuta) 
	REFERENCES Ruta(idRuta)
	ON DELETE RESTRICT);

CREATE TABLE Paquete(
idPaquete INT(9) NOT NULL AUTO_INCREMENT,
descripcion VARCHAR(25) NOT NULL,
idPunto INT(5),
fechaDeIngreso DATE NOT NULL,
casillero INT(5) NOT NULL,
priorizado BOOLEAN NOT NULL, 
libras INT NOT NULL,
idRuta VARCHAR(3) NOT NULL,
idFactura INT NOT NULL,
costo DOUBLE NOT NULL,
precioDestino DOUBLE NOT NULL,
retirado BOOLEAN NOT NULL,
CONSTRAINT PK_PAQUETE PRIMARY KEY(idPaquete),
CONSTRAINT FK_PUNTO_CONTROL FOREIGN KEY (idPunto)
	REFERENCES PuntoDeControl(idPunto)
	ON DELETE RESTRICT
	ON UPDATE RESTRICT,
CONSTRAINT FK_CLIENTE FOREIGN KEY(casilleroCliente)
	REFERENCES Cliente(casillero)
	ON UPDATE CASCADE,
CONSTRAINT FK_FACTURA FOREIGN KEY(idFactura)
	REFERENCES Factura(idFactura));
	
ALTER TABLE PAQUETE AUTO_INCREMENT=1000;

//Creacion del primer usuario 
INSERT INTO Empleado values(
'3145571991301',
'Kevin Josue',
'Fuentes Villatoro',
'Zaculeu Central Zona 9',
'fuentesjosue83@gmail.com',
true,
1,
'2019-08-01 08:00:00',
'98kevinf');


INSERT INTO TiposDeEmpleados VALUES(1, 'Administrador');
INSERT INTO TiposDeEmpleados VALUES(2, 'Operador');
INSERT INTO TiposDeEmpleados VALUES(3, 'Recepcionista');

//creacion del usuario administrador de la base de datos

CREATE USER admin@localhost IDENTIFIED BY "password";
GRANT ALL PRIVILEGES ON CodeNBugs.* TO admin@localhost;


//Llenado de los primeros precios 
INSERT INTO Precios VALUES ('p_priorizacion','Precio de Priorizacion',20.80);
INSERT INTO Precios VALUES ('p_punto_control','Tarifa Global',7,99);
