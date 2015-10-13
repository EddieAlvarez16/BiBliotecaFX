/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bibliotecafx.controllers;

import bibliotecafx.Mainapp;
import bibliotecafx.Mainapp.CrudOperation;
import bibliotecafx.helpers.Dialogs;
import bibliotecafx.models.Book;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Eddi
 */
public class DialogEditBookController implements Initializable {
    
    @FXML
    private TextField txtTitle;
    @FXML
    private TextField txtIsbn;
    @FXML
    private TextField txtEditorial;
    @FXML
    private TextField txtPages;
    
    private Stage dialogStage;
    private Book book;
    private boolean ClikedOk;
    private Mainapp.CrudOperation operation;
    
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setOperation(Mainapp.CrudOperation operation) {
        this.operation = operation;
    }
    
    public void setBook(Book book) {
        this.book = book;
        
        txtTitle.setText(book.getTitle());
        txtIsbn.setText(book.getIsbn());
        txtEditorial.setText(book.getEditorial());
        txtPages.setText(String.valueOf(book.getPages()));
    }
    
    public boolean ClikedOk(){
        return this.ClikedOk;
    }
    
    @FXML
    private void addAccept(){
        if(isBookValid()){
            book.setTitle(txtTitle.getText());
            book.setIsbn(txtIsbn.getText());
            book.setEditorial(txtEditorial.getText());
            book.setPages(Integer.parseInt(txtPages.getText()));
            if (operation.equals(CrudOperation.Create)){
                ClikedOk = Book.insertBook(book);
            }
            if (operation.equals(CrudOperation.Update)){
                ClikedOk = Book.editBook(book);
            }
            dialogStage.close();
        }
    }
    
    @FXML
    private void addCancel(){
        dialogStage.close();
    }
    
     private boolean isBookValid(){
        if(txtTitle.getText() == null || txtTitle.getText().length() == 0){
            Alert error = Dialogs.getDialog(Alert.AlertType.ERROR, "BibliotecaFX", null, "Invalid Title, "
                    + "Please enter a value!");
            error.showAndWait();
            txtTitle.requestFocus();
            return false;
        }
        if(txtIsbn.getText() == null || txtIsbn.getText().length() == 0){
            Alert error = Dialogs.getDialog(Alert.AlertType.ERROR, "BibliotecaFX", null, "Invalid ISBN, "
                    + "Please enter a value!");
            error.showAndWait();
            txtIsbn.requestFocus();
            return false;
        }
        if(txtEditorial.getText() == null || txtEditorial.getText().length() == 0){
            Alert error = Dialogs.getDialog(Alert.AlertType.ERROR, "BibliotecaFX", null, "Invalid Editorial, "
                    + "Please enter a value!");
            error.showAndWait();
            txtEditorial.requestFocus();
            return false;
        }
        if(txtPages.getText() == null || txtPages.getText().length() == 0){
            Alert error = Dialogs.getDialog(Alert.AlertType.ERROR, "BibliotecaFX", null, "Invalid Pages, "
                    + "Please enter a value!");
            error.showAndWait();
            txtPages.requestFocus();
            return false;
        }
        return true;
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
