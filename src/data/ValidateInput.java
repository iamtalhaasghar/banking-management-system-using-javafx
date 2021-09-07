/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

/**
 *
 * @author Talha Asghar
 */
public interface ValidateInput {
    
    public static boolean validateNames(String names){
    
        return names.matches("\\w+\\s*\\w*");
    }
    
    // CNIC Format : 12345-1234567-1
    public static boolean validateCNIC(String cnic){
    
        return cnic.matches("[1-9]\\d{4}-\\d{7}-\\d{1}");
    }
    
    public static boolean validatePhoneNumber(String phoneNum){
    
        return phoneNum.matches("[+]{1}\\d{2}-\\d{10}");
    
    }
    
    public static boolean validateEmail(String email){
    
        return email.matches("[a-z]+\\d*[@][a-z]*[.]{1}com");
    }
    
    public static boolean validateDomicile(String domicile){
        
        return domicile.matches("\\w+\\s*[A-Za-z]*\\s*\\w*");
    }
    
    public static boolean validateMoney(String balance){
    
        return balance.matches("\\d+.{0,1}\\d*");
    }    
    
    public static boolean validateAddress(String address){
    
        return address.matches("");
    }
    
    public static boolean validatePassword(String password){
    
        return password.matches("\\w*\\d*");
    }
    
    public static boolean passwordStrength(String password){
    
        return false;
    }
    
    
    public static boolean consumerReferenceNumber(String reference){
    
        return reference.matches("\\d{5}");
    }
    
    public static boolean bankAccountNumber(String accountNumber){
    
        return accountNumber.matches("\\d{16}");
    }
    
}
