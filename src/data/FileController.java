/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import bank.Customer;
import bank.Employee;
import interfaces.Images;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;


/**
 *
 * @author Talha Asghar
 */
public class FileController {
    
    public static ArrayList<String> readCountries(){
        
        ArrayList<String> countries = new ArrayList<>();
        try(Scanner input = new Scanner(Paths.get("files/countries.txt"))) {

            while(input.hasNext()){
                
                countries.add(input.nextLine());
                
            }            
        } catch (IOException ex) { 
            System.out.println("IOException while reading file.\n"+ex);
        }
        
        return countries;
    }

    public static ArrayList<String> readOccupations(){
        
        ArrayList<String> occupations = new ArrayList<>();
        try(Scanner input = new Scanner(Paths.get("files/occupations.txt"))) {

            while(input.hasNext()){
                
                occupations.add(input.nextLine());
                
            }            
        } catch (IOException ex) { 
            System.out.println("IOException while reading file.\n"+ex);
        }
        
        return occupations;
    }

    public static ArrayList<String> readAvailableCurrencies(){
        
        ArrayList<String> allCurrencies = new ArrayList<>();
        try(Scanner input = new Scanner(Paths.get("files/currency_rates.txt"))) {

            while(input.hasNext()){
                String temp[] = input.nextLine().split("###");
                allCurrencies.add(temp[0]);
                
            }            
        } catch (IOException ex) { 
            System.out.println("IOException while reading file.\n"+ex);
        }
        
        return allCurrencies;
    } 

    public static double readCurrencyRate(String currency){
        
        try(Scanner input = new Scanner(Paths.get("files/currency_rates.txt"))) {

            while(input.hasNext()){
                String temp[] = input.nextLine().split("###");
                if(currency.equals(temp[0])) {
                    return Double.parseDouble(temp[1]);
                }
            }            
        } catch (IOException ex) { 
            System.out.println("IOException while reading file.\n"+ex);
        }
        
        return -1;
    }     
    
    public static boolean isCustomerFileAlreadyPresent(){
    
        File customerTempFile = new File(Paths.get("files/customer.ser").toUri());
        return customerTempFile.exists();
    }
    
    public static boolean isEmployeeFileAlreadyPresent(){
    
        File employeeTempFile = new File(Paths.get("files/employee.ser").toUri());
        if(employeeTempFile.exists()){
            return true;
        }
        
        return false;
    }
    public static void writeEmployeeObject(Employee obj){
        
        try (ObjectOutputStream obWriter = 
                new ObjectOutputStream(Files.newOutputStream(
                        Paths.get("files/employee.ser"))))
        {
            
            obWriter.writeObject(obj);

        } catch (IOException ex) {
            System.out.println("Error in writing employee object.\n"+ex);
        }
    
    
    }

    public static Employee readEmployeeObject(){

        Employee obj = null;
        try(ObjectInputStream obReader = 
                new ObjectInputStream(Files.newInputStream(
                        Paths.get("files/employee.ser")))) 
        {
            obj = (Employee) obReader.readObject();

        } catch (IOException | ClassNotFoundException ex) {
            System.out.println(ex);
        }
        
        return obj;
    }    
    
    public static void writeCustomerObject(Customer obj){
        
        try (ObjectOutputStream obWriter = 
                new ObjectOutputStream(Files.newOutputStream(
                        Paths.get("files/customer.ser"))))
        {
            
            obWriter.writeObject(obj);

        } catch (IOException ex) {
            System.out.println("Error in writing customer object.\n"+ex);
        }
    
    
    }

    public static Customer readCustomerObject(){

        Customer obj = null;
        try(ObjectInputStream obReader = 
                new ObjectInputStream(Files.newInputStream(
                        Paths.get("files/customer.ser")))) 
        {
            obj = (Customer) obReader.readObject();

        } catch (IOException | ClassNotFoundException ex) {
            System.out.println(ex);
        }
        
        return obj;
    }
    public static long getAvailableEmployeeId(){
    
        long availableEmployeeId = 1234l;
        try(Scanner input = new Scanner(Paths.get("files/employee_accounts.dat"))){
            String temp = "";
            while(input.hasNext()){
            
                temp = input.nextLine();
                
            }
            if(!temp.equals("")){
                String tempArray[] = temp.split("###");
                availableEmployeeId = Long.parseLong(tempArray[0]) + 1;
            }
        
        } catch (IOException ex) {
            System.out.println(ex);
        }
      
        return availableEmployeeId;
    } 
    
    
    public static long getAvailableAccountNumber(){
    
        long availableAccountNumber = 1111_2222_3333_4444l;
        try(Scanner input = new Scanner(Paths.get("files/customer_accounts.dat"))){
            String temp = "";
            while(input.hasNext()){
            
                temp = input.nextLine();
                
            }
            if(!temp.equals("")){
                String tempArray[] = temp.split("###");
                availableAccountNumber = Long.parseLong(tempArray[0]) + 1;
            }
        
        } catch (IOException ex) {
            System.out.println(ex);
        }
      
        return availableAccountNumber;
    }    

