<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="model.entities.Game" %>

<%@ page import="javax.ws.rs.client.Client" %>
<%@ page import="javax.ws.rs.client.ClientBuilder" %>
<%@ page import="javax.ws.rs.core.GenericType" %>
<%@ page import="javax.ws.rs.core.MediaType" %>

<%
    // Llamada al servicio web para obtener la lista de juegos
    Client client = ClientBuilder.newClient();
    List<Game> games = client
            .target("http://localhost:8080/your-application-name/rest/game")
            .queryParam("type", request.getParameter("type"))
            .queryParam("console", request.getParameter("console"))
            .request(MediaType.APPLICATION_JSON)
            .get(new GenericType<List<Game>>() {});

    // Cerrar el cliente
    client.close();
%>

<!DOCTYPE html>
<html>
<head>
    <title>Lista de Juegos</title>
</head>
<body>
    <h2>Lista de Juegos</h2>

    <% if (games.isEmpty()) { %>
        <p>No hay juegos disponibles.</p>
    <% } else { %>
        <ul>
            <% for (Game game : games) { %>
                <li><%= game.getName() %></li>
            <% } %>
        </ul>
    <% } %>
</body>
</html>
