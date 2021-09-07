/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import bank.Accountable;
import bank.Customer;
import bank.MainMenu;
import data.FileController;
import static data.ValidateInput.*;
import static interfaces.Graphics.*;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.*;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 *
 * @author Talha Asghar
 */
public class NewCustomerAccountScenes implements Accountable{
    
    private Customer customer;
    private final double STAGE_WIDTH;
    private final double STAGE_HEIGHT;
    private static Stage parentStage;
    private final DateTimeFormatter DOB_FORMATTER;
    private boolean didFileExistAtStart;
    private ImageView profilePicture;
    private String imagePath ;
    private final boolean onlyToShow;
    private final boolean newAccount;
    private boolean personalInfoVisibility;
    private boolean contactInfoVisibility;
    private boolean accountInfoVisibility;
 
    public NewCustomerAccountScenes(final Stage stage){
    
        imagePath = Images.USER_PROFILE;
        this.DOB_FORMATTER = DateTimeFormatter.ofPattern("dd-MMMM-yyyy(EEEE)");
        STAGE_WIDTH = 800;
        STAGE_HEIGHT = 800;
        parentStage = stage;
        didFileExistAtStart = FileController.isCustomerFileAlreadyPresent();
        if(didFileExistAtStart){
            
            customer = FileController.readCustomerObject();
            imagePath = FileController.readCustomerProfilePicturePath(customer.getAccountNumber());
            newAccount = true;
            personalInfoVisibility = true;
           
        }
        else {
            customer = new Customer();
            newAccount = true;
            personalInfoVisibility = false;
        }
        
        profilePicture = new ImageView(new Image(imagePath,120,120,true,true));
        
        onlyToShow = false;
        contactInfoVisibility = false;
        accountInfoVisibility = false;
        
    }
    
    public NewCustomerAccountScenes(final Customer customer,boolean editable, final Stage stage ){
        this.customer = customer;
        imagePath = FileController.readCustomerProfilePicturePath(customer.getAccountNumber());
        profilePicture = new ImageView(new Image(imagePath,120,120,true,true));
        this.DOB_FORMATTER = DateTimeFormatter.ofPattern("dd-MMMM-yyyy(EEEE)");
        STAGE_WIDTH = 800;
        STAGE_HEIGHT = 800;
        parentStage = stage;
        onlyToShow = !editable;
        newAccount = false;
        
        personalInfoVisibility = false;
        contactInfoVisibility = false;
        accountInfoVisibility = false;
    }  
    
