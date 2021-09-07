/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import bank.Accountable;
import bank.CurrencyExchange;
import bank.Customer;
import bank.Employee;
import bank.MainMenu;
import static bank.MainMenu.HANDLE_DATABASE;
import data.FileController;
import data.ValidateInput;
import static interfaces.Graphics.*;
import java.math.BigDecimal;
import javafx.collections.FXCollections;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.*;
import sockets.Chat;

/**
 *
 * @author Talha Asghar
 */
public class Scenes {


    private static double initX;   // used in dragging of loadingAppStage
    private static double initY;   // used in dragging of loadinAppStage

    
    // show Loading App stage
    public final void showLoadingAppStage(double width, double height){

        Stage loadingAppStage = new Stage(StageStyle.TRANSPARENT);
        loadingAppStage.setResizable(false);
        
        GridPane root = new GridPane();
        
        loadingAppStage.centerOnScreen();
        loadingAppStage.setScene(new Scene(root,width,height,Color.TRANSPARENT));
        
        BackgroundImage hblImage = new BackgroundImage(new Image(Images.HBL_BACKGROUND),
              null,null,null,null);  

        
        root.setBackground(new Background(hblImage));
        root.setAlignment(Pos.BOTTOM_CENTER);
       
        Button startApplication = new Button("Start Application");
        GridPane.setMargin(startApplication, new Insets(0,0,10,0));
        root.addRow(0,startApplication);
        
        root.setOnMousePressed((MouseEvent ae) -> {
            initX = ae.getScreenX() - loadingAppStage.getX();
            initY = ae.getScreenY() - loadingAppStage.getY();
            
        });
        
        root.setOnMouseDragged(ae ->{
            
            loadingAppStage.setX(ae.getScreenX() - initX);
            loadingAppStage.setY(ae.getScreenY() - initY);
             
        });
        startApplication.setOnAction(ae ->{
        
            loadingAppStage.close();
        
        
        });
        
        loadingAppStage.showAndWait();
          
    }          // end of "showLoadinAppStage"
    
    
    // create Main Menu Stage
    public Stage createMainMenuStage(double width, double height, int pages){

        Stage mainMenuStage = new Stage (StageStyle.TRANSPARENT);
        mainMenuStage.setResizable(false);

        mainMenuStage.setTitle("Main Menu");
        
        BorderPane root = new BorderPane();
        Button close = new Button("Close", Images.CLOSE_ICON);
        Button minimize = new Button("Min", Images.MINIMIZE_ICON);
        close.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        minimize.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        minimize.setBackground(MINIMIZE_BUTTON_BACKGROUND);
        close.setBackground(CLOSE_BUTTON_BACKGROUND);
        
        HBox titleBar = new HBox(minimize,close);

        titleBar.setBackground(TITLE_BAR_BACKGROUND);
        titleBar.setAlignment(Pos.BASELINE_RIGHT);
        root.setTop(titleBar);
        
        minimize.setOnAction(ae->{
            
            mainMenuStage.setIconified(true);
        
        });
        
        close.setOnAction(ae->{
            
            mainMenuStage.close();
        
        });
        
        minimize.setOnMouseEntered(ae->{
            minimize.setStyle("-fx-border-color: WHITE");
        });
        close.setOnMouseEntered(ae->{
            close.setStyle("-fx-border-color: WHITE");
        });
        
        minimize.setOnMouseExited(ae->{
           minimize.setStyle("-fx-border-color: #262e41");
        });
        close.setOnMouseExited(ae->{
            close.setStyle("-fx-border-color: #f63c55");
        });
        
        root.setOnMousePressed((MouseEvent ae) -> {
            initX = ae.getScreenX() - mainMenuStage.getX();
            initY = ae.getScreenY() - mainMenuStage.getY();
            
        });
        
        root.setOnMouseDragged(ae ->{
            
            mainMenuStage.setX(ae.getScreenX() - initX);
            mainMenuStage.setY(ae.getScreenY() - initY);
             
        });
        
        root.setBorder(STAGE_BOUNDARY);
        
        Label credits = new Label("Programmer : Talha Asghar 2018 © Copyrights All rights reserved.");
        HBox creditsContainer = new HBox(credits);
        creditsContainer.setPrefHeight(30);
        creditsContainer.setAlignment(Pos.CENTER);
        HBox.setMargin(creditsContainer, new Insets(10,0,10,0));
        root.setBottom(creditsContainer);
        
        mainMenuStage.setScene(new Scene(root,width,height));
        Pagination menus = new Pagination(pages,0);
        
        menus.setPageFactory((Integer pageIndex) -> {
            if(null == pageIndex) {
                return null;
            } else {
                switch (pageIndex) {
                    case 0:
                        return createMenuPage1(mainMenuStage);
                    case 1:
                        return createMenuPage2(mainMenuStage);
                    default:
                        return null;
                }
            }

        });

        menus.getStyleClass().add(Pagination.STYLE_CLASS_BULLET);
        root.setCenter(menus);

           return mainMenuStage;

       }       // end of "createMainMenuStage"
    
    
    // create main Menu Page # 1
    private GridPane createMenuPage1(Stage menuStage){

        GridPane menu1 = new GridPane();
        
        menu1.setHgap(30);
        menu1.setVgap(30);
        menu1.setAlignment(Pos.CENTER);
        
        Label heading = new Label("Banking Management System");
        heading.setFont(HEADING_FONT);
        FlowPane headingContainer = new FlowPane(heading);
        headingContainer.setAlignment(Pos.CENTER);
        FlowPane.setMargin(heading, new Insets(0,0,30,0));
        
        Button employee = new Button("Employee Login", Images.ADMIN_LOGIN_ICON);
        Button user = new Button("Customer Login", Images.USER_LOGIN_ICON);
        Button atm = new Button("ATM", Images.ATM_ICON);
        Button newAccount = new Button ("Create New Account",Images.NEW_ACCOUNT_ICON_MAIN);
        Button exchange = new Button("Currency Exchange", Images.CURRENCY_EXCHANGE_ICON);
        Button payUtilityBills = new Button("Pay Utility Bills", Images.PAY_BILLS_ICON);

        employee.setContentDisplay(ContentDisplay.TOP);
        user.setContentDisplay(ContentDisplay.TOP);
        atm.setContentDisplay(ContentDisplay.TOP);
        newAccount.setContentDisplay(ContentDisplay.TOP);
        exchange.setContentDisplay(ContentDisplay.TOP);
        payUtilityBills.setContentDisplay(ContentDisplay.TOP);

        employee.setTooltip(new Tooltip("Employee Login Portal"));
        user.setTooltip(new Tooltip("User Login Portal"));
        atm.setTooltip(new Tooltip("ATM Simulation"));
        newAccount.setTooltip(new Tooltip("Create New Customer/Employee Account"));
        exchange.setTooltip(new Tooltip("Currency Exchange"));
        payUtilityBills.setTooltip(new Tooltip("Pay Utility Bills"));

        employee.setPrefSize(100,100);
        user.setPrefSize(100,100);
        atm.setPrefSize(100,100);
        newAccount.setPrefSize(100,100);
        exchange.setPrefSize(100,100);
        payUtilityBills.setPrefSize(100,100);

        menu1.addRow(0, headingContainer);
        menu1.addRow(1,employee,user,atm);
        menu1.addRow(2,newAccount, exchange, payUtilityBills);
        GridPane.setColumnSpan(headingContainer, 3);
         
        employee.setOnAction(ae -> {

            menuStage.setIconified(true);
            showEmployeePortal(900,900);
            menuStage.setIconified(false);

        });

        user.setOnAction(ae -> {

            menuStage.setIconified(true);
            showUserPortal(900,950);
            menuStage.setIconified(false);
        });

        newAccount.setOnAction(ae ->{

            menuStage.setIconified(true);
                showMakeNewAccountStage();
            menuStage.setIconified(false);


        });

        atm.setOnAction(ae ->{

            menuStage.setIconified(true);
                Stage atmStage = new Stage();
            AtmScenes atmScenes = new AtmScenes(700,700, atmStage);
                 atmScenes.atmSimulation();
            menuStage.setIconified(false);

        });


        exchange.setOnAction(ae->{

            Scenes.showCurrencyExchangeStage(700, 700);

        });

        payUtilityBills.setOnAction(ae->{

            showUtilityBillsStage(700,700);

        });


        return menu1;

    }           // end of "createMenuPage1"
 
    
    // create Main Menu Page # 2
    private GridPane createMenuPage2(Stage menuStage){
    
        GridPane menu2 = new GridPane();
        
        menu2.setAlignment(Pos.CENTER);
        menu2.setVgap(30);
        menu2.setHgap(30);
        
        Label heading = new Label("Banking Management System");
        heading.setFont(HEADING_FONT);
        FlowPane headingContainer = new FlowPane(heading);
        headingContainer.setAlignment(Pos.CENTER);
        FlowPane.setMargin(heading, new Insets(0,0,30,0));
        
        Button liveChat = new Button("Live Chat", Images.LIVE_CHAT_ICON);
        Button about = new Button("About", Images.ABOUT_ICON);
        Button liveAgent = new Button("Live Agent", Images.LIVE_AGENT_ICON);
        
        liveChat.setContentDisplay(ContentDisplay.TOP);
        about.setContentDisplay(ContentDisplay.TOP);
        liveAgent.setContentDisplay(ContentDisplay.TOP);
        
        liveChat.setTooltip(new Tooltip("Live Chat"));
        liveAgent.setTooltip(new Tooltip("Live Agent"));
        about.setTooltip(new Tooltip("About"));
        
        about.setPrefSize(100,100);
        liveChat.setPrefSize(100,100);
        liveAgent.setPrefSize(100,100);
        
        menu2.addRow(0, headingContainer);
        menu2.addRow(1, liveChat, liveAgent, about);
        GridPane.setColumnSpan(headingContainer, 3);
       
        liveAgent.setOnAction(ae ->{
               

            menuStage.setIconified(true);
                
            Chat.showLiveAgent(400, 400);
                
            menuStage.setIconified(false);

        });
        
        liveChat.setOnAction(ae ->{
        
            menuStage.setIconified(true);
            Chat.showLiveChat(400, 400);
            menuStage.setIconified(false);

        
        });
      
        about.setOnAction(ae->{
        
            menuStage.setIconified(true);
            showAboutStage(700,700);
            menuStage.setIconified(false);

        });
        
        return menu2;
        
    }       // end of "createMenuPage2" 
 
    
    // create and show Employee Login Portal
    @SuppressWarnings("unchecked")
    private void showEmployeePortal(double width, double height){

        Stage employeeLoginStage = new Stage(StageStyle.TRANSPARENT);
        employeeLoginStage.setTitle("Employee Login");
        employeeLoginStage.setResizable(false);
        
        BorderPane root = new BorderPane();
        Button close = new Button("Close", Images.CLOSE_ICON);
        Button minimize = new Button("Min", Images.MINIMIZE_ICON);
        close.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        minimize.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        minimize.setBackground(MINIMIZE_BUTTON_BACKGROUND);
        close.setBackground(CLOSE_BUTTON_BACKGROUND);
        
        HBox titleBar = new HBox(minimize,close);

        titleBar.setBackground(TITLE_BAR_BACKGROUND);
        titleBar.setAlignment(Pos.BASELINE_RIGHT);
        root.setTop(titleBar);
        
        minimize.setOnAction(ae->{
            
            employeeLoginStage.setIconified(true);
        
        });
        
        close.setOnAction(ae->{
            
            employeeLoginStage.close();
        
        });
        
        minimize.setOnMouseEntered(ae->{
            minimize.setStyle("-fx-border-color: WHITE");
        });
        close.setOnMouseEntered(ae->{
            close.setStyle("-fx-border-color: WHITE");
        });
        
        minimize.setOnMouseExited(ae->{
           minimize.setStyle("-fx-border-color: #262e41");
        });
        close.setOnMouseExited(ae->{
            close.setStyle("-fx-border-color: #f63c55");
        });
        
        root.setOnMousePressed((MouseEvent ae) -> {
            initX = ae.getScreenX() - employeeLoginStage.getX();
            initY = ae.getScreenY() - employeeLoginStage.getY();
            
        });
        
        root.setOnMouseDragged(ae ->{
            
            employeeLoginStage.setX(ae.getScreenX() - initX);
            employeeLoginStage.setY(ae.getScreenY() - initY);
             
        });
        
        root.setBorder(STAGE_BOUNDARY);
        
        Label credits = new Label("Programmer : Talha Asghar 2018 © Copyrights All rights reserved.");
        HBox creditsContainer = new HBox(credits);
        creditsContainer.setPrefHeight(30);
        creditsContainer.setAlignment(Pos.CENTER);
        HBox.setMargin(creditsContainer, new Insets(10,0,10,0));
        root.setBottom(creditsContainer);
        
        GridPane employee = new GridPane();
        employee.setAlignment(Pos.CENTER);
        employee.setVgap(40);
        employee.setHgap(40);
        
        root.setCenter(employee);
        Scene employeeLoginScene = new Scene(root,width,height);
        employeeLoginStage.setScene(employeeLoginScene);
        
        Label heading = new Label("Employee Login Portal");
        heading.setFont(HEADING_FONT);
        FlowPane headingContainer = new FlowPane(heading);
        headingContainer.setAlignment(Pos.CENTER);
        FlowPane.setMargin(heading, new Insets(0,0,30,0));
        
        
        CheckBox rememberMe = new CheckBox("Remember Me");
        
        TextField inputUserName = new TextField();
        PasswordField inputUserPassword = new PasswordField();
        Hyperlink signUp = new Hyperlink("Not Member Yet? Create Account");
        Hyperlink forgotPassword = new Hyperlink("Forgot Password?");
        Hyperlink differentAccount = new Hyperlink("Login with different account");
        differentAccount.setVisible(false);
        ImageView profilePicture = new ImageView(new Image(
                Images.ADMIN_LOGIN,250,250,true,true));
        
        signUp.setFont(new Font(signUp.getFont().getName(),15));
        differentAccount.setFont(new Font(differentAccount.getFont().getName(),15));
        rememberMe.setFont(new Font(rememberMe.getFont().getName(),15));
        
        inputUserName.setPromptText("User Name");
        inputUserName.setBackground(TRANSPARENT_BACKGROUND);
        inputUserName.setFont(TEXTFIELD_FONT);
        inputUserName.setBorder(TEXTFIELD_BORDER);
        inputUserName.setStyle("-fx-text-inner-Color : black");
        
        inputUserPassword.setPromptText("Password");
        inputUserPassword.setBackground(TRANSPARENT_BACKGROUND);
        inputUserPassword.setFont(TEXTFIELD_FONT);
        inputUserPassword.setBorder(TEXTFIELD_BORDER);
        inputUserPassword.setStyle("-fx-text-inner-Color : black");
        inputUserPassword.setVisible(false);
        Button login = new Button("Log In", Images.LOGIN_ICON);
        login.setContentDisplay(ContentDisplay.TOP);
        login.setTooltip(new Tooltip("Login"));
        
        employee.addRow(0, heading);
        employee.addRow(1,profilePicture);
        employee.addRow(2,inputUserName);
        employee.addRow(3,inputUserPassword);
        employee.addRow(4, rememberMe);
        //employee.addRow(5,forgotPassword);
        employee.addRow(5,signUp);
        employee.addRow(6,login);
        employee.addRow(7, differentAccount);
        GridPane.setColumnSpan(heading, 2);
        GridPane.setMargin(profilePicture, new Insets(0,0,0,10));
        //GridPane.setMargin(forgotPassword,new Insets(0,0,0,150));
        GridPane.setMargin(login, new Insets(-30,0,0,85));
        GridPane.setMargin(differentAccount, new Insets(0,0,0,85));
        login.requestFocus();
        
        if(FileController.readAdminLoginDataIfExists()!=null){
            
            Employee admin = HANDLE_DATABASE.readAllDataOfEmployee(FileController.readAdminLoginDataIfExists(),"Employee");
            employeeLoginStage.setIconified(true);
            AdminDashboard adminDashboard = new AdminDashboard(admin, new Stage());
            employeeLoginStage.close();
        }
        
        inputUserName.setOnAction(ae->{
            if(HANDLE_DATABASE.doesAccountExists(inputUserName.getText().trim(),"Employee")){
                profilePicture.setImage(new Image(
                        FileController.readAdminProfilePicturePath(inputUserName.getText().trim()),250,250,true,true));
                inputUserPassword.setVisible(true);        
                inputUserName.setVisible(false);
                login.requestFocus();
                differentAccount.setVisible(true);
            }
            else {
                PopUps.showInvalidAccountError();
            }
        });

        inputUserPassword.setOnAction(ae->{
            Employee admin;
            if(HANDLE_DATABASE.doesAccountExists(inputUserName.getText().trim(),"Employee")){
                if(HANDLE_DATABASE.isPasswordCorrect(inputUserName.getText().trim(), 
                        inputUserPassword.getText().trim(),"Employee")){

                    admin = HANDLE_DATABASE.readAllDataOfEmployee(
                            inputUserName.getText().trim(),"Employee");
                    if(rememberMe.isSelected()){
                        FileController.writeAdminLoginData(admin.getUserName());
                    }
                    employeeLoginStage.setIconified(true);
                    AdminDashboard adminDashboard = new AdminDashboard(admin, new Stage());
                    employeeLoginStage.close();
                }
                else {
                    PopUps.showIncorrectPasswordError();
                }
            }
        });        
        
        login.setOnAction(ae->{
            
            Employee admin;
            if(!inputUserPassword.isVisible()){
                if(HANDLE_DATABASE.doesAccountExists(inputUserName.getText().trim(),"Employee")){
                    profilePicture.setImage(new Image(
                            FileController.readAdminProfilePicturePath(inputUserName.getText().trim()),250,250,true,true));
                    inputUserPassword.setVisible(true);   
                    inputUserName.setVisible(false);
                    login.requestFocus();
                    differentAccount.setVisible(true);
                }
                else {
                    PopUps.showInvalidAccountError();
                }
            
            }else{
                if(HANDLE_DATABASE.doesAccountExists(inputUserName.getText().trim(),"Employee")){
                    if(HANDLE_DATABASE.isPasswordCorrect(inputUserName.getText().trim(), 
                            inputUserPassword.getText().trim(),"Employee")){

                        admin = HANDLE_DATABASE.readAllDataOfEmployee(
                                inputUserName.getText().trim(),"Employee");
                        if(rememberMe.isSelected()){
                            FileController.writeAdminLoginData(admin.getUserName());
                        }
                        employeeLoginStage.setIconified(true);
                        AdminDashboard adminDashboard = new AdminDashboard(admin, new Stage());
                        employeeLoginStage.close();
                    }
                    else {
                        PopUps.showIncorrectPasswordError();
                    }
                }
            }
        });
        
        differentAccount.setOnAction(ae->{
            inputUserPassword.setVisible(false); 
            inputUserName.setVisible(true);
            differentAccount.setVisible(false);
            profilePicture.setImage(new Image(
                Images.ADMIN_LOGIN,250,250,true,true));
            inputUserName.clear();
            inputUserPassword.clear();
            login.requestFocus();
        });
        
        signUp.setOnAction(ae ->{
            
            Scenes.showMakeNewAccountStage();
        });
        
        forgotPassword.setOnAction(ae->{
        
        });
        
        employeeLoginStage.showAndWait();
    
        
        
    }       // end of "showEmployeePortal"

    
    //  create and show user login Portal
    public void showUserPortal(double width, double height){

        Stage userLoginStage = new Stage(StageStyle.TRANSPARENT);
        userLoginStage.setTitle("User Login");
        userLoginStage.setResizable(false);
        
        
        BorderPane root = new BorderPane();
        Button close = new Button("Close", Images.CLOSE_ICON);
        Button minimize = new Button("Min", Images.MINIMIZE_ICON);
        close.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        minimize.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        minimize.setBackground(MINIMIZE_BUTTON_BACKGROUND);
        close.setBackground(CLOSE_BUTTON_BACKGROUND);
        
        HBox titleBar = new HBox(minimize,close);

        titleBar.setBackground(TITLE_BAR_BACKGROUND);
        titleBar.setAlignment(Pos.BASELINE_RIGHT);
        root.setTop(titleBar);
        
        minimize.setOnAction(ae->{
            
            userLoginStage.setIconified(true);
        
        });
        
        close.setOnAction(ae->{
            
            userLoginStage.close();
        
        });
        
        minimize.setOnMouseEntered(ae->{
            minimize.setStyle("-fx-border-color: WHITE");
        });
        close.setOnMouseEntered(ae->{
            close.setStyle("-fx-border-color: WHITE");
        });
        
        minimize.setOnMouseExited(ae->{
           minimize.setStyle("-fx-border-color: #262e41");
        });
        close.setOnMouseExited(ae->{
            close.setStyle("-fx-border-color: #f63c55");
        });
        
        root.setOnMousePressed((MouseEvent ae) -> {
            initX = ae.getScreenX() - userLoginStage.getX();
            initY = ae.getScreenY() - userLoginStage.getY();
            
        });
        
        root.setOnMouseDragged(ae ->{
            
            userLoginStage.setX(ae.getScreenX() - initX);
            userLoginStage.setY(ae.getScreenY() - initY);
             
        });
        
        root.setBorder(STAGE_BOUNDARY);
        
        Label credits = new Label("Programmer : Talha Asghar 2018 © Copyrights All rights reserved.");
        HBox creditsContainer = new HBox(credits);
        creditsContainer.setPrefHeight(30);
        creditsContainer.setAlignment(Pos.CENTER);
        HBox.setMargin(creditsContainer, new Insets(10,0,10,0));
        root.setBottom(creditsContainer);
        
        
        GridPane userLogin = new GridPane();
        userLogin.setAlignment(Pos.CENTER);
        userLogin.setVgap(40);
        userLogin.setHgap(40);

        Scene userLoginScene = new Scene(userLogin,width,height);
        userLoginStage.setScene(userLoginScene);

        root.setCenter(userLogin);
        Scene employeeLoginScene = new Scene(root,width,height);
        userLoginStage.setScene(employeeLoginScene);
        
        Label heading = new Label("Customer Login Portal");
        heading.setFont(HEADING_FONT);
        FlowPane headingContainer = new FlowPane(heading);
        headingContainer.setAlignment(Pos.CENTER);
        FlowPane.setMargin(heading, new Insets(0,0,30,0));

        CheckBox rememberMe = new CheckBox("Remember Me");
        TextField inputUserName = new TextField();
        PasswordField inputUserPassword = new PasswordField();
        Hyperlink signUp = new Hyperlink("Not Member Yet? Create Account");
        Hyperlink forgotPassword = new Hyperlink("Forgot Password?");
        Hyperlink differentAccount = new Hyperlink("Login with different account");
        differentAccount.setVisible(false);
        ImageView profilePicture = new ImageView(new Image(
                Images.USER_LOGIN,250,250,true,true));
        
        inputUserName.setPromptText("User Name");
        inputUserName.setBackground(TRANSPARENT_BACKGROUND);
        inputUserName.setFont(TEXTFIELD_FONT);
        inputUserName.setBorder(TEXTFIELD_BORDER);
        inputUserName.setStyle("-fx-text-inner-Color : black");
        
        inputUserPassword.setPromptText("Password");
        inputUserPassword.setBackground(TRANSPARENT_BACKGROUND);
        inputUserPassword.setFont(TEXTFIELD_FONT);
        inputUserPassword.setBorder(TEXTFIELD_BORDER);
        inputUserPassword.setStyle("-fx-text-inner-Color : black");
        inputUserPassword.setVisible(false);
        
        Button login = new Button("Log In", Images.LOGIN_ICON);
        
        login.setContentDisplay(ContentDisplay.TOP);
        login.setTooltip(new Tooltip("Login"));
        
        signUp.setFont(new Font(signUp.getFont().getName(),15));
        differentAccount.setFont(new Font(differentAccount.getFont().getName(),15));
        rememberMe.setFont(new Font(rememberMe.getFont().getName(),15));
        
        userLogin.addRow(0,headingContainer );
        userLogin.addRow(1,profilePicture);
        userLogin.addRow(2,inputUserName);
        userLogin.addRow(3,inputUserPassword);
        userLogin.addRow(4, rememberMe);
        //userLogin.addRow(4,forgotPassword);
        userLogin.addRow(5,signUp);
        userLogin.addRow(6,login);
        userLogin.addRow(7, differentAccount);
        GridPane.setColumnSpan(headingContainer, 2);
        GridPane.setMargin(profilePicture, new Insets(0,0,0,10));
        //GridPane.setMargin(forgotPassword,new Insets(0,0,0,150));
        GridPane.setMargin(login, new Insets(-30,0,0,85));
        GridPane.setMargin(differentAccount, new Insets(0,0,0,85));
        login.requestFocus();
        
        if(FileController.readCustomerLoginDataIfExists()!=null){
            
            Customer customer = HANDLE_DATABASE.readAllDataOfCustomer(FileController.readCustomerLoginDataIfExists(),"Customer");
            userLoginStage.setIconified(true);
            UserDashboard userDashboard = new UserDashboard(customer,new Stage());
            userLoginStage.close();
        }
        
        inputUserName.setOnAction(ae->{
            if(HANDLE_DATABASE.doesAccountExists(inputUserName.getText().trim(),"Customer")){
                profilePicture.setImage(new Image(
                        FileController.readCustomerProfilePicturePath(inputUserName.getText().trim()),250,250,true,true));
                inputUserPassword.setVisible(true);        
                inputUserName.setVisible(false);
                login.requestFocus();
                differentAccount.setVisible(true);
            }
            else {
                PopUps.showInvalidAccountError();
            }
        });

        inputUserPassword.setOnAction(ae->{
            Customer customer;
            if(HANDLE_DATABASE.doesAccountExists(inputUserName.getText().trim(),"Customer")){
                if(HANDLE_DATABASE.isPasswordCorrect(inputUserName.getText().trim(), 
                        inputUserPassword.getText().trim(),"Customer")){

                    customer = HANDLE_DATABASE.readAllDataOfCustomer(
                            inputUserName.getText().trim(),"Customer");
                    if(rememberMe.isSelected()){
                        FileController.writeCustomerLoginData(customer.getUserName());
                    }
                    userLoginStage.setIconified(true);
                    UserDashboard userDashboard = new UserDashboard(customer,new Stage());
                    userLoginStage.close();
                }
                else {
                    PopUps.showIncorrectPasswordError();
                }
            }
        });        
        
        login.setOnAction(ae->{
            
            Customer customer;
            if(!inputUserPassword.isVisible()){
                if(HANDLE_DATABASE.doesAccountExists(inputUserName.getText().trim(),"Customer")){
                    profilePicture.setImage(new Image(
                            FileController.readCustomerProfilePicturePath(inputUserName.getText().trim()),250,250,true,true));
                    inputUserPassword.setVisible(true);   
                    inputUserName.setVisible(false);
                    login.requestFocus();
                    differentAccount.setVisible(true);
                }
                else {
                    PopUps.showInvalidAccountError();
                }
            
            }else{
                if(HANDLE_DATABASE.doesAccountExists(inputUserName.getText().trim(),"Customer")){
                    if(HANDLE_DATABASE.isPasswordCorrect(inputUserName.getText().trim(), 
                            inputUserPassword.getText().trim(),"Customer")){

                        customer = HANDLE_DATABASE.readAllDataOfCustomer(
                                inputUserName.getText().trim(),"Customer");
                        if(rememberMe.isSelected()){
                            FileController.writeCustomerLoginData(customer.getUserName());
                        }
                        userLoginStage.setIconified(true);
                        UserDashboard userDashboard = new UserDashboard(customer,new Stage());
                        userLoginStage.close();
                    }
                    else {
                        PopUps.showIncorrectPasswordError();
                    }
                }
            }
        });
        
        differentAccount.setOnAction(ae->{
            inputUserPassword.setVisible(false); 
            inputUserName.setVisible(true);
            differentAccount.setVisible(false);
            profilePicture.setImage(new Image(Images.USER_LOGIN,250,250,true,true));
            inputUserName.clear();
            inputUserPassword.clear();
            login.requestFocus();
        });
        
        signUp.setOnAction(ae ->{
            
            Scenes.showMakeNewAccountStage();
        });
        
        forgotPassword.setOnAction(ae->{
        
        });
        
        userLoginStage.showAndWait();
    
        
    }       // end of "showUserPortal"  
    
