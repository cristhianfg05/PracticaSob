/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author crist
 */
public class CustomerDTO {
    
    private String nombre;
    private String homeAdress;
    private String tlf;
    private String pswd;

    public CustomerDTO() {
    }

    public CustomerDTO(String nombre, String homeAdress, String tlf, String pswd) {
        this.nombre = nombre;
        this.homeAdress = homeAdress;
        this.tlf = tlf;
        this.pswd = pswd;
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

    public String getPswd() {
        return pswd;
    }

    public void setPswd(String pswd) {
        this.pswd = pswd;
    }
    
    
    
}
