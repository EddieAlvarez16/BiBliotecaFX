/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bibliotecafx;

import bibliotecafx.controllers.AutorController;
import bibliotecafx.controllers.BookController;
import bibliotecafx.controllers.LayoutController;
import bibliotecafx.helpers.Dialogs;
import bibliotecafx.models.Author;
import bibliotecafx.models.Book;
import java.io.IOException;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Eddi
 */
public class Mainapp extends Application {
    
    private Stage primaryStage;
    private BorderPane Layout;
    private ObservableList<Author> autorList = FXCollections.observableArrayList();
    private ObservableList<Book> booksList = FXCollections.observableArrayList();
    public enum CrudOperation{None, Create, Read, Update, Delete}
    
    public Mainapp(){
        
    }
    @Override
    public void start(Stage primaryStage) {
      this.primaryStage = primaryStage;
      this.primaryStage.setTitle("BibliotecaFX");
      try{
          FXMLLoader loader = new FXMLLoader();
          loader.setLocation(Mainapp.class.getResource("views/Layout.fxml"));
          Layout = (BorderPane) loader.load();
          LayoutController controller = loader.getController();
          controller.setMainApp(this);
          Scene scene = new Scene(Layout);
          primaryStage.setScene(scene);
          primaryStage.show();
      }catch(IOException e){
          Alert error = Dialogs.getErrorDialog(Alert.AlertType.ERROR, "BibliotecaFx", null, "Error loading the FXML file", e);
          error.showAndWait();
          e.printStackTrace();
      }
      this.autorList = Author.getAuthorList();
      this.booksList = Book.getBookList();
    }
    
    private void showViewAuthors(){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Mainapp.class.getResource("views/Autor.fxml"));
            AnchorPane authorsPane = (AnchorPane) loader.load();
            AutorController controller = loader.getController();
            controller.setMainApp(this);
            Layout.setCenter(authorsPane);
        }catch(Exception e){
           Alert error = Dialogs.getErrorDialog(Alert.AlertType.ERROR, "BibliotecaFx", null, "Error loading the FXML file", e);
          error.showAndWait();
          e.printStackTrace();
        }
    }
    
    private void showViewBooks(){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Mainapp.class.getResource("views/Book.fxml"));
            AnchorPane booksPane = (AnchorPane) loader.load();
            BookController controller = loader.getController();
            controller.setMainApp(this);
            Layout.setCenter(booksPane);
        }catch(Exception e){
           Alert error = Dialogs.getErrorDialog(Alert.AlertType.ERROR, "BibliotecaFx", null, "Error loading the FXML file", e);
          error.showAndWait();
          e.printStackTrace();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    public ObservableList<Author> getAuthorsList() {
        return autorList;
    }

    public ObservableList<Book> getBooksList() {
        return booksList;
    }
    
    
}
