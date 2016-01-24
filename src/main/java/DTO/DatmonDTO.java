/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author Administrator
 */
public class DatmonDTO {
    private int diadiemId;
    private int bepId;
    private int menuChitietId;
    private String name;
    private int quantity;
    private int banId;
    private int price;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getDiadiemId() {
        return diadiemId;
    }

    public void setDiadiemId(int diadiemId) {
        this.diadiemId = diadiemId;
    }

    public int getBepId() {
        return bepId;
    }

    public void setBepId(int quanId) {
        this.bepId = quanId;
    }

    
    public int getMenuChitietId() {
        return menuChitietId;
    }

    public void setMenuChitietId(int menuChitietId) {
        this.menuChitietId = menuChitietId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getBanId() {
        return banId;
    }

    public void setBanId(int banId) {
        this.banId = banId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
