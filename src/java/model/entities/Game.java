/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.entities;


import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
//import jakarta.validation.constraints.NotNull;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 *
 * @author Cristhian y Arnau
 */

@Entity
@XmlRootElement
public class Game implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Game_Gen")
    @SequenceGenerator(name = "Game_Gen", sequenceName = "Game_Gen", initialValue = 1, allocationSize = 1)
    private int id;
    private String storeAdress;
    private String description;
    private boolean disponible;
    private float price;
    private String title;

    @OneToMany(mappedBy = "game")
    private List<Rent> rents;

    @ManyToOne
    @JoinColumn(name = "console_id")
    private Console console;

    @ManyToMany
    @JoinTable(
        name = "game_type",
        joinColumns = @JoinColumn(name = "game_id"),
        inverseJoinColumns = @JoinColumn(name = "type_id")
    )
    private List<Type> types;

    public Game() {
    }

    public Game(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Console getConsole() {
        return console;
    }

    public void setConsole(Console console) {
        this.console = console;
    }

    public List<Type> getTypes() {
        return types;
    }

    public void setTypes(List<Type> types) {
        this.types = types;
    }

    public String getStoreAdress() {
        return storeAdress;
    }

    public void setStoreAdress(String storeAdress) {
        this.storeAdress = storeAdress;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public List<Rent> getRents() {
        return rents;
    }

    public void setRents(List<Rent> rents) {
        this.rents = rents;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", storeAdress='" + storeAdress + '\'' +
                ", description='" + description + '\'' +
                ", disponible=" + disponible +
                ", price=" + price +
                ", title='" + title + '\'' +
                ", rents=" + rents +
                ", console=" + console +
                ", types=" + types +
                '}';
    }
}