/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;


import bank.Accountable;
import bank.Customer;
import bank.Employee;
import bank.MainMenu;
import data.FileController;
import static interfaces.Graphics.INNER_SHADOW_RED;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import sockets.Chat;

/**
 *
 * @author Talha Asghar
 */
public class AdminDashboard {


    
    private double width;
    private double height;
    private final Employee dashboardPerson;
    private final Stage DASHBOARD_STAGE;
    
    public AdminDashboard(Employee employee, Stage dashboardStage) {
        
        this.width = 1050;
        this.height = 900;
        this.dashboardPerson = employee;
        DASHBOARD_STAGE = dashboardStage;
        
        DASHBOARD_STAGE.setTitle("Admin Dashboard");
        DASHBOARD_STAGE.setResizable(false);
        DASHBOARD_STAGE.setScene(createDashboard());
        DASHBOARD_STAGE.show();
        
        
    }
    
    private static Accountable newAccountCreation;
    public final Scene createDashboard(){
    
        BorderPane dashboardLayout = new BorderPane();
        
        GridPane header = new GridPane();
        
        // top header of UserDashboard
        Text dashboardLabelText = new Text(
                String.format("Welcome \"%s\" !",
                    dashboardPerson.getFullName()));
        
        dashboardLabelText.setFont(new Font("Ubuntu",20));
        Label dashboardLabel = new Label("",dashboardLabelText);
        
        Text userNameText = new Text(dashboardPerson.getFullName());
        userNameText.setFont(new Font("Ubuntu", 15));
        Label userName = new Label("", userNameText);
        
        Text userTypeText = new Text(dashboardPerson.getCNIC());
        userTypeText.setFont(new Font("Ubuntu", 15));
        Label userType = new Label("", userTypeText);

        ImageView avatar = new ImageView(new Image(
                        FileController.readAdminProfilePicturePath(dashboardPerson.getUserName()),150,150,true,true));
        
        FlowPane userInfo = new FlowPane(Orientation.VERTICAL,userName,userType);
        userInfo.setAlignment(Pos.BASELINE_RIGHT);
        userInfo.setPrefWrapLength(80);
        
        GridPane.setMargin(dashboardLabel, new Insets(10,10,0,20));
        GridPane.setMargin(userInfo, new Insets(40,20,0,700));
        GridPane.setMargin(avatar, new Insets(0,0,30,20));
        
        header.addRow(0,dashboardLabel);
        header.addRow(1,userInfo,avatar);
        header.setBorder(Graphics.BOTTOM_LINE);
        dashboardLayout.setTop(header);
            
        // Status Bar
        Label credits = new Label("Programmer : Talha Asghar 2018 © Copyrights All rights reserved.");
        HBox creditsContainer = new HBox(credits);
        creditsContainer.setPrefHeight(30);
        creditsContainer.setAlignment(Pos.CENTER);
        HBox.setMargin(creditsContainer, new Insets(10,0,10,0));
        creditsContainer.setBorder(Graphics.TOP_LINE);
        dashboardLayout.setBottom(creditsContainer);
        
        // Main Central Window of DashBoard
        
        GridPane userControls = new GridPane();
        userControls.setAlignment(Pos.CENTER);
        userControls.setVgap(40);
        userControls.setHgap(40);
        
        Button addNewCustomer = new Button("Create New Customer",Images.NEW_ACCOUNT_ICON_ADMIN);
        Button allCustomers = new Button("View all Customers", Images.ALL_ACCOUNTS_ICON);
        Button editCustomer = new Button("Edit Customer",Images.EDIT_ACCOUNT_ICON);
        Button deleteCustomer = new Button("Delete Customer", Images.DELETE_ACCOUNT_ICON);
        Button consolidateAccounts = new Button("Consolidate Accounts",Images.CONSOLIDATE_ACCOUNTS_ICON);
        Button search = new Button("Search", Images.SEARCH_ACCOUNT_ICON);
        
        addNewCustomer.setContentDisplay(ContentDisplay.TOP);
        allCustomers.setContentDisplay(ContentDisplay.TOP);
        editCustomer.setContentDisplay(ContentDisplay.TOP);
        deleteCustomer.setContentDisplay(ContentDisplay.TOP);
        consolidateAccounts.setContentDisplay(ContentDisplay.TOP);
        search.setContentDisplay(ContentDisplay.TOP);
        
        addNewCustomer.setPrefSize(100,100);
        allCustomers.setPrefSize(100,100);
        editCustomer.setPrefSize(100,100);
        deleteCustomer.setPrefSize(100,100);
        consolidateAccounts.setPrefSize(100,100);
        search.setPrefSize(100,100);
        
        userControls.addRow(0, addNewCustomer, allCustomers, editCustomer);
        userControls.addRow(1, deleteCustomer, consolidateAccounts, search);
        
        dashboardLayout.setCenter(userControls);
        
        addNewCustomer.setOnAction(ae->{

        Stage makeNewAccountStage = new Stage();
        FlowPane root = new FlowPane();
        root.setAlignment(Pos.CENTER);
        root.setHgap(10);
        
        Button createCustomerAccount = new Button("New Customer Account");
        Button createEmployeeAccount = new Button("New Employee Account");
        root.getChildren().addAll(createCustomerAccount, createEmployeeAccount);
        
        makeNewAccountStage.setTitle("Create New Account");
        
        createCustomerAccount.setOnAction(e->{
        
            newAccountCreation = new NewCustomerAccountScenes(makeNewAccountStage);
            newAccountCreation.createNewAccountScene();
        });
        
        createEmployeeAccount.setOnAction(e->{
        
            newAccountCreation = new NewEmployeeAccountScenes(makeNewAccountStage);
            newAccountCreation.createNewAccountScene();
        });
         
         
        makeNewAccountStage.setScene(new Scene(root)); 
        makeNewAccountStage.showAndWait();
        
     
        
        });
        
        allCustomers.setOnAction(ae->{
        
            CustomersTable customersTable = new CustomersTable(
                    MainMenu.HANDLE_DATABASE.readAllCustomers("Customer"));
        
        
        });
        editCustomer.setOnAction(ae->{
            
            Stage editProfileStage = new Stage();
            editProfileStage.setTitle("Edit Customer");
            
            Customer customerToEdit = showSearchCustomerScene();
            
            if(customerToEdit != null){
                NewCustomerAccountScenes showProfile = new NewCustomerAccountScenes(customerToEdit,true, editProfileStage);
                editProfileStage.setScene(showProfile.createPersonalInfoScene());
                editProfileStage.showAndWait();             
            }
           
        });
        search.setOnAction(ae->{
            
            showSearchWindow(800, 800);
            
            
        });
        
        deleteCustomer.setOnAction(ae->{
            
            Customer customer = showSearchCustomerScene();
            
            if(customer != null){
                showDeleteCustomerStage(customer);
            }        
            
        });
        
        consolidateAccounts.setOnAction(a->{
    
            showAccountConsolidateWindow(800,800);
    
        });
     
        
        
        // left side of UserDashboard
        
        GridPane leftSide = new GridPane();
        
        Text leftSideHeadingText = new Text("Profile");
        leftSideHeadingText.setFont(new Font("Ubuntu", 30));
        Label leftSideHeading = new Label("", leftSideHeadingText);
        
        Text fullNameText = new Text(dashboardPerson.getOccupation());
        fullNameText.setFont(new Font("Ubuntu", 15));
        Label fullName = new Label("",fullNameText);
        
        Text contactText = new Text(dashboardPerson.getNationality());
        contactText.setFont(new Font("Ubuntu", 15));
        Label contact = new Label("",contactText);
        
        Text emailText = new Text(dashboardPerson.getDOB());
        contactText.setFont(new Font("Ubuntu", 15));
        Label email = new Label("",emailText);
        
        Text ageText = new Text(dashboardPerson.getDomicile());
        contactText.setFont(new Font("Ubuntu", 15));
        Label age = new Label("",ageText);
        
        Text educationText = new Text(dashboardPerson.getAddress());
        contactText.setFont(new Font("Ubuntu", 15));
        Label education = new Label("",educationText);
        
        Text addressText = new Text(dashboardPerson.getEmail());
        contactText.setFont(new Font("Ubuntu", 15));
        Label address = new Label("",addressText);
        
        Hyperlink viewCompleteProfile = new Hyperlink("View Complete Profile");
        viewCompleteProfile.setOnAction(ae ->{
        
            Stage viewProfileStage = new Stage();
            viewProfileStage.setTitle("My Profile");
            NewEmployeeAccountScenes showProfile = new NewEmployeeAccountScenes(dashboardPerson,false, viewProfileStage);
            viewProfileStage.setScene(showProfile.createPersonalInfoScene());
            viewProfileStage.showAndWait();
        
        });
        
        FlowPane leftSideInfo = new FlowPane(Orientation.VERTICAL,
            leftSideHeading, fullName, contact, email, age, education,
            address, viewCompleteProfile);
        
        leftSideInfo.setVgap(20);
        leftSideInfo.setPrefWrapLength(500);
        FlowPane.setMargin(leftSideHeading, new Insets(20,0,20,20));
        FlowPane.setMargin(viewCompleteProfile, new Insets(10,0,0,0));
        leftSide.addRow(0,leftSideInfo);
        leftSide.setBorder(Graphics.RIGHT_LINE);
        GridPane.setMargin(leftSideInfo, new Insets(30,10,10,40));
        
        dashboardLayout.setLeft(leftSide);
        
        // right side of UserDashboard
        
        GridPane rightSide = new GridPane();
        
        Button mySalary = new Button("Salary",Images.SALARY_ICON);
        Button chat = new Button("Live Chat", Images.ADMIN_CHAT_ICON);
        Button currencyExchange = new Button("Exchange",Images.ADMIN_CURRENCY_EXCHANGE_ICON);
        Button logout = new Button("Logout",Images.ADMIN_LOGOUT_ICON);
        Button calculator = new Button("Calculator",Images.CALCULATOR_ICON);
        
        mySalary.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        chat.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        currencyExchange.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        calculator.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        logout.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        
        
        mySalary.setPrefSize(50,50);
        chat.setPrefSize(50, 50);
        currencyExchange.setPrefSize(50,50);
        logout.setPrefSize(50,50);
        calculator.setPrefSize(50,50);
        
        mySalary.setShape(new Circle(10));
        chat.setShape(new Circle(10));
        logout.setShape(new Circle(10));
        calculator.setShape(new Circle(10));
        
        currencyExchange.setShape(new Circle(10));
        
        chat.setOnAction(ae ->{
        
            Chat.showLiveAgent(400,400);
       
        });        
        
        FlowPane rightSideInfo = new FlowPane(Orientation.VERTICAL,
            mySalary,currencyExchange,calculator, chat, logout);
        rightSideInfo.setVgap(20);
        rightSideInfo.setPrefWrapLength(500);
        rightSide.addRow(0,rightSideInfo);
        
        GridPane.setMargin(rightSideInfo, new Insets(30,10,10,10));
        rightSide.setBorder(Graphics.LEFT_LINE);
        dashboardLayout.setRight(rightSide);
        
        logout.setOnAction(ae->{
           
            try {
                PopUps.showLogoutConfirmation(DASHBOARD_STAGE);
                Files.delete(Paths.get("files/admin_login.dat"));
            } catch (IOException ex) {
                System.out.println(ex);
            }
            
        });
        
        mySalary.setOnAction(ae->{
    
            Stage currentBalanceStage = new Stage();
            currentBalanceStage.setScene(mySalaryScene());
            currentBalanceStage.centerOnScreen();
            currentBalanceStage.showAndWait();
        });
        
        currencyExchange.setOnAction(ae->{
        
            Scenes.showCurrencyExchangeStage(700, 700);
        
        });
        
        calculator.setOnAction(ae->{
            try {
                Runtime r = Runtime.getRuntime();
                Process p = null;
                p = r.exec("calc");
                p.waitFor();
            } catch (IOException ex) {
                System.out.println(ex);
            } catch (InterruptedException ex) {
                System.out.println(ex);
            }
        
        });
        
        return (new Scene(dashboardLayout, width, height));
        
    }
    
