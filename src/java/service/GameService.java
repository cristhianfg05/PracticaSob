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
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Stateless
@Path("game")
public class GameService extends AbstractFacade<Game>{

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
    @Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public void create(Game g) {
        super.create(g);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    

}
