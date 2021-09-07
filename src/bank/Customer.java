/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank;

import data.FileController;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author Talha Asghar
 */
public class Customer extends Person implements Serializable{
    
    private String accountTitle;
    private String accountType;
    private long accountNumber;
    private BigDecimal balance;
    private BigDecimal loan;
    
    public Customer(){
    
        accountNumber = FileController.getAvailableAccountNumber();
    }
    
    public Customer(Customer c){
    
        super(c.getFullName(), c.getFatherName(), c.getDOB(), c.getCNIC(), c.getGender(),
                c.getReligion(), c.getEducation(), c.getMartialStatus(),
                c.getNationality(), c.getOccupation(), c.getContactNumber(), c.getEmail(),
                c.getDomicile(), c.getAddress(), c.getCity(), c.getProvince(), c.getMailingAddress());

        accountNumber = FileController.getAvailableAccountNumber();
    }

    
    public String getAccountTitle() {
        return accountTitle;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountTitle(String accountTitle) {
        this.accountTitle = accountTitle;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getLoan() {
        return loan;
    }

    public void setLoan(BigDecimal loan) {
        this.loan = loan;
    }
    
}
