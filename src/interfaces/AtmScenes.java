/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import bank.Customer;
import bank.MainMenu;
import data.ValidateInput;
import static interfaces.Graphics.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

/**
 *
 * @author Talha Asghar
 */
public class AtmScenes {

    private MediaPlayer atmPlayerHbl;
    private MediaPlayer atmPlayerUbl;
    private MediaPlayer atmPlayerKonnect;
    private Media hbl = new Media(Paths.get("videos/hbl.mp4").toUri().toString()); 
    private Media konnect = new Media(Paths.get("videos/konnect.mp4").toUri().toString());
    private Media ubl = new Media(Paths.get("videos/ubl.mp4").toUri().toString());
    
    private double amountToWithdraw;
    private Customer customer;
    private Stage stage;
    private double width;
    private double height;
 /*
    static {
        
        hbl = new Media(Paths.get("videos/hbl.mp4").toUri().toString()); 
        konnect = new Media(Paths.get("videos/konnect.mp4").toUri().toString());
        ubl = new Media(Paths.get("videos/ubl.mp4").toUri().toString()); 
        
    }
*/
    public AtmScenes(double width, double height,Stage stage){
    
        this.stage = stage;
        this.width= width;
        this.height  = height;
        
        hbl = new Media(Paths.get("videos/hbl.mp4").toUri().toString()); 
        konnect = new Media(Paths.get("videos/konnect.mp4").toUri().toString());
        ubl = new Media(Paths.get("videos/ubl.mp4").toUri().toString());
        atmPlayerHbl = new MediaPlayer(hbl);
        atmPlayerUbl = new MediaPlayer(ubl);
        atmPlayerKonnect = new MediaPlayer(konnect);
        customer = new Customer();
    
    }
 
