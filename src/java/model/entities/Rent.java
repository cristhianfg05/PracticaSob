/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.entities;

import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotNull;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 *
 * @author crist
 */
@Entity
@XmlRootElement
public class Rent {
    private static final long serialVersion = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Rent_Gen")
    @SequenceGenerator(name = "Rent_Gen", sequenceName = "Rent_Seq", initialValue = 1, allocationSize = 1)
    private int id;
    private Date dayRented;     //Dia que se alquila el juego
    private float totalPrice;   //Precio de este alquiler
    private Date returnDate;    //Dia que tiene que devolver
    
    @ManyToMany
    @JoinTable(
        name = "RENT_GAME",
        joinColumns = @JoinColumn(name = "RENT_ID"),
        inverseJoinColumns = @JoinColumn(name = "GAME_ID")
    )
    private List<Game> games;

    @OneToOne
    @NotNull
    private Customer customer;
    
    @Transient
    private Collection<Integer> gameIds = new ArrayList();

    public Rent() {
    }

    public Rent(int id) {
        this.id = id;
        this.games = new ArrayList();
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

    public List<Game> getGames() {
        return games;
    }

    public void setGame(List<Game> games) {
        this.games = games;
    }

    public Customer getUser() {
        return customer;
    }

    public void setUser(Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Collection<Integer> getGameIds() {
        return gameIds;
    }

    public void setGameIds(Collection<Integer> gameIds) {
        this.gameIds = gameIds;
    }
        
    

    @Override
    public String toString() {
        return "Rent{" + "id=" + id + ", dayRented=" + dayRented + ", totalPrice=" + totalPrice + ", returnDate=" + returnDate + '}';
    }
    
    
}
