/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bibliotecafx.controllers;

import bibliotecafx.Mainapp;
import bibliotecafx.Mainapp.CrudOperation;
import bibliotecafx.helpers.Dialogs;
import bibliotecafx.models.Copy;
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
public class DialogEditCopyController implements Initializable {
    
    @FXML
    private TextField txtLocation;
    @FXML
    private TextField txtidBook;
    
    private Stage dialogStage;
    private Copy copy;
    private boolean ClikedOk;
    private Mainapp.CrudOperation operation;
    
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setOperation(Mainapp.CrudOperation operation) {
        this.operation = operation;
    }
    
    
    public void setCopy(Copy copy) {
        this.copy = copy;
        
        txtLocation.setText(copy.getLocation());
        txtidBook.setText(String.valueOf(copy.getId()));
    }
    
    public boolean ClikedOk(){
        return this.ClikedOk;
    }
    
    @FXML
    private void addAccept(){
        if(isCopyValied()){
            copy.setLocation(txtLocation.getText());
            copy.setIdBook(Integer.parseInt(txtidBook.getText()));
            if (operation.equals(CrudOperation.Create)){
                ClikedOk = Copy.insertCopy(copy);
            }
            if (operation.equals(CrudOperation.Update)){
                ClikedOk = Copy.editCopy(copy);
            }
            dialogStage.close();
        }
    }
    
    @FXML
    private void addCancel(){
        dialogStage.close();
    }
    
    private boolean isCopyValied(){
        if(txtLocation.getText() == null || txtLocation.getText().length() == 0){
            Alert error = Dialogs.getDialog(Alert.AlertType.ERROR, "BibliotecaFX", null, "Invalid Location, "
                    + "Please enter a value!");
            error.showAndWait();
            txtLocation.requestFocus();
            return false;
        }
        if(txtidBook.getText() == null || txtidBook.getText().length() == 0){
            Alert error = Dialogs.getDialog(Alert.AlertType.ERROR, "BibliotecaFX", null, "Invalid id, "
                    + "Please enter a value!");
            error.showAndWait();
            txtidBook.requestFocus();
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
