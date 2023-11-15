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
                "INSERT INTO "+schema+".CONSOLE (id,CONSOLENAME) VALUES (NEXT VALUE FOR Console_Seq,'PS5')",
                "INSERT INTO "+schema+".CONSOLE (id,CONSOLENAME) VALUES (NEXT VALUE FOR Console_Seq,'Switch')",
                "INSERT INTO "+schema+".CONSOLE (id,CONSOLENAME) VALUES (NEXT VALUE FOR Console_Seq,'Xbox Serie S')",
                "INSERT INTO "+schema+".CONSOLE (id,CONSOLENAME) VALUES (NEXT VALUE FOR Console_Seq,'PC')",
                "INSERT INTO "+schema+".TYPE (id,TYPENAME) VALUES (NEXT VALUE FOR Type_Seq,'Action')",
                "INSERT INTO "+schema+".TYPE (id,TYPENAME) VALUES (NEXT VALUE FOR Type_Seq,'Adventure')",
                "INSERT INTO "+schema+".TYPE (id,TYPENAME) VALUES (NEXT VALUE FOR Type_Seq,'Multiplayer')",
                "INSERT INTO "+schema+".TYPE (id,TYPENAME) VALUES (NEXT VALUE FOR Type_Seq,'Sci-Fi')",
                "INSERT INTO "+schema+".TYPE (id,TYPENAME) VALUES (NEXT VALUE FOR Type_Seq,'Puzzle')",
                "INSERT INTO "+schema+".GAME (id,DESCRIPTION, DISPONIBLE, PRICE, TITLE, STOREADRESS, CONSOLE_ID) VALUES (NEXT VALUE FOR Game_Seq,'Awesome game 1', 1, 59.99, 'Game 1', 'Store 1', 1)",
                "INSERT INTO "+schema+".GAME (id,DESCRIPTION, DISPONIBLE, PRICE, TITLE, STOREADRESS, CONSOLE_ID) VALUES (NEXT VALUE FOR Game_Seq,'Exciting adventure', 1, 49.99, 'Game 2', 'Store 2', 2)",
                "INSERT INTO "+schema+".GAME (id,DESCRIPTION, DISPONIBLE, PRICE, TITLE, STOREADRESS, CONSOLE_ID) VALUES (NEXT VALUE FOR Game_Seq,'Multiplayer challenge', 0, 29.99, 'Game 3', 'Store 3', 3)",
                "INSERT INTO "+schema+".GAME (id,DESCRIPTION, DISPONIBLE, PRICE, TITLE, STOREADRESS, CONSOLE_ID) VALUES (NEXT VALUE FOR Game_Seq,'Sci-fi experience', 1, 54.99, 'Game 4', 'Store 4', 4)",
                "INSERT INTO "+schema+".GAME (id,DESCRIPTION, DISPONIBLE, PRICE, TITLE, STOREADRESS, CONSOLE_ID) VALUES (NEXT VALUE FOR Game_Seq,'Puzzle-solving fun', 1, 39.99, 'Game 5', 'Store 5', 2)",
                "INSERT INTO "+schema+".CUSTOMER (DNI, NOMBRE, HOMEADRESS, PSWD, TLF) VALUES ('12345678A', 'User 1', 'Address 1', 'password1', '123456789')",
                "INSERT INTO "+schema+".CUSTOMER (DNI, NOMBRE, HOMEADRESS, PSWD, TLF) VALUES ('23456789B', 'User 2', 'Address 2', 'password2', '987654321')",
                "INSERT INTO "+schema+".CUSTOMER (DNI, NOMBRE, HOMEADRESS, PSWD, TLF) VALUES ('34567890C', 'User 3', 'Address 3', 'password3', '567890123')",
                "INSERT INTO "+schema+".CUSTOMER (DNI, NOMBRE, HOMEADRESS, PSWD, TLF) VALUES ('45678901D', 'User 4', 'Address 4', 'password4', '654321098')",
                "INSERT INTO "+schema+".CUSTOMER (DNI, NOMBRE, HOMEADRESS, PSWD, TLF) VALUES ('56789012E', 'User 5', 'Address 5', 'password5', '432109876')",
                "INSERT INTO "+schema+".RENT (id,DAYRENTED, TOTALPRICE, RETURNDATE, CUSTOMER_DNI) VALUES (NEXT VALUE FOR Rent_Seq,TIMESTAMP('2023-10-01 00:00:00'), 49.99, TIMESTAMP('2023-10-10 00:00:00'), '12345678A')",
                "INSERT INTO "+schema+".RENT (id,DAYRENTED, TOTALPRICE, RETURNDATE, CUSTOMER_DNI) VALUES (NEXT VALUE FOR Rent_Seq,TIMESTAMP('2023-10-03 00:00:00'), 39.99, TIMESTAMP('2023-10-12 00:00:00'), '23456789B')",
                "INSERT INTO "+schema+".RENT (id,DAYRENTED, TOTALPRICE, RETURNDATE, CUSTOMER_DNI) VALUES (NEXT VALUE FOR Rent_Seq,TIMESTAMP('2023-10-05 00:00:00'), 59.99, TIMESTAMP('2023-10-15 00:00:00'), '34567890C')",
                "INSERT INTO "+schema+".RENT (id,DAYRENTED, TOTALPRICE, RETURNDATE, CUSTOMER_DNI) VALUES (NEXT VALUE FOR Rent_Seq,TIMESTAMP('2023-10-07 00:00:00'), 29.99, TIMESTAMP('2023-10-17 00:00:00'), '45678901D')",
                "INSERT INTO "+schema+".RENT (id,DAYRENTED, TOTALPRICE, RETURNDATE, CUSTOMER_DNI) VALUES (NEXT VALUE FOR Rent_Seq,TIMESTAMP('2023-10-09 00:00:00'), 54.99, TIMESTAMP('2023-10-19 00:00:00'), '56789012E')",
                "INSERT INTO "+schema+".GAME_TYPE (GAME_ID, TYPE_ID) VALUES (1, 1)",
                "INSERT INTO "+schema+".GAME_TYPE (GAME_ID, TYPE_ID) VALUES (2, 2)",
                "INSERT INTO "+schema+".GAME_TYPE (GAME_ID, TYPE_ID) VALUES (3, 3)",
                "INSERT INTO "+schema+".GAME_TYPE (GAME_ID, TYPE_ID) VALUES (4, 4)",
                "INSERT INTO "+schema+".GAME_TYPE (GAME_ID, TYPE_ID) VALUES (5, 5)",
                "INSERT INTO "+schema+".GAME_RENT (RENTS_ID, GAMES_ID) VALUES (1, 1)",
                "INSERT INTO "+schema+".GAME_RENT (RENTS_ID, GAMES_ID) VALUES (2, 2)",
                "INSERT INTO "+schema+".GAME_RENT (RENTS_ID, GAMES_ID) VALUES (3, 3)",
                "INSERT INTO "+schema+".GAME_RENT (RENTS_ID, GAMES_ID) VALUES (4, 4)",
                "INSERT INTO "+schema+".GAME_RENT (RENTS_ID, GAMES_ID) VALUES (5, 5)"
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