    // create and show Atm Machine
    public void atmSimulation(){

        stage.setTitle("ATM Machine");

        stage.setScene(createVideoScene());
        
        //stage.setScene(createAtmLoginScene());
        
        stage.showAndWait();
    
    }    // end of "atmSimulation"

    
    private Scene createAtmLoginScene(){
        
        GridPane root = new GridPane();
        Scene loginScene = new Scene(root,width, height);
        
        Label accountNumberHeading = new Label("Enter Account Number : ");
        Label passwordHeading = new Label("Enter Password : ");
        
        TextField enterAccountNumber = new TextField();
        PasswordField enterPassword = new PasswordField();

        root.setAlignment(Pos.CENTER);
        
        root.setVgap(20);
        root.setHgap(20);
        
        GridPane screen = new GridPane();
        screen.setPrefSize(600, 450);
        screen.setBackground(GREEN_BACKGROUND);
        screen.setAlignment(Pos.CENTER);

        enterPassword.setVisible(false);
        passwordHeading.setVisible(false);
        GridPane.setColumnSpan(screen,4);
        
        Button zero = new Button("0");
        Button one = new Button("1");
        Button two = new Button("2");
        Button three = new Button("3");
        Button four = new Button("4");
        Button five = new Button("5");
        Button six = new Button("6");
        Button seven = new Button("7");
        Button eight = new Button("8");
        Button nine = new Button("9");
        Button enter = new Button("Enter");
        Button back = new Button("Back");
        Button next = new Button("Next");
        Button delete = new Button("Delete");
        Button clear = new Button("Clear");
        Button logout = new Button("Logout");
        
        logout.setDisable(true);
        
        zero.setPrefSize(60,60);
        one.setPrefSize(60,60);
        two.setPrefSize(60,60);
        three.setPrefSize(60,60);
        four.setPrefSize(60,60);
        five.setPrefSize(60,60);
        six.setPrefSize(60,60);
        seven.setPrefSize(60,60);
        eight.setPrefSize(60,60);
        nine.setPrefSize(60,60);
        enter.setPrefSize(60,60);
        next.setPrefSize(60,60);
        back.setPrefSize(60,60);
        delete.setPrefSize(60,60);
        clear.setPrefSize(60,60);
        logout.setPrefSize(60,60);
        
        zero.setOnAction(ae->{
        
            if(enterAccountNumber.isVisible()){
            
                enterAccountNumber.appendText("0");
            }
            else if(enterPassword.isVisible()){
            
                enterPassword.appendText("0");
            } 
            
        });
        one.setOnAction(ae->{
        
            if(enterAccountNumber.isVisible()){
            
                enterAccountNumber.appendText("1");
            }
            else if(enterPassword.isVisible()){
            
                enterPassword.appendText("1");
            } 
            
        });
        
        two.setOnAction(ae->{
        
            if(enterAccountNumber.isVisible()){
            
                enterAccountNumber.appendText("2");
            }
            else if(enterPassword.isVisible()){
            
                enterPassword.appendText("2");
            } 
            
        });
        
        three.setOnAction(ae->{
        
            if(enterAccountNumber.isVisible()){
            
                enterAccountNumber.appendText("3");
            }
            else if(enterPassword.isVisible()){
            
                enterPassword.appendText("3");
            } 
            
        });
        
       four.setOnAction(ae->{
        
            if(enterAccountNumber.isVisible()){
            
                enterAccountNumber.appendText("4");
            }
            else if(enterPassword.isVisible()){
            
                enterPassword.appendText("4");
            } 
            
        });
       
        five.setOnAction(ae->{
        
            if(enterAccountNumber.isVisible()){
            
                enterAccountNumber.appendText("5");
            }
            else if(enterPassword.isVisible()){
            
                enterPassword.appendText("5");
            } 
            
        });
        
        six.setOnAction(ae->{
        
            if(enterAccountNumber.isVisible()){
            
                enterAccountNumber.appendText("6");
            }
            else if(enterPassword.isVisible()){
            
                enterPassword.appendText("6");
            } 
            
        });
        
        seven.setOnAction(ae->{
        
            if(enterAccountNumber.isVisible()){
            
                enterAccountNumber.appendText("7");
            }
            else if(enterPassword.isVisible()){
            
                enterPassword.appendText("7");
            } 
            
        });
        
       eight.setOnAction(ae->{
        
            if(enterAccountNumber.isVisible()){
            
                enterAccountNumber.appendText("8");
            }
            else if(enterPassword.isVisible()){
            
                enterPassword.appendText("8");
            } 
            
        });
       
       
        nine.setOnAction(ae->{
        
            if(enterAccountNumber.isVisible()){
            
                enterAccountNumber.appendText("9");
            }
            else if(enterPassword.isVisible()){
            
                enterPassword.appendText("9");
            } 
            
        });
        
         clear.setOnAction(ae->{
        
            if(enterAccountNumber.isVisible()){
            
                enterAccountNumber.clear();
            }
            else if(enterPassword.isVisible()){
            
                enterPassword.clear();
            } 
            
        });
         
        
        screen.setAlignment(Pos.CENTER);
        screen.setVgap(10);
        screen.setHgap(10);
       
        screen.addRow(0,accountNumberHeading, enterAccountNumber);
        screen.addRow(1,passwordHeading, enterPassword);
        screen.addRow(2,enter);
        
        root.addRow(0,screen);
        root.addRow(1,seven,eight,nine,enter,clear);
        root.addRow(2,four,five,six,back,logout);
        root.addRow(3,one,two,three,next);
        GridPane.setColumnSpan(screen,5);
       
        
        enter.setOnAction(ae->{
        
            if(enterAccountNumber.isVisible()){
                String userName = MainMenu.HANDLE_DATABASE.doesAccountNumberExists(
                    enterAccountNumber.getText().trim(), "Customer");
            
                if(userName!=null){

                    customer = MainMenu.HANDLE_DATABASE.readAllDataOfCustomer(userName, "Customer");
                    enterPassword.setVisible(true);
                    passwordHeading.setVisible(true);
                    enterAccountNumber.setVisible(false);
                    accountNumberHeading.setVisible(false);
                }
                else {
                    PopUps.showAccountNumberDoesNotExistError();
                }
            }else if(enterPassword.isVisible()){
                if(customer.getPassword().equals(enterPassword.getText().trim()))
                    {
                           System.out.println("iam here");
                        stage.setScene(createAtmDashboard());

                    }else {
                        PopUps.showIncorrectPasswordError();
                    }
            
            }
            
        });
        
        
        return loginScene;
        
    }
    
    
    private Scene createVideoScene(){
        
        GridPane root = new GridPane();
        Scene videoScreen = new Scene(root, width, height);
        /*
        atmPlayerHbl = new MediaPlayer(hbl);
        atmPlayerUbl = new MediaPlayer(ubl);
        atmPlayerKonnect = new MediaPlayer(konnect);
        */
        MediaView atmVideos = new MediaView(atmPlayerHbl);

        atmVideos.setPreserveRatio(true);
        atmVideos.setFitWidth(600);
        atmVideos.setFitHeight(450);    

        root.setAlignment(Pos.CENTER);
        
        root.setVgap(20);
        root.setHgap(20);
        
        GridPane screen = new GridPane();
        screen.setPrefSize(600, 450);
        screen.setBackground(BLACK_BACKGROUND);
        
        
        Button zero = new Button("0");
        Button one = new Button("1");
        Button two = new Button("2");
        Button three = new Button("3");
        Button four = new Button("4");
        Button five = new Button("5");
        Button six = new Button("6");
        Button seven = new Button("7");
        Button eight = new Button("8");
        Button nine = new Button("9");
        Button enter = new Button("Enter");
        
        
        zero.setPrefSize(60,60);
        one.setPrefSize(60,60);
        two.setPrefSize(60,60);
        three.setPrefSize(60,60);
        four.setPrefSize(60,60);
        five.setPrefSize(60,60);
        six.setPrefSize(60,60);
        seven.setPrefSize(60,60);
        eight.setPrefSize(60,60);
        nine.setPrefSize(60,60);
        enter.setPrefSize(60,60);
       
        root.addRow(0,screen);
        root.addRow(1,seven,eight,nine,enter);
        root.addRow(2,four,five,six);
        root.addRow(3,one,two,three);
        GridPane.setColumnSpan(screen,4);
        
        screen.setAlignment(Pos.CENTER);
        screen.setVgap(10);
        screen.setHgap(10);
       
        screen.setBackground(BLACK_BACKGROUND);
        screen.setAlignment(Pos.CENTER);
      
        screen.addRow(0,atmVideos);

        atmVideos.setOnMousePressed(ae->{
        
            if(atmVideos.getMediaPlayer().getStatus()==MediaPlayer.Status.PLAYING){
                
               stage.setScene(createAtmLoginScene());
               atmVideos.getMediaPlayer().stop();
            }
        });
        
        atmPlayerHbl.play();
       
        atmPlayerHbl.setOnEndOfMedia(() -> {
            
            atmVideos.setMediaPlayer(atmPlayerUbl);
            atmPlayerUbl.play();
            
        });
        atmPlayerUbl.setOnEndOfMedia(() -> {
            System.out.println("End of Ubl");
            atmVideos.setMediaPlayer(atmPlayerHbl);
            atmPlayerHbl.play();
        });
 /*       atmPlayerKonnect.setOnEndOfMedia(() -> {
 
            atmVideos.setMediaPlayer(atmPlayerHbl);
            atmPlayerHbl.play();
        });
*/      
        return videoScreen;
        
    }
    
