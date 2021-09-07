package bank;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Talha Asghar
 */
import data.DatabaseHandler;
import data.DatabaseManager;
import interfaces.Scenes;
import java.sql.Connection;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.stage.Stage;



public class MainMenu extends Application{
   
    public static final Connection ACCOUNTS_MANAGER;
    public static final DatabaseHandler HANDLE_DATABASE;
    
    static{
        
        ACCOUNTS_MANAGER = DatabaseManager.connectToDatabase("Accounts", "sqlite");
        HANDLE_DATABASE = new DatabaseHandler();
    }

    public static void main(String[] args) {
     
        try {
            launch(args);
            ACCOUNTS_MANAGER.close();
            
        } catch (SQLException ex) {
            System.out.println("Exception in closing Database.\n"+ex);
        }
        System.exit(0);
    }


    

    @Override
    public void start(Stage primaryStage){
        
        
        Scenes bankingApplication = new Scenes();
        bankingApplication.showLoadingAppStage(750, 300);
        primaryStage = bankingApplication.createMainMenuStage(800, 800, 2);
        
        primaryStage.showAndWait();
        
    }

 
}          // end of class "Application"
