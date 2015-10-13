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
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Eddi
 */
public class BookController implements Initializable {

    @FXML
    private TableView<Book> tbvBooks;
    @FXML
    private TableColumn<Book, String> tbcId;
    @FXML
    private TableColumn<Book, String> tbcTitle;
    @FXML
    private Label lblIsbn;
    @FXML
    private Label lblEditorial;
    @FXML
    private Label lblPages;
    
    private Mainapp mainApp;
    
    public void setMainApp(Mainapp mainApp) {
        this.mainApp = mainApp;
        tbvBooks.setItems(mainApp.getBooksList());
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tbcId.setCellValueFactory(new PropertyValueFactory<Book, String>("id"));
        tbcTitle.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
        
        tbvBooks.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        showDetails(null);
        
        tbvBooks.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Book>() {        
            @Override
            public void changed(ObservableValue <? extends Book> observable,
                Book oldValue, Book newValue) {
                showDetails(newValue);
            }
        });
    }    
    
    
    private void showDetails(Book book){
        if(book == null){
            lblIsbn.setText("");
            lblEditorial.setText("");
            lblPages.setText("");
        }else{
            lblIsbn.setText(book.getIsbn());
            lblEditorial.setText(book.getEditorial());
            lblPages.setText(String.valueOf(book.getPages()));
        }
    }
    
    @FXML
    private void ClikedMain(){
        mainApp.showViewMenu();
    }
    
    @FXML
    private void addBook(){
        Book bookTemp = new Book();
        boolean pressAdd = mainApp.ShowDialogEditBook(bookTemp, CrudOperation.Create);
        if (pressAdd){
            mainApp.getBooksList().add(bookTemp);
        }
    }
    
    @FXML
    private void editBook() {
        Book selectBook = tbvBooks.getSelectionModel().getSelectedItem();
        if (selectBook != null) {
            boolean okClicked = mainApp.ShowDialogEditBook(selectBook, Mainapp.CrudOperation.Update);
            if (okClicked) {
                Refresh();
            }

        } else {
            Alert error = Dialogs.getDialog(AlertType.ERROR, "BibliotecaFX", null, "You did not select any field");
            error.showAndWait();
        }
    }
    
    
    @FXML
    private void deleteBook() {
        int selectedIndex = tbvBooks.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Book deleteBook = tbvBooks.getSelectionModel().getSelectedItem();
            Alert pregunta = Dialogs.getDialog(AlertType.CONFIRMATION, "BibliotecaFX", null, "You want to delete this author");
            Optional<ButtonType> result = pregunta.showAndWait();
            if (result.get() == ButtonType.OK){
                if(Book.deleteBook(deleteBook)){
                    tbvBooks.getItems().remove(selectedIndex);
                }
            }
        } else {
            Alert error = Dialogs.getDialog(AlertType.ERROR, "BibliotecaFX", null, "You did not select any field");
            error.showAndWait();
        }
    }
    
    
    
    
    
    
    private void Refresh(){
        int selectedIndex = tbvBooks.getSelectionModel().getSelectedIndex();
        tbvBooks.setItems(null);
        tbvBooks.layout();
        tbvBooks.setItems(mainApp.getBooksList());
        tbvBooks.getSelectionModel().select(selectedIndex);
    }
}
