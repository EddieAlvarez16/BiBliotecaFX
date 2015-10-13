/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bibliotecafx.models;

import bibliotecafx.helpers.DBHelper;
import bibliotecafx.helpers.Dialogs;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

/**
 *
 * @author Eddi
 */
public class Copy {
    private int Id;
    private String Location;
    private int idBook;

    public Copy(int Id, String Location, int idBook) {
        this.Id = Id;
        this.Location = Location;
        this.idBook = idBook;
    }

    public Copy() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String Location) {
        this.Location = Location;
    }

    public int getIdBook() {
        return idBook;
    }

    public void setIdBook(int idBook) {
        this.idBook = idBook;
    }
    
     public static ObservableList<Copy> getCopiesList(){
        ObservableList<Copy> Copies = FXCollections.observableArrayList();
        
        try{
            Connection con = DBHelper.getConnection();
            String sql = "SELECT * FROM Copy";
            ResultSet rs = con.createStatement().executeQuery(sql);
            while(rs.next()){
                Copy copy = new Copy();
                copy.setId(rs.getInt("id"));
                copy.setLocation(rs.getString("location"));
                copy.setIdBook(rs.getInt("idBook"));
                
                Copies.add(copy);
            }
        }catch(Exception e){
            Alert error = Dialogs.getErrorDialog(Alert.AlertType.ERROR, "BibliotecaFX", null, "Failed to load the list of Copies", e);
            error.showAndWait();
        }
        return Copies;
    }
     
     public static boolean insertCopy(Copy newCopy){
        
        String insertSQL =  "INSERT INTO Copy (location, idBook) "
                + "VALUES (?,?)";
        try{
            PreparedStatement insertStatement = DBHelper.getConnection().prepareStatement(insertSQL);
            
            insertStatement.setString(1, newCopy.getLocation());
            insertStatement.setInt(2, newCopy.getIdBook());
           
            
            insertStatement.executeUpdate();
            
        }catch( SQLException | ClassNotFoundException ex){
            Alert error = Dialogs.getErrorDialog(Alert.AlertType.ERROR, "BibliotecaFX", null, "Error inserted Copy", ex);
            error.showAndWait();
            return false;
        }
        return true;
    }
     
    public static boolean editCopy(Copy newCopy){
        String updateSQL = "UPDATE Copy"
                + " SET location = ?, idBook = ?"
                + " WHERE id = ?";
        
        try{
            PreparedStatement updateStatement = DBHelper.getConnection().prepareStatement(updateSQL);
            
            updateStatement.setString(1, newCopy.getLocation());
            updateStatement.setInt(2, newCopy.getIdBook());
            
            
            updateStatement.executeUpdate();
            
        }catch( SQLException | ClassNotFoundException ex){
            Alert error = Dialogs.getErrorDialog(Alert.AlertType.ERROR, "BibliotecaFX", null, "Error update Copy", ex);
            error.showAndWait();
            return false;
        }
        
        return true;
    }
    
    public static boolean deleteCopy(Copy copy){
        String deleteSQL = "DELETE FROM Copy "
                + "WHERE id = ?";
        try{
            PreparedStatement deleteStatement = DBHelper.getConnection().prepareStatement(deleteSQL);
            deleteStatement.setInt(1, copy.getId());
            
            deleteStatement.executeUpdate();
            
        }catch( SQLException | ClassNotFoundException ex){
            Alert error = Dialogs.getErrorDialog(Alert.AlertType.ERROR, "BibliotecaFX", null, "Error delte Copy", ex);
            error.showAndWait();
            return false;
        }
        return true;
    }
}