    private Scene createAtmDashboard()
    {
        GridPane root = new GridPane();
        GridPane atmDashboard = new GridPane();
        Scene atmDashboardScene = new Scene(root, width, height);
        
        Label heading = new Label(String.format("Welcome %s!", customer.getFullName()));
        Button withdrawMoney = new Button("Withdraw Money");
        Button currentBalance = new Button("Remaining Balance");
        
        withdrawMoney.setPrefSize(100,100);
        currentBalance.setPrefSize(100,100);
        atmDashboard.setHgap(20);
        atmDashboard.setVgap(20);
        atmDashboard.setAlignment(Pos.CENTER);
        atmDashboard.addRow(0, withdrawMoney, currentBalance);
        
        root.setAlignment(Pos.CENTER);
        
        root.setVgap(20);
        root.setHgap(20);
        
        GridPane screen = new GridPane();
        screen.setPrefSize(600, 450);
        screen.setBackground(GREEN_BACKGROUND);
        
        
        Button zero = new Button("0");
        Button one = new Button("1");
        Button two = new Button("2");
        Button three = new Button("3");
        Button four = new Button("4");
        Button five = new Button("5");
        Button six = new Button("6");
        Button seven = new Button("7");
        Button eight = new Button("8");
        Button nine = new Button("9");
        Button enter = new Button("Enter");
       Button next = new Button("Next");
        Button back = new Button("Back");
        Button clear = new Button("clear");
        Button logout = new Button("logout");

        
        
        zero.setPrefSize(60,60);
        one.setPrefSize(60,60);
        two.setPrefSize(60,60);
        three.setPrefSize(60,60);
        four.setPrefSize(60,60);
        five.setPrefSize(60,60);
        six.setPrefSize(60,60);
        seven.setPrefSize(60,60);
        eight.setPrefSize(60,60);
        nine.setPrefSize(60,60);
        enter.setPrefSize(60,60);
        next.setPrefSize(60,60);
        back.setPrefSize(60,60);
        clear.setPrefSize(60,60);
        logout.setPrefSize(60,60);
       
        root.addRow(0,screen);
        root.addRow(1,seven,eight,nine,enter,clear);
        root.addRow(2,four,five,six,next,logout);
        root.addRow(3,one,two,three,back);
        GridPane.setColumnSpan(screen,5);
        
        screen.setAlignment(Pos.CENTER);
        screen.setVgap(10);
        screen.setHgap(10);
       
        screen.setAlignment(Pos.CENTER);
      
        screen.addRow(0,atmDashboard);        
        
        currentBalance.setOnAction(ae->{
        
            stage.setScene(createCurrentBalanceScene());
        
        });  
        
        withdrawMoney.setOnAction(ae->{
        
            stage.setScene(createWithDrawBalanceScene());
        });
                
        logout.setOnAction(ae->{
    
            stage.setScene(createVideoScene());
        });
        
       
        return atmDashboardScene;
    }

