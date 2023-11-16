/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

/**
 *
 * @author crist
 */
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import model.entities.Game;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
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
    @Produces(MediaType.APPLICATION_XML)
    public List<Game> findAll() {
        return super.findAll();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    

}
