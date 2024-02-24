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
     * @return Response BAD_REQUEST si el id proporcionado no existe
     * @return Response BAD_REQUEST si el id proporcionado no es numerico
     * @return Response OK si existe el Rent
     */
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRentById(
            @PathParam("id") String rentId
    ) {
        Rent rent = null;
        if (rentId.matches("\\d")) {
            rent = super.find(Integer.parseInt(rentId));
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("El id es incorrecto").build();
        }

        if (rent == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("El rent no existe").build();
        }

        RentDTO rentDTO = convertToDTO(rent);
        return Response.status(Response.Status.OK).entity(rentDTO).build();
    }

    /**
     * POST nuevo Rent si existen los juegos del body de la request y el
     * customer al que va enlazado
     *
     * @param r Rent a crear
     * @return Response CONFLICT si la lista de juegos esta vacia
     * @return Response CONFLICT si hay un juego que no existe en el sistema
     * @return Response CONFLICT si el customer al que va el rent no existe
     * @return Response CONFLICT si el customer ya tiene un rent activo
     * @return Response CREATED si el JSON es correcto
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postNewRent(Rent r) {
        if (r.getGameIds().isEmpty()) {
            return Response.status(Response.Status.CONFLICT).entity("La lista de GAMES esta vacia").build();
        }
        Query findIn = em.createNamedQuery("game.findIn").setParameter("ids", r.getGameIds());
        List<Integer> ids = (List<Integer>) r.getGameIds();
        List<Game> gameList = findIn.getResultList();
        System.out.print("Estoy en la API"+ids+" dni"+r.getCustomerDni());
        int i = 0;
        while (i < ids.size()) {
            if (em.find(Game.class, ids.get(i)) == null) {
                return Response.status(Response.Status.CONFLICT).entity("Uno o mas juegos no existe en el sistema").build();
            }
            i++;
        }

        if (juegosDisponibles(gameList)) {
            return Response.status(Response.Status.CONFLICT).entity("Uno o mas juegos no estan disponibles").build();
        }
        r.setGame(gameList);
        System.out.print("PASO LA LISTA DE JUEGO");
        if (em.find(Customer.class, r.getCustomerDni()) == null) {
            return Response.status(Response.Status.CONFLICT).entity("El customer no existe en el sistema").build();
        }
        System.out.print("PASO LA COMRPOBACIÓN DE CUSTOMER");
        if (comprobarRentsCust(r.getCustomerDni())) {
            return Response.status(Response.Status.CONFLICT).entity("El customer ya tiene un Rent asignado").build();
        }
        System.out.print("HAGO SET DEL CUSTOMER Y LO CREO");
        r.setCustomer(em.find(Customer.class, r.getCustomerDni()));
        super.create(r);
        return Response.status(Response.Status.CREATED).entity(convertToDTO(r)).build();
    }

    /**
     * GET Rent según el DNI del Customer proporcionado en la request
     *
     * @param dni DNI del Customer
     * @return Response BAD_REQUEST si el DNI proporcionado no existe o si el
     * Customer no tiene Rent
     * @return Response OK con el Rent si existe
     */
    @GET
    @Path("/customer/{dni}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRentByCustomerDni(
            @PathParam("dni") String dni
    ) {
        Customer customer = em.find(Customer.class, dni);

        if (customer == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("El DNI no existe").build();
        }

        Rent rent = em.createNamedQuery("rent.findByCustomerDni", Rent.class)
                .setParameter("customerDni", dni)
                .getSingleResult();

        if (rent == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("El Customer no tiene Rent asociado").build();
        }

        RentDTO rentDTO = convertToDTO(rent);

        return Response.status(Response.Status.OK).entity(rentDTO).build();
    }

    private RentDTO convertToDTO(Rent rent) {
        RentDTO rentDTO = new RentDTO();
        rentDTO.setId(rent.getId());
        rentDTO.setDayReturn(rent.getReturnDate());
        rentDTO.setTotalPrice(rent.getTotalPrice());

        return rentDTO;
    }

    private boolean comprobarRentsCust(String dni) {
        List<Rent> rents = super.findAll();
        for (Rent r : rents) {
            if (r.getCustomer().getDni().equals(dni)) {
                return true;
            }
        }
        return false;
    }

    private boolean juegosDisponibles(List<Game> gameList) {
        for (Game g : gameList) {
            if (!g.isDisponible()) {
                return true;
            }
        }
        return false;
    }
}
