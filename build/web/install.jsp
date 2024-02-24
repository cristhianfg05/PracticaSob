<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import = "java.sql.*" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Database SQL Load</title>
    </head>
    <style>
        .error {
            color: red;
        }
        pre {
            color: green;
        }
    </style>
    <body>
        <h2>Database SQL Load</h2>
        <%
            /* How to customize:
             * 1. Update the database name on dbname.
             * 2. Create the list of tables, under tablenames[].
             * 3. Create the list of table definition, under tables[].
             * 4. Create the data into the above table, under data[]. 
             * 
             * If there is any problem, it will exit at the very first error.
             */
            String dbname = "homework1";
            String schema = "ROOT";
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            /* this will generate database if not exist */
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/" + dbname, "root", "root");
            Statement stmt = con.createStatement();
            
            String sqlInserts[] = new String[]{
                "INSERT INTO "+schema+".CREDENTIALS VALUES (NEXT VALUE FOR CREDENTIALS_GEN, 'sob', 'sob')",
                "INSERT INTO "+schema+".GAME (id,CONSOLE,DESCRIPTION, DISPONIBLE, PRICE, TITLE, STOREADRESS, TYPE) VALUES (NEXT VALUE FOR Game_Seq,'MegaDrive','Awesome game 1', 1, 59.99, 'Game 1', 'Store 1','Action')",
                "INSERT INTO "+schema+".GAME (id,CONSOLE,DESCRIPTION, DISPONIBLE, PRICE, TITLE, STOREADRESS, TYPE) VALUES (NEXT VALUE FOR Game_Seq,'GameCube','Exciting adventure', 1, 49.99, 'Game 2', 'Store 2','Adventure')",
                "INSERT INTO "+schema+".GAME (id,CONSOLE,DESCRIPTION, DISPONIBLE, PRICE, TITLE, STOREADRESS, TYPE) VALUES (NEXT VALUE FOR Game_Seq,'GameCube','Multiplayer challenge', 0, 29.99, 'Game 3', 'Store 3','Puzzle')",
                "INSERT INTO "+schema+".GAME (id,CONSOLE,DESCRIPTION, DISPONIBLE, PRICE, TITLE, STOREADRESS, TYPE) VALUES (NEXT VALUE FOR Game_Seq,'GameCube','Sci-fi experience', 1, 54.99, 'Game 4', 'Store 4','Adventure')",
                "INSERT INTO "+schema+".GAME (id,CONSOLE,DESCRIPTION, DISPONIBLE, PRICE, TITLE, STOREADRESS, TYPE) VALUES (NEXT VALUE FOR Game_Seq,'Nds','Puzzle-solving fun', 1, 39.99, 'Game 5', 'Store 5','Action')",
                "INSERT INTO "+schema+".CUSTOMER (DNI, NOMBRE, HOMEADRESS, PSWD, TLF) VALUES ('12345678A', 'User 1', 'Address 1', '0b14d501a594442a01c6859541bcb3e8164d183d32937b851835442f69d5c94e', '123456789')",
                "INSERT INTO "+schema+".CUSTOMER (DNI, NOMBRE, HOMEADRESS, PSWD, TLF) VALUES ('23456789B', 'User 2', 'Address 2', '6cf615d5bcaac778352a8f1f3360d23f02f34ec182e259897fd6ce485d7870d4', '987654321')",
                "INSERT INTO "+schema+".CUSTOMER (DNI, NOMBRE, HOMEADRESS, PSWD, TLF) VALUES ('34567890C', 'User 3', 'Address 3', '5906ac361a137e2d286465cd6588ebb5ac3f5ae955001100bc41577c3d751764', '567890123')",
                "INSERT INTO "+schema+".CUSTOMER (DNI, NOMBRE, HOMEADRESS, PSWD, TLF) VALUES ('45678901D', 'User 4', 'Address 4', 'b97873a40f73abedd8d685a7cd5e5f85e4a9cfb83eac26886640a0813850122b', '654321098')",
                "INSERT INTO "+schema+".CUSTOMER (DNI, NOMBRE, HOMEADRESS, PSWD, TLF) VALUES ('56789012E', 'User 5', 'Address 5', '8b2c86ea9cf2ea4eb517fd1e06b74f399e7fec0fef92e3b482a6cf2e2b092023', '432109876')",
                "INSERT INTO "+schema+".CUSTOMER (DNI, NOMBRE, HOMEADRESS, PSWD, TLF) VALUES ('49978348S', 'Cristhian 5', 'Address 5', '8b2c86ea9cf2ea4eb517fd1e06b74f399e7fec0fef92e3b482a6cf2e2b092023', '432109876')",
                "INSERT INTO "+schema+".RENT (id,DAYRENTED, TOTALPRICE, RETURNDATE, CUSTOMER_DNI) VALUES (NEXT VALUE FOR Rent_Seq,TIMESTAMP('2023-10-01 00:00:00'), 49.99, TIMESTAMP('2023-10-10 00:00:00'), '12345678A')",
                "INSERT INTO "+schema+".RENT (id,DAYRENTED, TOTALPRICE, RETURNDATE, CUSTOMER_DNI) VALUES (NEXT VALUE FOR Rent_Seq,TIMESTAMP('2023-10-03 00:00:00'), 39.99, TIMESTAMP('2023-10-12 00:00:00'), '23456789B')",
                "INSERT INTO "+schema+".RENT (id,DAYRENTED, TOTALPRICE, RETURNDATE, CUSTOMER_DNI) VALUES (NEXT VALUE FOR Rent_Seq,TIMESTAMP('2023-10-05 00:00:00'), 59.99, TIMESTAMP('2023-10-15 00:00:00'), '34567890C')",
                "INSERT INTO "+schema+".RENT (id,DAYRENTED, TOTALPRICE, RETURNDATE, CUSTOMER_DNI) VALUES (NEXT VALUE FOR Rent_Seq,TIMESTAMP('2023-10-07 00:00:00'), 29.99, TIMESTAMP('2023-10-17 00:00:00'), '45678901D')",
                "INSERT INTO "+schema+".RENT (id,DAYRENTED, TOTALPRICE, RETURNDATE, CUSTOMER_DNI) VALUES (NEXT VALUE FOR Rent_Seq,TIMESTAMP('2023-10-09 00:00:00'), 54.99, TIMESTAMP('2023-10-19 00:00:00'), '56789012E')",
                "INSERT INTO "+schema+".RENT_GAME (RENT_ID, GAME_ID) VALUES (1, 1)",
                "INSERT INTO "+schema+".RENT_GAME (RENT_ID, GAME_ID) VALUES (2, 2)",
                "INSERT INTO "+schema+".RENT_GAME (RENT_ID, GAME_ID) VALUES (3, 3)",
                "INSERT INTO "+schema+".RENT_GAME (RENT_ID, GAME_ID) VALUES (4, 4)",
                "INSERT INTO "+schema+".RENT_GAME (RENT_ID, GAME_ID) VALUES (5, 5)"
            };
            
            for (String q : sqlInserts){
                if(stmt.executeUpdate(q)<=0){
                out.println("<span class='error'>Error inserting data: " + q + "</span>");
                return;
            }
                out.println("<pre> -> " + q + "<pre>");
            }

            /* inserting data */
            /* you have to exclude the id autogenerated from the list of columns if you have use it. */

        %>
        <button onclick="window.location='<%=request.getSession().getServletContext().getContextPath()%>'">Go home</button>
    </body>
</html>
