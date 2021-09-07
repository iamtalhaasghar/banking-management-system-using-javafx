/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import bank.Accountable;
import bank.Employee;
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
public class NewEmployeeAccountScenes implements Accountable{
    
    private Employee employee;
    private final double STAGE_WIDTH;
    private final double STAGE_HEIGHT;
    private final Stage parentStage;
    private final DateTimeFormatter DOB_FORMATTER;
    private ImageView profilePicture;
    private boolean didFileExistAtStart;
    private String imagePath ;
    private final boolean onlyToShow;
    private final boolean newAccount;
    private boolean personalInfoVisibility;
    private boolean contactInfoVisibility;
    private boolean accountInfoVisibility;
    
    public NewEmployeeAccountScenes(final Stage stage){
        
        imagePath = Images.ADMIN_PROFILE;
        this.DOB_FORMATTER = DateTimeFormatter.ofPattern("dd-MMMM-yyyy(EEEE)");
        STAGE_WIDTH = 800;
        STAGE_HEIGHT = 800;
        parentStage = stage;
        didFileExistAtStart = FileController.isEmployeeFileAlreadyPresent();
        if(didFileExistAtStart){
            employee = FileController.readEmployeeObject();
            imagePath = FileController.readAdminProfilePicturePath(employee.getEmployeeId());
            newAccount = true;
            personalInfoVisibility = true;
        }
        else {
            employee = new Employee();
            newAccount = true;
            personalInfoVisibility = false;
        }
        
        profilePicture = new ImageView(new Image(imagePath,120,120,true,true));
        onlyToShow = false;
        contactInfoVisibility = false;
        accountInfoVisibility = false;
    }
    
