/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.nio.file.Paths;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Talha Asghar
 */
public interface Images {
    
    // Images
    
    String HBL_BACKGROUND = Paths.get("images/hbl.png").toUri().toString();
    String ADMIN_PROFILE = Paths.get("images/admin.png").toUri().toString();
    String ADMIN_LOGIN = Paths.get("images/admin_login.png").toUri().toString();
    String USER_PROFILE = Paths.get("images/user.png").toUri().toString();
    String USER_LOGIN = Paths.get("images/user_login.png").toUri().toString();
    String ABOUT = Paths.get("images/about.png").toUri().toString();
    String CLOSE = Paths.get("icons/close.png").toUri().toString();
    String MINIMIZE = Paths.get("icons/minimze.png").toUri().toString();
    
    
    // Icons
    
    ImageView CLOSE_ICON = new ImageView(new Image(Paths.get("icons/close.png").toUri().toString(),30,30,true,true));
    ImageView MINIMIZE_ICON = new ImageView(new Image(Paths.get("icons/minimize.png").toUri().toString(),30,30,true,true));
    
    ImageView NEW_ACCOUNT_ICON_MAIN = new ImageView(new Image(Paths.get("icons/new_account_main.png").toUri().toString(),150,150,true,true));
    ImageView NEW_ACCOUNT_ICON_ADMIN = new ImageView(new Image(Paths.get("icons/new_account_admin.png").toUri().toString(),150,150,true,true));
    ImageView ADMIN_LOGIN_ICON = new ImageView(new Image(Paths.get("icons/admin_login.png").toUri().toString(),150,150,true,true));
    ImageView USER_LOGIN_ICON = new ImageView(new Image(Paths.get("icons/user_login.png").toUri().toString(),150,150,true,true));
    ImageView ATM_ICON = new ImageView(new Image(Paths.get("icons/atm.png").toUri().toString(),150,150,true,true));
    ImageView CURRENCY_EXCHANGE_ICON = new ImageView(new Image(Paths.get("icons/currency_exchange.png").toUri().toString(),150,150,true,true));
    ImageView PAY_BILLS_ICON = new ImageView(new Image(Paths.get("icons/paybills.png").toUri().toString(),150,150,true,true));
    ImageView LIVE_CHAT_ICON = new ImageView(new Image(Paths.get("icons/live_chat.png").toUri().toString(),150,150,true,true));
    ImageView LIVE_AGENT_ICON = new ImageView(new Image(Paths.get("icons/live_agent.png").toUri().toString(),150,150,true,true));
    ImageView ABOUT_ICON = new ImageView(new Image(Paths.get("icons/about.png").toUri().toString(),150,150,true,true));
    ImageView LOGIN_ICON = new ImageView(new Image(Paths.get("icons/login.png").toUri().toString(),80,80,true,true));
    
    ImageView NEW_CUSTOMER_ICON_MAIN = new ImageView(new Image(Paths.get("icons/customer_main.png").toUri().toString(),150,150,true,true));
    ImageView NEW_EMPLOYEE_ICON_MAIN = new ImageView(new Image(Paths.get("icons/employee_main.png").toUri().toString(),150,150,true,true));
    ImageView NEW_CUSTOMER_ICON_ADMIN = new ImageView(new Image(Paths.get("icons/customer_admin.png").toUri().toString(),150,150,true,true));
    ImageView NEW_EMPLOYEE_ICON_ADMIN = new ImageView(new Image(Paths.get("icons/employee_admin.png").toUri().toString(),150,150,true,true));
    
    ImageView SEARCH_ACCOUNT_ICON = new ImageView(new Image(Paths.get("icons/search_account.png").toUri().toString(),150,150,true,true));
    ImageView ALL_ACCOUNTS_ICON = new ImageView(new Image(Paths.get("icons/all_accounts.png").toUri().toString(),150,150,true,true));
    ImageView EDIT_ACCOUNT_ICON = new ImageView(new Image(Paths.get("icons/edit_customer.png").toUri().toString(),150,150,true,true));
    ImageView DELETE_ACCOUNT_ICON = new ImageView(new Image(Paths.get("icons/delete_account.png").toUri().toString(),150,150,true,true));
    ImageView CONSOLIDATE_ACCOUNTS_ICON = new ImageView(new Image(Paths.get("icons/consolidate_accounts.png").toUri().toString(),150,150,true,true));
    
    ImageView SALARY_ICON = new ImageView(new Image(Paths.get("icons/salary.png").toUri().toString(),50,50,true,true));
    ImageView ADMIN_CHAT_ICON = new ImageView(new Image(Paths.get("icons/admin_dashboard_chat.png").toUri().toString(),50,50,true,true));
    ImageView CALCULATOR_ICON = new ImageView(new Image(Paths.get("icons/calculator.png").toUri().toString(),50,50,true,true));
    ImageView ADMIN_CURRENCY_EXCHANGE_ICON = new ImageView(new Image(Paths.get("icons/admin_exchange.png").toUri().toString(),50,50,true,true));        
    ImageView ADMIN_LOGOUT_ICON = new ImageView(new Image(Paths.get("icons/admin_logout.png").toUri().toString(),50,50,true,true));     
    
    ImageView USER_DASHBOARD_CHAT_ICON = new ImageView(new Image(Paths.get("icons/user_dashboard_chat.png").toUri().toString(),50,50,true,true));
    ImageView USER_EXCHANGE_ICON = new ImageView(new Image(Paths.get("icons/user_exchange.png").toUri().toString(),50,50,true,true));  
    ImageView USER_LOGOUT_ICON = new ImageView(new Image(Paths.get("icons/user_logout.png").toUri().toString(),50,50,true,true));
    ImageView USER_DASHBOARD_CURRENT_BALANCE_ICON = new ImageView(new Image(Paths.get("icons/current_balance_user_dashboard.png").toUri().toString(),50,50,true,true));
    ImageView USER_CALCULATOR_ICON = new ImageView(new Image(Paths.get("icons/user_calculator.png").toUri().toString(),50,50,true,true));
    
    ImageView USER_DEPOSIT_MONEY_ICON = new ImageView(new Image(Paths.get("icons/user_deposit_money.png").toUri().toString(),150,150,true,true));
    ImageView USER_WITHDRAW_MONEY_ICON = new ImageView(new Image(Paths.get("icons/user_withdraw_money.png").toUri().toString(),150,150,true,true));
    ImageView USER_TRANSFER_MONEY_ICON = new ImageView(new Image(Paths.get("icons/user_transfer_money.png").toUri().toString(),150,150,true,true));
    ImageView USER_PAY_BILL_ICON = new ImageView(new Image(Paths.get("icons/user_pay_bill.png").toUri().toString(),150,150,true,true));
    ImageView USER_VIEW_PROFILE_ICON = new ImageView(new Image(Paths.get("icons/user_view_profile.png").toUri().toString(),150,150,true,true));
    ImageView USER_EDIT_PROFILE_ICON = new ImageView(new Image(Paths.get("icons/user_edit_profile.png").toUri().toString(),150,150,true,true));
    ImageView USER_LOAN_ICON = new ImageView(new Image(Paths.get("icons/user_loan.png").toUri().toString(),150,150,true,true));
}