    private Scene createCurrentBalanceScene()
    {
        GridPane root = new GridPane();
        GridPane currentBalanceGrid = new GridPane();
        Scene currentBalanceScene = new Scene(root, width, height);
        
        TextField currentBalance = new TextField(customer.getBalance().toString());
        currentBalance.setEditable(false);
        Label currentBalanceHeading = new Label("Remaining Balance : ");
        
        
        currentBalanceGrid.setHgap(20);
        currentBalanceGrid.setVgap(20);
        currentBalanceGrid.setAlignment(Pos.CENTER);
        currentBalanceGrid.addRow(0, currentBalanceHeading, currentBalance);
        
        root.setAlignment(Pos.CENTER);
        
        root.setVgap(20);
        root.setHgap(20);
        
        GridPane screen = new GridPane();
        screen.setPrefSize(600, 450);
        screen.setBackground(GREEN_BACKGROUND);
        
        
        Button zero = new Button("0");
        Button one = new Button("1");
        Button two = new Button("2");
        Button three = new Button("3");
        Button four = new Button("4");
        Button five = new Button("5");
        Button six = new Button("6");
        Button seven = new Button("7");
        Button eight = new Button("8");
        Button nine = new Button("9");
        Button enter = new Button("Enter");
        Button next = new Button("Next");
        Button back = new Button("Back");
        Button clear = new Button("clear");
        Button logout = new Button("logout");
        
        zero.setPrefSize(60,60);
        one.setPrefSize(60,60);
        two.setPrefSize(60,60);
        three.setPrefSize(60,60);
        four.setPrefSize(60,60);
        five.setPrefSize(60,60);
        six.setPrefSize(60,60);
        seven.setPrefSize(60,60);
        eight.setPrefSize(60,60);
        nine.setPrefSize(60,60);
        enter.setPrefSize(60,60);
        next.setPrefSize(60,60);
        back.setPrefSize(60,60);
        clear.setPrefSize(60,60);
        logout.setPrefSize(60,60);
       
        root.addRow(0,screen);
        root.addRow(1,seven,eight,nine,enter,clear);
        root.addRow(2,four,five,six,next,logout);
        root.addRow(3,one,two,three,back);
        GridPane.setColumnSpan(screen,5);
        
        screen.setAlignment(Pos.CENTER);
        screen.setVgap(10);
        screen.setHgap(10);
       
        screen.setAlignment(Pos.CENTER);
      
        screen.addRow(0,currentBalanceGrid);        
      
        back.setOnAction(ae->{
        
            stage.setScene(createAtmDashboard());
        
        
        });
                
        logout.setOnAction(ae->{
    
            stage.setScene(createVideoScene());
        });
        
        
        return currentBalanceScene;
    }