    private static Accountable newAccountCreation;
    // create and show "Make new Account Stage"
    public static void showMakeNewAccountStage(){
            
        Stage makeNewAccountStage = new Stage();
        makeNewAccountStage.setResizable(false);
        FlowPane child = new FlowPane();
        child.setAlignment(Pos.CENTER);
        child.setHgap(30);
        
        Button createCustomerAccount = new Button("New Customer Account", Images.NEW_CUSTOMER_ICON_MAIN);
        Button createEmployeeAccount = new Button("New Employee Account", Images.NEW_EMPLOYEE_ICON_MAIN);
        createCustomerAccount.setContentDisplay(ContentDisplay.TOP);
        createEmployeeAccount.setContentDisplay(ContentDisplay.TOP);
        
        createCustomerAccount.setTooltip(new Tooltip("Create new Customer Account"));
        createEmployeeAccount.setTooltip(new Tooltip("Create new Employee Account"));
        child.getChildren().addAll(createCustomerAccount, createEmployeeAccount);
        
        makeNewAccountStage.setTitle("Create New Account");
        
        createCustomerAccount.setOnAction(ae->{
        
            newAccountCreation = new NewCustomerAccountScenes(makeNewAccountStage);
            newAccountCreation.createNewAccountScene();
        });
        
        createEmployeeAccount.setOnAction(ae->{
        
            newAccountCreation = new NewEmployeeAccountScenes(makeNewAccountStage);
            newAccountCreation.createNewAccountScene();
        });
         
        makeNewAccountStage.setScene(new Scene(child, 400,400)); 
        makeNewAccountStage.showAndWait();

    
    }    // end of "showMakeNewAccountStage"
    
 
    public static void showCurrencyExchangeStage(double width, double height){
    
        Stage currencyExchangeStage = new Stage();
    
        BorderPane root = new BorderPane();
        GridPane currencyExchangeControls = new GridPane();
        currencyExchangeControls.setAlignment(Pos.CENTER);
        currencyExchangeControls.setVgap(20);
        currencyExchangeControls.setHgap(20);
        
        Label convertFrom = new Label("Exchange From : ");
        Label convertTo = new Label("Exchange To : ");
        
        Label enterAmountHeading = new Label("Enter Amount : ");
        TextField enterAmount = new TextField();
        Label result = new Label("Answer");
        ComboBox<String> exchangeFrom = new ComboBox<>(
                FXCollections.observableArrayList(FileController.readAvailableCurrencies()));
        exchangeFrom.setValue("Pakistani Rupee");
        
        ComboBox<String> exchangeTo = new ComboBox<>(
                FXCollections.observableArrayList(FileController.readAvailableCurrencies()));
        exchangeTo.setValue("Pakistani Rupee");
       
        Button convert = new Button("Convert");
        Button copy = new Button("Copy Result to Clipboard");
        
        currencyExchangeControls.addRow(0,convertFrom, exchangeFrom);
        currencyExchangeControls.addRow(1, convertTo, exchangeTo);
        currencyExchangeControls.addRow(2,enterAmountHeading, enterAmount);
        currencyExchangeControls.addRow(3, convert , copy);
        currencyExchangeControls.addRow(4, result);
        
        GridPane.setColumnSpan(result, 2);
        
        enterAmount.setOnMousePressed(ae->{
        
            enterAmount.setEffect(null);
        
        });
        
        enterAmount.setOnKeyReleased(ae->{

            enterAmount.setEffect(null);
            
            if(ValidateInput.validateMoney(enterAmount.getText().trim())){

                if(enterAmount.getText().trim().equals("")){
                    enterAmount.setText("0");
                }

                double answer = CurrencyExchange.currencyConverter(exchangeFrom.getValue(), exchangeTo.getValue(), Double.parseDouble(enterAmount.getText().trim()));

                result.setText(String.format("%s %s = %s %s",enterAmount.getText(),exchangeFrom.getValue(),CurrencyExchange.CURRENCY_FORMAT.format(answer), exchangeTo.getValue()));
         
            }else{
            
                enterAmount.setEffect(INNER_SHADOW_RED);
            }
            
        });
        
        copy.setOnAction(ae->{
        
            double answer = CurrencyExchange.currencyConverter(exchangeFrom.getValue(), exchangeTo.getValue(), Double.parseDouble(enterAmount.getText().trim()));
            Clipboard clipboard = Clipboard.getSystemClipboard();
            clipboard.clear();
            ClipboardContent content = new ClipboardContent();
            content.putString(Double.toString(answer));
            clipboard.setContent(content);
            
        });
        
        
        convert.setOnAction(ae->{

            enterAmount.setEffect(null);
            
            if(ValidateInput.validateMoney(enterAmount.getText().trim())){

                if(enterAmount.getText().trim().equals("")){
                    enterAmount.setText("0");
                }

                double answer = CurrencyExchange.currencyConverter(exchangeFrom.getValue(), exchangeTo.getValue(), Double.parseDouble(enterAmount.getText().trim()));

                result.setText(String.format("%s %s = %s %s",enterAmount.getText(),exchangeFrom.getValue(),CurrencyExchange.CURRENCY_FORMAT.format(answer), exchangeTo.getValue()));
         
            }else{
            
                enterAmount.setEffect(INNER_SHADOW_RED);
            }
         
        
        });
        
       
        root.setCenter(currencyExchangeControls);
        currencyExchangeStage.setScene(new Scene(root, width, height));
        currencyExchangeStage.showAndWait();
    }
    
