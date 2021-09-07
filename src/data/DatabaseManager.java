package data;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Talha Asghar
 */
public interface DatabaseManager{
    
    
    // connect to given database databaseName

    public static Connection connectToDatabase(final String databaseName,
            final String ext){
    
        Connection temp = null;
        
        try {
           
            Class.forName("org.sqlite.JDBC");
            temp = DriverManager.getConnection(
                    String.format("jdbc:sqlite:%s.%s",databaseName,ext),"","");
           
        }
        catch (ClassNotFoundException ex) {
            
            System.out.println("Class \"org.sqlite.JDBC not found\". ");
            System.out.println("More Details : \n" + ex);
        }
        catch (SQLException ex){
            
            System.out.printf("Program was unable to connect to Database %s.%s",
                    databaseName, ext);
            System.out.println("More Details : \n" + ex);
        }
        
        return temp;
   
    }   // end of "connectToDatabase"
   
}       // end of class "DatabaseManager"
