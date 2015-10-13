/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bibliotecafx.controllers;

import bibliotecafx.Mainapp;
import bibliotecafx.models.Copy;
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
public class CopyController implements Initializable {
    
    @FXML
    private TableView<Copy> tbvCopy;
    @FXML
    private TableColumn<Copy, String> tbcId;
    @FXML
    private TableColumn<Copy, String> tbcLocation;
    @FXML
    private TableColumn<Copy, String> tbcidBook;
    
    private Mainapp mainApp;
    
     public void setMainApp(Mainapp mainApp) {
        this.mainApp = mainApp;
        tbvCopy.setItems(mainApp.getCopiesList());
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        tbcId.setCellValueFactory(new PropertyValueFactory<Copy, String>("id"));
        tbcLocation.setCellValueFactory(new PropertyValueFactory<Copy, String>("location"));
        tbcidBook.setCellValueFactory(new PropertyValueFactory<Copy, String>("idBook"));
        
        tbvCopy.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        tbvCopy.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Copy>() {        
            @Override
            public void changed(ObservableValue <? extends Copy> observable,
                Copy oldValue, Copy newValue) {
            }
        });
    }    
    
}
