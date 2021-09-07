/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sockets;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.LinkedList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author Talha Asghar
 */
public abstract class Chat {
    
    static int sPort;
    static int cPort;
    static DatagramSocket server;
    static DatagramSocket client;
    static byte [] message;
    
    static{
        System.out.println("Chat static block is ...");
        try {
            server  = new DatagramSocket();
            client = new DatagramSocket();
            sPort = server.getLocalPort();
            cPort = client.getLocalPort();
        } catch (SocketException ex) {
            System.out.println(ex);
        }
    
    }
    
    private static final LinkedList<Boolean> C_RECIEVE = new LinkedList<>();
    private static final LinkedList<Boolean> S_RECIEVE = new LinkedList<>();
    
    public static void showLiveAgent(double width, double height){
    
            Stage chatWindow = new Stage();
            chatWindow.setTitle("Live Agent");
            BorderPane root = new BorderPane();
            
            TextField chattingText = new TextField();
            
            GridPane chattingNodes = new GridPane();
            
            TextArea sent = new TextArea();
            TextArea recieved = new TextArea();
            recieved.setEditable(false);
            sent.setEditable(false);
            
            chattingNodes.setHgap(0);
            
            chattingNodes.addRow(0,recieved, sent);
            chattingNodes.addRow(1, chattingText);
            GridPane.setColumnSpan(chattingText, 2);
            chattingNodes.setAlignment(Pos.CENTER);
            root.setCenter(chattingNodes);
            
            chatWindow.setScene(new Scene(root, width, height));
            
            chattingText.setOnAction(ae ->{
                
                message = chattingText.getText().getBytes();
                
                try {
                    server.send(new DatagramPacket(message,message.length,
                            InetAddress.getLocalHost(),cPort));
                    C_RECIEVE.add(true);
                } catch (IOException ex) {
                    
                }
                
                sent.appendText(
                        String.format("%s",chattingText.getText()).concat("\n"));
                chattingText.clear();
                
            });
            
            class agentReciever extends Thread{
                
                @Override
                public void run(){
                    System.out.println("agent Reciever is Running");
                    while(chatWindow.isShowing()){
                        if(!S_RECIEVE.isEmpty()){
                            try {
                                
                                message = new byte[1024];
                                DatagramPacket recMessage = new DatagramPacket(message,
                                        message.length);
                                server.receive(recMessage);
                                
                                String messageText = new String(recMessage.getData(),0,
                                        recMessage.getLength());
                                
                                recieved.appendText(
                                        String.format("%s",messageText).concat("\n"));
                                S_RECIEVE.poll();
                                
                            } catch (IOException ex) {
                                
                                System.out.println(ex);
                                
                            }
                        }
                    }
                    System.out.println("agent Reciever ended");
                }

            }
      
            agentReciever th = new agentReciever();
            th.start();
            
        chatWindow.showAndWait();
        
    }    
    
    public static void showLiveChat(double width, double height){

            Stage chatWindow = new Stage();
            chatWindow.setTitle("User");
            BorderPane root = new BorderPane();

            TextField chattingText = new TextField();

            GridPane chattingNodes = new GridPane();
            chattingNodes.setHgap(0);
            TextArea sent = new TextArea();
            TextArea recieved = new TextArea();
            recieved.setEditable(false);
            sent.setEditable(false);

            chattingNodes.addRow(0,recieved, sent);
            chattingNodes.addRow(1, chattingText);
            GridPane.setColumnSpan(chattingText, 2);
            chattingNodes.setAlignment(Pos.CENTER);
            root.setCenter(chattingNodes);
                
            chatWindow.setScene(new Scene(root, width, height));
            
            
            chattingText.setOnAction(ae ->{

                message = chattingText.getText().getBytes();

                try {
                    client.send(new DatagramPacket(message, message.length,
                            InetAddress.getLocalHost(),sPort));
                    S_RECIEVE.add(true);
                } catch (IOException ex) {

                }
                
                sent.appendText(
                        String.format("%s",chattingText.getText()).concat("\n"));
                chattingText.clear(); 
            });

            class clientReciever extends Thread{

                @Override
                public void run(){

                    while(chatWindow.isShowing()){

                        if(!C_RECIEVE.isEmpty()){
                            try {
                                
                                message = new byte[1024];
                                DatagramPacket recMessage = new DatagramPacket(message,
                                        message.length);
                                client.receive(recMessage);

                                String messageText = new String(recMessage.getData(),0,
                                        recMessage.getLength());
                                
                                recieved.appendText(
                                        String.format("%s",messageText).concat("\n"));
                                C_RECIEVE.poll();

                            } catch (IOException ex) {

                                System.out.println(ex);
                                
                            }
                            
                        }

                    }

                }

            }

            clientReciever th = new clientReciever();
            th.start();
            chatWindow.showAndWait();
           
        }    
    
}    
    
    