    public void showDeleteCustomerStage(Customer customerToDelete){
              
        Stage deleteCustomerStage = new Stage();
        deleteCustomerStage.setTitle("Delete Customer");

        GridPane root = new GridPane();
        deleteCustomerStage.setScene(new Scene(root,500,500));
        root.setVgap(20);
        root.setAlignment(Pos.CENTER);
        Button deleteCustomer = new Button("Delete");
       
        Hyperlink viewCompleteProfile = new Hyperlink(String.format("View Complete Profile of '%s'",customerToDelete.getFullName()));
        viewCompleteProfile.setOnAction(ae ->{
        
            Stage viewProfileStage = new Stage();
            viewProfileStage.setTitle("My Profile");
            NewCustomerAccountScenes showProfile = new NewCustomerAccountScenes(customerToDelete,false, viewProfileStage);
            viewProfileStage.setScene(showProfile.createPersonalInfoScene());
            viewProfileStage.showAndWait();
        
        });
  
        root.addRow(1, viewCompleteProfile);
        root.addRow(0, deleteCustomer);
        deleteCustomer.setOnAction(ae->{
        
            Alert deleteCustomerPopUp = PopUps.customerDeleteConfirmation();
            deleteCustomerPopUp.setHeaderText(String.format("Delete '%s' ?",customerToDelete.getUserName()));
            deleteCustomerPopUp.setGraphic(
                    new ImageView(new Image(FileController.readCustomerProfilePicturePath(customerToDelete.getUserName()),120,120,true,true)));
        
            deleteCustomerPopUp.showAndWait().ifPresent(response ->{
            
                if(response==ButtonType.OK){
                    if(MainMenu.HANDLE_DATABASE.tryDeleteCustomer(
                          Long.toString(customerToDelete.getAccountNumber()), "Customer")){
                       
                       FileController.deleteCustomer(Long.toString(customerToDelete.getAccountNumber()));
                       PopUps.customerDeletedSuccessfully();
                       deleteCustomerStage.close();
                   
                   }
                   else{
                       PopUps.customerDeleteError();
                   }
                    
                }
            });
        });
        
        
        deleteCustomerStage.showAndWait();    
    
    }

