/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import bank.Customer;
import static bank.MainMenu.HANDLE_DATABASE;
import data.FileController;
import data.ValidateInput;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sockets.Chat;

/**
 *
 * @author Talha Asghar
 */
public class UserDashboard {
    
    private double width;
    private double height;
    private final Customer dashboardPerson;
    private final Stage DASHBOARD_STAGE;
    
    public UserDashboard(Customer customer, Stage dashboardStage) {
        
        this.width = 1050;
        this.height = 900;
        this.dashboardPerson = customer;
        DASHBOARD_STAGE = dashboardStage;
        
        DASHBOARD_STAGE.setTitle("User Dashboard");
        DASHBOARD_STAGE.setResizable(false);
        DASHBOARD_STAGE.setScene(createDashboard());
        DASHBOARD_STAGE.showAndWait();
        
    }
    
    
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
        
        Text userCnicText = new Text(dashboardPerson.getCNIC());
        userCnicText.setFont(new Font("Ubuntu", 15));
        Label userCnic = new Label("", userCnicText);

        ImageView profilePicture = new ImageView(new Image(
                        FileController.readCustomerProfilePicturePath(dashboardPerson.getUserName()),150,150,true,true));
        
        FlowPane userInfo = new FlowPane(Orientation.VERTICAL,userName,userCnic);
        userInfo.setAlignment(Pos.BASELINE_RIGHT);
        userInfo.setPrefWrapLength(80);
        
        GridPane.setMargin(dashboardLabel, new Insets(10,10,0,20));
        GridPane.setMargin(userInfo, new Insets(40,20,0,700));
        GridPane.setMargin(profilePicture, new Insets(0,0,30,20));
        
        header.addRow(0,dashboardLabel);
        header.addRow(1,userInfo,profilePicture);
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
        
        Button depositMoney = new Button("Deposit Money",Images.USER_DEPOSIT_MONEY_ICON);
        Button withdrawMoney = new Button("Withdraw Money",Images.USER_WITHDRAW_MONEY_ICON);
        Button payBills = new Button("Pay Utility Bills",Images.USER_PAY_BILL_ICON);
        Button requestLoan = new Button("Request Loan",Images.USER_LOAN_ICON);
        Button accountDetails = new Button("View Account Details",Images.USER_VIEW_PROFILE_ICON);
        Button editProfile = new Button("Edit Profile",Images.USER_EDIT_PROFILE_ICON);
        Button transferMoney = new Button("Transfer Money", Images.USER_TRANSFER_MONEY_ICON);
        
        depositMoney.setContentDisplay(ContentDisplay.TOP);
        withdrawMoney.setContentDisplay(ContentDisplay.TOP);
        payBills.setContentDisplay(ContentDisplay.TOP);
        requestLoan.setContentDisplay(ContentDisplay.TOP);
        accountDetails.setContentDisplay(ContentDisplay.TOP);
        editProfile.setContentDisplay(ContentDisplay.TOP);
        transferMoney.setContentDisplay(ContentDisplay.TOP);
        
        depositMoney.setPrefSize(100,100);
        withdrawMoney.setPrefSize(100,100);
        payBills.setPrefSize(100,100);
        requestLoan.setPrefSize(100,100);
        accountDetails.setPrefSize(100,100);
        editProfile.setPrefSize(100,100);
        transferMoney.setPrefSize(100,100);
        
        userControls.addRow(0, depositMoney, withdrawMoney, payBills);
        userControls.addRow(1, requestLoan, accountDetails, editProfile);
        userControls.addRow(2, transferMoney);
        
        dashboardLayout.setCenter(userControls);
        
        depositMoney.setOnAction(ae->{
            showDepositMoneyScreen();
        
        });
        
        withdrawMoney.setOnAction(ae ->{
            showWithdrawMoneyScreen();
        });
        
        payBills.setOnAction(ae->{});
        
        accountDetails.setOnAction(ae->{
            
            Stage myAccountDetails = new Stage();
            myAccountDetails.setTitle("My Account");
            NewCustomerAccountScenes showProfile = new NewCustomerAccountScenes(dashboardPerson,false, myAccountDetails);
            myAccountDetails.setScene(showProfile.createPersonalInfoScene());
            myAccountDetails.showAndWait();
        });
        
        requestLoan.setOnAction(ae->{
        
            requestLoanScreen();
        
        });
        
