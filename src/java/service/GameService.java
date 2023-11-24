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
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;


@Stateless
@Path("game")
public class GameService extends AbstractFacade<Game> {

    @PersistenceContext(unitName = "Homework1PU")
    protected EntityManager em;

    public GameService() {
        super(Game.class);
    }

    
    @GET
    @Override
    @Produces(MediaType.APPLICATION_JSON)
    public List<Game> findAll() {
        return super.findAll();
    }

    @POST
    @Override
    @Secured
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response create(Game g) {
        return super.create(g);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    /**
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Game> findAll1(
            @QueryParam("type") String typeName,
            @QueryParam("console") String consoleName) {

        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Game> cq = cb.createQuery(Game.class);
        Root<Game> gameRoot = cq.from(Game.class);
        Join<Game, Console> consoleJoin = gameRoot.join("console", JoinType.INNER);
        Join<Game, Type> typeJoin = gameRoot.join("types", JoinType.INNER);

        cq.select(gameRoot);

        // Añadir condiciones para la consola y el tipo si se proporcionan
        if (consoleName != null && !consoleName.isEmpty()) {
            cq.where(cb.equal(consoleJoin.get("consoleName"), consoleName));
        }

        if (typeName != null && !typeName.isEmpty()) {
            cq.where(cb.equal(typeJoin.get("typeName"), typeName));
        }

        return getEntityManager().createQuery(cq).getResultList();
    }**/

}
