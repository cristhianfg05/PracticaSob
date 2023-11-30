/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.entities;


import jakarta.json.bind.annotation.JsonbTransient;
import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.SequenceGenerator;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 *
 * @author Cristhian y Arnau
 */


@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "game.findByTypeAndConsole", query = "SELECT g FROM Game g WHERE g.type = :type AND g.console = :console"),
    @NamedQuery(name = "game.findByConsole", query = "SELECT g FROM Game g WHERE g.console = :console"),
    @NamedQuery(name = "game.findByType", query = "SELECT g FROM Game g WHERE g.type = :type")
})

public class Game implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Game_Gen")
    @SequenceGenerator(name = "Game_Gen", sequenceName = "Game_Seq", initialValue = 1, allocationSize = 1)
    private int id;
    private String storeAdress;
    private String description;
    private boolean disponible;
    private float price;
    private String title;

    @ManyToMany
    private List<Rent> rents;

    @Enumerated(EnumType.STRING)
    private Console console;
    
    public enum Console{
        GameBoy,
        Nds,
        PS1,
        PS2,
        N64,
        GameCube,
        MegaDrive
    }

    @Enumerated(EnumType.STRING)
    private Type type;
    
    public enum Type{
        Action,
        Adventure,
        Platform,
        Puzzle,
        Horror
    }

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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
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
    
    @JsonbTransient
    public List<Rent> getRents() {
        return rents;
    }

    public void setRents(List<Rent> rents) {
        this.rents = rents;
    }

}