    public static Customer showSearchCustomerScene() {

        TextInputDialog inputSearchAccountNumber = new TextInputDialog();
        inputSearchAccountNumber.setTitle("Search Customer");
        inputSearchAccountNumber.setHeaderText("[Customer Name]");
        ImageView recieverPictureView = new ImageView(new Image(Images.USER_PROFILE, 120, 120, true, true));
        inputSearchAccountNumber.setGraphic(recieverPictureView);
        TextField inputAccountNumber = inputSearchAccountNumber.getEditor();
        inputAccountNumber.setText("Enter account number to Search");
        inputAccountNumber.setOnKeyReleased((KeyEvent ae) -> {
            inputAccountNumber.setEffect(null);
            String transferUserName = MainMenu.HANDLE_DATABASE.doesAccountNumberExists(inputAccountNumber.getText().trim(), "Customer");
            if (transferUserName != null) {
                Customer searchedCustomer = MainMenu.HANDLE_DATABASE.readAllDataOfCustomer(transferUserName, "Customer");
                recieverPictureView.setImage(new Image(FileController.readCustomerProfilePicturePath(transferUserName), 120, 120, true, true));
                inputSearchAccountNumber.setHeaderText(String.format("%s%n%s", searchedCustomer.getFullName(), searchedCustomer.getCNIC()));
            } else {
                inputSearchAccountNumber.setHeaderText("[Customer Name]");
                recieverPictureView.setImage(new Image(FileController.readCustomerProfilePicturePath("images/user.png"), 120, 120, true, true));
            }
        });
        Optional<String> result = inputSearchAccountNumber.showAndWait();
        if (result.isPresent()) {
            inputAccountNumber.setEffect(null);
            String transferUserName = MainMenu.HANDLE_DATABASE.doesAccountNumberExists(inputAccountNumber.getText().trim(), "Customer");
            if (transferUserName != null) {
                Customer customer = MainMenu.HANDLE_DATABASE.readAllDataOfCustomer(transferUserName, "Customer");
                return customer;
            } else {
                PopUps.showAccountNumberDoesNotExistError();
            }
        } else {
            PopUps.showNoInputError();
        }
        return null;
    }

