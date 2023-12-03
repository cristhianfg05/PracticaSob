/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import DTO.CustomerDTO;
import authn.Secured;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import model.entities.Customer;

/**
 *
 * @author crist
 */
@Stateless
@Path("/customer")
public class CustomerRest extends AbstractFacade<Customer> {

    @PersistenceContext(unitName = "Homework1PU")
    protected EntityManager em;

    public CustomerRest() {
        super(Customer.class);
    }

    /**
     * GET todos los Rent
     *
     * @return Response NO_CONTENT si no hay customers en la base de datos
     * @return Response OK si tenemos algun customer en la base de datos
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAllDTO() {
        List<Customer> customers = super.findAll();
        List<CustomerDTO> custDTO = new ArrayList<>();

        if (customers.isEmpty()) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }

        for (Customer customer : customers) {
            custDTO.add(convertToDTO(customer));
        }
        return Response.status(Response.Status.OK).entity(custDTO).build();
    }

    /**
     * GET customer unico segun dni
     *
     * @param dni del Customer
     * @return Response NO_CONTENT si no existe el customer
     * @return Response OK si existe el customer
     */
    @GET
    @Path("/{dni}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findCustDNI(
            @PathParam("dni") String dni
    ) {
        Customer c = em.find(Customer.class, dni);
        if (c == null) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        return Response.status(Response.Status.OK).entity(convertToDTO(c)).build();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    private CustomerDTO convertToDTO(Customer customer) {
        CustomerDTO dto = new CustomerDTO();
        dto.setHomeAdress(customer.getHomeAdress());
        dto.setNombre(customer.getNombre());
        dto.setTlf(customer.getTlf());
        return dto;
    }

    /**
     * PUT edit customer segun dni
     *
     * @param dni Customer a modificar
     * @param cNew Nuevos datos
     * @return Response BAD_REQUEST Si el customer a modificar no existe
     * @return Response OK Si el customer a modificar existe
     */
    @PUT
    @Path("/{dni}")
    @Secured
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response edditCustomer(
            @PathParam("dni") String dni,
            Customer cNew
    ) {

        if(comprobarNewCust(cNew))
            return Response.status(Response.Status.BAD_REQUEST).entity("JSON o XML mas construido").build();
        Customer c = em.find(Customer.class, dni);
        if (c == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("El customer no existe").build();
        }

        if (cNew.getHomeAdress() != null) {
            c.setHomeAdress(cNew.getHomeAdress());
        }

        if (cNew.getNombre() != null) {
            c.setNombre(cNew.getNombre());
        }

        if (cNew.getPswd() != null) {
            c.setPswd(cNew.getPswd());
        }

        if (cNew.getTlf() != null) //Se podria añadir un control para que el telefono tenga 9 numeros y sea real
        {
            c.setTlf(cNew.getTlf());
        }

        super.edit(c);
        return Response.status(Response.Status.OK).entity(convertToDTO(c)).build();
    }
    
    private boolean comprobarNewCust(Customer c){
        if(c.getHomeAdress() == null && c.getNombre() == null && c.getPswd() == null && c.getTlf() == null)        
            return true;
        return false;
    }
}
