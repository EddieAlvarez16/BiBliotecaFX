/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bibliotecafx.controllers;

import bibliotecafx.Mainapp;
import bibliotecafx.models.Author;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Eddi
 */
public class AutorController implements Initializable {

    
    @FXML
    private TableView<Author> tbvAuthors;
    @FXML
    private TableColumn<Author, String> tbcId;
    @FXML
    private TableColumn<Author, String> tbcName;
    
    private Mainapp mainApp;
    
    public void setMainApp(Mainapp mainApp) {
        this.mainApp = mainApp;
        tbvAuthors.setItems(mainApp.getAuthorsList());
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        tbcId.setCellValueFactory(new PropertyValueFactory<Author, String>("id"));
        tbcName.setCellValueFactory(new PropertyValueFactory<Author, String>("name"));
        
        tbvAuthors.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        tbvAuthors.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Author>() {        
            @Override
            public void changed(ObservableValue <? extends Author> observable,
                Author oldValue, Author newValue) {
            }
        });
    }    
    
}
