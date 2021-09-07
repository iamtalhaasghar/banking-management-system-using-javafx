/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank;

import data.FileController;
import java.math.BigDecimal;

/**
 *
 * @author Talha Asghar
 */
public class Employee extends Person {

    private long employeeId;
    private String employeeType;
    private BigDecimal salary;
    
    public Employee(){
        employeeId = FileController.getAvailableEmployeeId();
    
    }

    public Employee(long employeeId) {
        this.employeeId = employeeId;
    }

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(String employeeType) {
        this.employeeType = employeeType;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }
}
