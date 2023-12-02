/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import DTO.RentDTO;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import model.entities.Customer;
import model.entities.Game;
import model.entities.Rent;

/**
 *
 * @author crist
 */
@Stateless
@Path("rental")
public class RentService extends AbstractFacade<Rent> {

    @PersistenceContext(unitName = "Homework1PU")
    protected EntityManager em;

    public RentService() {
        super(Rent.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRentById(
            @PathParam("id") int rentId
    ) {
        Rent rent = super.find(rentId);

        if (rent == null) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }

        RentDTO rentDTO = convertToDTO(rent);
        return Response.status(Response.Status.CREATED).entity(rentDTO).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postNewRent(Rent r) {
        Query findIn = em.createNamedQuery("game.findIn").setParameter("ids", r.getGameIds());
        List<Game> gameList = findIn.getResultList();
        if(gameList.size() == 0)
            return Response.status(Response.Status.CONFLICT).entity("La lista de GAMES esta vacia").build();
        r.setGame(gameList);
        r.setCustomer(em.find(Customer.class,r.getCustomerDni()));
        super.create(r);
        return Response.status(Response.Status.CREATED).entity(convertToDTO(r)).build();
    }

    private RentDTO convertToDTO(Rent rent) {
        RentDTO rentDTO = new RentDTO();
        rentDTO.setId(rent.getId());
        rentDTO.setDayReturn(rent.getReturnDate());
        rentDTO.setTotalPrice(rent.getTotalPrice());

        return rentDTO;
    }
}