    @Override
    public void createNewAccountScene() {
        
        parentStage.setTitle("New Customer Account");
        parentStage.setScene(createPersonalInfoScene());
    }    
    
    
    public Scene createPersonalInfoScene(){
        
        BorderPane personalInfoLayout = new BorderPane();
        Scene personalInfoScene = new Scene(personalInfoLayout, STAGE_WIDTH, STAGE_HEIGHT);
        
        GridPane personalInfoHeader = new GridPane();
        Text personalInfoHeadingText = new Text("Personal Information");
        personalInfoHeadingText.setFont(new Font("Ubuntu",25));
        Label personalInfoHeading = new Label("", personalInfoHeadingText);
        
        
        personalInfoHeader.setBorder(BOTTOM_LINE);
        
        personalInfoHeader.addRow(0,personalInfoHeading);
        GridPane.setMargin(personalInfoHeading, new Insets(10,0,10,80));
        
        personalInfoLayout.setTop(personalInfoHeader);
        GridPane personalInfo = new GridPane();
        personalInfoLayout.setCenter(personalInfo);
        
        personalInfo.setVgap(20);
        personalInfo.setHgap(30);
        personalInfo.setAlignment(Pos.CENTER);
        
        Button next = new Button("Next");
        Button clear = new Button("Clear");
        Button save = new Button("Save");
        AnchorPane buttons = new AnchorPane();
        AnchorPane.setLeftAnchor(clear,60d);
        AnchorPane.setBottomAnchor(clear,80d);
        AnchorPane.setRightAnchor(save,60d);
        AnchorPane.setBottomAnchor(save,80d);
        AnchorPane.setRightAnchor(next,60d);
        AnchorPane.setBottomAnchor(next,40d);
        buttons.getChildren().addAll(clear,save,next);
        personalInfoLayout.setBottom(buttons);
        
        Button uploadPicture = new Button("Upload Picture");
        GridPane profilePictureArea = new GridPane();
        profilePictureArea.setVgap(30);
        profilePictureArea.addRow(0,profilePicture);
        profilePictureArea.addRow(1, uploadPicture);
        //profilePictureArea.setGridLinesVisible(true);
        GridPane.setMargin(uploadPicture, new Insets(0,0,0,15));
        
        personalInfoLayout.setRight(profilePictureArea);
        BorderPane.setMargin(profilePictureArea, new Insets(30,40,0,0));
        Label accountNumber = new Label("Account Number : ");
        Label fullName = new Label("Full Name : ");
        Label fatherName = new Label("Father Name : ");
        Label dateOfBirth = new Label("Date Of Birth : ");
        Label cnic = new Label("CNIC : ");
        Label gender = new Label("Gender : ");
        Label religion = new Label("Religion : ");
        Label education = new Label("Education : ");
        Label martialStatus = new Label("Martial Status : ");
        Label country = new Label("Country : ");
        Label occupation = new Label("Occupation : ");
        
        TextField enterAccountNumber = new TextField(
                String.format("%,d",customer.getAccountNumber())); 
        enterAccountNumber.setEditable(false);
        TextField enterFullName = new TextField();
        TextField enterFatherName = new TextField();
        DatePicker enterDOB = new DatePicker();
        enterDOB.setEditable(false);
        enterDOB.setPromptText("Select your Date Of Birth");
        TextField enterCNIC = new TextField();
        
        RadioButton male = new RadioButton("Male");
        RadioButton female = new RadioButton("Female");
        ToggleGroup genderGroup = new ToggleGroup();
        male.setToggleGroup(genderGroup);
        female.setToggleGroup(genderGroup);
        
        ObservableList<String> religions = 
                FXCollections.observableArrayList("Islam", "Christianity",
                        "Judaism","Sikhism","Confucanism","Others");
        ComboBox<String> enterReligion = new ComboBox<>(religions);
        enterReligion.setPromptText("Select Your Religion");
        
        ObservableList<String> educationList = 
                FXCollections.observableArrayList("Matric","O-Level","A-Level",
                        "F.Sc (Pre-Engr)","F.Sc (Pre-Med)","ICS","DAE","Intermediate",
                        "B.Sc (2-Yr)","BS (Bachelors 4-Yr)","Masters","M.Phil",
                        "Ph.D");
        ComboBox<String> enterEducation = new ComboBox<>(educationList);
        enterEducation.setPromptText("Select Your Education");
        
        RadioButton married = new RadioButton("Married");
        RadioButton unmarried = new RadioButton("UnMarried");
        ToggleGroup martialStatusGroup = new ToggleGroup();
        married.setToggleGroup(martialStatusGroup);
        unmarried.setToggleGroup(martialStatusGroup);
        
        ObservableList<String> countriesList =
                FXCollections.observableArrayList(FileController.readCountries());
        ComboBox<String> enterCountry = new ComboBox<>(countriesList);
        enterCountry.setPromptText("Select Your Country");
        
        ObservableList<String> occupationsList =
                FXCollections.observableArrayList(FileController.readOccupations());        
        ComboBox<String> enterOccupation = new ComboBox<>(occupationsList);
        enterOccupation.setPromptText("Select Your Occupation");
        
        personalInfo.addRow(0, accountNumber, enterAccountNumber);
        personalInfo.addRow(1,fullName,enterFullName);
        personalInfo.addRow(2, fatherName, enterFatherName);
        personalInfo.addRow(3, dateOfBirth, enterDOB);
        personalInfo.addRow(4, cnic, enterCNIC);
        personalInfo.addRow(5, gender, male, female);
        personalInfo.addRow(6, religion, enterReligion);
        personalInfo.addRow(7, education, enterEducation);
        personalInfo.addRow(8, martialStatus, married, unmarried);
        personalInfo.addRow(9, country, enterCountry);
        personalInfo.addRow(10, occupation, enterOccupation); 

        GridPane.setColumnSpan(enterFullName,2);
        GridPane.setColumnSpan(enterFatherName, 2);
        GridPane.setColumnSpan(enterDOB, 2);
        GridPane.setColumnSpan(enterCNIC,2);
        
        if(newAccount==false && onlyToShow==true){
                save.setVisible(false);
                clear.setVisible(false);
                next.setDisable(false);
                uploadPicture.setVisible(false);
                enterFullName.setEditable(false);
                enterFatherName.setEditable(false);
                enterDOB.setEditable(false);
                enterCNIC.setEditable(false);
                enterReligion.setEditable(false);
                enterEducation.setEditable(false);
                enterCountry.setEditable(false);
                enterOccupation.setEditable(false);
        }else if(newAccount == false && onlyToShow == false){
        
            enterCNIC.setEditable(false);
            save.setDisable(true);
            next.setDisable(true);
        }
        else {
                save.setDisable(true);
                next.setDisable(true);
        }
        
        
        if(newAccount==false ||(newAccount==true && personalInfoVisibility == true ))
        {
           
            enterFullName.setText(customer.getFullName());
            enterFatherName.setText(customer.getFatherName());
            enterDOB.setValue(LocalDate.parse(customer.getDOB(),DOB_FORMATTER));
            enterCNIC.setText(customer.getCNIC());

            switch (customer.getGender()) {
                case "Male":
                    male.setSelected(true);
                    break;
                case "Female":
                    female.setSelected(true);
                    break;
                default:
                    System.out.println("Invalid Gender in Ser");
                    break;
            }

            enterReligion.setValue(customer.getReligion());
            enterEducation.setValue(customer.getEducation());

            switch (customer.getMartialStatus()) {
                case "Married":
                    married.setSelected(true);
                    break;
                case "UnMarried":
                    unmarried.setSelected(true);
                    break;
                default:
                    System.out.println("Invalid Martial Status in Ser");
                    break;
            }

            enterCountry.setValue(customer.getNationality());
            enterOccupation.setValue(customer.getOccupation());
            next.setDisable(false);
            if(customer.getContactNumber() != null){
                contactInfoVisibility = true;
                
            }
        }
        
        uploadPicture.setOnAction(ae->{
            if(newAccount == true){
                imagePath = uploadUserPicture();
                profilePicture.setImage(new Image(imagePath,120,120,true,true));
            }
            else if(newAccount == false){
                imagePath = uploadUserPicture();
                FileController.updateCustomerProfilePicture(Long.toString(customer.getAccountNumber()), imagePath);
                profilePicture.setImage(new Image(imagePath,120,120,true,true));
                save.setDisable(false);
            }
        });
        
        enterFullName.setOnMousePressed(ae->{
            enterFullName.setEffect(null);
        });
        enterFatherName.setOnMousePressed(ae->{
            enterFatherName.setEffect(null);
        });
        enterCNIC.setOnMousePressed(ae->{
            enterCNIC.setEffect(null);
        });
        
        
        enterDOB.setOnAction(ae ->{
            customer.setDOB(enterDOB.getValue().format(DOB_FORMATTER));
            save.setDisable(false);
        });
        
        male.setOnAction(ae->{
            customer.setGender(male.getText());
            save.setDisable(false);
        });
        
        female.setOnAction(ae->{
            customer.setGender(female.getText());
            save.setDisable(false);
        });
        
        enterReligion.setOnAction(ae->{
            customer.setReligion(enterReligion.getValue());
            save.setDisable(false);
        });
        
        enterEducation.setOnAction(ae->{
            customer.setEducation(enterEducation.getValue());
            save.setDisable(false);
        });
        
        married.setOnAction(ae->{
            customer.setMartialStatus(married.getText());
            save.setDisable(false);
        });
        
        unmarried.setOnAction(ae->{
            customer.setMartialStatus(unmarried.getText());
            save.setDisable(false);
        });
        
        enterCountry.setOnAction(ae->{
            customer.setNationality(enterCountry.getValue());
            save.setDisable(false);
        });
        
        enterOccupation.setOnAction(ae->{
            customer.setOccupation(enterOccupation.getValue());
            save.setDisable(false);
        });
        
        enterFullName.setOnKeyPressed(ae->{
            save.setDisable(false);
        });
        enterFatherName.setOnKeyPressed(ae->{
            save.setDisable(false);
        });
        enterCNIC.setOnKeyPressed(ae->{    
            save.setDisable(false);
        });
        
        save.setOnAction(ae ->{
        
            boolean shallGoNext = false;
            
            if(validateNames(enterFullName.getText().trim())){
                shallGoNext = true;
                customer.setFullName(enterFullName.getText().trim());
            }
            else{
                shallGoNext = false;
                enterFullName.setEffect(INNER_SHADOW_RED);
            }
            
            if(shallGoNext && validateNames(enterFatherName.getText().trim())){
                shallGoNext = true;
                customer.setFatherName(enterFatherName.getText().trim());
            }
            else if(shallGoNext){
                shallGoNext = false;
                enterFatherName.setEffect(INNER_SHADOW_RED);
            }
            
            if(shallGoNext && enterDOB.getValue() == null){
                shallGoNext = false;
                PopUps.showInvalidDobPopUp();
            }
            
            if(shallGoNext && validateCNIC(enterCNIC.getText().trim())){
                shallGoNext = true;
                customer.setCNIC(enterCNIC.getText().trim());
            }
            else if(shallGoNext){
                shallGoNext = false;
                enterCNIC.setEffect(INNER_SHADOW_RED);
            }
            
            if(shallGoNext && (!(male.isSelected() || female.isSelected()))){
                shallGoNext = false;
                PopUps.showInvalidGenderPopUp();
            }
            
            if(shallGoNext && enterReligion.getValue() == null){
                shallGoNext = false;
                PopUps.showInvalidReligionPopUp();
            }
            
            if(shallGoNext && enterEducation.getValue() == null){
                shallGoNext = false;
                PopUps.showInvalidEducationPopUp();
            }
            
            if(shallGoNext && (!(married.isSelected() || unmarried.isSelected()))){
                shallGoNext = false;
                PopUps.showInvalidMartialStatusPopUp();
            }
            
            if(shallGoNext && enterCountry.getValue() == null){
                shallGoNext = false;
                PopUps.showInvalidCountryPopUp();
            }
            
            if(shallGoNext && enterOccupation.getValue() == null){
                shallGoNext = false;
                PopUps.showInvalidOccupationPopUp();
            }
            
            if(shallGoNext){
                FileController.writeCustomerObject(customer);
                next.setDisable(false);
                save.setDisable(true);
            }else {
                next.setDisable(true);
            }
            
        });
        next.setOnAction(ae->{
            
            parentStage.setScene(this.createContactInfoScene());
            personalInfoVisibility = true;
            
        });
        
        clear.setOnAction(ae->{

            enterFullName.setEffect(null);
            enterFatherName.setEffect(null);
            enterCNIC.setEffect(null);
            enterFullName.clear();
            enterFatherName.clear();
            enterDOB.setPromptText("Select your Date Of Birth");
            enterCNIC.clear();
            
            if(male.isSelected()) {
                male.setSelected(false);
            }
            if(female.isSelected()) {
                female.setSelected(false);
            }
            
            enterReligion.setValue(null);
            enterReligion.setPromptText("Select Your Religion");
            enterEducation.setValue(null);
            enterEducation.setPromptText("Select Your Education");
          
            if(married.isSelected()) {
                married.setSelected(false);
            }
            if(unmarried.isSelected()) {
                unmarried.setSelected(false);
            }
            
            enterCountry.setValue(null);
            enterCountry.setPromptText("Select Your Country");
            enterOccupation.setValue(null);
            enterOccupation.setPromptText("Select Your Occupation");
                        
            save.setDisable(true);
            next.setDisable(true);
            
        });
        
        return personalInfoScene;
       
    }       // end of "createPersonalInfoScene"
    
