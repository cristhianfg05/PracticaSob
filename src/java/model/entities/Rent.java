/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotNull;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 *
 * @author crist
 */
@Entity
@XmlRootElement
public class Rent {
    private static final long serialVersion = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private Date dayRented;     //Dia que se alquila el juego
    private float totalPrice;   //Precio de este alquiler
    private Date returnDate;    //Dia que tiene que devolver
    
    @ManyToOne
    @NotNull
    private Game game;

    @ManyToOne
    @NotNull
    private Customer customer;

    public Rent() {
    }

    public Rent(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Date getDayRented() {
        return dayRented;
    }

    public void setDayRented(Date dayRented) {
        this.dayRented = dayRented;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Customer getUser() {
        return customer;
    }

    public void setUser(Customer customer) {
        this.customer = customer;
    }
    
    

    @Override
    public String toString() {
        return "Rent{" + "id=" + id + ", dayRented=" + dayRented + ", totalPrice=" + totalPrice + ", returnDate=" + returnDate + '}';
    }
    
    
}
