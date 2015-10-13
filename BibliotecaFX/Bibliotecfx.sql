CREATE DATABASE BibliotecaFX
GO
USE BibliotecaFX
GO 
CREATE TABLE Author
(
id INT PRIMARY KEY IDENTITY(1,1),
name VARCHAR(100) NOT NULL,
)
GO
CREATE TABLE Book
(
id INT PRIMARY KEY IDENTITY(1,1),
title VARCHAR(100) NOT NULL,
isbn VARCHAR(100) UNIQUE NOT NULL,
editorial VARCHAR(100) NOT NULL,
pages INT NOT NULL
)
GO
CREATE TABLE Users
(
id INT IDENTITY(1,1) PRIMARY KEY,
name VARCHAR(100) NOT NULL,
phone VARCHAR(100) NOT NULL,
userAddress VARCHAR(100) NOT NULL
)
GO
CREATE TABLE Copy
(
id INT IDENTITY(1,1) PRIMARY KEY,
location VARCHAR(100) NOT NULL,
idBook INT FOREIGN KEY REFERENCES Book(id) NOT NULL
)
GO
CREATE TABLE Write
(
idAuthor INT FOREIGN KEY REFERENCES Author(id) NOT NULL,
idBook INT FOREIGN KEY REFERENCES Book(id) NOT NULL
)
GO
CREATE TABLE Loan
(
idUser INT FOREIGN KEY REFERENCES Users(id) NOT NULL,
idCopy INT FOREIGN KEY REFERENCES Copy(id) NOT NULL,
fechaPrestamo DATE NOT NULL,
fechaDevolucion DATE 
)
GO
INSERT INTO Author(name )
VALUES('H.P. Lovecraft')
INSERT INTO Author(name )
VALUES('Miguel Angel Asturias')
INSERT INTO Author(name )
VALUES('Marcel Proust')
INSERT INTO Author(name )
VALUES('Homero')
INSERT INTO Author(name )
VALUES('James Joyce')
INSERT INTO Author(name )
VALUES('Jorge Luis Borges')
INSERT INTO Author(name )
VALUES('William Shakespeare')
INSERT INTO Author(name )
VALUES('Jane Austen')
INSERT INTO Author(name )
VALUES('Mario Vargas Llosa')
INSERT INTO Author(name )
VALUES('Pablo Neruda')
GO
INSERT INTO Book(title, isbn, editorial, pages)
VALUES('Mitos de Chutulu', 'sqxox', 'Piedra Santa', 100)
INSERT INTO Book(title, isbn, editorial, pages)
VALUES('Odisea', 'das2w2', 'Piedra Santa', 560)
INSERT INTO Book(title, isbn, editorial, pages)
VALUES('Moby Dick', 'sdaslop12', 'Piedra Santa', 800)
INSERT INTO Book(title, isbn, editorial, pages)
VALUES('Ulises', 'scdoow', 'Piedra Santa', 250)
INSERT INTO Book(title, isbn, editorial, pages)
VALUES('Rojo y Negro', '12321.dsa', 'Maya', 760)
INSERT INTO Book(title, isbn, editorial, pages)
VALUES('Rayuela', '321njsx', 'Maya', 1000)
INSERT INTO Book(title, isbn, editorial, pages)
VALUES('El Gatopardo', 'lñsdasd', 'Piedra Santa', 900)
INSERT INTO Book(title, isbn, editorial, pages)
VALUES('Peter Pan', '122328', 'Maya', 300)
INSERT INTO Book(title, isbn, editorial, pages)
VALUES('La Caida', '?¿22', 'Maya', 500)
INSERT INTO Book(title, isbn, editorial, pages)
VALUES('Sonetos', 'sdaslc..', 'Piedra Santa', 800)
GO
INSERT INTO Users(name,phone,userAddress )
VALUES('Federico Runa', '3321331', 'Zona 2')
INSERT INTO Users(name,phone,userAddress )
VALUES('Alcides Escobar', '92891291', 'Zona 5')
INSERT INTO Users(name,phone,userAddress )
VALUES('Mohamed Ali', '92012888', 'Zona 1')
INSERT INTO Users(name,phone,userAddress )
VALUES('Almicar Rondo', '2820011', 'Zona 10')
INSERT INTO Users(name,phone,userAddress )
VALUES('Pedro Baez', '10782738', 'Zona 11')
INSERT INTO Users(name,phone,userAddress )
VALUES('Andres Polanco', '290821', 'Zona 8')
INSERT INTO Users(name,phone,userAddress )
VALUES('Andres Bello', '8782012', 'Zona 9')
INSERT INTO Users(name,phone,userAddress )
VALUES('Amilcar Soto', '92721222', 'Zona 1')
INSERT INTO Users(name,phone,userAddress )
VALUES('Eugenio Derbez', '9820112', 'Zona 5')
INSERT INTO Users(name,phone,userAddress )
VALUES('Gonzalo Romero', '97210022', 'Zona 9')
GO
INSERT INTO Copy(location, idBook)
VALUES('Artemis', 2)
INSERT INTO Copy(location, idBook)
VALUES('Artemis', 9)
INSERT INTO Copy(location, idBook)
VALUES('Shopos', 1)
INSERT INTO Copy(location, idBook)
VALUES('Artemis', 1)
INSERT INTO Copy(location, idBook)
VALUES('Artemis', 3)
INSERT INTO Copy(location, idBook)
VALUES('Shopos', 10)
INSERT INTO Copy(location, idBook)
VALUES('Artemis', 8)
INSERT INTO Copy(location, idBook)
VALUES('Artemis', 4)
INSERT INTO Copy(location, idBook)
VALUES('Sophos', 9)
INSERT INTO Copy(location, idBook)
VALUES('Artemis', 7)
GO
INSERT INTO Write(idAuthor,idBook )
VALUES(1,1)
INSERT INTO Write(idAuthor,idBook )
VALUES(2,2)
INSERT INTO Write(idAuthor,idBook )
VALUES(3,3)
INSERT INTO Write(idAuthor,idBook )
VALUES(4,4)
INSERT INTO Write(idAuthor,idBook )
VALUES(5,5)
INSERT INTO Write(idAuthor,idBook )
VALUES(6,6)
INSERT INTO Write(idAuthor,idBook )
VALUES(7,7)
INSERT INTO Write(idAuthor,idBook )
VALUES(8,8)
INSERT INTO Write(idAuthor,idBook )
VALUES(9,9)
INSERT INTO Write(idAuthor,idBook )
VALUES(10,10)
GO
INSERT INTO Loan(idUser, idCopy, fechaPrestamo, fechaDevolucion)
VALUES(1, 1, 2005-09-01, 2005-09-10)
INSERT INTO Loan(idUser, idCopy, fechaPrestamo, fechaDevolucion)
VALUES(2, 2, 2005-09-03, 2005-09-20)
INSERT INTO Loan(idUser, idCopy, fechaPrestamo, fechaDevolucion)
VALUES(3, 3, 2006-12-05, NULL)
INSERT INTO Loan(idUser, idCopy, fechaPrestamo, fechaDevolucion)
VALUES(4, 4, 2006-07-11, 2006-07-22)
INSERT INTO Loan(idUser, idCopy, fechaPrestamo, fechaDevolucion)
VALUES(5, 5, 2009-10-01, 2009-10-10)
INSERT INTO Loan(idUser, idCopy, fechaPrestamo, fechaDevolucion)
VALUES(6, 6, 2009-11-10, 2009-12-12)
INSERT INTO Loan(idUser, idCopy, fechaPrestamo, fechaDevolucion)
VALUES(7, 7, 2010-01-11, NULL)
INSERT INTO Loan(idUser, idCopy, fechaPrestamo, fechaDevolucion)
VALUES(8, 8, 2010-02-20, NULL)
INSERT INTO Loan(idUser, idCopy, fechaPrestamo, fechaDevolucion)
VALUES(9, 9, 2010-05-12, 2010-05-22)
INSERT INTO Loan(idUser, idCopy, fechaPrestamo, fechaDevolucion)
VALUES(10, 10, 2011-10-02, 2011-10-10)
GO