    public static void writeCustomerLoginData(String userName){
    
        try(Formatter output = new Formatter(new 
                FileOutputStream("files/customer_login.dat")))
        {
            output.format("%s%n",userName);
            
        } catch (IOException ex) {
            System.out.println(ex);
        }
      
    }
    public static void writeAdminLoginData(String userName){
    
        try(Formatter output = new Formatter(new 
                FileOutputStream("files/admin_login.dat")))
        {
            output.format("%s%n",userName);
            
        } catch (IOException ex) {
            System.out.println(ex);
        }
      
    }    
    
    public static String readCustomerLoginDataIfExists(){
    
        File loginFile = new File(Paths.get("files/customer_login.dat").toUri());
        
        if(loginFile.exists()){
            
            try(Scanner reader = new Scanner(new FileInputStream(loginFile)))
            {
                if(reader.hasNext()){
                    return reader.nextLine();
                
                }
            } catch (IOException ex) {
                System.out.println(ex);
            }
           
        }
        return null;
    }
    public static String readAdminLoginDataIfExists(){
    
        File loginFile = new File(Paths.get("files/admin_login.dat").toUri());
        
        if(loginFile.exists()){
            
            try(Scanner reader = new Scanner(new FileInputStream(loginFile)))
            {
                if(reader.hasNext()){
                    return reader.nextLine();
                
                }
            } catch (IOException ex) {
                System.out.println(ex);
            }
           
        }
        return null;
    }     

    public static String readCustomerProfilePicturePath(final long accountNumber){
    
        try(Scanner output = new Scanner(Paths.get("files/customer_accounts.dat")))
        {
            String tempArray[];
            while(output.hasNext()){
                tempArray=output.nextLine().split("###");
                if(tempArray[0].equals(Long.toString(accountNumber))){
                    return tempArray[2];
                }
            }
            
        } catch (IOException ex) {
            System.out.println(ex);
        }
      
        return Images.USER_PROFILE;
    }

    public static String readCustomerProfilePicturePath(String userName){
    
        try(Scanner output = new Scanner(Paths.get("files/customer_accounts.dat")))
        {
            String tempArray[];
            while(output.hasNext()){
                tempArray=output.nextLine().split("###");
                if(tempArray[1].equals(userName)){
                    return tempArray[2];
                }
            }
            
        } catch (IOException ex) {
            System.out.println(ex);
        }
      
        return Images.USER_PROFILE;
    }
    
    public static String readAdminProfilePicturePath(final long accountNumber){
    
        try(Scanner output = new Scanner(Paths.get("files/employee_accounts.dat")))
        {
            String tempArray[];
            while(output.hasNext()){
                tempArray=output.nextLine().split("###");
                if(tempArray[0].equals(Long.toString(accountNumber))){
                    return tempArray[2];
                }
            }
            
        } catch (IOException ex) {
            System.out.println(ex);
        }
      
        return Images.ADMIN_PROFILE;
    }

    public static String readAdminProfilePicturePath(String userName){
    
        try(Scanner output = new Scanner(Paths.get("files/employee_accounts.dat")))
        {
            String tempArray[];
            while(output.hasNext()){
                tempArray=output.nextLine().split("###");
                if(tempArray[1].equals(userName)){
                    return tempArray[2];
                }
            }
            
        } catch (IOException ex) {
            System.out.println(ex);
        }
      
        return Images.ADMIN_PROFILE;
    }    

    public static boolean doesCUserNameAlreadyExists(String userName){
    
        try(Scanner reader = new Scanner(Paths.get("files/customer_accounts.dat")))
        {
            String tempArray[];
            while(reader.hasNext()){
                tempArray=reader.nextLine().split("###");
                if(tempArray[1].equals(userName)){
                    return true;
                }
            }
            
        } catch (IOException ex) {
            System.out.println(ex);
        }
      
        return false;
    }
    
    public static boolean doesEUserNameAlreadyExists(String userName){
    
        try(Scanner reader = new Scanner(Paths.get("files/employee_accounts.dat")))
        {
            String tempArray[];
            while(reader.hasNext()){
                tempArray=reader.nextLine().split("###");
                if(tempArray[1].equals(userName)){
                    return true;
                }
            }
            
        } catch (IOException ex) {
            System.out.println(ex);
        }
      
        return false;
    }    

