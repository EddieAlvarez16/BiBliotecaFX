/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bibliotecafx.controllers;

import bibliotecafx.Mainapp;
import bibliotecafx.models.Book;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
}