    public Scene createContactInfoScene(){
        
        BorderPane contactInfoLayout = new BorderPane();
        Scene contactInfoScene = new Scene(contactInfoLayout, STAGE_WIDTH, STAGE_HEIGHT);
        
        GridPane contactInfoHeader = new GridPane();
        Text contactInfoHeadingText = new Text("Contact Information");
        contactInfoHeadingText.setFont(new Font("Ubuntu",25));
        Label contactInfoHeading = new Label("", contactInfoHeadingText);

        contactInfoHeader.setBorder(BOTTOM_LINE);
        contactInfoHeader.addRow(0,contactInfoHeading);
        GridPane.setMargin(contactInfoHeading, new Insets(10,0,10,80));
        
        contactInfoLayout.setTop(contactInfoHeader);
        GridPane contactInfo = new GridPane();
        contactInfoLayout.setCenter(contactInfo);
        
        contactInfo.setVgap(20);
        contactInfo.setHgap(30);
        contactInfo.setAlignment(Pos.CENTER);
        
        Button next = new Button("Next");
        Button clear = new Button("Clear");
        Button save = new Button("Save");
        Button back = new Button("Back");
        AnchorPane buttons = new AnchorPane();
        AnchorPane.setLeftAnchor(clear,60d);
        AnchorPane.setBottomAnchor(clear,80d);
        AnchorPane.setRightAnchor(save,60d);
        AnchorPane.setBottomAnchor(save,80d);
        AnchorPane.setLeftAnchor(back,60d);
        AnchorPane.setBottomAnchor(back,40d);
        AnchorPane.setRightAnchor(next,60d);
        AnchorPane.setBottomAnchor(next,40d);
        buttons.getChildren().addAll(clear,save,back,next);
        contactInfoLayout.setBottom(buttons);
        
        Label contactNum = new Label("Contact Number : ");
        Label email = new Label("E-mail : ");
        Label domicile = new Label("Domicile");
        Label address = new Label("Address : ");
        Label city = new Label("City : ");
        Label province = new Label("Province : ");
        Label mailingAddress = new Label("Mailing Address");
        
        TextField enterContactNum = new TextField();
        TextField enterEmail = new TextField();
        TextField enterDomicile = new TextField();
        TextArea enterAddress = new TextArea();
        ComboBox<String> enterCity = new ComboBox<>();
        ComboBox<String> enterProvince = new ComboBox<>();
        enterCity.setEditable(true);
        enterProvince.setEditable(true);
        
        TextArea enterMailingAddress = new TextArea();
        
        contactInfo.addRow(0, contactNum, enterContactNum);
        contactInfo.addRow(1, email, enterEmail);
        contactInfo.addRow(2, domicile, enterDomicile);
        contactInfo.addRow(3, address, enterAddress);
        contactInfo.addRow(4, city, enterCity);
        contactInfo.addRow(5, province, enterProvince);
        contactInfo.addRow(6, mailingAddress, enterMailingAddress);
        
        if(newAccount== false && onlyToShow==true){
                save.setVisible(false);
                clear.setVisible(false);
                next.setDisable(false);
                enterContactNum.setEditable(false);
                enterEmail.setEditable(false);
                enterDomicile.setEditable(false);
                enterAddress.setEditable(false);
                enterCity.setEditable(false);
                enterProvince.setEditable(false);
                enterMailingAddress.setEditable(false);
        }
        else {
                save.setDisable(true);
                next.setDisable(true);
        }
        if((newAccount == false) || (newAccount==true && contactInfoVisibility == true )){
            
            enterContactNum.setText(customer.getContactNumber());
            enterEmail.setText(customer.getEmail());
            enterDomicile.setText(customer.getDomicile());
            enterAddress.setText(customer.getAddress());
            enterMailingAddress.setText(customer.getMailingAddress());
            enterCity.setValue(customer.getCity());
            enterProvince.setValue(customer.getProvince());
            next.setDisable(false);
            if(customer.getPassword() != null){
            
                accountInfoVisibility = true;
            }

        }
        
        enterContactNum.setOnMousePressed(ae->{
            enterContactNum.setEffect(null);
        });
        enterEmail.setOnMousePressed(ae->{
            enterEmail.setEffect(null);
        });
        enterDomicile.setOnMousePressed(ae->{
            enterDomicile.setEffect(null);
        });
        enterAddress.setOnMouseClicked(ae->{
            enterAddress.setEffect(null);
        });
        enterMailingAddress.setOnMouseClicked(ae->{
            enterMailingAddress.setEffect(null);
            
        });

        enterContactNum.setOnKeyPressed(ae->{
            save.setDisable(false);
        });
        enterEmail.setOnKeyPressed(ae->{
            save.setDisable(false);
        });
        enterDomicile.setOnKeyPressed(ae->{
            save.setDisable(false);
        });
        enterCity.setOnMouseClicked(ae->{
            save.setDisable(false);
        });
        enterProvince.setOnMouseClicked(ae->{
            save.setDisable(false);
        });
        enterAddress.setOnKeyPressed(ae->{
            save.setDisable(false);
        });
        enterMailingAddress.setOnKeyPressed(ae->{
            save.setDisable(false);
        });        
        
        
        save.setOnAction(ae -> {
            
            boolean shallGoNext = false;
            
            if(validatePhoneNumber(enterContactNum.getText().trim())){
                shallGoNext = true;
                customer.setContactNum(enterContactNum.getText().trim());
            }
            else{
                shallGoNext = false;
                enterContactNum.setEffect(INNER_SHADOW_RED);
            }
                
            if(shallGoNext && validateEmail(enterEmail.getText().trim())){
                shallGoNext = true;
                customer.setEmail(enterEmail.getText().trim());
            }
            else if(shallGoNext){
                shallGoNext = false;
                enterEmail.setEffect(INNER_SHADOW_RED);
            }
            
            if(shallGoNext && validateDomicile(enterDomicile.getText().trim())){
                shallGoNext = true;
                customer.setDomicile(enterDomicile.getText().trim());
            }
            else if(shallGoNext){
                shallGoNext = false;
                enterDomicile.setEffect(INNER_SHADOW_RED);
            }
            
            if(shallGoNext && !enterAddress.getText().trim().equals("")){
                shallGoNext = true;
                customer.setAddress(enterAddress.getText().trim());
            }
            else if(shallGoNext){
                shallGoNext = false;
                enterAddress.setEffect(INNER_SHADOW_RED);
            }
              
            if(shallGoNext && !enterMailingAddress.getText().trim().equals("")){
                shallGoNext = true;
                customer.setMailingAddress(enterMailingAddress.getText().trim());
            }
            else if(shallGoNext){
                shallGoNext = false;
                enterMailingAddress.setEffect(INNER_SHADOW_RED);
            }
            
            if(shallGoNext && (enterCity.getValue() == null)){
                shallGoNext = false;
                PopUps.showInvalidCityPopUp();
            }
            else if(shallGoNext){
                
                customer.setCity(enterCity.getValue());
            }
            
            if(shallGoNext && (enterProvince.getValue() == null)){
                shallGoNext = false;
                PopUps.showInvalidProvincePopUp();
            }
            else if(shallGoNext){
                
                customer.setProvince(enterProvince.getValue());
            }
            
            if(shallGoNext){
                
                FileController.writeCustomerObject(customer);
                save.setDisable(true);
                next.setDisable(false);
            }else {
                next.setDisable(true);
            }
        });
        
        next.setOnAction(ae->{
       
            parentStage.setScene(this.createAccountInfoScene());
            contactInfoVisibility = true;
            
        });
        
        back.setOnAction(ae->{
            parentStage.setScene(this.createPersonalInfoScene());
        });
        
        clear.setOnAction(ae ->{
            
            enterContactNum.setEffect(null);
            enterEmail.setEffect(null);
            enterAddress.setEffect(null);
            enterMailingAddress.setEffect(null);
            enterDomicile.setEffect(null);
            enterContactNum.clear();
            enterEmail.clear();
            enterAddress.clear();
            enterMailingAddress.clear();
            enterDomicile.clear();
            enterCity.setValue(null);
            enterCity.setPromptText("Select Your City");
            enterProvince.setValue(null);
            enterProvince.setPromptText("Select your Province");
            save.setDisable(true);
            next.setDisable(true);            
            
        });
        
        return contactInfoScene;
    
    }       // end of createContactInfoScene
    
