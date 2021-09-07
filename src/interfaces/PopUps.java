/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

/**
 *
 * @author Talha Asghar
 */

import java.math.BigDecimal;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public class PopUps {

    public PopUps(){}
    
    public static void showInvalidFullNamePopUp(){
    
        Alert invalidFullName = new Alert(AlertType.WARNING,"Please Enter correct name.");
        invalidFullName.setTitle("Invalid Name");
        invalidFullName.setHeaderText("Invalid Full Name");
        invalidFullName.showAndWait();
        
    }

    public static void showInvalidFatherNamePopUp(){
    
        Alert invalidFatherName = new Alert(AlertType.WARNING,"Please Enter correct name.");
        invalidFatherName.setTitle("Invalid Name");
        invalidFatherName.setHeaderText("Invalid Father Name");
        invalidFatherName.showAndWait();
        
    }

    public static void showInvalidCnicPopUp(){
    
        Alert invalidCnic = new Alert(AlertType.WARNING,"Please Enter correct CNIC.");
        invalidCnic.setTitle("Invalid CNIC");
        invalidCnic.setHeaderText("Invalid CNIC");
        invalidCnic.showAndWait();
        
    }
    
    public static void showInvalidGenderPopUp(){
    
        Alert invalidGender = new Alert(AlertType.WARNING,"Please Select your Gender.");
        invalidGender.setTitle("Invalid Gender");
        invalidGender.setHeaderText("Nothing was selected");
        invalidGender.showAndWait();
        
    }

    public static void showInvalidMartialStatusPopUp(){
    
        Alert invalidMartialStatus = new Alert(AlertType.WARNING,"Please Select your Martial Stauts.");
        invalidMartialStatus.setTitle("Invalid Martial Status");
        invalidMartialStatus.setHeaderText("Nothing was selected");
        invalidMartialStatus.showAndWait();
        
    } 

    public static void showInvalidDobPopUp(){
    
        Alert invalidDob = new Alert(AlertType.WARNING,"Please Select your Date of Birth.");
        invalidDob.setTitle("Invalid Date of Birth");
        invalidDob.setHeaderText("Nothing was selected");
        invalidDob.showAndWait();
        
    }  

    public static void showInvalidEducationPopUp(){
    
        Alert invalidEducation = new Alert(AlertType.WARNING,"Please Select your Education.");
        invalidEducation.setTitle("Invalid Education");
        invalidEducation.setHeaderText("Nothing was selected");
        invalidEducation.showAndWait();
        
    } 

    public static void showInvalidReligionPopUp(){
    
        Alert invalidReligion = new Alert(AlertType.WARNING,"Please Select your Religion.");
        invalidReligion.setTitle("Invalid Religion");
        invalidReligion.setHeaderText("Nothing was selected");
        invalidReligion.showAndWait();
        
    } 

    public static void showInvalidOccupationPopUp(){
    
        Alert invalidOccupation = new Alert(AlertType.WARNING,"Please Select your Occupation.");
        invalidOccupation.setTitle("Invalid Occupation");
        invalidOccupation.setHeaderText("Nothing was selected");
        invalidOccupation.showAndWait();
        
    } 
    
    public static void showInvalidCountryPopUp(){
    
        Alert invalidCountry = new Alert(AlertType.WARNING,"Please Select your Country.");
        invalidCountry.setTitle("Invalid Country");
        invalidCountry.setHeaderText("Nothing was selected");
        invalidCountry.showAndWait();
        
    }
    
    public static void showInvalidCityPopUp(){
    
        Alert invalidCity = new Alert(AlertType.WARNING,"Please Select your City.");
        invalidCity.setTitle("Invalid City");
        invalidCity.setHeaderText("Nothing was selected");
        invalidCity.showAndWait();
        
    }
    
    public static void showInvalidProvincePopUp(){
    
        Alert invalidProvince = new Alert(AlertType.WARNING,"Please Select your Province.");
        invalidProvince.setTitle("Invalid Province");
        invalidProvince.setHeaderText("Nothing was selected");
        invalidProvince.showAndWait();
        
    }

    public static void showPasswordMisMatchPopUp(){
    
        Alert invalidPassword = new Alert(AlertType.WARNING,"The Passwords do not match.");
        invalidPassword.setTitle("Invalid Password");
        invalidPassword.setHeaderText("Password Confirmation Error.");
        invalidPassword.showAndWait();
        
    } 
    
    public static void showAccountCreationMessage(){
    
        Alert accountCreated = new Alert(AlertType.INFORMATION,"Account Created Successfully.");
        accountCreated.setTitle("New Account");
        accountCreated.setHeaderText("Account Created Successfully.");
        accountCreated.showAndWait();
        
    }
    
    public static void showAccountCreationError(){
    
        Alert accountCreationError = new Alert(AlertType.ERROR,"Account was not Created Successfully.");
        accountCreationError.setTitle("New Account Creation Error");
        accountCreationError.setHeaderText("Error in creating New Account.");
        accountCreationError.showAndWait();
        
    }  
    
    public static void showUpdateCompleteMessage(){
    
        Alert accountUpdate = new Alert(AlertType.INFORMATION,"Customer Profile Information Update completed Successfully.");
        accountUpdate.setTitle("Account Info Updated");
        accountUpdate.setHeaderText("Customer Profile Update.");
        accountUpdate.showAndWait();
        
    }
    
    public static void showAccountUpdateError(){
    
        Alert accountUpdateError = new Alert(AlertType.ERROR,"Customer Profile was not Updated Successfully.");
        accountUpdateError.setTitle("Account Info Update Error");
        accountUpdateError.setHeaderText("Error in Updating Customer Profile");
        accountUpdateError.showAndWait();
        
    } 
    public static void showInvalidUserNameError(){
    
        Alert invalidUserName = new Alert(AlertType.WARNING,"UserName already Exists.");
        invalidUserName.setTitle("Invalid UserName");
        invalidUserName.setHeaderText("Choose another UserName");
        invalidUserName.showAndWait();
        
    }

    public static void showInvalidAccountError(){
    
        Alert invalidAccount = new Alert(AlertType.WARNING,"This account does not Exists.");
        invalidAccount.setTitle("Incorrect UserName");
        invalidAccount.setHeaderText("Please enter correct UserName");
        invalidAccount.showAndWait();
        
    }

    public static void showIncorrectPasswordError(){
    
        Alert incorrectPassword = new Alert(AlertType.ERROR,"Please enter correct Password.");
        incorrectPassword.setTitle("Wrong Password");
        incorrectPassword.setHeaderText("Incorrect Password");
        incorrectPassword.showAndWait();
        
    }
    
    public static void showLogoutConfirmation(Stage myStage){
    
        Alert logoutConfirmation = new Alert(AlertType.CONFIRMATION,"Are you sure you want to logout?");
        logoutConfirmation.setTitle("Logout");
        logoutConfirmation.setHeaderText("Confirm Logout");
        logoutConfirmation.showAndWait().ifPresent(response->{
            
            if(response == ButtonType.OK){
                myStage.close();
            }
        });
        
    }
    public static void showTransactionCompleteMessage(BigDecimal balance){
    
        Alert balanceUpdate = new Alert(AlertType.INFORMATION,String.format("Current Balance = Rs %,.2f /-",
               balance));
        balanceUpdate.setTitle("Balance Updated");
        balanceUpdate.setHeaderText("Transaction Completed Successfully!");
        balanceUpdate.showAndWait();
        
    }
    
    public static void showInvalidBalanceMessage(){
    
        Alert invalidBalance = new Alert(AlertType.ERROR,"Account balance should only contain positive numbers.");
        invalidBalance.setTitle("Invalid Account Balance");
        invalidBalance.setHeaderText("Account Balance contains Invalid Characters");
        invalidBalance.showAndWait();
        
    }

    public static void showInsufficientBalanceMessage(){
    
        Alert invalidWithdrawBalance = new Alert(AlertType.INFORMATION,"You don`t have sufficient Balance!");
        invalidWithdrawBalance.setTitle("Insufficient Balance");
        invalidWithdrawBalance.setHeaderText("Can not proceed with this Transaction");
        invalidWithdrawBalance.showAndWait();
        
    }
    
    public static void showNoInputError(){
    
        Alert noInput = new Alert(AlertType.ERROR,"You did not entered anything or "
                + "Reciever Account Number Search was Cancelled!");
        noInput.setTitle("No Input Error");
        noInput.setHeaderText("Can not proceed further");
        noInput.showAndWait();
        
    }
    
    public static void showCannotTransferToYourselfError(){

        Alert transferToYourself = new Alert(AlertType.ERROR,"You can not Transfer Money to yourself !");
        transferToYourself.setTitle("Money Transfer Error");
        transferToYourself.setHeaderText("Can not proceed further");
        transferToYourself.showAndWait();
            
    }
    
    public static void showAccountNumberDoesNotExistError(){

        Alert transferToYourself = new Alert(AlertType.WARNING,"This Account Number does not Exists.");
        transferToYourself.setTitle("Account Number Does Not Exists Error");
        transferToYourself.setHeaderText("Incorrect Account Number");
        transferToYourself.showAndWait();
            
    }

    public static void showLoanUpdateMessage(BigDecimal balance, BigDecimal loan){
    
        Alert loanUpdate = new Alert(AlertType.INFORMATION,String.format("You have successfully got the Loan of Rs %,.2f /-\n"
                + "Current Balance = Rs %,.2f /-",loan, balance));
        loanUpdate.setTitle("Request Loan");
        loanUpdate.setHeaderText("Congratulations!");
        loanUpdate.showAndWait();
        
    }
    
    public static void showLoanUpdateError(){
    
        Alert loadUpdateError = new Alert(AlertType.ERROR,"You were not given the requested loan amount.");
        loadUpdateError.setTitle("Request Loan Error");
        loadUpdateError.setHeaderText("We are really Sorry!");
        loadUpdateError.showAndWait();
        
    }

  public static Alert customerDeleteConfirmation(){
    
        Alert customerDeleteConfirmation = new Alert(AlertType.CONFIRMATION,"Are you sure you want to Delete this customer?");
        customerDeleteConfirmation.setTitle("Delete Customer");
        customerDeleteConfirmation.setHeaderText("Confirm Customer Delete");
       
        return customerDeleteConfirmation;
    }

    public static void customerDeletedSuccessfully(){
    
        Alert customerDeleted = new Alert(AlertType.INFORMATION,"Customer was deleted Successfully.");
        customerDeleted.setTitle("Customer Deleted");
        customerDeleted.setHeaderText("Customer Deleted");
        customerDeleted.showAndWait();
        
    }
    
    public static void customerDeleteError(){
    
        Alert customerDeleteError = new Alert(AlertType.ERROR,"Customer was not deleted.");
        customerDeleteError.setTitle("Delete Customer");
        customerDeleteError.setHeaderText("Customer Deletion Error");
        customerDeleteError.showAndWait();
        
    } 

    public static void showNothingSelectedError(){
    
        Alert customerDeleteError = new Alert(AlertType.ERROR,"You selected Nothing.");
        customerDeleteError.setTitle("Invalid Choice");
        customerDeleteError.setHeaderText("Invalid Choice");
        customerDeleteError.showAndWait();
        
    }     

    public static void consolidateErrorDueToSameAccountNumber(){
    
        Alert consolidateError = new Alert(AlertType.ERROR,"Account Numbers of Both accounts are same.");
        consolidateError.setTitle("Account Consolidation Error");
        consolidateError.setHeaderText("Violation of Account Consolidation Rules");
        consolidateError.showAndWait();
        
    }

    public static void consolidateRulesViolationError(){
    
        Alert consolidateError = new Alert(AlertType.ERROR,"You can only Consolidate accounts with same attributes.");
        consolidateError.setTitle("Account Consolidation Error");
        consolidateError.setHeaderText("Violation of Account Consolidation Rules");
        consolidateError.showAndWait();
        
    }  
    
    public static void showConsolidationCompleteMessage(){
    
        Alert consolidationComplete = new Alert(AlertType.INFORMATION,"Accounts were cosolidated Successfully.");
        consolidationComplete.setTitle("Account Consolidation");
        consolidationComplete.setHeaderText("Congratulations");
        consolidationComplete.showAndWait();
        
    }
    
    public static void showConsolidationError(){
    
        Alert consolidationError = new Alert(AlertType.ERROR,"Accounts were not cosolidated.");
        consolidationError.setTitle("Account Consolidation Error");
        consolidationError.setHeaderText("Error");
        consolidationError.showAndWait();
        
    }
    
    public static void showInvalidConsumerReferenceError(){
    
        Alert consolidationError = new Alert(AlertType.WARNING,"Invalid Consumer Reference Number Entered");
        consolidationError.setTitle("Invalid Consumer Reference Error");
        consolidationError.setHeaderText("Not a valid Consumer Reference Number");
        consolidationError.showAndWait();
        
    }
    
    public static void showNotAccountNumberError(){
    
        Alert consolidationError = new Alert(AlertType.WARNING,"Invalid Bank Account Number Entered");
        consolidationError.setTitle("Invalid Account Number Error");
        consolidationError.setHeaderText("Not a valid Bank Account Number");
        consolidationError.showAndWait();
        
    }
    
  public static Alert showBillingConfirmation(){
    
        Alert customerDeleteConfirmation = new Alert(AlertType.CONFIRMATION,"Are you sure that you want to proceede?");
        customerDeleteConfirmation.setTitle("Pay Bill");
        customerDeleteConfirmation.setHeaderText("Confirm Billing");
       
        return customerDeleteConfirmation;
    }

    public static void billPaidSuccessfully(){
    
        Alert customerDeleted = new Alert(AlertType.INFORMATION,"Bill was Paid Successfully.");
        customerDeleted.setTitle("Bill Paid");
        customerDeleted.setHeaderText("Bill Paid");
        customerDeleted.showAndWait();
        
    }
    
    public static void billPayError(){
    
        Alert customerDeleteError = new Alert(AlertType.ERROR,"Bill was not paid.");
        customerDeleteError.setTitle("Bill Pay Error");
        customerDeleteError.setHeaderText("Error");
        customerDeleteError.showAndWait();
        
    }     
    
    public static void showInvalidMoneyMessage(){
    
        Alert invalidBalance = new Alert(AlertType.ERROR,"Money Amount should only contain positive numbers.");
        invalidBalance.setTitle("Invalid Money Amount");
        invalidBalance.setHeaderText("Money Amount contains Invalid Characters");
        invalidBalance.showAndWait();
        
    }
    
    public static Alert showWithdrawMoneyConfirmation(){
    
        Alert moneyWithdrawConfirmation = new Alert(AlertType.CONFIRMATION,"Are you sure you want to withdraw money?");
        moneyWithdrawConfirmation.setTitle("withdraw Money");
        moneyWithdrawConfirmation.setHeaderText("Confirm Money Withdraw");

        return moneyWithdrawConfirmation;
    }
 
    
    
}
