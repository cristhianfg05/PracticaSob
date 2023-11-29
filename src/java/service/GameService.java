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
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
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
public List<Game> findGameByTypeConsole(
        @PathParam("type") String type,
        @PathParam("console") String console
) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Game> cq = cb.createQuery(Game.class);
        Root<Game> root = cq.from(Game.class);

        List<Predicate> predicates = new ArrayList<>();

        if (console != null && !console.isEmpty()) {
            Join<Game, Console> consoleJoin = root.join("console");
            predicates.add(cb.equal(consoleJoin.get("consoleName"), console));
        }

        if (type != null && !type.isEmpty()) {
            Join<Game, Type> typeJoin = root.join("types");
            predicates.add(cb.equal(typeJoin.get("typeName"), type));
        }

        cq.where(predicates.toArray(new Predicate[0]));

        return getEntityManager().createQuery(cq).getResultList();
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

}