    public Scene createAccountInfoScene(){
    
        BorderPane accountInfoLayout = new BorderPane();
        Scene accountInfoScene = new Scene(accountInfoLayout, STAGE_WIDTH, STAGE_HEIGHT);
        
        GridPane accountInfoHeader = new GridPane();
        Text accountInfoHeadingText = new Text("Account Information");
        accountInfoHeadingText.setFont(new Font("Ubuntu",25));
        Label accountInfoHeading = new Label("", accountInfoHeadingText);

        accountInfoHeader.setBorder(BOTTOM_LINE);
        accountInfoHeader.addRow(0,accountInfoHeading);
        GridPane.setMargin(accountInfoHeading, new Insets(10,0,10,80));
        
        accountInfoLayout.setTop(accountInfoHeader);
        GridPane accountInfo = new GridPane();
        accountInfoLayout.setCenter(accountInfo);
        
        accountInfo.setVgap(20);
        accountInfo.setHgap(30);
        accountInfo.setAlignment(Pos.CENTER);
        
        Button done = new Button("Done");
        Button clear = new Button("Clear");
        Button save = new Button("Save");
        Button back = new Button("Back");
        AnchorPane buttons = new AnchorPane();
        AnchorPane.setLeftAnchor(clear,60d);
        AnchorPane.setBottomAnchor(clear,80d);
        AnchorPane.setRightAnchor(save,60d);
        AnchorPane.setBottomAnchor(save,80d);
        AnchorPane.setLeftAnchor(back,60d);
        AnchorPane.setBottomAnchor(back,40d);
        AnchorPane.setRightAnchor(done,60d);
        AnchorPane.setBottomAnchor(done,40d);
        buttons.getChildren().addAll(clear,save,back,done);
        accountInfoLayout.setBottom(buttons);
        
        Label accountTitle = new Label("Account Title : ");
        Label accountType = new Label("Account Type : ");
        Label userName = new Label("User Name : ");
        Label balance = new Label("Initial Balance : ");
        Label loan = new Label("Initial Loan : ");
        Label password = new Label("Password : ");
        Label passwordAgain = new Label("Enter Password Again : ");
        CheckBox showPassword = new CheckBox("Show Password");
        TextField enterAccountTitle = new TextField();
        
        ObservableList<String> accountTypes = FXCollections.observableArrayList(
            "Current Account", "Saving Account", "Student Account");
        ComboBox<String> enterAccountType = new ComboBox<>(accountTypes);
        
        TextField enterUserName = new TextField();
        TextField enterBalance = new TextField("50000");
        TextField enterLoan = new TextField("0");
        PasswordField enterPassword = new PasswordField();
        PasswordField enterPasswordAgain = new PasswordField();
        
        accountInfo.addRow(0, accountTitle, enterAccountTitle);
        accountInfo.addRow(1, accountType, enterAccountType);
        accountInfo.addRow(2, userName, enterUserName);
        accountInfo.addRow(3, balance, enterBalance);
        accountInfo.addRow(4, loan, enterLoan);
        accountInfo.addRow(5, password, enterPassword);
        accountInfo.addRow(6, passwordAgain, enterPasswordAgain);
        accountInfo.addRow(7, showPassword);

        if(newAccount == false && onlyToShow==true){
            
                save.setVisible(false);
                clear.setVisible(false);
                done.setVisible(false);
                enterAccountTitle.setEditable(false);
                enterAccountType.setEditable(false);
                enterUserName.setEditable(false);
                enterBalance.setEditable(false);
                enterLoan.setEditable(false);
                enterPassword.setEditable(false);
                enterPasswordAgain.setEditable(false);
                
        }else if(newAccount == false && onlyToShow==false){
            enterUserName.setEditable(false);
            save.setDisable(true);
            done.setDisable(true);
            
        }
        else {
                save.setDisable(true);
                done.setDisable(true);
        }
        
        if(newAccount==false || (newAccount==true && accountInfoVisibility == true )){
            
            enterAccountTitle.setText(customer.getAccountTitle());
            enterAccountType.setValue(customer.getAccountType());
            enterUserName.setText(customer.getUserName());
            enterBalance.setText(customer.getBalance().toString());
            enterLoan.setText(customer.getLoan().toString());
            enterPassword.setText(customer.getPassword());
            enterPasswordAgain.setText(customer.getPassword());
            done.setDisable(false);
        }        
        
        
        enterAccountTitle.setText(customer.getFullName().concat(
                (customer.getGender().equals("Male") ? " S/O " : " D/O ")).concat(
                        customer.getFatherName()));
        
        enterAccountType.setOnAction(ae->{
            customer.setAccountType(enterAccountType.getValue());
        });
        
        enterAccountTitle.setOnMousePressed(ae->{
            enterAccountTitle.setEffect(null);
            save.setDisable(false);
        });
        
        enterUserName.setOnMousePressed(ae->{
            enterUserName.setEffect(null);
        });
        
        
        enterBalance.setOnMousePressed(ae->{
            enterBalance.setEffect(null);
        });
        
        
        enterLoan.setOnMousePressed(ae->{
            enterLoan.setEffect(null);
        });
        
        enterPassword.setOnMousePressed(ae->{
            enterPassword.setEffect(null);
        });
        
        enterPasswordAgain.setOnMousePressed(ae->{
            enterPasswordAgain.setEffect(null);
        });
        
        enterUserName.setOnKeyPressed(ae->{
            save.setDisable(false);
        });
        
        enterBalance.setOnKeyPressed(ae->{
            save.setDisable(false);
        });
        
        enterLoan.setOnKeyPressed(ae->{
            save.setDisable(false);
        });
        
        enterPassword.setOnKeyPressed(ae->{
            save.setDisable(false);
        });
        enterPasswordAgain.setOnKeyPressed(ae->{
            save.setDisable(false);
        });
        
        showPassword.setOnAction(ae->{
            if(showPassword.isSelected()){
                if(enterPassword.getText().equals(enterPasswordAgain.getText())){
                    String tempPassword = enterPassword.getText();
                    enterPassword.clear();
                    enterPassword.setPromptText(tempPassword);
                    enterPassword.setStyle("-fx-prompt-text-fill: FORESTGREEN");
                    enterPasswordAgain.setVisible(false);
                    passwordAgain.setVisible(false);
                }
                else{
                    String tempPassword = enterPassword.getText();
                    enterPassword.clear();
                    enterPassword.setPromptText(tempPassword);
                    enterPassword.setStyle("-fx-prompt-text-fill: FORESTGREEN");
                    String tempPasswordAgain = enterPasswordAgain.getText();
                    enterPasswordAgain.clear();
                    enterPasswordAgain.setPromptText(tempPasswordAgain);
                    enterPasswordAgain.setStyle("-fx-prompt-text-fill: RED");
                    
                }
            }
            else{
                
                if(!passwordAgain.isVisible()){
                    enterPassword.setText(enterPassword.getPromptText());
                    enterPasswordAgain.setText(enterPassword.getPromptText());
                    enterPasswordAgain.setVisible(true);
                    passwordAgain.setVisible(true);
                    enterPassword.setPromptText("");
                }
                else{
                    enterPassword.setText(enterPassword.getPromptText());
                    enterPasswordAgain.setText(enterPasswordAgain.getPromptText());
                    enterPassword.setPromptText("");
                    enterPasswordAgain.setPromptText("");
                }
            }
        
        });
        save.setOnAction(ae->{
            
            boolean shallGoNext = false;
            
            if(!enterAccountTitle.getText().trim().equals("")){
                shallGoNext = true;
                customer.setAccountTitle(enterAccountTitle.getText().trim());
            }
            else{
                shallGoNext = false;
                enterAccountTitle.setEffect(INNER_SHADOW_RED);
            }
            
            if(shallGoNext && !enterUserName.getText().trim().equals("")){
                
                if(newAccount == true){
                
                    if(FileController.doesCUserNameAlreadyExists(enterUserName.getText().trim())){
                        shallGoNext = false;
                        PopUps.showInvalidUserNameError();
                        enterUserName.setEffect(INNER_SHADOW_RED);
                    }else{
                        shallGoNext = true;
                        customer.setUserName(enterUserName.getText().trim());
                    }
                }
            }
            else if(shallGoNext){
                shallGoNext = false;
                enterUserName.setEffect(INNER_SHADOW_RED);
            }
            
            if(shallGoNext && validateMoney(enterBalance.getText().trim())){
                
                customer.setBalance(BigDecimal.valueOf(
                        Double.parseDouble(enterBalance.getText().trim())));
                shallGoNext = true;
            }else if(shallGoNext){
                
                enterBalance.setEffect(INNER_SHADOW_RED);
                shallGoNext = false;
            }
            
            if(shallGoNext && validateMoney(enterLoan.getText().trim())){
                
                customer.setLoan(BigDecimal.valueOf(
                        Double.parseDouble(enterLoan.getText().trim())));
                shallGoNext = true;
            }else if(shallGoNext){
                
                enterLoan.setEffect(INNER_SHADOW_RED);
                shallGoNext = false;
            }
            
            if(shallGoNext && !enterPassword.getText().equals("")){
                
                if(enterPasswordAgain.getText().equals(
                        enterPassword.getText()))
                {
                    shallGoNext = true;
                    customer.setPassword(enterPassword.getText());
                }
                else{
                    shallGoNext = false;
                    PopUps.showPasswordMisMatchPopUp();
                    enterPassword.setEffect(INNER_SHADOW_RED);
                    enterPasswordAgain.setEffect(INNER_SHADOW_RED);
                }
            }
            else if(shallGoNext){
                
                shallGoNext = false;
                enterPassword.setEffect(INNER_SHADOW_RED);
                enterPasswordAgain.setEffect(INNER_SHADOW_RED);
                
            }
                
            if(shallGoNext){
                FileController.writeCustomerObject(customer);
                done.setDisable(false);
                accountInfoVisibility = true;
            }
            else {
                done.setDisable(true);
            }
            
                
        });
        
        done.setOnAction(ae->{
        
            if(newAccount==false && onlyToShow ==false){
                if(MainMenu.HANDLE_DATABASE.tryCustomerDataUpdate(customer,"Customer")){
                    
                    try {
                        Files.deleteIfExists(Paths.get("files/customer.ser"));
                        PopUps.showUpdateCompleteMessage();
                        parentStage.close();
                    } catch (IOException ex) {
                        System.out.println(ex);
                    }
                }
                else{
                    PopUps.showAccountUpdateError();
                }
            }
            else if(newAccount == true){
                if(MainMenu.HANDLE_DATABASE.tryAddingNewCustomer(customer, "Customer")){
                    FileController.writeCustomerAccount(customer.getAccountNumber(),
                            customer.getUserName(),imagePath);
                    PopUps.showAccountCreationMessage();
                    parentStage.close();
                }
                else {
                    PopUps.showAccountCreationError();
                }
            }
        });
        
        back.setOnAction(ae->{
            parentStage.setScene(this.createContactInfoScene());    
        });
        
        clear.setOnAction(ae->{
            
            enterAccountTitle.setEffect(null);
            enterUserName.setEffect(null);
            enterPassword.setEffect(null);
            
            enterAccountTitle.clear();
            enterAccountType.setValue(null);
            enterAccountType.setPromptText("Select your Account Type");
            enterUserName.clear();
            enterPassword.clear();
            enterPasswordAgain.clear();
            save.setDisable(true);
            done.setDisable(true);            
            
        });
        
        return accountInfoScene;
    }
    
    public static String uploadUserPicture(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Upload Picture");
        fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("Image Files", "*.png","*.jpg")
        );
        File selectedPicture = fileChooser.showOpenDialog(parentStage);
        if(selectedPicture != null){
            return selectedPicture.toURI().toString();
        }
        return Images.USER_PROFILE;
    }

}

    