    public static void showSearchWindow(double width, double height) {
        
        Stage searchWindow = new Stage();
        BorderPane root = new BorderPane();
        searchWindow.setScene(new Scene(root, width, height));
        
        GridPane searchTools = new GridPane();
        searchTools.setAlignment(Pos.CENTER);
        searchTools.setVgap(20);
        searchTools.setHgap(20);
        ToggleGroup searchOptionsGroup = new ToggleGroup();
        TextField searchBar = new TextField();
        TextArea searchResult = new TextArea();
        searchResult.setEditable(false);
        Label searchByHeading = new Label("Search By : ");
        RadioButton fullNameSearch = new RadioButton("Full Name");
        RadioButton userNameSearch = new RadioButton("User Name");
        RadioButton accountNumberSearch = new RadioButton("Account Number");
        RadioButton cnicSearch = new RadioButton("CNIC");
        RadioButton balanceSearch = new RadioButton("Balance");
        RadioButton loanSearch = new RadioButton("Loan");
        RadioButton customSearch = new RadioButton("Custom Search");
        
        ObservableList<String> allColumns = FXCollections.observableArrayList("FatherName","DOB","Gender","Religion","Education","MartialStatus","Nationality","Occupation","ContactNumber","Email","Domicile","Address","City","Province","MailingAddress","AccountTitle","AccountType");
        ComboBox<String> customSearchOptions = new ComboBox<>(allColumns);
        customSearchOptions.setPromptText("Select any Option");
        customSearchOptions.setVisible(false);
        Button searchButton = new Button("Search");
        CheckBox equalTo = new CheckBox("Equal To");
        CheckBox greaterThan = new CheckBox("Greater Than");
        CheckBox lessThan = new CheckBox("Smaller Than");
        
        equalTo.setDisable(true);
        greaterThan.setDisable(true);
        lessThan.setDisable(true);
        
        searchTools.addRow(0, searchByHeading);
        searchTools.addRow(1, fullNameSearch, userNameSearch);
        searchTools.addRow(2, accountNumberSearch, cnicSearch);
        searchTools.addRow(3, balanceSearch, loanSearch);
        searchTools.addRow(4, equalTo, greaterThan, lessThan);
        searchTools.addRow(5, customSearch, customSearchOptions);
        searchTools.addRow(6, searchBar, searchButton);
        searchTools.addRow(7, searchResult);
        
        GridPane.setColumnSpan(searchResult, 4);
        
        fullNameSearch.setToggleGroup(searchOptionsGroup);
        userNameSearch.setToggleGroup(searchOptionsGroup);
        accountNumberSearch.setToggleGroup(searchOptionsGroup);
        cnicSearch.setToggleGroup(searchOptionsGroup);
        balanceSearch.setToggleGroup(searchOptionsGroup);
        loanSearch.setToggleGroup(searchOptionsGroup);
        customSearch.setToggleGroup(searchOptionsGroup);
        
        fullNameSearch.setSelected(true);
        root.setCenter(searchTools);

        fullNameSearch.setOnAction(ae->{
            equalTo.setDisable(true);
            greaterThan.setDisable(true);
            lessThan.setDisable(true);
            customSearchOptions.setVisible(false);

        });
        
        userNameSearch.setOnAction(ae->{
            equalTo.setDisable(true);
            greaterThan.setDisable(true);
            lessThan.setDisable(true);
            customSearchOptions.setVisible(false);

        });
        
        accountNumberSearch.setOnAction(ae->{
            equalTo.setDisable(true);
            greaterThan.setDisable(true);
            lessThan.setDisable(true);
            customSearchOptions.setVisible(false);

        });  
           
        cnicSearch.setOnAction(ae->{
            equalTo.setDisable(true);
            greaterThan.setDisable(true);
            lessThan.setDisable(true);
            customSearchOptions.setVisible(false);

        });

        customSearch.setOnAction(ae->{
            
            customSearchOptions.setVisible(true);
            equalTo.setDisable(true);
            greaterThan.setDisable(true);
            lessThan.setDisable(true);
            
            
        });        

        
        balanceSearch.setOnAction(ae->{
                equalTo.setDisable(false);
                greaterThan.setDisable(false);
                lessThan.setDisable(false);
                equalTo.setSelected(true);
                lessThan.setSelected(false);
                greaterThan.setSelected(false);
                customSearchOptions.setVisible(false);
        
        });
        
        loanSearch.setOnAction(ae->{
            
                equalTo.setDisable(false);
                greaterThan.setDisable(false);
                lessThan.setDisable(false);
                equalTo.setSelected(true);
                lessThan.setSelected(false);
                greaterThan.setSelected(false);
                customSearchOptions.setVisible(false);
        });        
        
        greaterThan.setOnAction(ae->{
        
            if(greaterThan.isSelected()) {
                lessThan.setDisable(true);
            } else {
                lessThan.setDisable(false);
            }
        
        });

        lessThan.setOnAction(ae->{
        
            if(lessThan.isSelected()) {
                greaterThan.setDisable(true);
            } else {
                greaterThan.setDisable(false);
            }
        
        });        
        
        searchBar.setOnKeyReleased((KeyEvent ae) -> {
            searchResult.clear();
            
            if (fullNameSearch.isSelected()) {
                Customer[] results = MainMenu.HANDLE_DATABASE.searchInCustomers(searchBar.getText().trim(), "FullName", "Customer");
                for (int i = 0; i < results.length; i++) {
                    searchResult.appendText(String.format("%04d  %-20s  %s%n", i + 1, results[i].getAccountNumber(), results[i].getFullName()));
                }
            } else if (userNameSearch.isSelected()) {
                Customer[] results = MainMenu.HANDLE_DATABASE.searchInCustomers(searchBar.getText().trim(), "UserName", "Customer");
                for (int i = 0; i < results.length; i++) {
                    searchResult.appendText(String.format("%04d   %-20s  %-40s %s%n", i + 1, results[i].getAccountNumber(), results[i].getFullName(), results[i].getUserName()));
                }
            } else if (accountNumberSearch.isSelected()) {
                Customer[] results = MainMenu.HANDLE_DATABASE.searchInCustomers(searchBar.getText().trim(), "AccountNumber", "Customer");
                for (int i = 0; i < results.length; i++) {
                    searchResult.appendText(String.format("%04d   %-20s  %s%n", i + 1, results[i].getAccountNumber(), results[i].getFullName()));
                }
            } else if (cnicSearch.isSelected()) {
                Customer[] results = MainMenu.HANDLE_DATABASE.searchInCustomers(searchBar.getText().trim(), "CNIC", "Customer");
                for (int i = 0; i < results.length; i++) {
                    searchResult.appendText(String.format("%04d   %-20s  %-40s %-15s%n", i + 1, results[i].getAccountNumber(), results[i].getFullName(), results[i].getCNIC()));
                }
            } else if(customSearch.isSelected()){
            
                String customSearchOptionColumn = customSearchOptions.getValue();
                Customer[] results = MainMenu.HANDLE_DATABASE.customSearchInCustomers("=",searchBar.getText().trim(), customSearchOptionColumn, "Customer");
                for (int i = 0; i < results.length; i++) {
                    searchResult.appendText(String.format("%04d   %-20s  %-40s %s %n", i + 1, results[i].getAccountNumber(), results[i].getFullName(), customCustomerPropertyGetter(results[i],customSearchOptionColumn)));
                }   
            
            }else if(balanceSearch.isSelected()){
               
               String condition = "=";
               
               if(lessThan.isSelected()){
               
                   condition = "<";
               }
               else if(greaterThan.isSelected()){
               
                   condition = ">";
               }
               
               if(equalTo.isSelected()){
               
                   condition += "=";
               }
                
                Customer[] results = MainMenu.HANDLE_DATABASE.customSearchInCustomers(condition,searchBar.getText().trim(), "CurrentBalance", "Customer");
                for (int i = 0; i < results.length; i++) {
                    searchResult.appendText(String.format("%04d   %-20s  %-40s    Rs %,.2f%n", i + 1, results[i].getAccountNumber(), results[i].getFullName(),results[i].getBalance()));
                }            
            
            }else if(loanSearch.isSelected()){
               
               String condition = "=";
               
               if(lessThan.isSelected()){
               
                   condition = "<";
               }
               else if(greaterThan.isSelected()){
               
                   condition = ">";
               }
               
               if(equalTo.isSelected()){
               
                   condition += "=";
               }

                Customer[] results = MainMenu.HANDLE_DATABASE.customSearchInCustomers(condition,searchBar.getText().trim(), "Loan", "Customer");
                for (int i = 0; i < results.length; i++) {
                    searchResult.appendText(String.format("%04d   %-20s  %-40s    Rs %,.2f%n", i + 1, results[i].getAccountNumber(), results[i].getFullName(),results[i].getLoan()));
                }            
            
            }
        });
        searchButton.setOnAction((ActionEvent ae) -> {
            Customer[] results = null;
            searchResult.clear();
            
            if (fullNameSearch.isSelected()) {
                results = MainMenu.HANDLE_DATABASE.searchInCustomers(searchBar.getText().trim(), "FullName", "Customer");
                for (int i = 0; i < results.length; i++) {
                    searchResult.appendText(String.format("%04d  %-20s  %s%n", i + 1, results[i].getAccountNumber(), results[i].getFullName()));
                }
            } else if (userNameSearch.isSelected()) {
                results = MainMenu.HANDLE_DATABASE.searchInCustomers(searchBar.getText().trim(), "UserName", "Customer");
                for (int i = 0; i < results.length; i++) {
                    searchResult.appendText(String.format("%04d   %-20s  %-40s %s%n", i + 1, results[i].getAccountNumber(), results[i].getFullName(), results[i].getUserName()));
                }
            } else if (accountNumberSearch.isSelected()) {
                results = MainMenu.HANDLE_DATABASE.searchInCustomers(searchBar.getText().trim(), "AccountNumber", "Customer");
                for (int i = 0; i < results.length; i++) {
                    searchResult.appendText(String.format("%04d   %-20s  %s%n", i + 1, results[i].getAccountNumber(), results[i].getFullName()));
                }
            } else if (cnicSearch.isSelected()) {
                results = MainMenu.HANDLE_DATABASE.searchInCustomers(searchBar.getText().trim(), "CNIC", "Customer");
                for (int i = 0; i < results.length; i++) {
                    searchResult.appendText(String.format("%04d   %-20s  %-40s %-15s%n", i + 1, results[i].getAccountNumber(), results[i].getFullName(), results[i].getCNIC()));
                }
            } else if(customSearch.isSelected()){
            
                String customSearchOptionColumn = customSearchOptions.getValue();
                results = MainMenu.HANDLE_DATABASE.customSearchInCustomers("=",searchBar.getText().trim(), customSearchOptionColumn, "Customer");
                for (int i = 0; i < results.length; i++) {
                    searchResult.appendText(String.format("%04d   %-20s  %-40s %s %n", i + 1, results[i].getAccountNumber(), results[i].getFullName(), customCustomerPropertyGetter(results[i],customSearchOptionColumn)));
                }   
            
            }else if(balanceSearch.isSelected()){
               
               String condition = "=";
               
               if(lessThan.isSelected()){
               
                   condition = "<";
               }
               else if(greaterThan.isSelected()){
               
                   condition = ">";
               }
               
               if(equalTo.isSelected()){
               
                   condition += "=";
               }

                results = MainMenu.HANDLE_DATABASE.customSearchInCustomers(condition,searchBar.getText().trim(), "CurrentBalance", "Customer");
                for (int i = 0; i < results.length; i++) {
                    searchResult.appendText(String.format("%04d   %-20s  %-40s    Rs %,.2f%n", i + 1, results[i].getAccountNumber(), results[i].getFullName(),results[i].getBalance()));
                }            
            
            }else if(loanSearch.isSelected()){
               
               String condition = "=";
               
               if(lessThan.isSelected()){
               
                   condition = "<";
               }
               else if(greaterThan.isSelected()){
               
                   condition = ">";
               }
               
               if(equalTo.isSelected()){
               
                   condition += "=";
               }

                results = MainMenu.HANDLE_DATABASE.customSearchInCustomers(condition,searchBar.getText().trim(), "Loan", "Customer");
                for (int i = 0; i < results.length; i++) {
                    searchResult.appendText(String.format("%04d   %-20s  %-40s    Rs %,.2f%n", i + 1, results[i].getAccountNumber(), results[i].getFullName(),results[i].getLoan()));
                }            
            
            }            
            if (results != null && results.length > 0) {
                showCustomerSearchResults(results, 700, 700);
            }
        });
        
        searchWindow.showAndWait();
    }

