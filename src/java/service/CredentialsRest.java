/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import authn.Credentials;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import model.entities.Game;

/**
 *
 * @author crist
 */
@Stateless
@Path("/credentials")
public class CredentialsRest extends AbstractFacade<Credentials>{
    
    @PersistenceContext(unitName = "Homework1PU")
    protected EntityManager em;

    public CredentialsRest() {
        super(Credentials.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findCredentialsUsername(
            @QueryParam("username") String username
    ) {
      try{
        TypedQuery<Credentials> query= (TypedQuery<Credentials>) em.createNamedQuery("Credentials.findUser");
        query.setParameter("username", username);
        return Response.status(Response.Status.OK).entity(query.getSingleResult()).build();
      }catch(Exception e){
          return Response.status(Response.Status.NOT_FOUND).build();
      }
      
    }
    
    
}
