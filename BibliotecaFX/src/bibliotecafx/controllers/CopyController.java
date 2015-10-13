/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bibliotecafx.controllers;

import bibliotecafx.Mainapp;
import bibliotecafx.helpers.Dialogs;
import bibliotecafx.models.Copy;
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
    
    @FXML
    private void ClikedMain(){
        mainApp.showViewMenu();
    }
    
    @FXML
    private void addCopy(){
        Copy copyTemp = new Copy();
        boolean pressAdd = mainApp.ShowDialogEditCopy(copyTemp, Mainapp.CrudOperation.Create);
        if(pressAdd){
            mainApp.getCopiesList().add(copyTemp);
        }
    }
    
    @FXML
    private void editCopy() {
        Copy selectCopy = tbvCopy.getSelectionModel().getSelectedItem();
        if (selectCopy != null) {
            boolean okClicked = mainApp.ShowDialogEditCopy(selectCopy, Mainapp.CrudOperation.Update);
            if (okClicked) {
                Refresh();
            }

        } else {
            Alert error = Dialogs.getDialog(AlertType.ERROR, "BibliotecaFX", null, "You did not select any field");
            error.showAndWait();
        }
    }
    
    @FXML
    private void deleteCopy() {
        int selectedIndex = tbvCopy.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Copy deleteAuthor = tbvCopy.getSelectionModel().getSelectedItem();
            Alert pregunta = Dialogs.getDialog(AlertType.CONFIRMATION, "BibliotecaFX", null, "You want to delete this Copy");
            Optional<ButtonType> result = pregunta.showAndWait();
            if (result.get() == ButtonType.OK){
                if(Copy.deleteCopy(deleteAuthor)){
                    tbvCopy.getItems().remove(selectedIndex);
                }
            }
        } else {
            Alert error = Dialogs.getDialog(AlertType.ERROR, "BibliotecaFX", null, "You did not select any field");
            error.showAndWait();
        }
    }
    
   
    private void Refresh(){
        int selectedIndex = tbvCopy.getSelectionModel().getSelectedIndex();
        tbvCopy.setItems(null);
        tbvCopy.layout();
        tbvCopy.setItems(mainApp.getCopiesList());
        tbvCopy.getSelectionModel().select(selectedIndex);
    }
}
