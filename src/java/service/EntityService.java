/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

/**
 *
 * @author crist
 */
import jakarta.persistence.EntityManager;
import model.entities.Game;
import model.entities.Rent;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


public class EntityService {

    @PersistenceContext(unitName = "Homework1PU")
    protected EntityManager em;

    public EntityService(EntityManager em) {
        this.em = em;
    }

    public void deleteGame(int id) {
        Game g = findGame(id);
        if (g != null) {
            em.remove(g);
        }
    }

    
    
    public Game changePrice(float newPrice, int id) {
        Game g = findGame(id);
        if (g != null) {
            g.setPrice(newPrice);
        }
        return g;
    }

    public Game findGame(int id) {
        return em.find(Game.class, id);
    }

    private boolean isValidConsole(String console) {
        List<String> validConsoles = Arrays.asList(
                "Console1", "Console2", "Console3", "Console4", "Console5", "Console6", "Console7", "Console8", "Console9",
                "Console10", "Console11", "Console12", "Console13", "Console14", "Console15"
        );

        return validConsoles.contains(console);
    }

    private boolean isValidType(String type) {
        List<String> validTypes = Arrays.asList(
                "Type1", "Type2", "Type3", "Type4", "Type5", "Type6", "Type7", "Type8", "Type9"
        );

        return validTypes.contains(type);
    }

    /**
    @Path("game")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Game> getGames(
            @QueryParam("type") String type,
            @QueryParam("console") String console
    ) {
        // Validar que los parámetros sean correctos (opcional)
        if ((type != null && !isValidType(type)) || (console != null && !isValidConsole(console))) {
            throw new BadRequestException("Parámetros incorrectos");
        }

        // Construir la consulta con la cláusula ORDER BY
        StringBuilder queryString = new StringBuilder("SELECT e FROM Game e");

        if (type != null && console != null) {
            queryString.append(" WHERE e.type = :type AND e.console = :console ORDER BY e.name ASC");
        } else if (type != null) {
            queryString.append(" WHERE e.type = :type ORDER BY e.name ASC");
        } else if (console != null) {
            queryString.append(" WHERE e.console = :console ORDER BY e.name ASC");
        } else {
            queryString.append(" ORDER BY e.name ASC");
        }

        // Crear la consulta
        Query query = em.createQuery(queryString.toString());

        if (type != null) {
            query.setParameter("type", type);
        }

        if (console != null) {
            query.setParameter("console", console);
        }

        // Obtener los resultados y responder
        List<Game> games = query.getResultList();
        return games;
    }**/

    public Rent createRent(int id, Date dayRent, Date returnDay, float totalPrice, boolean autenticat) {
        if (autenticat) {
            Rent r = new Rent(id);
            r.setDayRented(dayRent);
            r.setReturnDate(returnDay);
            r.setTotalPrice(totalPrice);
            em.persist(r);
            return r;
        }
        return null;
    }

    public void removeRent(int id) {
        Rent r = findRent(id);
        if (r != null) {
            em.remove(r);
        }
    }

    private Rent findRent(int id) {
        return em.find(Rent.class, id);
    }

}
