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
public class Author {
    private int Id;
    private String Name;

    public Author(int Id, String Name) {
        this.Id = Id;
        this.Name = Name;
    }

    public Author() {
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
    
    public static ObservableList<Author> getAuthorList(){
        ObservableList<Author> authors = FXCollections.observableArrayList();
        
        try{
            Connection con = DBHelper.getConnection();
            String sql = "SELECT * FROM Author";
            ResultSet rs = con.createStatement().executeQuery(sql);
            while(rs.next()){
                Author author = new Author();
                author.setId(rs.getInt("id"));
                author.setName(rs.getString("name"));
                
                authors.add(author);
            }
        }catch(Exception e){
            Alert error = Dialogs.getErrorDialog(Alert.AlertType.ERROR, "BibliotecaFX", null, "Failed to load the list of authors", e);
            error.showAndWait();
        }
        return authors;
    }
    
    public static boolean insertAuthor(Author newAuthor){
        
        String insertSQL =  "INSERT INTO Author (name) "
                + "VALUES (?)";
        try{
            PreparedStatement insertStatement = DBHelper.getConnection().prepareStatement(insertSQL);
            
            insertStatement.setString(1, newAuthor.getName());
            
            insertStatement.executeUpdate();
            
        }catch( SQLException | ClassNotFoundException ex){
            Alert error = Dialogs.getErrorDialog(Alert.AlertType.ERROR, "BibliotecaFX", null, "Error inserted author", ex);
            error.showAndWait();
            return false;
        }
        return true;
    }
    
    public static boolean editAuhor(Author newAuthor){
        String updateSQL = "UPDATE Author"
                + " SET name = ?"
                + " WHERE id ";
        
        try{
            PreparedStatement updateStatement = DBHelper.getConnection().prepareStatement(updateSQL);
            
            updateStatement.setString(1, newAuthor.getName());
            updateStatement.setInt(2, newAuthor.getId());
            
            updateStatement.executeUpdate();
            
        }catch( SQLException | ClassNotFoundException ex){
            Alert error = Dialogs.getErrorDialog(Alert.AlertType.ERROR, "BibliotecaFX", null, "Error update Author", ex);
            error.showAndWait();
            return false;
        }
        
        return true;
    }
    
     public static boolean deleteAuthor(Author author){
        String deleteSQL = "DELETE FROM Author "
                + "WHERE id = ?";
        try{
            PreparedStatement deleteStatement = DBHelper.getConnection().prepareStatement(deleteSQL);
            deleteStatement.setInt(1, author.getId());
            
            deleteStatement.executeUpdate();
            
        }catch( SQLException | ClassNotFoundException ex){
            Alert error = Dialogs.getErrorDialog(Alert.AlertType.ERROR, "BibliotecaFX", null, "Error delte Author", ex);
            error.showAndWait();
            return false;
        }
        return true;
    }
}
