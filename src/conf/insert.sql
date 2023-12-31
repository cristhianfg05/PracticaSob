/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  crist
 * Created: 9 nov 2023
 */

-- CREDENTIALS --
INSERT INTO root.CREDENTIALS VALUES (NEXT VALUE FOR CREDENTIALS_GEN , 'sob','sob');

-- CONSOLE --
INSERT INTO root.CONSOLE (id,CONSOLENAME) 
VALUES (NEXT VALUE FOR Console_gen ,'PS5');

INSERT INTO root.CONSOLE (id,CONSOLENAME) 
VALUES (NEXT VALUE FOR Console_gen ,'Switch');

INSERT INTO root.CONSOLE (id,CONSOLENAME)  
VALUES (NEXT VALUE FOR Console_gen ,'Xbox Serie S');

INSERT INTO root.CONSOLE (id,CONSOLENAME)  
VALUES (NEXT VALUE FOR Console_gen ,'PC');

-- TYPE --
INSERT INTO root.TYPE (id,TYPENAME) 
VALUES (NEXT VALUE FOR Type_gen,'Action');

INSERT INTO root.TYPE (id,TYPENAME)
VALUES (NEXT VALUE FOR Type_gen,'Adventure');

INSERT INTO root.TYPE (id,TYPENAME) 
VALUES (NEXT VALUE FOR Type_gen,'Multiplayer');

INSERT INTO root.TYPE (id,TYPENAME) 
VALUES (NEXT VALUE FOR Type_gen,'Sci-Fi');

INSERT INTO root.TYPE (id,TYPENAME)
VALUES (NEXT VALUE FOR Type_gen,'Puzzle');

-- Inserts para la tabla Game --
INSERT INTO root.GAME (id,DESCRIPTION, DISPONIBLE, PRICE, TITLE, STOREADRESS, CONSOLE_ID) 
VALUES (NEXT VALUE FOR Game_gen,'Awesome game 1', true, 59.99, 'Game 1', 'Store 1', 1);

INSERT INTO root.GAME (id,DESCRIPTION, DISPONIBLE, PRICE, TITLE, STOREADRESS, CONSOLE_ID) 
VALUES (NEXT VALUE FOR Game_gen,'Exciting adventure', true, 49.99, 'Game 2', 'Store 2', 2);

INSERT INTO root.GAME (id,DESCRIPTION, DISPONIBLE, PRICE, TITLE, STOREADRESS, CONSOLE_ID) 
VALUES (NEXT VALUE FOR Game_gen,'Multiplayer challenge', false, 29.99, 'Game 3', 'Store 3', 3);

INSERT INTO root.GAME (id,DESCRIPTION, DISPONIBLE, PRICE, TITLE, STOREADRESS, CONSOLE_ID) 
VALUES (NEXT VALUE FOR Game_gen,'Sci-fi experience', true, 54.99, 'Game 4', 'Store 4', 1);

INSERT INTO root.GAME (id,DESCRIPTION, DISPONIBLE, PRICE, TITLE, STOREADRESS, CONSOLE_ID) 
VALUES (NEXT VALUE FOR Game_gen,'Puzzle-solving fun', true, 39.99, 'Game 5', 'Store 5', 4);

-- Inserts para la tabla CUSTOMERS --
INSERT INTO root.CUSTOMER (id,DNI, NOMBRE, HOMEADRESS, PSWD, TLF) 
VALUES (NEXT VALUE FOR Customers_gen,'12345678A', 'User 1', 'Address 1', 'password1', '123456789');

INSERT INTO root.CUSTOMER (id,DNI, NOMBRE, HOMEADRESS, PSWD, TLF) 
VALUES (NEXT VALUE FOR Customers_gen,'23456789B', 'User 2', 'Address 2', 'password2', '987654321');

INSERT INTO root.CUSTOMER (id,DNI, NOMBRE, HOMEADRESS, PSWD, TLF) 
VALUES (NEXT VALUE FOR Customers_gen,'34567890C', 'User 3', 'Address 3', 'password3', '567890123');

INSERT INTO root.CUSTOMER (id,DNI, NOMBRE, HOMEADRESS, PSWD, TLF) 
VALUES (NEXT VALUE FOR Customers_gen,'45678901D', 'User 4', 'Address 4', 'password4', '654321098');

INSERT INTO root.CUSTOMER (id,DNI, NOMBRE, HOMEADRESS, PSWD, TLF) 
VALUES (NEXT VALUE FOR Customers_gen,'56789012E', 'User 5', 'Address 5', 'password5', '432109876');

-- Inserts para la tabla RENT --
INSERT INTO root.RENT (id,DAYRENTED, TOTALPRICE, RETURNDATE, CUSTOMER_DNI, GAME_ID) 
VALUES (NEXT VALUE FOR Rent_gen,'2023-10-01', 49.99, '2023-10-10', '12345678A', 1);

INSERT INTO root.RENT (id,DAYRENTED, TOTALPRICE, RETURNDATE, CUSTOMER_DNI, GAME_ID) 
VALUES (NEXT VALUE FOR Rent_gen,'2023-10-03', 39.99, '2023-10-12', '23456789B', 5);

INSERT INTO root.RENT (id,DAYRENTED, TOTALPRICE, RETURNDATE, CUSTOMER_DNI, GAME_ID) 
VALUES (NEXT VALUE FOR Rent_gen,'2023-10-05', 59.99, '2023-10-15', '34567890C', 2);

INSERT INTO root.RENT (id,DAYRENTED, TOTALPRICE, RETURNDATE, CUSTOMER_DNI, GAME_ID) 
VALUES (NEXT VALUE FOR Rent_gen,'2023-10-07', 29.99, '2023-10-17', '45678901D', 3);

INSERT INTO root.RENT (id,DAYRENTED, TOTALPRICE, RETURNDATE, CUSTOMER_DNI, GAME_ID) 
VALUES (NEXT VALUE FOR Rent_gen,'2023-10-09', 54.99, '2023-10-19', '56789012E', 4);

-- Inserts para la tabla GAME_TYPE --
INSERT INTO root.GAME_TYPE (GAME_ID, TYPE_ID) 
VALUES (1, 1);

INSERT INTO root.GAME_TYPE (GAME_ID, TYPE_ID) 
VALUES (2, 2);

INSERT INTO root.GAME_TYPE (GAME_ID, TYPE_ID) 
VALUES (3, 3);

INSERT INTO root.GAME_TYPE (GAME_ID, TYPE_ID) 
VALUES (4, 4);

INSERT INTO root.GAME_TYPE (GAME_ID, TYPE_ID) 
VALUES (5, 5);
