/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

/**
 *
 * @author crist
 */
import authn.Secured;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import model.entities.Game;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import model.entities.Game.Console;
import model.entities.Game.Type;

@Stateless
@Path("game")
public class GameService extends AbstractFacade<Game> {

    @PersistenceContext(unitName = "Homework1PU")
    protected EntityManager em;

    public GameService() {
        super(Game.class);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll(
            @QueryParam("type") String type,
            @QueryParam("console") String console
    ) {
        if (type == null && console != null) {
            if (isValidConsole(console)) {
                TypedQuery<Game> query= (TypedQuery<Game>) em.createNamedQuery("game.findByConsole");
                query.setParameter("console", Console.valueOf(console));
                
                return Response.status(Response.Status.OK).entity(query.getResultList()).build();
            }else{
                return Response.status(Response.Status.BAD_REQUEST).entity("Console no validos").build();
            }
        }

        if (type != null && console == null) {
            if (isValidType(type)) {
                TypedQuery<Game> query = (TypedQuery<Game>) em.createNamedQuery("game.findByType").setParameter("type", Type.valueOf(type));
                return Response.status(Response.Status.OK).entity(query.getResultList()).build();
            }else{
                return Response.status(Response.Status.BAD_REQUEST).entity("Type no validos").build();
            }
        }

        if (type != null && console != null) {
            if (isValidConsole(console) && isValidType(type)) {
                TypedQuery<Game> query = (TypedQuery<Game>) em.createNamedQuery("game.findByTypeAndConsole").setParameter("type", Type.valueOf(type));
                query.setParameter("console", Console.valueOf(console));
                return Response.status(Response.Status.OK).entity(query.getResultList()).build();
            }else{
                return Response.status(Response.Status.BAD_REQUEST).entity("Type o console no validos").build();
            }
        }

        return Response.status(Response.Status.OK).entity(super.findAll()).build();
    }

    @POST
    @Override
    @Secured
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response create(Game g) {
        if (g == null) {
            return Response.status(Response.Status.NO_CONTENT).entity("No hay un json hecho").build();
        } else if (checkCorrectGame(g)) {
            System.out.print("NO CREADO ID:" + g.getId());
            return Response.status(Response.Status.NO_CONTENT).entity("No hay un json hecho").build();
        } else if (checkRepeatedGame(g, super.findAll())) {
            return Response.status(Response.Status.CONFLICT).entity("Juego Repetido").build();
        } else {
            super.create(g);
            return Response.status(Response.Status.CREATED).entity("Nuevo objeto Creado correctamente").build();
        }

    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    private boolean checkCorrectGame(Game g) {
        if (g.getConsole() == null || g.getDescription() == null || g.getPrice() == 0.0 || g.getStoreAdress() == null || g.getTitle() == null || g.getType() == null) {
            return true;
        } else {
            return false;
        }
    }

    private boolean checkRepeatedGame(Game g, List<Game> listG) {
        boolean repeated = false;
        int i = 0;
        while (!repeated && i < listG.size()) {
            repeated = g.equalGame(listG.get(i));
            i++;
        }
        return repeated;
    }

    private boolean isValidConsole(String consoleValue) {
        try {
            Console.valueOf(consoleValue);
            return true; // El valor es válido
        } catch (IllegalArgumentException e) {
            return false; // El valor no es válido
        }
    }

    private boolean isValidType(String typeValue) {
        try {
            Type.valueOf(typeValue);
            return true; // El valor es válido
        } catch (IllegalArgumentException e) {
            return false; // El valor no es válido
        }
    }
}