    public static void writeCustomerAccount(final long accountNumber,final String userName,
            final String profilePicturePath){
    
        try(Formatter output = new Formatter(new 
                FileOutputStream("files/customer_accounts.dat",true));
            )
        {
            output.format("%d###%s###%s%n",accountNumber, userName,profilePicturePath);
            Files.deleteIfExists(Paths.get("files/customer.ser"));
            
        } catch (IOException ex) {
            System.out.println(ex);
        }
      
    }

    public static void writeEmployeeAccount(final long employeeId,final String userName,
            final String profilePicturePath){
    
        try(Formatter output = new Formatter(new 
                FileOutputStream("files/employee_accounts.dat",true));
            )
        {
            output.format("%d###%s###%s%n",employeeId, userName,profilePicturePath);
            Files.deleteIfExists(Paths.get("files/employee.ser"));
            
        } catch (IOException ex) {
            System.out.println(ex);
        }
      
    }
    
    public static void deleteCustomer(String accountNumber){
        
        try(Scanner reader = new Scanner(Paths.get("files/customer_accounts.dat"));
                Formatter writer = new Formatter(new FileOutputStream("files/temp_deleting_customer_account.txt"))){
            
            while(reader.hasNext()){
                String temp[] = reader.nextLine().split("###");
                if(!temp[0].equals(accountNumber)){
                    writer.format("%s###%s###%s%n", temp[0],temp[1],temp[2]);    
                   
                }
            }
        
        } catch (IOException ex) {
            System.out.println(ex);
        }

        try(Scanner reader = new Scanner(Paths.get("files/temp_deleting_customer_account.txt"));
                Formatter writer = new Formatter(new FileOutputStream("files/customer_accounts.dat"))){
            
            while(reader.hasNext()){

                writer.format("%s%n",reader.nextLine());    
            }
            
            Files.delete(Paths.get("files/temp_deleting_customer_account.txt"));
            
        } catch (IOException ex) {
            System.out.println(ex);
        }        
     
    }
    
    public static void updateCustomerProfilePicture(String accountNumber, String newPath){
        
        try(Scanner reader = new Scanner(Paths.get("files/customer_accounts.dat"));
                Formatter writer = new Formatter(new FileOutputStream("files/temp_customer_account_update_picture.txt"))){
            
            while(reader.hasNext()){
                String temp[] = reader.nextLine().split("###");
                if(temp[0].equals(accountNumber)){
                    writer.format("%s###%s###%s%n", temp[0],temp[1],newPath);    
                }
                else{
                    writer.format("%s###%s###%s%n", temp[0],temp[1],temp[2]);    
                }
            }
        
        } catch (IOException ex) {
            System.out.println(ex);
        }

        try(Scanner reader = new Scanner(Paths.get("files/temp_customer_account_update_picture.txt"));
                Formatter writer = new Formatter(new FileOutputStream("files/customer_accounts.dat"))){
            
            while(reader.hasNext()){

                writer.format("%s%n",reader.nextLine());    
            }
            
            Files.delete(Paths.get("files/temp_customer_account_update_picture.txt"));
            
        } catch (IOException ex) {
            System.out.println(ex);
        }        
     
    }

    public static void updateEmployeeProfilePicture(String employeeID, String newPath){
        
        try(Scanner reader = new Scanner(Paths.get("files/employee_accounts.dat"));
                Formatter writer = new Formatter(new FileOutputStream("files/temp_employee_account_update_picture.txt"))){
            
            while(reader.hasNext()){
                String temp[] = reader.nextLine().split("###");
                if(temp[0].equals(employeeID)){
                    writer.format("%s###%s###%s%n", temp[0],temp[1],newPath);    
                }
                else{
                    writer.format("%s###%s###%s%n", temp[0],temp[1],temp[2]);    
                }
            }
        
        } catch (IOException ex) {
            System.out.println(ex);
        }

        try(Scanner reader = new Scanner(Paths.get("files/temp_employee_account_update_picture.txt"));
                Formatter writer = new Formatter(new FileOutputStream("files/employee_accounts.dat"))){
            
            while(reader.hasNext()){

                writer.format("%s%n",reader.nextLine());    
            }
            
            Files.delete(Paths.get("files/temp_employee_account_update_picture.txt"));
            
        } catch (IOException ex) {
            System.out.println(ex);
        }        
     
    } 
    
    public static void writeBillData(String billType,String amount, String month,
            String refNumber, String accountNumber){
    
        try(Formatter output = new Formatter(new 
                FileOutputStream("files/bills.txt",true));
            )
        {
            output.format("%s###%s###%s###%s###%s%n",billType, amount,month,
                    refNumber, accountNumber);
            
        } catch (IOException ex) {
            System.out.println(ex);
        }
      
    }    
    
    
}
