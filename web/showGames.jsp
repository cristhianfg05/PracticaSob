<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="jakarta.persistence.EntityManagerFactory" %>
<%@ page import="jakarta.persistence.EntityManager" %>
<%@ page import="jakarta.persistence.Persistence" %>
<%@ page import="model.entities.Game" %>
<%@ page import="service.GameService" %>

<%
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("Homework1PU");
    EntityManager em = emf.createEntityManager();
    GameService ent = new GameService();
    List<Game> games = ent.findAll();
%>

<!DOCTYPE html>
<html>
<head>
    <title>Game List</title>
</head>
<body>

<h2>Game List</h2>

<table border="1">
    <thead>
        <tr>
            <th>ID</th>
            <th>Title</th>
            <th>Description</th>
            <th>Price</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="game" items="${games}">
            <tr>
                <td>${game.id}</td>
                <td>${game.title}</td>
                <td>${game.description}</td>
                <td>${game.price}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>

</body>
</html>