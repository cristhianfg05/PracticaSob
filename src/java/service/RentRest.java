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
public class RentRest extends AbstractFacade<Rent> {

    @PersistenceContext(unitName = "Homework1PU")
    protected EntityManager em;

    public RentRest() {
        super(Rent.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    /**
     * GET Rent según el ID proporcionado en la request
     * 
     * @param rentId
     * @return Response NO_CONTENT si el id proporcionado no existe
     * @return Response OK si existe el Rent 
     */
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
        return Response.status(Response.Status.OK).entity(rentDTO).build();
    }

    /**
     * POST nuevo Rent si existen los juegos del body de la request
     * y el customer al que va enlazado
     * 
     * @param r Rent a crear
     * @return Response CONFLICT si la lista de juegos esta vacia
     * @return Response CONFLICT si hay un juego que no existe en el sistema
     * @return Response CONFLICT si el customer al que va el rent no existe
     * @return Response CREATED si el JSON es correcto
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postNewRent(Rent r) {
        Query findIn = em.createNamedQuery("game.findIn").setParameter("ids", r.getGameIds());
        List<Integer> ids = (List<Integer>) r.getGameIds();
        List<Game> gameList = findIn.getResultList();
        if (ids.isEmpty()) {
            return Response.status(Response.Status.CONFLICT).entity("La lista de GAMES esta vacia").build();
        }
        int i = 0;
        while (i < ids.size()) {
            if (em.find(Game.class, ids.get(i)) == null) {
                return Response.status(Response.Status.CONFLICT).entity("Uno o mas juegos no existe en el sistema").build();
            }
            i++;
        }

        r.setGame(gameList);
        if (em.find(Customer.class, r.getCustomerDni()) == null) {
            return Response.status(Response.Status.CONFLICT).entity("El customer no existe en el sistema").build();
        }
        r.setCustomer(em.find(Customer.class, r.getCustomerDni()));
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