        editProfile.setOnAction(ae->{
            
            Stage editProfileStage = new Stage();
            editProfileStage.setTitle("Edit Profile");
            NewCustomerAccountScenes showProfile = new NewCustomerAccountScenes(dashboardPerson,true, editProfileStage);
            editProfileStage.setScene(showProfile.createPersonalInfoScene());
            editProfileStage.showAndWait();
            profilePicture.setImage(new Image(
                        FileController.readCustomerProfilePicturePath(dashboardPerson.getUserName()),150,150,true,true));
        
        });
        
        
        transferMoney.setOnAction(ae->{
            
            Customer reciever = AdminDashboard.showSearchCustomerScene();
            
            if(reciever != null){
                if(reciever.getAccountNumber()==dashboardPerson.getAccountNumber()){
                    PopUps.showCannotTransferToYourselfError();
                }
                else{
                    showTransferMoneyScreen(reciever);
                }
            }
            
        
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
            NewCustomerAccountScenes showProfile = new NewCustomerAccountScenes(dashboardPerson,false, viewProfileStage);
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
        
        Button currentBalance = new Button("Current Balance", Images.USER_DASHBOARD_CURRENT_BALANCE_ICON);
        Button chat = new Button("Live Chat",Images.USER_DASHBOARD_CHAT_ICON);
        Button currencyExchange = new Button("Exchange",Images.USER_EXCHANGE_ICON);
        Button calculator = new Button("Calculator",Images.USER_CALCULATOR_ICON);
        Button logout = new Button("Logout", Images.USER_LOGOUT_ICON);
        
        calculator.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        currentBalance.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        chat.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        currencyExchange.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        logout.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        
        currentBalance.setPrefSize(50,50);
        chat.setPrefSize(50, 50);
        currencyExchange.setPrefSize(50,50);
        logout.setPrefSize(50,50);
        calculator.setPrefSize(50,50);
        
        currentBalance.setShape(new Circle(10));
        chat.setShape(new Circle(10));
        currencyExchange.setShape(new Circle(10));
        calculator.setShape(new Circle(10));
        logout.setShape(new Circle(10));
        
        chat.setOnAction(ae ->{
        
            Chat.showLiveChat(400,400);
       
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
        
        FlowPane rightSideInfo = new FlowPane(Orientation.VERTICAL,
            currentBalance,currencyExchange,calculator, chat, logout);
        rightSideInfo.setVgap(20);
        rightSideInfo.setPrefWrapLength(500);
        rightSide.addRow(0,rightSideInfo);
        
        GridPane.setMargin(rightSideInfo, new Insets(30,10,10,10));
        rightSide.setBorder(Graphics.LEFT_LINE);
        dashboardLayout.setRight(rightSide);
        
        logout.setOnAction(ae->{
           
            try {
                PopUps.showLogoutConfirmation(DASHBOARD_STAGE);
                Files.delete(Paths.get("files/customer_login.dat"));
            } catch (IOException ex) {
                System.out.println(ex);
            }
            
        });
        
        currentBalance.setOnAction(ae->{
    
            Stage currentBalanceStage = new Stage();
            currentBalanceStage.setScene(createCurrentBalanceScene());
            currentBalanceStage.centerOnScreen();
            currentBalanceStage.showAndWait();
        });

        currencyExchange.setOnAction(ae->{
        
            Scenes.showCurrencyExchangeStage(700, 700);
        
        });        
        
        return (new Scene(dashboardLayout, width, height));
        
    }
    
    // show deposit Money Stage
    private void showDepositMoneyScreen(){
    
        Stage depositMoneyStage = new Stage();
        depositMoneyStage.setTitle("Deposit Money");
        BorderPane root = new BorderPane();
        depositMoneyStage.setScene(new Scene(root,400,400));
        GridPane depositMoneyControls = new GridPane();
        depositMoneyControls.setAlignment(Pos.CENTER);
        depositMoneyControls.setVgap(20);
        Label currentBalance = new Label(String.format("Current Balance = Rs %,.2f /-",
                dashboardPerson.getBalance()));
        Label depositMoneyHeading = new Label("Enter Amount to Deposit : ");
        TextField inputDepositAmount = new TextField("0");
        Label newBalance = new Label(String.format("New Balance = Rs %,.2f /-",
                dashboardPerson.getBalance()));
        
        Label confirmPassword = new Label("Confirm your Password : ");
        PasswordField inputPassword = new PasswordField();
        Button enter = new Button("Enter");
        depositMoneyControls.addRow(0, currentBalance);
        depositMoneyControls.addRow(1,depositMoneyHeading);
        depositMoneyControls.addRow(2,inputDepositAmount);
        depositMoneyControls.addRow(3, confirmPassword);
        depositMoneyControls.addRow(4, inputPassword);
        depositMoneyControls.addRow(5,enter);
        
        root.setCenter(depositMoneyControls);
        root.setBottom(newBalance);
        BorderPane.setMargin(newBalance, new Insets(0,0,40,80));
        inputDepositAmount.setOnKeyReleased(ae->{
            
            if(inputDepositAmount.getText().trim().equals("")){
                inputDepositAmount.setText("0");
            }
            
            String temp = inputDepositAmount.getText().trim();
            
            if(ValidateInput.validateMoney(temp)){
                inputDepositAmount.setEffect(null);
                newBalance.setText(String.format("New Balance = Rs %,.2f /-",
                dashboardPerson.getBalance().add(BigDecimal.valueOf(Double.parseDouble(temp)))));
            
            }else {
                inputDepositAmount.setEffect(Graphics.INNER_SHADOW_RED);
            }
              
        });
        
        enter.setOnAction(ae->{
            
            String balance = inputDepositAmount.getText().trim();
            
            if(ValidateInput.validateMoney(balance)){
                if(inputPassword.getText().equals(dashboardPerson.getPassword())){
                    dashboardPerson.setBalance(dashboardPerson.getBalance().add(
                            BigDecimal.valueOf(Double.parseDouble(balance))));
                    HANDLE_DATABASE.updateCustomerBalance(dashboardPerson.getUserName(),
                            dashboardPerson.getBalance().toString(), "Customer");
                    PopUps.showTransactionCompleteMessage(dashboardPerson.getBalance());
                    depositMoneyStage.close();
                }
                else{
                    PopUps.showIncorrectPasswordError();
                }
            }
            else{
                PopUps.showInvalidBalanceMessage();
            }
                
        });
    
        depositMoneyStage.showAndWait();
    }
    
    private void showWithdrawMoneyScreen(){

        Stage withdrawMoneyStage = new Stage();
        withdrawMoneyStage.setTitle("Withdraw Money");
        BorderPane root = new BorderPane();
        withdrawMoneyStage.setScene(new Scene(root,400,400));
        GridPane withdrawMoneyControls = new GridPane();
        withdrawMoneyControls.setAlignment(Pos.CENTER);
        withdrawMoneyControls.setVgap(20);
        Label currentBalance = new Label(String.format("Current Balance = Rs %,.2f /-",
                dashboardPerson.getBalance()));
        Label withdrawMoneyHeading = new Label("Enter Amount to Withdraw : ");
        TextField inputWithdrawAmount = new TextField("0");
        Label newBalance = new Label(String.format("New Balance = Rs %,.2f /-",
                dashboardPerson.getBalance()));
        
        Label confirmPassword = new Label("Confirm your Password : ");
        PasswordField inputPassword = new PasswordField();
        Button enter = new Button("Enter");
        withdrawMoneyControls.addRow(0, currentBalance);
        withdrawMoneyControls.addRow(1,withdrawMoneyHeading);
        withdrawMoneyControls.addRow(2,inputWithdrawAmount);
        withdrawMoneyControls.addRow(3, confirmPassword);
        withdrawMoneyControls.addRow(4, inputPassword);
        withdrawMoneyControls.addRow(5,enter);
        
        root.setCenter(withdrawMoneyControls);
        root.setBottom(newBalance);
        BorderPane.setMargin(newBalance, new Insets(0,0,40,80));
        inputWithdrawAmount.setOnKeyReleased(ae->{
            
            if(inputWithdrawAmount.getText().trim().equals("")){
                inputWithdrawAmount.setText("0");
            }
            
            String temp = inputWithdrawAmount.getText().trim();
            
            if(ValidateInput.validateMoney(temp)){
                if(Double.parseDouble(inputWithdrawAmount.getText().trim())
                        >dashboardPerson.getBalance().doubleValue()){
                    
                    inputWithdrawAmount.setEffect(Graphics.INNER_SHADOW_RED);
                    
                }else{
                    inputWithdrawAmount.setEffect(null);
                    newBalance.setText(String.format("New Balance = Rs %,.2f /-",
                    dashboardPerson.getBalance().subtract(BigDecimal.valueOf(Double.parseDouble(temp)))));
                }
            }else {
                inputWithdrawAmount.setEffect(Graphics.INNER_SHADOW_RED);
            }
              
        });
        
        enter.setOnAction(ae->{
            
            String balance = inputWithdrawAmount.getText().trim();
            
            if(ValidateInput.validateMoney(balance)){
                if(Double.parseDouble(inputWithdrawAmount.getText().trim())
                        >dashboardPerson.getBalance().doubleValue()){
             
                    PopUps.showInsufficientBalanceMessage();
                    
                }else{
                    if(inputPassword.getText().equals(dashboardPerson.getPassword())){
                        dashboardPerson.setBalance(dashboardPerson.getBalance().subtract(
                                BigDecimal.valueOf(Double.parseDouble(balance))));
                        HANDLE_DATABASE.updateCustomerBalance(dashboardPerson.getUserName(),
                                dashboardPerson.getBalance().toString(), "Customer");
                        PopUps.showTransactionCompleteMessage(dashboardPerson.getBalance());
                        withdrawMoneyStage.close();
                    }
                    else{
                        PopUps.showIncorrectPasswordError();
                    }
                }
            }
            else{
                PopUps.showInvalidBalanceMessage();
            }
            
        });
    
        withdrawMoneyStage.showAndWait();
      
    }
    
    
    private void requestLoanScreen(){
        
        Stage requestLoanStage = new Stage();
        requestLoanStage.setTitle("Request Loan");
        BorderPane root = new BorderPane();
        requestLoanStage.setScene(new Scene(root,400,400));
        GridPane requestLoanControls = new GridPane();
        requestLoanControls.setAlignment(Pos.CENTER);
        requestLoanControls.setVgap(20);
        
        Label currentLoan = new Label(String.format("Current Balance = Rs %,.2f /-%n"
            + "Current Loan = Rs %,.2f /-",dashboardPerson.getBalance(),dashboardPerson.getLoan()));
        Label loanAmountHeading = new Label("Loan Amount : ");
        TextField inputLoanAmount = new TextField("0");
        Label newBalance = new Label(String.format("New Balance = Rs %,.2f /-%n"
            + "Total Loan = Rs %,.2f /-",dashboardPerson.getBalance(),dashboardPerson.getLoan()));
        
        Label confirmPassword = new Label("Confirm your Password : ");
        PasswordField inputPassword = new PasswordField();
        Button enter = new Button("Enter");
        requestLoanControls.addRow(0, currentLoan);
        requestLoanControls.addRow(1,loanAmountHeading);
        requestLoanControls.addRow(2,inputLoanAmount);
        requestLoanControls.addRow(3, confirmPassword);
        requestLoanControls.addRow(4, inputPassword);
        requestLoanControls.addRow(5,enter);
        
        root.setCenter(requestLoanControls);
        root.setBottom(newBalance);
        BorderPane.setMargin(newBalance, new Insets(0,0,40,80));
        inputLoanAmount.setOnKeyReleased(ae->{
            
            if(inputLoanAmount.getText().trim().equals("")){
                inputLoanAmount.setText("0");
            }
            
            String temp = inputLoanAmount.getText().trim();
            
            if(ValidateInput.validateMoney(temp)){
                inputLoanAmount.setEffect(null);
                newBalance.setText(String.format("New Balance = Rs %,.2f /-%n"
            + "Total Loan = Rs %,.2f /-",dashboardPerson.getBalance().add(BigDecimal.valueOf(Double.parseDouble(temp))),
                dashboardPerson.getLoan().add(BigDecimal.valueOf(Double.parseDouble(temp)))));
            
            }else {
                inputLoanAmount.setEffect(Graphics.INNER_SHADOW_RED);
            }
              
        });
        
        enter.setOnAction(ae->{
            
            String balance = inputLoanAmount.getText().trim();
            
            if(ValidateInput.validateMoney(balance)){
                if(inputPassword.getText().equals(dashboardPerson.getPassword())){
                                       
                    dashboardPerson.setLoan(dashboardPerson.getLoan().add(
                            BigDecimal.valueOf(Double.parseDouble(balance))));
                    dashboardPerson.setBalance(dashboardPerson.getBalance().add(
                            BigDecimal.valueOf(Double.parseDouble(balance))));
                    if(HANDLE_DATABASE.tryCustomerDataUpdate(dashboardPerson, "Customer")){
                    
                        PopUps.showLoanUpdateMessage(dashboardPerson.getBalance(),dashboardPerson.getLoan());
                    }else {
                        PopUps.showLoanUpdateError();
                    }
                    
                    requestLoanStage.close();
                }
                else{
                    PopUps.showIncorrectPasswordError();
                }
            }
            else{
                PopUps.showInvalidBalanceMessage();
            }
                
        });
    
 
        requestLoanStage.showAndWait();
      
    }

    private void showTransferMoneyScreen(Customer reciever){
    
        Stage transferMoneyStage = new Stage();
        transferMoneyStage.setTitle("Transfer Money");
        BorderPane root = new BorderPane();
        transferMoneyStage.setScene(new Scene(root,400,400));
        GridPane transferMoneyControls = new GridPane();
        transferMoneyControls.setAlignment(Pos.CENTER);
        transferMoneyControls.setVgap(20);
        Label currentBalance = new Label(String.format("My Current Balance = Rs %,.2f /-",
                dashboardPerson.getBalance()));
        Label transferMoneyHeading = new Label("Enter Amount to Transfer : ");
        TextField inputTransferAmount = new TextField("0");
        Label newBalance = new Label(String.format("My New Balance = Rs %,.2f /-",
                dashboardPerson.getBalance()));
        
        Label confirmPassword = new Label("Confirm your Password : ");
        PasswordField inputPassword = new PasswordField();
        Button enter = new Button("Enter");
        transferMoneyControls.addRow(0, currentBalance);
        transferMoneyControls.addRow(1,transferMoneyHeading);
        transferMoneyControls.addRow(2,inputTransferAmount);
        transferMoneyControls.addRow(3, confirmPassword);
        transferMoneyControls.addRow(4, inputPassword);
        transferMoneyControls.addRow(5,enter);
        
        root.setCenter(transferMoneyControls);
        root.setBottom(newBalance);
        BorderPane.setMargin(newBalance, new Insets(0,0,40,80));
        inputTransferAmount.setOnKeyReleased(ae->{
            
            if(inputTransferAmount.getText().trim().equals("")){
                inputTransferAmount.setText("0");
            }
            
            String temp = inputTransferAmount.getText().trim();
            
            if(ValidateInput.validateMoney(temp)){
                if(Double.parseDouble(inputTransferAmount.getText().trim())
                        >dashboardPerson.getBalance().doubleValue()){
                    
                    inputTransferAmount.setEffect(Graphics.INNER_SHADOW_RED);
                    
                }else{
                    inputTransferAmount.setEffect(null);
                    newBalance.setText(String.format("New Balance = Rs %,.2f /-",
                    dashboardPerson.getBalance().subtract(BigDecimal.valueOf(Double.parseDouble(temp)))));
                }
            }else {
                inputTransferAmount.setEffect(Graphics.INNER_SHADOW_RED);
            }
              
        });
        
        enter.setOnAction(ae->{
            
            String balance = inputTransferAmount.getText().trim();
            
            if(ValidateInput.validateMoney(balance)){
                if(Double.parseDouble(inputTransferAmount.getText().trim())
                        >dashboardPerson.getBalance().doubleValue()){
             
                    PopUps.showInsufficientBalanceMessage();
                    
                }else{
                    if(inputPassword.getText().equals(dashboardPerson.getPassword())){
                        
                        double transferAmount = Double.parseDouble(inputTransferAmount.getText().trim());
                        
                        reciever.setBalance(reciever.getBalance().add(BigDecimal.valueOf(transferAmount)));
                        HANDLE_DATABASE.updateCustomerBalance(reciever.getUserName() ,reciever.getBalance().toString() , "Customer");

                        dashboardPerson.setBalance(dashboardPerson.getBalance().subtract(BigDecimal.valueOf(transferAmount)));
                        HANDLE_DATABASE.updateCustomerBalance(dashboardPerson.getUserName(),dashboardPerson.getBalance().toString(),
                                "Customer");
                        PopUps.showTransactionCompleteMessage(dashboardPerson.getBalance());
                        transferMoneyStage.close();
                    }
                    else{
                        PopUps.showIncorrectPasswordError();
                    }
                }
            }
            else{
                PopUps.showInvalidBalanceMessage();
            }
            
        });
    
        transferMoneyStage.showAndWait();    


    }
    
    private Scene createCurrentBalanceScene(){
        
        GridPane root = new GridPane();
        Scene currentBalance = new Scene(root,400,400);
        root.setVgap(20);
        Label currentBalanceHeading = new Label(String.format("My Current balance = Rs %,.2f /-",
                dashboardPerson.getBalance()));
        Label currentLoanHeading = new Label(String.format("My Current Loan Amount = Rs %,.2f /-",
                dashboardPerson.getLoan()));
        
        root.setAlignment(Pos.CENTER);
        root.addRow(0,currentBalanceHeading);
        root.addRow(1,currentLoanHeading);
        return currentBalance;
    }
    

    
   
}          // end of class "UserDashboard"