    private Scene createWithDrawBalanceScene()
    {
        GridPane root = new GridPane();
        GridPane withDrawBalanceControls = new GridPane();
        Scene withdrawBalanceScene = new Scene(root, width, height);
        
        TextField withdrawAmount = new TextField("0");
        withdrawAmount.setVisible(false);
        Label withdrawHeading = new Label("Select amount to withdraw : ");
        
        RadioButton fiveHundred = new RadioButton("500");
        RadioButton fifteenHundred = new RadioButton("1500");
        RadioButton fiveThousand = new RadioButton("5000");
        RadioButton tenThousand = new RadioButton("10000");        
        RadioButton custom = new RadioButton("Other Amount");
        
        ToggleGroup withdrawMoney = new ToggleGroup();
        fiveHundred.setToggleGroup(withdrawMoney);
        fifteenHundred.setToggleGroup(withdrawMoney);
        fiveThousand.setToggleGroup(withdrawMoney);
        tenThousand.setToggleGroup(withdrawMoney);
        custom.setToggleGroup(withdrawMoney);
        
        fiveHundred.setSelected(true);
        amountToWithdraw = Double.parseDouble(fiveHundred.getText());
        
        withDrawBalanceControls.setHgap(20);
        withDrawBalanceControls.setVgap(20);
        withDrawBalanceControls.setAlignment(Pos.CENTER);
        withDrawBalanceControls.addRow(0, withdrawHeading);
        withDrawBalanceControls.addRow(1, fiveHundred, fifteenHundred);
        withDrawBalanceControls.addRow(2, fiveThousand, tenThousand);
        withDrawBalanceControls.addRow(3, custom);
        withDrawBalanceControls.addRow(4, withdrawAmount);
     
        
        
        
        fiveHundred.setOnAction(ae->{
            amountToWithdraw = Double.parseDouble(fiveHundred.getText());
            withdrawAmount.setVisible(false);
        });
        fifteenHundred.setOnAction(ae->{
            amountToWithdraw = Double.parseDouble(fifteenHundred.getText());
            withdrawAmount.setVisible(false);
        });
        fiveThousand.setOnAction(ae->{
            amountToWithdraw = Double.parseDouble(fiveThousand.getText());
            withdrawAmount.setVisible(false);
        });
        tenThousand.setOnAction(ae->{
            amountToWithdraw = Double.parseDouble(tenThousand.getText());
            withdrawAmount.setVisible(false);
        });
        
        custom.setOnAction(ae->{
            if(custom.isSelected()) {
                withdrawAmount.setVisible(true);
            }
            else{
            
                withdrawAmount.setVisible(false);
            }
       
        });
        

        
        root.setAlignment(Pos.CENTER);
        
        root.setVgap(20);
        root.setHgap(20);
        
        GridPane screen = new GridPane();
        screen.setPrefSize(600, 450);
        screen.setBackground(GREEN_BACKGROUND);
        
        
        Button zero = new Button("0");
        Button one = new Button("1");
        Button two = new Button("2");
        Button three = new Button("3");
        Button four = new Button("4");
        Button five = new Button("5");
        Button six = new Button("6");
        Button seven = new Button("7");
        Button eight = new Button("8");
        Button nine = new Button("9");
        Button enter = new Button("Enter");
        Button next = new Button("Next");
        Button back = new Button("Back");
        Button clear = new Button("clear");
        Button logout = new Button("logout");
        
        next.setPrefSize(60,60);
        back.setPrefSize(60,60);
        clear.setPrefSize(60,60);
        logout.setPrefSize(60,60);
        
        
        zero.setPrefSize(60,60);
        one.setPrefSize(60,60);
        two.setPrefSize(60,60);
        three.setPrefSize(60,60);
        four.setPrefSize(60,60);
        five.setPrefSize(60,60);
        six.setPrefSize(60,60);
        seven.setPrefSize(60,60);
        eight.setPrefSize(60,60);
        nine.setPrefSize(60,60);
        enter.setPrefSize(60,60);
       
        root.addRow(0,screen);
        root.addRow(1,seven,eight,nine,enter,clear);
        root.addRow(2,four,five,six, next,logout);
        root.addRow(3,one,two,three,back);
        GridPane.setColumnSpan(screen,5);
        
        screen.setAlignment(Pos.CENTER);
        screen.setVgap(10);
        screen.setHgap(10);
       
        
        enter.setOnAction(ae->{
            if(custom.isSelected()){
                if(ValidateInput.validateMoney(withdrawAmount.getText().trim())){
                    amountToWithdraw = Double.parseDouble(withdrawAmount.getText().trim());
                    if(amountToWithdraw<customer.getBalance().doubleValue()){
                        Alert confirm =  PopUps.showWithdrawMoneyConfirmation();
                        confirm.showAndWait().ifPresent(response->{

                            if(response == ButtonType.OK){
                               
                                customer.setBalance(customer.getBalance()
                                        .subtract(BigDecimal.valueOf(amountToWithdraw)));
                                MainMenu.HANDLE_DATABASE.updateCustomerBalance(customer.getUserName(), customer.getBalance().toString(), "Customer");
                                PopUps.showTransactionCompleteMessage(customer.getBalance());
                            }


                        });
                    }else{
                    
                        PopUps.showInsufficientBalanceMessage();
                    }
                }
                else{
                
                    PopUps.showInvalidMoneyMessage();
                }
            
            }else{
            
                RadioButton temp = (RadioButton) withdrawMoney.getSelectedToggle();
                amountToWithdraw = Double.parseDouble(temp.getText().trim());
                
                if(amountToWithdraw<customer.getBalance().doubleValue()){
                            Alert confirm =  PopUps.showWithdrawMoneyConfirmation();
                            confirm.showAndWait().ifPresent(response->{

                                if(response == ButtonType.OK){

                                    customer.setBalance(customer.getBalance()
                                            .subtract(BigDecimal.valueOf(amountToWithdraw)));
                                    MainMenu.HANDLE_DATABASE.updateCustomerBalance(customer.getUserName(), customer.getBalance().toString(), "Customer");
                                    PopUps.showTransactionCompleteMessage(customer.getBalance());
                                }


                            });
                        }else{

                            PopUps.showInsufficientBalanceMessage();
                        }

                }
           
        });
        
        back.setOnAction(ae->{
        
            stage.setScene(createAtmLoginScene());
        
        });
        
        next.setOnAction(ae->{
        
            stage.setScene(createCurrentBalanceScene());
        
        });
        
        
        logout.setOnAction(ae->{
    
            stage.setScene(createVideoScene());
        });
        
        screen.setAlignment(Pos.CENTER);
      
        screen.addRow(0,withDrawBalanceControls);        
       
        return withdrawBalanceScene;
    }      

}