    private static String billType;
    
    public static void showUtilityBillsStage(double width , double height){
    
        Stage payBillStage = new Stage();
        BorderPane root = new BorderPane();
        
        GridPane payBillControls = new GridPane();
        payBillControls.setAlignment(Pos.CENTER);
        payBillControls.setVgap(20);
        payBillControls.setHgap(20);
        
        Label heading = new Label("Select Bill Type : ");
        Label consumerRef = new Label("Enter Consumer Reference Number : ");
        Label billMonth = new Label("Choose Billing Month : ");
        Label billAmount = new Label("Bill Amount : ");
        Label accountNumber = new Label("Bank Account Number : ");
        
        TextField enterConsumerRef = new TextField();
        TextField enterAccountNumber = new TextField();
        TextField enterAmount = new TextField("0");
        RadioButton electricity = new RadioButton("Electricity");
        RadioButton gas = new RadioButton("Gas");
        RadioButton water = new RadioButton("Water");
        RadioButton telephone = new RadioButton("Telephone");
        RadioButton internet = new RadioButton("Internet");
        
        Button done = new Button("Done");
        
        ToggleGroup bills = new ToggleGroup();
        
        electricity.setToggleGroup(bills);
        gas.setToggleGroup(bills);
        water.setToggleGroup(bills);
        telephone.setToggleGroup(bills);
        internet.setToggleGroup(bills);
        
        electricity.setSelected(true);
        
        ComboBox<String> electricityBillType = new ComboBox<>(
                FXCollections.observableArrayList("KESC", "IESC", "LESCO","GEPCO", "PESCO","FESCO","QESCO","MEPCO","HESCO","SEPCO","Nizam Energy"));
        ComboBox<String> gasBillType = new ComboBox<>(
                FXCollections.observableArrayList("SSGC", "SNGPL"));
        ComboBox<String> waterBillType = new ComboBox<>(
                FXCollections.observableArrayList("KWSB", "LWASA","MWASA","RWASA", "BWASA","FWASA"));
        ComboBox<String> telephoneBillType = new ComboBox<>(
                FXCollections.observableArrayList("PTCL Landline","PTCL Vfone Prepaid","PTCL Vfone Postpaid" ));
        ComboBox<String> internetBillType = new ComboBox<>(
                FXCollections.observableArrayList("PTCL Evo Prepaid","PTCL Evo Postpaid","Wi-Tribe","Word Call","Qubee","Nayatel","Wateen"));
        
        ChoiceBox<String> enterBillMonth = new ChoiceBox<>(
            FXCollections.observableArrayList("January","Februay","March","April","May","June", "July","August","September","October","November","December")); 
        
        electricityBillType.setValue("MEPCO");
        gasBillType.setValue("SNGPL");
        waterBillType.setValue("BWASA");
        telephoneBillType.setValue("PTCL Landline");
        internetBillType.setValue("World Call");
        enterBillMonth.setValue("January");

        billType = electricityBillType.getValue();
        
        payBillControls.addRow(0, heading);
        payBillControls.addRow(1, electricity, electricityBillType);
        payBillControls.addRow(2, gas, gasBillType);
        payBillControls.addRow(3, water, waterBillType);
        payBillControls.addRow(4, telephone, telephoneBillType);
        payBillControls.addRow(5, internet, internetBillType);
        payBillControls.addRow(6, billMonth, enterBillMonth);
        payBillControls.addRow(7, consumerRef, enterConsumerRef);
        payBillControls.addRow(8, accountNumber, enterAccountNumber);
        payBillControls.addRow(9, billAmount, enterAmount);
        payBillControls.addRow(10, done);
        
        electricity.setOnAction(ae->{
            billType = electricityBillType.getValue();
        });
        
        gas.setOnAction(ae->{
            
            billType = gasBillType.getValue();
        });
        
        water.setOnAction(ae->{
            billType = waterBillType.getValue();
        });
        
        
        telephone.setOnAction(ae->{
            billType = telephoneBillType.getValue();
        });
        
        internet.setOnAction(ae->{
            billType = internetBillType.getValue();
        });
        
        electricityBillType.setOnAction(ae->{
            billType = electricityBillType.getValue();
        });
        
        gasBillType.setOnAction(ae->{
            billType = gasBillType.getValue();
        });
        
        waterBillType.setOnAction(ae->{
            billType = waterBillType.getValue();
        });
        
        
        telephoneBillType.setOnAction(ae->{
           billType = telephoneBillType.getValue();
        });
        
        internetBillType.setOnAction(ae->{
            billType = internetBillType.getValue();
        });
        
        enterAmount.setOnMousePressed(ae->{
        
            enterAmount.setEffect(null);
        
        });
        
        enterAccountNumber.setOnMousePressed(ae->{
            enterAccountNumber.setEffect(null);
            
        });
        
        enterConsumerRef.setOnMousePressed(ae->{
        
            enterConsumerRef.setEffect(null);
        
        });
        
        
        done.setOnAction(ae->{
       
            enterAmount.setEffect(null);
            enterAccountNumber.setEffect(null);
            enterConsumerRef.setEffect(null);
            
            if(ValidateInput.consumerReferenceNumber(enterConsumerRef.getText().trim())){
            
                if(ValidateInput.bankAccountNumber(enterAccountNumber.getText().trim())){
                
                    String userName = MainMenu.HANDLE_DATABASE.doesAccountNumberExists(enterAccountNumber.getText().trim(), "Customer");
                    if(userName != null){
                        
                        Customer customer = MainMenu.HANDLE_DATABASE.readAllDataOfCustomer(userName, "Customer");
                        
                        if(ValidateInput.validateMoney(enterAmount.getText().trim())){
                            
                            BigDecimal billMoney = BigDecimal.valueOf(Double.parseDouble(enterAmount.getText().trim()));
                            if(billMoney.compareTo(customer.getBalance())<=0){
                                Alert payBillConfirm = PopUps.showBillingConfirmation();
                                payBillConfirm.showAndWait().ifPresent(response ->{
                            
                                    if(response == ButtonType.OK){
                                        customer.setBalance(customer.getBalance().subtract(billMoney));
                                        MainMenu.HANDLE_DATABASE.updateCustomerBalance(customer.getUserName(),customer.getBalance().toString(),"Customer");
                                        FileController.writeBillData(billType,
                                                enterAmount.getText().trim(),
                                                enterBillMonth.getValue(),
                                                Long.toString(customer.getAccountNumber()),
                                                enterConsumerRef.getText().trim());

                                        PopUps.billPaidSuccessfully();
                                        payBillStage.close();
                                        
                                    }
                            
                                });
                            
                            }else{
                                enterAmount.setEffect(INNER_SHADOW_RED);
                                PopUps.showInsufficientBalanceMessage();
                            
                            }
                        
                        }else{
                            enterAmount.setEffect(INNER_SHADOW_RED);
                            PopUps.showInvalidMoneyMessage();
                        }
                    
                    }
                    else{
                        enterAccountNumber.setEffect(INNER_SHADOW_RED);
                        PopUps.showAccountNumberDoesNotExistError();
                    }
                    
                }
                else{
                    enterAccountNumber.setEffect(INNER_SHADOW_RED);
                    PopUps.showNotAccountNumberError();
                } 
            }
            else{
                enterConsumerRef.setEffect(INNER_SHADOW_RED);
                PopUps.showInvalidConsumerReferenceError();
            }
            
        });
        
        root.setCenter(payBillControls);
        payBillStage.setScene(new Scene(root, width, height));
        payBillStage.showAndWait();
    
    
    }
    
