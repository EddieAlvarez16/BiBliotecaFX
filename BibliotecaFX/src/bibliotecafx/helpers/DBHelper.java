/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bibliotecafx.helpers;

import bibliotecafx.Mainapp;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Eddi
 */
public class DBHelper {
    private static Mainapp mainApp;
    private Connection connection;
    private static DBHelper instance;
    
    private static final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static final String DB_SERVER = "localhost:1433";
    private static final String DB_INSTANCE = "SQLEXPRESS";
    private static final String DB_NAME = "BibliotecaFX";
    
    public static void setMainapp(Mainapp mainApp){
        DBHelper.mainApp = mainApp;
    }
    
    public DBHelper() throws ClassNotFoundException, SQLException{
        Class.forName(DRIVER);
        connection = DriverManager.getConnection("jdbc:sqlserver://" + DB_SERVER + ";instanceName=" + DB_INSTANCE +";"
                + "databaseName=" + DB_NAME +";user=sa" + ";"
            + "password= sa");
    }
    public static Connection getConnection() throws ClassNotFoundException, SQLException{
        if(instance == null){
            instance = new DBHelper();
        }
       return instance.connection;
    }
}
