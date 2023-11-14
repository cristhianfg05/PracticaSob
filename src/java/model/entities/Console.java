/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 *
 * @author crist
 */
@Entity
@XmlRootElement
public class Console {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CONSOLE_GEN")
    @SequenceGenerator(name = "CONSOLE_GEN", sequenceName = "CONSOLE_GEN", initialValue = 1, allocationSize = 1)
    private int id;

    private String consoleName;

    @OneToMany(mappedBy = "console")
    private List<Game> games;

    public Console() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getConsoleName() {
        return consoleName;
    }

    public void setConsoleName(String consoleName) {
        this.consoleName = consoleName;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    @Override
    public String toString() {
        return "Console{" +
                "id=" + id +
                ", consoleName='" + consoleName + '\'' +
                ", games=" + games +
                '}';
    }
}