    public static void showCustomerSearchResults(final Customer[] customers, double width, double height) {
        Stage searchResults = new Stage();
        BorderPane root = new BorderPane();
        searchResults.setScene(new Scene(root, width, height));
        GridPane searchResultControls = new GridPane();
        searchResultControls.setAlignment(Pos.CENTER);
        searchResultControls.setVgap(20);
        searchResultControls.setHgap(20);
        Label accountNumberHeading = new Label("Account Number");
        Label fullNameHeading = new Label("Full Name");
        Label cnicHeading = new Label("Cnic"); 
        RadioButton[] accountNumbers = new RadioButton[customers.length];
        Label[] fullNames = new Label[customers.length];
        Label[] cnicNumbers = new Label[customers.length];
        Button viewDetails = new Button("View Details");
        ToggleGroup allResultsGroup = new ToggleGroup();
        
        searchResultControls.addRow(0, accountNumberHeading, fullNameHeading, cnicHeading);
        
        for (int i = 0; i < customers.length; i++) {

            accountNumbers[i] = new RadioButton(Long.toString(customers[i].getAccountNumber()));
            accountNumbers[i].setToggleGroup(allResultsGroup);
            fullNames[i] = new Label(customers[i].getFullName());
            cnicNumbers[i] = new Label(customers[i].getCNIC());
            searchResultControls.addRow(i+1, accountNumbers[i], fullNames[i], cnicNumbers[i]);
        }
        
        searchResultControls.addRow(customers.length+1, viewDetails);
        viewDetails.setOnAction((ActionEvent ae) -> {
            RadioButton temp = (RadioButton) allResultsGroup.getSelectedToggle();
            if (temp != null) {
                Stage accountDetails = new Stage();
                accountDetails.setTitle("Account Details");
                NewCustomerAccountScenes showProfile = new NewCustomerAccountScenes(MainMenu.HANDLE_DATABASE.readAllDataOfCustomer(MainMenu.HANDLE_DATABASE.doesAccountNumberExists(temp.getText(), "Customer"), "Customer"), false, accountDetails);
                accountDetails.setScene(showProfile.createPersonalInfoScene());
                accountDetails.showAndWait();
            } else {
                PopUps.showNothingSelectedError();
            }
        });
        root.setCenter(searchResultControls);
        searchResults.showAndWait();
    }

