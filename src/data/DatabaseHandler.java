
package data;

import bank.Customer;
import bank.Employee;
import static bank.MainMenu.ACCOUNTS_MANAGER;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Talha Asghar
 * @param <T>
 */
public class DatabaseHandler{
    

    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    
    public boolean doesAccountExists(String userName, String tableName){
    
        try {
            String query = String.format("Select * from %s where UserName = ?", tableName);
            preparedStatement = ACCOUNTS_MANAGER.prepareStatement(query);
            preparedStatement.setString(1, userName);
            
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return true;
            }
          
            
        } catch (SQLException ex) {
            System.out.println("Unable to read from database.");
            System.out.println("Further Details : \n" + ex);
            
        }
       
        return false;
       
    }
    
    public String doesAccountNumberExists(String accountNumber, String tableName){
    
        try {
            String query = String.format("Select * from %s where AccountNumber = ?", tableName);
            preparedStatement = ACCOUNTS_MANAGER.prepareStatement(query);
            preparedStatement.setString(1, accountNumber);
            resultSet = preparedStatement.executeQuery();
            
            while(resultSet.next()){
               return resultSet.getString("UserName");
            }
          
            
        } catch (SQLException ex) {
            System.out.println("Unable to read from database.");
            System.out.println("Further Details : \n" + ex);
            
        }
       
        return null;
       
    }
    
    public boolean isPasswordCorrect(String userName, String password, String tableName){
    
        if(doesAccountExists(userName, tableName)){
        
            try {
                
                String query = String.format("Select * from %s where UserName = ? and Password = ?",
                        tableName);
                preparedStatement = ACCOUNTS_MANAGER.prepareStatement(query);
                preparedStatement.setString(1, userName);
                preparedStatement.setString(2, password);
                
                resultSet = preparedStatement.executeQuery();
                
                if(resultSet.next()){
                
                    return true;
                    
                }
                
            } catch (SQLException ex) {
                
                System.out.println("Unable to read data from database.");
                System.out.println("Further Details: \n" + ex);
                
            }
        
        }
        else{
            
            System.out.println("Account does not exists.");
                    
        }
        
    
        return false;
        
    }
    
    public Customer readAllDataOfCustomer(String userName, String tableName){
    
        Customer customer = new Customer();
        try {

                String query = String.format("Select * from %s where UserName = ?", tableName);
                preparedStatement = ACCOUNTS_MANAGER.prepareStatement(query);
                preparedStatement.setString(1, userName);
                resultSet = preparedStatement.executeQuery();

                while(resultSet.next()){
                   customer = extractCustomerDataFromResultSet(resultSet);
                  
                }

            } catch (SQLException ex) {

                System.out.println("Unable to read data from database.");
                System.out.println("Further Details: \n" + ex);
            }
       
        
        return customer;
    
    }

     public void updateCustomerBalance(String userName, String balance, String tableName){
    
        try {
            
            String query = String.format("Update %s SET CurrentBalance=? where UserName=?",tableName);
            preparedStatement = ACCOUNTS_MANAGER.prepareStatement(query);
            preparedStatement.setString(1, balance);
            preparedStatement.setString(2, userName);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {

           System.out.println("Unable to update balance in Database.");
           System.out.println("Further Details: \n" + ex); 

        }
        
    }     

      public void updateLoan(String userName, String loan, String tableName){
    
        try {
            
            String query = String.format("Update %s SET Loan=? where UserName=?",tableName);
            preparedStatement = ACCOUNTS_MANAGER.prepareStatement(query);
            preparedStatement.setString(1, loan);
            preparedStatement.setString(2, userName);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {

           System.out.println("Unable to update Loan in Database.");
           System.out.println("Further Details: \n" + ex); 

        }
        
    }

    public Customer[] readAllCustomers(String tableName){
     
        ArrayList<Customer> allCustomers = new ArrayList<>();
        try {

                String query = String.format("Select * from %s", tableName);
                preparedStatement = ACCOUNTS_MANAGER.prepareStatement(query);
                resultSet = preparedStatement.executeQuery();

                while(resultSet.next()){
                    allCustomers.add(
                            extractCustomerDataFromResultSet(resultSet));
                }

            } catch (SQLException ex) {

                System.out.println("Unable to read data from database.");
                System.out.println("Further Details: \n" + ex);
            }
       
        
        return allCustomers.toArray(new Customer[allCustomers.size()]);
    
    }
    
    public Customer[] searchInCustomers(String value,String column,String table ){
          
        ArrayList<Customer> customers = new ArrayList<>(); 
        try {
            String query = String.format("Select * from %s where %s=?",table,column);
            preparedStatement = ACCOUNTS_MANAGER.prepareStatement(query);
            preparedStatement.setString(1, value);
            resultSet = preparedStatement.executeQuery();
            
            while(resultSet.next()){
            
                customers.add(
                        extractCustomerDataFromResultSet(resultSet));
               
            }
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    
        return customers.toArray(new Customer[customers.size()]);
    }

    public Customer[] customSearchInCustomers(String condition,String amount,String column,String table ){
          
        ArrayList<Customer> customers = new ArrayList<>(); 
        try {
            String query = String.format("Select * from %s where %s%s?",table,column,condition);
            preparedStatement = ACCOUNTS_MANAGER.prepareStatement(query);
            preparedStatement.setString(1, amount);
            resultSet = preparedStatement.executeQuery();
            
            while(resultSet.next()){
            
                customers.add(
                        extractCustomerDataFromResultSet(resultSet));
               
            }
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    
        return customers.toArray(new Customer[customers.size()]);
    }    
    
    private Customer extractCustomerDataFromResultSet(ResultSet results){
       
        Customer customer = new Customer();
            
        try {
            customer.setFullName(results.getString("FullName"));
            customer.setFatherName(results.getString("FatherName"));
            customer.setDOB(results.getString("DOB"));
            customer.setCNIC(results.getString("CNIC"));
            customer.setGender(results.getString("Gender"));
            customer.setReligion(results.getString("Religion"));
            customer.setEducation(results.getString("Education"));
            customer.setMartialStatus(results.getString("MartialStatus"));
            customer.setNationality(results.getString("Nationality"));
            customer.setOccupation(results.getString("Occupation"));
            customer.setContactNum(results.getString("ContactNumber"));
            customer.setEmail(results.getString("Email"));
            customer.setDomicile(results.getString("Domicile"));
            customer.setAddress(results.getString("Address"));
            customer.setCity(results.getString("City"));
            customer.setProvince(results.getString("Province"));
            customer.setMailingAddress(results.getString("MailingAddress"));
            customer.setUserName(results.getString("UserName"));
            customer.setPassword(results.getString("Password"));
            customer.setBalance(results.getBigDecimal("CurrentBalance"));
            customer.setAccountTitle(results.getString("AccountTitle"));
            customer.setAccountNumber(results.getLong("AccountNumber"));
            customer.setLoan(results.getBigDecimal("Loan"));    
            customer.setAccountType(results.getString("AccountType"));
           
        } catch (SQLException ex) {
            System.out.println("Exception in Extracting Customer "
                    + "Data from Result.\n Details"+ex);
        }
        return customer;
    }
    
    public boolean tryAddingNewCustomer(Customer newCustomer, String tableName){
        
        try {
                
            String query = String.format("Insert Into %s (AccountNumber,UserName,FullName,"
                    + "FatherName, DOB, CNIC, Gender, Religion, Education, MartialStatus, Nationality,"
                    + "Occupation, ContactNumber, Email, Domicile, Address, City, Province, MailingAddress,"
                    + "AccountTitle, AccountType, Password, CurrentBalance, Loan) "
                    + "Values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", tableName);
            
            preparedStatement = ACCOUNTS_MANAGER.prepareStatement(query);
            preparedStatement.setString(1, Long.toString(newCustomer.getAccountNumber()));
            preparedStatement.setString(2, newCustomer.getUserName());
            preparedStatement.setString(3, newCustomer.getFullName());
            preparedStatement.setString(4, newCustomer.getFatherName());
            preparedStatement.setString(5, newCustomer.getDOB());
            preparedStatement.setString(6, newCustomer.getCNIC());
            preparedStatement.setString(7, newCustomer.getGender());
            preparedStatement.setString(8, newCustomer.getReligion());
            preparedStatement.setString(9, newCustomer.getEducation());
            preparedStatement.setString(10, newCustomer.getMartialStatus());
            preparedStatement.setString(11, newCustomer.getNationality());
            preparedStatement.setString(12, newCustomer.getOccupation());
            preparedStatement.setString(13, newCustomer.getContactNumber());
            preparedStatement.setString(14, newCustomer.getEmail());
            preparedStatement.setString(15, newCustomer.getDomicile());
            preparedStatement.setString(16, newCustomer.getAddress());
            preparedStatement.setString(17, newCustomer.getCity());
            preparedStatement.setString(18, newCustomer.getProvince());
            preparedStatement.setString(19, newCustomer.getMailingAddress());
            preparedStatement.setString(20, newCustomer.getAccountTitle());
            preparedStatement.setString(21, newCustomer.getAccountType());
            preparedStatement.setString(22, newCustomer.getPassword());
            preparedStatement.setString(23, newCustomer.getBalance().toString());
            preparedStatement.setString(24, newCustomer.getLoan().toString());
            preparedStatement.executeUpdate();
            return true;

        } catch (SQLException ex) {

            System.out.println("Unable to read data from database.");
            System.out.println("Further Details: \n" + ex);
        }
       
        
        return false;
    
    }       
    public boolean tryCustomerDataUpdate(Customer customerToUpdate, String tableName){
        
        try {
                
            String query = String.format("Update %s SET UserName=?,FullName=?,"
                    + "FatherName=?, DOB=?, CNIC=?, Gender=?, Religion=?, Education=?, MartialStatus=?, Nationality=?,"
                    + "Occupation=?, ContactNumber=?, Email=?, Domicile=?, Address=?, City=?, Province=?, MailingAddress=?,"
                    + "AccountTitle=?, AccountType=?, Password=?, CurrentBalance=?, Loan=? "
                    + "where AccountNumber = ?", tableName);
            
            preparedStatement = ACCOUNTS_MANAGER.prepareStatement(query);
            
            preparedStatement.setString(1, customerToUpdate.getUserName());
            preparedStatement.setString(2, customerToUpdate.getFullName());
            preparedStatement.setString(3, customerToUpdate.getFatherName());
            preparedStatement.setString(4, customerToUpdate.getDOB());
            preparedStatement.setString(5, customerToUpdate.getCNIC());
            preparedStatement.setString(6, customerToUpdate.getGender());
            preparedStatement.setString(7, customerToUpdate.getReligion());
            preparedStatement.setString(8, customerToUpdate.getEducation());
            preparedStatement.setString(9, customerToUpdate.getMartialStatus());
            preparedStatement.setString(10, customerToUpdate.getNationality());
            preparedStatement.setString(11, customerToUpdate.getOccupation());
            preparedStatement.setString(12, customerToUpdate.getContactNumber());
            preparedStatement.setString(13, customerToUpdate.getEmail());
            preparedStatement.setString(14, customerToUpdate.getDomicile());
            preparedStatement.setString(15, customerToUpdate.getAddress());
            preparedStatement.setString(16, customerToUpdate.getCity());
            preparedStatement.setString(17, customerToUpdate.getProvince());
            preparedStatement.setString(18, customerToUpdate.getMailingAddress());
            preparedStatement.setString(19, customerToUpdate.getAccountTitle());
            preparedStatement.setString(20, customerToUpdate.getAccountType());
            preparedStatement.setString(21, customerToUpdate.getPassword());
            preparedStatement.setString(22, customerToUpdate.getBalance().toString());
            preparedStatement.setString(23, customerToUpdate.getLoan().toString());
            preparedStatement.setString(24, Long.toString(customerToUpdate.getAccountNumber()));
            preparedStatement.executeUpdate();
            return true;

        } catch (SQLException ex) {

            System.out.println("Unable to read data from database.");
            System.out.println("Further Details: \n" + ex);
        }
       
        
        return false;
    
    }
    
    public boolean tryAddingNewEmployee(Employee newEmployee, String tableName){
        
        try {
                
            String query = String.format("Insert Into %s (EmployeeID,UserName,FullName,"
                    + "FatherName, DOB, CNIC, Gender, Religion, Education, MartialStatus, Nationality,"
                    + "Occupation, ContactNumber, Email, Domicile, Address, City, Province, MailingAddress,"
                    + "EmployeeType, Password, Salary) "
                    + "Values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", tableName);
            
            preparedStatement = ACCOUNTS_MANAGER.prepareStatement(query);
            preparedStatement.setString(1, Long.toString(newEmployee.getEmployeeId()));
            preparedStatement.setString(2, newEmployee.getUserName());
            preparedStatement.setString(3, newEmployee.getFullName());
            preparedStatement.setString(4, newEmployee.getFatherName());
            preparedStatement.setString(5, newEmployee.getDOB());
            preparedStatement.setString(6, newEmployee.getCNIC());
            preparedStatement.setString(7, newEmployee.getGender());
            preparedStatement.setString(8, newEmployee.getReligion());
            preparedStatement.setString(9, newEmployee.getEducation());
            preparedStatement.setString(10, newEmployee.getMartialStatus());
            preparedStatement.setString(11, newEmployee.getNationality());
            preparedStatement.setString(12, newEmployee.getOccupation());
            preparedStatement.setString(13, newEmployee.getContactNumber());
            preparedStatement.setString(14, newEmployee.getEmail());
            preparedStatement.setString(15, newEmployee.getDomicile());
            preparedStatement.setString(16, newEmployee.getAddress());
            preparedStatement.setString(17, newEmployee.getCity());
            preparedStatement.setString(18, newEmployee.getProvince());
            preparedStatement.setString(19, newEmployee.getMailingAddress());
            preparedStatement.setString(20, newEmployee.getEmployeeType());
            preparedStatement.setString(21, newEmployee.getPassword());
            preparedStatement.setString(22, newEmployee.getSalary().toString());
            preparedStatement.executeUpdate();
            return true;

        } catch (SQLException ex) {

            System.out.println("Unable to insert new Employee to database.");
            System.out.println("Further Details: \n" + ex);
        }
       
        
        return false;
    
    }    

    public Employee readAllDataOfEmployee(String userName, String tableName){
    
        Employee employee = new Employee();
        try {

                String query = String.format("Select * from %s where UserName = ?", tableName);
                preparedStatement = ACCOUNTS_MANAGER.prepareStatement(query);
                preparedStatement.setString(1, userName);
                resultSet = preparedStatement.executeQuery();

                while(resultSet.next()){
                   employee = extractEmployeeDataFromResultSet(resultSet);
                  
                }

            } catch (SQLException ex) {

                System.out.println("Unable to read data from database.");
                System.out.println("Further Details: \n" + ex);
            }
       
        
        return employee;
    
    }
    private Employee extractEmployeeDataFromResultSet(ResultSet results){
       
        Employee employee = new Employee();
            
        try {
            employee.setFullName(results.getString("FullName"));
            employee.setFatherName(results.getString("FatherName"));
            employee.setDOB(results.getString("DOB"));
            employee.setCNIC(results.getString("CNIC"));
            employee.setGender(results.getString("Gender"));
            employee.setReligion(results.getString("Religion"));
            employee.setEducation(results.getString("Education"));
            employee.setMartialStatus(results.getString("MartialStatus"));
            employee.setNationality(results.getString("Nationality"));
            employee.setOccupation(results.getString("Occupation"));
            employee.setContactNum(results.getString("ContactNumber"));
            employee.setEmail(results.getString("Email"));
            employee.setDomicile(results.getString("Domicile"));
            employee.setAddress(results.getString("Address"));
            employee.setCity(results.getString("City"));
            employee.setProvince(results.getString("Province"));
            employee.setMailingAddress(results.getString("MailingAddress"));
            employee.setUserName(results.getString("UserName"));
            employee.setPassword(results.getString("Password"));
            employee.setEmployeeId(results.getLong("EmployeeID"));
            employee.setEmployeeType(results.getString("EmployeeType"));
            employee.setSalary(results.getBigDecimal("Salary"));
            
        } catch (SQLException ex) {
            System.out.println("Exception in Extracting Employee "
                    + "Data from Result.\n Details"+ex);
        }
        return employee;
    }    
      
    public boolean tryEmployeeDataUpdate(Employee employeeToUpdate, String tableName){
        
        try {
                
            String query = String.format("Update %s SET UserName=?,FullName=?,"
                    + "FatherName=?, DOB=?, CNIC=?, Gender=?, Religion=?, Education=?, MartialStatus=?, Nationality=?,"
                    + "Occupation=?, ContactNumber=?, Email=?, Domicile=?, Address=?, City=?, Province=?, MailingAddress=?,"
                    + "EmployeeType=?, Password=?, Salary=?"
                    + "where EmployeeID = ?", tableName);
            
            preparedStatement = ACCOUNTS_MANAGER.prepareStatement(query);
            
            preparedStatement.setString(1, employeeToUpdate.getUserName());
            preparedStatement.setString(2, employeeToUpdate.getFullName());
            preparedStatement.setString(3, employeeToUpdate.getFatherName());
            preparedStatement.setString(4, employeeToUpdate.getDOB());
            preparedStatement.setString(5, employeeToUpdate.getCNIC());
            preparedStatement.setString(6, employeeToUpdate.getGender());
            preparedStatement.setString(7, employeeToUpdate.getReligion());
            preparedStatement.setString(8, employeeToUpdate.getEducation());
            preparedStatement.setString(9, employeeToUpdate.getMartialStatus());
            preparedStatement.setString(10, employeeToUpdate.getNationality());
            preparedStatement.setString(11, employeeToUpdate.getOccupation());
            preparedStatement.setString(12, employeeToUpdate.getContactNumber());
            preparedStatement.setString(13, employeeToUpdate.getEmail());
            preparedStatement.setString(14, employeeToUpdate.getDomicile());
            preparedStatement.setString(15, employeeToUpdate.getAddress());
            preparedStatement.setString(16, employeeToUpdate.getCity());
            preparedStatement.setString(17, employeeToUpdate.getProvince());
            preparedStatement.setString(18, employeeToUpdate.getMailingAddress());
            preparedStatement.setString(19, employeeToUpdate.getEmployeeType());
            preparedStatement.setString(20, employeeToUpdate.getPassword());
            preparedStatement.setString(21, employeeToUpdate.getSalary().toString());
            preparedStatement.setString(22, Long.toString(employeeToUpdate.getEmployeeId()));
            preparedStatement.executeUpdate();
            return true;

        } catch (SQLException ex) {

            System.out.println("Unable to read data from database.");
            System.out.println("Further Details: \n" + ex);
        }
       
        
        return false;
    
    }

    public boolean tryDeleteCustomer(String accountNumber, String tableName){
    
        try {
            String query = String.format("Delete from %s where AccountNumber = ?", tableName);
            preparedStatement = ACCOUNTS_MANAGER.prepareStatement(query);
            preparedStatement.setString(1, accountNumber);
            preparedStatement.executeUpdate();
            return true;
          
            
        } catch (SQLException ex) {
            System.out.println("Unable to delete customer from database.");
            System.out.println("Further Details : \n" + ex);
            
        }
       
        return false;
       
    }    
    
    
}
