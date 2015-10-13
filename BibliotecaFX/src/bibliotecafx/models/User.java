/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bibliotecafx.models;

/**
 *
 * @author Eddi
 */
public class User {
    private int Id;
    private String Name;
    private String Phone;
    private String Address;

    public User(int Id, String Name, String Phone, String Address) {
        this.Id = Id;
        this.Name = Name;
        this.Phone = Phone;
        this.Address = Address;
    }

    public User() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }
    
    
}
