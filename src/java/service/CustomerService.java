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
public class CustomerService extends AbstractFacade<Customer> {

    @PersistenceContext(unitName = "Homework1PU")
    protected EntityManager em;

    public CustomerService() {
        super(Customer.class);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAllDTO() {
        List<Customer> customers = super.findAll();
        List<CustomerDTO> custDTO = new ArrayList<>();

        for (Customer customer : customers) {
            custDTO.add(convertToDTO(customer));
        }
        return Response.status(Response.Status.OK).entity(custDTO).build();
    }

    @GET
    @Path("/{dni}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findCustDNI(
            @PathParam("dni") String dni
    ) {
        TypedQuery<Customer> query = (TypedQuery<Customer>) em.createNamedQuery("Customer.findByDNI");
        query.setParameter("dni", dni);
        Customer c;
        try {
            c = (Customer) query.getSingleResult();
        } catch (Exception e) {
            return null;
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

    @PUT
    @Path("/{dni}")
    @Secured
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response edditCustomer(
            @PathParam("dni") String dni,
            Customer cNew
    ) {
        TypedQuery<Customer> query = (TypedQuery<Customer>) em.createNamedQuery("Customer.findByDNI");
        query.setParameter("dni", dni);
        Customer c;
        try {
            c = (Customer) query.getSingleResult();
        } catch (Exception e) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        c.setHomeAdress(cNew.getHomeAdress());
        c.setNombre(cNew.getNombre());
        c.setPswd(cNew.getPswd());
        c.setTlf(cNew.getTlf());
        super.edit(c);
        return Response.status(Response.Status.OK).entity(convertToDTO(c)).build();
    }

}