    private static class CustomersTable extends JFrame{

        private static final long serialVersionUID = 1L;

        private final JTable allCustomers;
        private final JScrollPane scrollAllCustomers;
        CustomersTable(final Customer customers[]){
            
            super("All Customers List");
            
            String headings[] = {"User Name", "Full Name","Account Number", 
                "CNIC", "Account Title", "Acount Type",
                "Current Balance", "Loan"};
            Object customersList[][] = new Object[customers.length][10];
            
            for(int i=0; i<customersList.length; i++){
            
                customersList[i][0] = customers[i].getUserName();
                customersList[i][1] = customers[i].getFullName();
                customersList[i][2] = customers[i].getAccountNumber();
                customersList[i][3] = customers[i].getCNIC();
                customersList[i][4] = customers[i].getAccountTitle();
                customersList[i][5] = customers[i].getAccountType();
                customersList[i][6] = customers[i].getBalance();
                customersList[i][7] = customers[i].getLoan();
                
            }
            
            allCustomers = new JTable(customersList, headings);
            scrollAllCustomers = new JScrollPane(allCustomers);
            add(scrollAllCustomers);
            allCustomers.setEnabled(false);
            setVisible(true);
            setSize(1000,500);
             
        }

    }
    
    private static Customer customerOneToConsolidate;
    private static Customer customerTwoToConsolidate;
    private static String imagePath = Images.USER_PROFILE;
    private static ImageView profilePicture = new ImageView(new Image(imagePath,120,120,true,true));    
    
