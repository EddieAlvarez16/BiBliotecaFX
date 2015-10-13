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
public class Book {
    private int Id;
    private String Title;
    private String Isbn;
    private String Editorial;
    private int Pages;

    public Book(int Id, String Title, String Isbn, String Editorial, int Pages) {
        this.Id = Id;
        this.Title = Title;
        this.Isbn = Isbn;
        this.Editorial = Editorial;
        this.Pages = Pages;
    }

    public Book() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getIsbn() {
        return Isbn;
    }

    public void setIsbn(String Isbn) {
        this.Isbn = Isbn;
    }

    public String getEditorial() {
        return Editorial;
    }

    public void setEditorial(String Editorial) {
        this.Editorial = Editorial;
    }

    public int getPages() {
        return Pages;
    }

    public void setPages(int Pages) {
        this.Pages = Pages;
    }
    
    public static ObservableList<Book> getBookList(){
        ObservableList<Book> books = FXCollections.observableArrayList();
        
        try{
            Connection con = DBHelper.getConnection();
            String sql = "SELECT * FROM Book";
            ResultSet rs = con.createStatement().executeQuery(sql);
            while(rs.next()){
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setTitle(rs.getString("title"));
                book.setIsbn(rs.getString("isbn"));
                book.setEditorial(rs.getString("editorial"));
                book.setPages(rs.getInt("pages"));
                
                books.add(book);
            }
        }catch(Exception e){
            Alert error = Dialogs.getErrorDialog(Alert.AlertType.ERROR, "BibliotecaFX", null, "Failed to load the list of books", e);
            error.showAndWait();
        }
        return books;
    }
    
    public static boolean insertBook(Book newBook){
        
        String insertSQL =  "INSERT INTO Book (title, isbn, editorial, pages) "
                + "VALUES (?,?,?,?)";
        try{
            PreparedStatement insertStatement = DBHelper.getConnection().prepareStatement(insertSQL);
            
            insertStatement.setString(1, newBook.getTitle());
            insertStatement.setString(2, newBook.getIsbn());
            insertStatement.setString(3, newBook.getEditorial());
            insertStatement.setInt(4, newBook.getPages());
            
            insertStatement.executeUpdate();
            
        }catch( SQLException | ClassNotFoundException ex){
            Alert error = Dialogs.getErrorDialog(Alert.AlertType.ERROR, "BibliotecaFX", null, "Error inserted Book", ex);
            error.showAndWait();
            return false;
        }
        return true;
    }
    
    public static boolean editBook(Book newBook){
        String updateSQL = "UPDATE Book"
                + " SET title = ?, isbn = ?, editorial = ?, pages = ?"
                + " WHERE id = ?";
        
        try{
            PreparedStatement updateStatement = DBHelper.getConnection().prepareStatement(updateSQL);
            
            updateStatement.setString(1, newBook.getTitle());
            updateStatement.setString(2, newBook.getIsbn());
            updateStatement.setString(3, newBook.getEditorial());
            updateStatement.setInt(4, newBook.getPages());
            updateStatement.setInt(5, newBook.getId());
            
            
            updateStatement.executeUpdate();
            
        }catch( SQLException | ClassNotFoundException ex){
            Alert error = Dialogs.getErrorDialog(Alert.AlertType.ERROR, "BibliotecaFX", null, "Error update Book", ex);
            error.showAndWait();
            return false;
        }
        
        return true;
    }
    
    public static boolean deleteBook(Book book){
        String deleteSQL = "DELETE FROM Book "
                + "WHERE id = ?";
        try{
            PreparedStatement deleteStatement = DBHelper.getConnection().prepareStatement(deleteSQL);
            deleteStatement.setInt(1, book.getId());
            
            deleteStatement.executeUpdate();
            
        }catch( SQLException | ClassNotFoundException ex){
            Alert error = Dialogs.getErrorDialog(Alert.AlertType.ERROR, "BibliotecaFX", null, "Error delte Book", ex);
            error.showAndWait();
            return false;
        }
        return true;
    }
}
