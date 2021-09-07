/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank;

import java.io.Serializable;

/**
 *
 * @author Talha Asghar
 */
public abstract class Person implements Serializable{

    private static final long serialVersionUID = 1L;
    
    private String fullName;
    private String fatherName;
    private String dob;
    private String cnic;
    private String gender;
    private String religion;
    private String education;
    private String martialStatus;
    private String nationality;
    private String occupation;
    
    private String contactNum;
    private String email;
    private String domicile;
    private String address;
    private String city;
    private String province;
    private String mailingAddress;
    
    private String userName;
    private String password;

    public Person(){}
    
    public Person(String fullName, String fatherName, String dob, String cnic, String gender, String religion, String education, String martialStatus, String nationality, String occupation, String contactNum, String email, String domicile, String address, String city, String province, String mailingAddress, String userName, String password) {
        
        this.fullName = fullName;
        this.fatherName = fatherName;
        this.dob = dob;
        this.cnic = cnic;
        this.gender = gender;
        this.religion = religion;
        this.education = education;
        this.martialStatus = martialStatus;
        this.nationality = nationality;
        this.occupation = occupation;
        this.contactNum = contactNum;
        this.email = email;
        this.domicile = domicile;
        this.address = address;
        this.city = city;
        this.province = province;
        this.mailingAddress = mailingAddress;
        this.userName = userName;
        this.password = password;
    }

    public Person(String fullName, String fatherName, String dob, String cnic, String gender, String religion, String education, String martialStatus, String nationality, String occupation, String contactNum, String email, String domicile, String address, String city, String province, String mailingAddress) {
        this.fullName = fullName;
        this.fatherName = fatherName;
        this.dob = dob;
        this.cnic = cnic;
        this.gender = gender;
        this.religion = religion;
        this.education = education;
        this.martialStatus = martialStatus;
        this.nationality = nationality;
        this.occupation = occupation;
        this.contactNum = contactNum;
        this.email = email;
        this.domicile = domicile;
        this.address = address;
        this.city = city;
        this.province = province;
        this.mailingAddress = mailingAddress;
    }

    
    
    
    public String getFullName() {
        return fullName;
    }


    public String getFatherName() {
        return fatherName;
    }

    public String getDOB() {
        return dob;
    }

    public String getCNIC() {
        return cnic;
    }

    public String getGender() {
        return gender;
    }

    public String getReligion() {
        return religion;
    }

    public String getEducation() {
        return education;
    }

    public String getMartialStatus() {
        return martialStatus;
    }

    public String getNationality() {
        return nationality;
    }

    public String getOccupation() {
        return occupation;
    }

    public String getContactNumber() {
        return contactNum;
    }

    public String getEmail() {
        return email;
    }

    public String getDomicile() {
        return domicile;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getProvince() {
        return province;
    }

    public String getMailingAddress() {
        return mailingAddress;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public void setDOB(String dob) {
        this.dob = dob;
    }

    public void setCNIC(String cnic) {
        this.cnic = cnic;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public void setMartialStatus(String martialStatus) {
        this.martialStatus = martialStatus;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public void setContactNum(String contactNum) {
        this.contactNum = contactNum;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDomicile(String domicile) {
        this.domicile = domicile;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setMailingAddress(String mailingAddress) {
        this.mailingAddress = mailingAddress;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