    private static void showAccountConsolidateWindow(double width , double height){
    
        Stage accountConsolidateStage = new Stage();
        FlowPane root = new FlowPane(Orientation.VERTICAL);
        Scene accountConsolidation = new Scene(root, width ,height);
    
        GridPane accountOne = new GridPane();
        GridPane accountTwo = new GridPane();
        
        root.setAlignment(Pos.CENTER);
        accountOne.setAlignment(Pos.CENTER);
        accountTwo.setAlignment(Pos.CENTER);
        
        accountOne.setVgap(20);
        accountTwo.setVgap(20);
        accountOne.setHgap(20);
        accountTwo.setHgap(20);
        
        Button consolidate = new Button("Consolidate");
        Label accountNumberHeading1 = new Label("Account Number : ");
        Label fullNameHeading1 = new Label("Full Name : ");
        Label cnicHeading1 = new Label("CNIC : ");
        Label balanceHeading1 = new Label("Balance : ");
    
        TextField accountNumber1 = new TextField();
        TextField fullName1 = new TextField();
        TextField cnic1 = new TextField();
        TextField balance1 = new TextField();
        Button selectAccount1 = new Button("Select Account # 1");
        Hyperlink account1Detail = new Hyperlink("View Full Details");
        
        accountNumber1.setEditable(false);
        fullName1.setEditable(false);
        cnic1.setEditable(false);
        balance1.setEditable(false);
        
        Label accountNumberHeading2 = new Label("Account Number : ");
        Label fullNameHeading2 = new Label("Full Name : ");
        Label cnicHeading2 = new Label("CNIC : ");
        Label balanceHeading2 = new Label("Balance : ");
    

        TextField accountNumber2 = new TextField();
        TextField fullName2 = new TextField();
        TextField cnic2 = new TextField();
        TextField balance2 = new TextField();
        Button selectAccount2 = new Button("Select Account # 2");
        Hyperlink account2Detail = new Hyperlink("View Full Details");
        
        accountNumber2.setEditable(false);
        fullName2.setEditable(false);
        cnic2.setEditable(false);
        balance2.setEditable(false);        

        accountOne.addRow(0,accountNumberHeading1, accountNumber1);
        accountOne.addRow(1,fullNameHeading1, fullName1);
        accountOne.addRow(2, cnicHeading1, cnic1);
        accountOne.addRow(3, balanceHeading1, balance1);
        accountOne.addRow(4, account1Detail);

        accountTwo.addRow(0,accountNumberHeading2, accountNumber2);
        accountTwo.addRow(1,fullNameHeading2, fullName2);
        accountTwo.addRow(2, cnicHeading2, cnic2);
        accountTwo.addRow(3, balanceHeading2, balance2);
        accountTwo.addRow(4, account2Detail);        
   
        selectAccount1.setOnAction(ae->{
            
            customerOneToConsolidate = showSearchCustomerScene();
            if(customerOneToConsolidate != null){
                accountNumber1.setText(Long.toString(customerOneToConsolidate.getAccountNumber()));
                fullName1.setText(customerOneToConsolidate.getFullName());
                cnic1.setText(customerOneToConsolidate.getCNIC());
                balance1.setText(customerOneToConsolidate.getBalance().toString());
                root.getChildren().removeAll(selectAccount1);
                
                if(root.getChildren().contains(selectAccount2)) {
                    root.getChildren().removeAll(selectAccount2);
                    root.getChildren().addAll(accountOne,selectAccount2);
                }else {
                    root.getChildren().removeAll(accountTwo);
                    root.getChildren().addAll(accountOne,accountTwo,consolidate);
                }
            }
        });
        
        account1Detail.setOnAction(ae->{

            Stage accountDetails = new Stage();
            accountDetails.setTitle("Account Details");
            NewCustomerAccountScenes showProfile = new NewCustomerAccountScenes(customerOneToConsolidate, false, accountDetails);
            accountDetails.setScene(showProfile.createPersonalInfoScene());
            accountDetails.showAndWait();
        
        
        });

        selectAccount2.setOnAction(ae->{
            
            customerTwoToConsolidate = showSearchCustomerScene();
            if(customerTwoToConsolidate != null){
                accountNumber2.setText(Long.toString(customerTwoToConsolidate.getAccountNumber()));
                fullName2.setText(customerTwoToConsolidate.getFullName());
                cnic2.setText(customerTwoToConsolidate.getCNIC());
                balance2.setText(customerTwoToConsolidate.getBalance().toString());
                root.getChildren().removeAll(selectAccount2);
                
                if(root.getChildren().contains(selectAccount1)) {
                     root.getChildren().removeAll(selectAccount1);
                    root.getChildren().addAll(selectAccount1,accountTwo);
                }else {
                    root.getChildren().removeAll(accountOne);
                    root.getChildren().addAll(accountOne,accountTwo,consolidate);
                }
                
            }
        });
        
        account2Detail.setOnAction(ae->{

            Stage accountDetails = new Stage();
            accountDetails.setTitle("Account Details");
            NewCustomerAccountScenes showProfile = new NewCustomerAccountScenes(customerTwoToConsolidate, false, accountDetails);
            accountDetails.setScene(showProfile.createPersonalInfoScene());
            accountDetails.showAndWait();
        
        
        });        
        
        
        consolidate.setOnAction(ae->{
        
            if(customerOneToConsolidate.getAccountNumber()==customerTwoToConsolidate.getAccountNumber()){
                PopUps.consolidateErrorDueToSameAccountNumber();
                root.getChildren().removeAll(accountTwo,consolidate);
                root.getChildren().addAll(selectAccount2);
            }
            else if(compareTwoCustomers(customerOneToConsolidate, customerTwoToConsolidate)){
            
                Customer consolidatedCustomer = new Customer(customerOneToConsolidate);
                
                consolidatedCustomer.setBalance(customerOneToConsolidate.getBalance()
                    .add(customerTwoToConsolidate.getBalance()));
                consolidatedCustomer.setLoan(customerOneToConsolidate.getLoan()
                    .add(customerTwoToConsolidate.getLoan()));
                
                Stage childConsolidateStage = new Stage();
                BorderPane childRoot = new BorderPane();
                GridPane controls = new GridPane();
                controls.setAlignment(Pos.CENTER);
                controls.setVgap(20);
                controls.setHgap(20);
                
                Button uploadPicture = new Button("Upload Picture");
                GridPane profilePictureArea = new GridPane();
                profilePictureArea.setVgap(30);
                profilePictureArea.addRow(0,profilePicture);
                profilePictureArea.addRow(1, uploadPicture);
                //profilePictureArea.setGridLinesVisible(true);
                GridPane.setMargin(uploadPicture, new Insets(0,0,0,15));
                childRoot.setRight(profilePictureArea);
                BorderPane.setMargin(profilePictureArea, new Insets(30,40,0,0));
                
                Label userNameHeading = new Label("New User Name : ");
                Label accountTitleHeading = new Label("New Account Title : ");
                Label accountTypeHeading = new Label("New Account Type : ");
                
                ComboBox<String> enterUserName = new ComboBox<>(
                        FXCollections.observableArrayList(customerOneToConsolidate.getUserName(),
                        customerTwoToConsolidate.getUserName()));
                
                ComboBox<String> enterAccountTitle = new ComboBox<>();
                ComboBox<String> enterAccountType = new ComboBox<>();
                
                enterUserName.setPromptText("Select New User Name");
                enterAccountTitle.setPromptText("Select New Account Title");
                enterAccountType.setPromptText("Select New Account Type");
                
                Label password = new Label("Password : ");
                Label passwordAgain = new Label("Enter Password Again : ");
                CheckBox showPassword = new CheckBox("Show Password");
                PasswordField enterPassword = new PasswordField();
                PasswordField enterPasswordAgain = new PasswordField(); 
                Button save = new Button("Save");
                
                int rowIndex = 0;
                controls.addRow( rowIndex++, userNameHeading, enterUserName);
                
                if(customerOneToConsolidate.getAccountTitle().
                        equals(customerTwoToConsolidate.getAccountTitle())){
                
                    consolidatedCustomer.setAccountTitle(customerOneToConsolidate.getAccountTitle());
                
                }else{
                
                    enterAccountTitle.setItems(
                            FXCollections.observableArrayList(customerOneToConsolidate.getAccountTitle(),
                                    customerTwoToConsolidate.getAccountTitle()));
                    controls.addRow(rowIndex++ , accountTitleHeading, enterAccountTitle);
                }

                if(customerOneToConsolidate.getAccountType().
                        equals(customerTwoToConsolidate.getAccountType())){
                
                    consolidatedCustomer.setAccountType(customerOneToConsolidate.getAccountType());
                
                }else{
                
                    enterAccountType.setItems(
                            FXCollections.observableArrayList(customerOneToConsolidate.getAccountType(),
                                    customerTwoToConsolidate.getAccountType()));
                    controls.addRow(rowIndex++ , accountTypeHeading, enterAccountType);
                }                
                
                controls.addRow(rowIndex++ ,password, enterPassword );
                controls.addRow(rowIndex++ ,passwordAgain, enterPasswordAgain );
                controls.addRow(rowIndex++ ,showPassword );
                controls.addRow(rowIndex++, save);
                
                enterUserName.setOnAction(e->{
                    consolidatedCustomer.setUserName(enterUserName.getValue());
                
                });
                
                enterAccountTitle.setOnAction(e->{
                    consolidatedCustomer.setAccountTitle(enterAccountTitle.getValue());
                
                });
                
                enterAccountType.setOnAction(e->{
                    consolidatedCustomer.setAccountType(enterAccountType.getValue());
                
                });
                
                enterPassword.setOnMousePressed(e->{
                    enterPassword.setEffect(null);
                });
        
                enterPasswordAgain.setOnMousePressed(e->{
                    enterPasswordAgain.setEffect(null);
                });
                
                uploadPicture.setOnAction(e->{
                    imagePath = NewCustomerAccountScenes.uploadUserPicture();
                    profilePicture.setImage(new Image(imagePath,120,120,true,true));
            
                });
                save.setOnAction(e->{
                    
                    if(!enterPassword.getText().equals("")){
                        if(enterPasswordAgain.getText().equals(
                                enterPassword.getText()))
                        {

                            consolidatedCustomer.setPassword(enterPassword.getText());
                            
                            if(MainMenu.HANDLE_DATABASE.tryAddingNewCustomer(consolidatedCustomer, "Customer")){
                            
                                FileController.writeCustomerAccount(consolidatedCustomer.getAccountNumber(), consolidatedCustomer.getUserName(), imagePath);
                                
                                if(MainMenu.HANDLE_DATABASE.tryDeleteCustomer(Long.toString(customerOneToConsolidate.getAccountNumber()), "Customer")
                                   && MainMenu.HANDLE_DATABASE.tryDeleteCustomer(Long.toString(customerTwoToConsolidate.getAccountNumber()), "Customer")
                                   ){

                                    FileController.deleteCustomer(Long.toString(customerOneToConsolidate.getAccountNumber()));
                                    FileController.deleteCustomer(Long.toString(customerTwoToConsolidate.getAccountNumber()));

                                    PopUps.showConsolidationCompleteMessage();
                                    childConsolidateStage.close();
                                    accountConsolidateStage.close();
                                }
                                else{
                                    PopUps.showConsolidationError();
                                    childConsolidateStage.close();
                                    accountConsolidateStage.close();
                                }
                            }
                            else{
                                PopUps.showAccountCreationError();
                                childConsolidateStage.close();
                                accountConsolidateStage.close();
                            }
                           
                        }
                        else{

                            PopUps.showPasswordMisMatchPopUp();
                            enterPassword.setEffect(INNER_SHADOW_RED);
                            enterPasswordAgain.setEffect(INNER_SHADOW_RED);
                        }
                    }
                    else{

                        enterPassword.setEffect(INNER_SHADOW_RED);
                        enterPasswordAgain.setEffect(INNER_SHADOW_RED);

                    }                

                });
                
                showPassword.setOnAction(e->{
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
                
                
                childRoot.setCenter(controls);
                childConsolidateStage.setScene(new Scene(childRoot, 700,700));
                childConsolidateStage.showAndWait();
             
            }
            else{
                PopUps.consolidateRulesViolationError();
            }
                
   
        });
        
        root.setVgap(20);
        root.setHgap(20);
        root.getChildren().addAll(selectAccount1,selectAccount2);
        accountConsolidateStage.setScene(accountConsolidation);
        accountConsolidateStage.showAndWait();
        
    }
    
    private static String customCustomerPropertyGetter(Customer customer, String property){
    
        switch (property) {
            case "FatherName":
                return customer.getFatherName();
            case "DOB":
                return customer.getDOB();
            case "Gender":
                return customer.getGender();
            case "Religion":
                return customer.getReligion();
            case "Education":
                return customer.getEducation();
            case "MartialStatus":
                return customer.getMartialStatus();
            case "Nationality":
                return customer.getNationality();
            case "Occupation":
                return customer.getOccupation();
            case "ContactNumber":
                return customer.getContactNumber();
            case "Email":
                return customer.getEmail();
            case "Domicile":
                return customer.getDomicile();
            case "Address":
                return customer.getAddress();
            case "City":
                return customer.getCity();
            case "Province":
                return customer.getProvince();
            case "MailingAddress":
                return customer.getMailingAddress();
            case "AccountTitle":
                return customer.getAccountTitle();
            case "AccountType":
                return customer.getAccountType();
            default:
                System.out.println("This is default case of switch in function custom customer Property getter");
                break;
        }
        
    
        return "";
    }
    
    private static boolean compareTwoCustomers(final Customer c1,final Customer c2){
     
        return c1.getFullName().equalsIgnoreCase(c2.getFullName()) && 
                c1.getFatherName().equalsIgnoreCase(c2.getFatherName()) &&
                c1.getDOB().equalsIgnoreCase(c2.getDOB()) &&
                c1.getCNIC().equalsIgnoreCase(c2.getCNIC()) &&
                c1.getGender().equalsIgnoreCase(c2.getGender()) &&
                c1.getReligion().equalsIgnoreCase(c2.getReligion()) &&
                c1.getEducation().equalsIgnoreCase(c2.getEducation()) &&
                c1.getMartialStatus().equalsIgnoreCase(c2.getMartialStatus()) &&
                c1.getNationality().equalsIgnoreCase(c2.getNationality()) &&
                c1.getOccupation().equalsIgnoreCase(c2.getOccupation()) &&
                c1.getContactNumber().equalsIgnoreCase(c2.getContactNumber()) &&
                c1.getEmail().equalsIgnoreCase(c2.getEmail()) &&
                c1.getDomicile().equalsIgnoreCase(c2.getDomicile()) &&
                c1.getAddress().equalsIgnoreCase(c2.getAddress()) &&
                c1.getCity().equalsIgnoreCase(c2.getCity()) &&
                c1.getProvince().equalsIgnoreCase(c2.getProvince()) &&
                c1.getMailingAddress().equalsIgnoreCase(c2.getMailingAddress());
        
    }
    
    public Scene mySalaryScene(){
        
        GridPane root = new GridPane();
        Scene currentSalary = new Scene(root,400,400);
        root.setVgap(20);
        Label currentBalanceHeading = new Label(String.format("My Current Salary = Rs %,.2f /-",
                dashboardPerson.getSalary()));
        
        root.setAlignment(Pos.CENTER);
        root.addRow(0,currentBalanceHeading);
        return currentSalary;
    }
    
  
}          // end of class "UserDashboard"
