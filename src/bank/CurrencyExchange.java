/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank;

import data.FileController;
import java.text.DecimalFormat;

/**
 *
 * @author Talha Asghar
 */
public abstract class CurrencyExchange {
    
    public static final DecimalFormat CURRENCY_FORMAT = new DecimalFormat(".####");
    
    public static double currencyConverter(String from, String to,final double amount){
       
        double ratio;
        
        if(to.equals("Pakistani Rupee") || from.equals("Pakistani Rupee")){
            
            if(to.equals("Pakistani Rupee")){

                ratio = FileController.readCurrencyRate(from);
                return (ratio*amount);

            }
            else if(from.equals("Pakistani Rupee")){

                ratio = FileController.readCurrencyRate(to);
                return (amount/ratio);

            }
        }
        else{

            ratio = FileController.readCurrencyRate(from);
            double temp = ratio*amount;
            ratio = FileController.readCurrencyRate(to);
            return temp / ratio;
            
        
        }
        return -9999;
    }
    
    
    
}
