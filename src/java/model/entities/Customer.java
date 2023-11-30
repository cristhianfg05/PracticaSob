/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.entities;

import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToOne;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Cristhian y Arnau
 */

@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Customer.findByDNI", query = "SELECT c FROM Customer c WHERE c.dni = :dni")})
public class Customer implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    private String dni;
    
    private String nombre;
    private String homeAdress;
    private String pswd;
    private String tlf;
    
    @OneToOne(mappedBy = "customer")
    @JoinColumn(name="RENT_ID")
    private Rent rent;

    public Customer() {
    }


    public Customer(String dni) {
        this.dni = dni;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getHomeAdress() {
        return homeAdress;
    }

    public void setHomeAdress(String homeAdress) {
        this.homeAdress = homeAdress;
    }

    public String getTlf() {
        return tlf;
    }

    public void setTlf(String tlf) {
        this.tlf = tlf;
    }

    public Rent getRent() {
        return rent;
    }

    public void setRent(Rent rent) {
        this.rent = rent;
    }

    public String getPswd() {
        return pswd;
    }

    public void setPswd(String pswd) {
        this.pswd = pswd;
    }

    @Override
    public String toString() {
        return "User{" + "dni=" + dni + ", nombre=" + nombre + ", homeAdress=" + homeAdress + ", tlf=" + tlf + ", rents=" + rent + '}';
    }
    
    
}
