/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.util.Date;

/**
 *
 * @author crist
 */
public class RentDTO {
    private int id;
    private Date returnDate;
    private float totalPrice;

    public RentDTO(int id, Date dayReturn, float totalPrice) {
        this.id = id;
        this.returnDate = dayReturn;
        this.totalPrice = totalPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDayReturn() {
        return returnDate;
    }

    public void setDayReturn(Date dayReturn) {
        this.returnDate = dayReturn;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public RentDTO() {
    }

    @Override
    public String toString() {
        return "RentDTO{" + "id=" + id + ", returnDate=" + returnDate + ", totalPrice=" + totalPrice + '}';
    }
    
    
}