    public NewEmployeeAccountScenes(final Employee employee,boolean editable, final Stage stage ){
        this.employee = employee;
        imagePath = FileController.readAdminProfilePicturePath(employee.getEmployeeId());
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

        parentStage.setTitle("New Employee Account");
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
        Label employeeID = new Label("Employee ID : ");
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
        
        TextField enterEmployeeID = new TextField(
                String.format("%,d",employee.getEmployeeId())); 
        enterEmployeeID.setEditable(false);
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
        
        personalInfo.addRow(0, employeeID, enterEmployeeID);
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
        
            enterFullName.setText(employee.getFullName());
            enterFatherName.setText(employee.getFatherName());
            enterDOB.setValue(LocalDate.parse(employee.getDOB(),DOB_FORMATTER));
            enterCNIC.setText(employee.getCNIC());

            switch (employee.getGender()) {
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

            enterReligion.setValue(employee.getReligion());
            enterEducation.setValue(employee.getEducation());

            switch (employee.getMartialStatus()) {
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

            enterCountry.setValue(employee.getNationality());
            enterOccupation.setValue(employee.getOccupation());
            next.setDisable(false);
            if(employee.getContactNumber() != null){
                contactInfoVisibility = true;
                
            }
        }
        
        uploadPicture.setOnAction(ae->{
            if(newAccount == true){
                imagePath = uploadAdminPicture();
                profilePicture.setImage(new Image(imagePath,120,120,true,true));
            }
            else if(newAccount == false){
                imagePath = uploadAdminPicture();
                FileController.updateEmployeeProfilePicture(Long.toString(employee.getEmployeeId()), imagePath);
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
            employee.setDOB(enterDOB.getValue().format(DOB_FORMATTER));
            save.setDisable(false);
        });
        
        male.setOnAction(ae->{
            employee.setGender(male.getText());
            save.setDisable(false);
        });
        
        female.setOnAction(ae->{
            employee.setGender(female.getText());
            save.setDisable(false);
        });
        
        enterReligion.setOnAction(ae->{
            employee.setReligion(enterReligion.getValue());
            save.setDisable(false);
        });
        
        enterEducation.setOnAction(ae->{
            employee.setEducation(enterEducation.getValue());
            save.setDisable(false);
        });
        
        married.setOnAction(ae->{
            employee.setMartialStatus(married.getText());
            save.setDisable(false);
        });
        
        unmarried.setOnAction(ae->{
            employee.setMartialStatus(unmarried.getText());
            save.setDisable(false);
        });
        
        enterCountry.setOnAction(ae->{
            employee.setNationality(enterCountry.getValue());
            save.setDisable(false);
        });
        
        enterOccupation.setOnAction(ae->{
            employee.setOccupation(enterOccupation.getValue());
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
                employee.setFullName(enterFullName.getText().trim());
            }
            else{
                shallGoNext = false;
                enterFullName.setEffect(INNER_SHADOW_RED);
            }
            
            if(shallGoNext && validateNames(enterFatherName.getText().trim())){
                shallGoNext = true;
                employee.setFatherName(enterFatherName.getText().trim());
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
                employee.setCNIC(enterCNIC.getText().trim());
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
                FileController.writeEmployeeObject(employee);
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
            
            enterContactNum.setText(employee.getContactNumber());
            enterEmail.setText(employee.getEmail());
            enterDomicile.setText(employee.getDomicile());
            enterAddress.setText(employee.getAddress());
            enterMailingAddress.setText(employee.getMailingAddress());
            enterCity.setValue(employee.getCity());
            enterProvince.setValue(employee.getProvince());
            next.setDisable(false);
            if(employee.getPassword() != null){
            
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
                employee.setContactNum(enterContactNum.getText().trim());
            }
            else{
                shallGoNext = false;
                enterContactNum.setEffect(INNER_SHADOW_RED);
            }
                
            if(shallGoNext && validateEmail(enterEmail.getText().trim())){
                shallGoNext = true;
                employee.setEmail(enterEmail.getText().trim());
            }
            else if(shallGoNext){
                shallGoNext = false;
                enterEmail.setEffect(INNER_SHADOW_RED);
            }
            
            if(shallGoNext && validateDomicile(enterDomicile.getText().trim())){
                shallGoNext = true;
                employee.setDomicile(enterDomicile.getText().trim());
            }
            else if(shallGoNext){
                shallGoNext = false;
                enterDomicile.setEffect(INNER_SHADOW_RED);
            }
            
            if(shallGoNext && !enterAddress.getText().trim().equals("")){
                shallGoNext = true;
                employee.setAddress(enterAddress.getText().trim());
            }
            else if(shallGoNext){
                shallGoNext = false;
                enterAddress.setEffect(INNER_SHADOW_RED);
            }
              
            if(shallGoNext && !enterMailingAddress.getText().trim().equals("")){
                shallGoNext = true;
                employee.setMailingAddress(enterMailingAddress.getText().trim());
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
                shallGoNext = true;
                employee.setCity(enterCity.getValue());
            }
            
            if(shallGoNext && (enterProvince.getValue() == null)){
                shallGoNext = false;
                PopUps.showInvalidProvincePopUp();
            }
            else if(shallGoNext){
                shallGoNext = true;
                employee.setProvince(enterProvince.getValue());
            }
            
            if(shallGoNext){
                
                FileController.writeEmployeeObject(employee);
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
        
        Label employeeType = new Label("Employee Type : ");
        Label userName = new Label("User Name : ");
        Label salary = new Label("Salary : ");
        Label password = new Label("Password : ");
        Label passwordAgain = new Label("Enter Password Again : ");
        CheckBox showPassword = new CheckBox("Show Password");
        
        
        ObservableList<String> employeeTypes = FXCollections.observableArrayList(
            "Admin");
        ComboBox<String> enterEmployeeTypes = new ComboBox<>(employeeTypes);
        enterEmployeeTypes.setPromptText("Select an Employee Type");
        TextField enterUserName = new TextField();
        TextField enterSalary = new TextField("50000");
        PasswordField enterPassword = new PasswordField();
        PasswordField enterPasswordAgain = new PasswordField();
        
        accountInfo.addRow(0, employeeType, enterEmployeeTypes);
        accountInfo.addRow(1, userName, enterUserName);
        accountInfo.addRow(2, salary, enterSalary);
        accountInfo.addRow(3, password, enterPassword);
        accountInfo.addRow(4, passwordAgain, enterPasswordAgain);
        accountInfo.addRow(5, showPassword);

        if(newAccount == false && onlyToShow==true){
            
                save.setVisible(false);
                clear.setVisible(false);
                done.setVisible(false);
                enterEmployeeTypes.setEditable(false);
                enterUserName.setEditable(false);
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
            
            enterEmployeeTypes.setValue(employee.getEmployeeType());
            enterUserName.setText(employee.getUserName());
            enterSalary.setText(employee.getSalary().toString());
            enterPassword.setText(employee.getPassword());
            enterPasswordAgain.setText(employee.getPassword());
            done.setDisable(false);
        }        
        
        
        
        enterEmployeeTypes.setOnAction(ae->{
            employee.setEmployeeType(enterEmployeeTypes.getValue());
        });
        
        enterUserName.setOnMousePressed(ae->{
            enterUserName.setEffect(null);
        });
        
        enterSalary.setOnMousePressed(ae->{
            enterSalary.setEffect(null);
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
        

        enterSalary.setOnKeyPressed(ae->{
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
            
            if(!enterUserName.getText().trim().equals("")){
                if(newAccount == true){
                    if(FileController.doesEUserNameAlreadyExists(enterUserName.getText().trim())){
                        shallGoNext = false;
                        PopUps.showInvalidUserNameError();
                        enterUserName.setEffect(INNER_SHADOW_RED);
                    }else{
                        shallGoNext = true;
                        employee.setUserName(enterUserName.getText().trim());
                    }
                }
            }
            else{
                shallGoNext = false;
                enterUserName.setEffect(INNER_SHADOW_RED);
            }
            
            if(shallGoNext && validateMoney(enterSalary.getText().trim())){
                
                employee.setSalary(BigDecimal.valueOf(
                        Double.parseDouble(enterSalary.getText().trim())));
                shallGoNext = true;
            }else if(shallGoNext){
                
                enterSalary.setEffect(INNER_SHADOW_RED);
                shallGoNext = false;
            }
            
            if(shallGoNext && !enterPassword.getText().equals("")){
                if(enterPasswordAgain.getText().equals(
                        enterPassword.getText()))
                {
                    shallGoNext = true;
                    employee.setPassword(enterPassword.getText());
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
                FileController.writeEmployeeObject(employee);
                done.setDisable(false);
            }
            else {
                done.setDisable(true);
            }
            
                
        });
        
        done.setOnAction(ae->{
        
            if(newAccount==false && onlyToShow ==false){
                if(MainMenu.HANDLE_DATABASE.tryEmployeeDataUpdate(employee,"Employee")){
                    
                    try {
                        Files.deleteIfExists(Paths.get("files/employee.ser"));
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
                if(MainMenu.HANDLE_DATABASE.tryAddingNewEmployee(employee, "Employee")){
                    FileController.writeEmployeeAccount(employee.getEmployeeId(),
                            employee.getUserName(),imagePath);
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
            
            enterUserName.setEffect(null);
            enterPassword.setEffect(null);
            
            enterEmployeeTypes.setValue(null);
            enterEmployeeTypes.setPromptText("Select your Account Type");
            enterUserName.clear();
            enterPassword.clear();
            enterPasswordAgain.clear();
            save.setDisable(true);
            done.setDisable(true);            
            
        });
        
        return accountInfoScene;
    }
    
    private String uploadAdminPicture(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Upload Picture");
        fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("Image Files", "*.png","*.jpg")
        );
        File selectedPicture = fileChooser.showOpenDialog(parentStage);
        if(selectedPicture != null){
            return selectedPicture.toURI().toString();
        }
        return Images.ADMIN_PROFILE;
    }
    
}

    