    public void showAboutStage(double width, double height){

        Stage aboutStage = new Stage (StageStyle.TRANSPARENT);
        aboutStage.setResizable(false);

        aboutStage.setTitle("About Me");
        
        BorderPane root = new BorderPane();
        Button close = new Button("Close", Images.CLOSE_ICON);
        Button minimize = new Button("Min", Images.MINIMIZE_ICON);
        close.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        minimize.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        minimize.setBackground(MINIMIZE_BUTTON_BACKGROUND);
        close.setBackground(CLOSE_BUTTON_BACKGROUND);
        
        HBox titleBar = new HBox(minimize,close);

        titleBar.setBackground(TITLE_BAR_BACKGROUND);
        titleBar.setAlignment(Pos.BASELINE_RIGHT);
        root.setTop(titleBar);
        
        minimize.setOnAction(ae->{
            
            aboutStage.setIconified(true);
        
        });
        
        close.setOnAction(ae->{
            
            aboutStage.close();
        
        });
        
        minimize.setOnMouseEntered(ae->{
            minimize.setStyle("-fx-border-color: WHITE");
        });
        close.setOnMouseEntered(ae->{
            close.setStyle("-fx-border-color: WHITE");
        });
        
        minimize.setOnMouseExited(ae->{
           minimize.setStyle("-fx-border-color: #262e41");
        });
        close.setOnMouseExited(ae->{
            close.setStyle("-fx-border-color: #f63c55");
        });
        
        root.setOnMousePressed((MouseEvent ae) -> {
            initX = ae.getScreenX() - aboutStage.getX();
            initY = ae.getScreenY() - aboutStage.getY();
            
        });
        
        root.setOnMouseDragged(ae ->{
            
            aboutStage.setX(ae.getScreenX() - initX);
            aboutStage.setY(ae.getScreenY() - initY);
             
        });
        
        root.setBorder(STAGE_BOUNDARY);
        
        Label credits = new Label("Programmer : Talha Asghar 2018 © Copyrights All rights reserved.");
        HBox creditsContainer = new HBox(credits);
        creditsContainer.setPrefHeight(30);
        creditsContainer.setAlignment(Pos.CENTER);
        HBox.setMargin(creditsContainer, new Insets(10,0,10,0));
        root.setBottom(creditsContainer);
        
        GridPane myInfo = new GridPane();
        
        
        aboutStage.setScene(new Scene(root,width,height));
        aboutStage.showAndWait();

    }
    
    
    
}       // end of class Scenes
