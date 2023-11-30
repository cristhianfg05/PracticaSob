/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import DTO.RentDTO;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
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
    public RentDTO getRentById(
            @PathParam("id") int rentId
    ) {
        Rent rent = super.find(rentId);

        if (rent == null) {
            return null;
        }

        RentDTO rentDTO = convertToDTO(rent);
        return rentDTO;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Rent postNewRent(Rent r) {
        super.create(r);
        return (r);
    }

    private RentDTO convertToDTO(Rent rent) {
        RentDTO rentDTO = new RentDTO();
        rentDTO.setId(rent.getId());
        rentDTO.setDayReturn(rent.getReturnDate());
        rentDTO.setTotalPrice(rent.getTotalPrice());

        return rentDTO;
    }
}
