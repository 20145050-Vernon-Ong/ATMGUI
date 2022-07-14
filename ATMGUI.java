/**
 * I declare that this code was written by me.
 * I will not copy or allow others to copy my code.
 * I understand that copying code is considered as plagiarism.
 *
 * Vernon Ong, 14 Jul 2022 12:28:09 am
 */

package atm;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class ATMGUI extends Application {

	TextField userName = new TextField();
	PasswordField pinNum = new PasswordField();
	TextField balance = new TextField();
	TextField withDraw = new TextField();
	TextField deposit = new TextField();

	Label user = new Label();
	Label pin = new Label();
	Label bal = new Label();
	Label with = new Label();
	Label dep = new Label();
	Label labelOutput = new Label();
	
	FlowPane pane = new FlowPane();
	FlowPane pane1 = new FlowPane();
	
	Button close = new Button();
	Button logIn = new Button();
	Button viewBal = new Button();
	Button witD = new Button();
	Button depD = new Button();

	ArrayList<user> userList = new ArrayList<user>();
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}
	
	public void start(Stage primaryStage) {
		
		userList.add(new user("John Tan", 107224, 109332.20));
		userList.add(new user("Mary Lim", 954521, 133679));
		userList.add(new user("David Teo", 765363, 152358.50));
		userList.add(new user("Daniel Lee", 517864, 147876));
		userList.add(new user("Scott Lai", 247864, 174690.70));
		
		userName.setPrefColumnCount(30);
		pinNum.setPrefColumnCount(30);
		
		user.setText("Enter your Username");
		pin.setText("Enter your PIN");
		close.setText("close ATM");
		logIn.setText("Log In");
			
		pane.getChildren().addAll(user, userName, pin, pinNum, logIn, close, labelOutput);
		pane.setAlignment(Pos.CENTER);
		
		EventHandler<ActionEvent> exit = (ActionEvent ae) -> exitWin();
		close.setOnAction(exit);
		
		EventHandler<ActionEvent> log = (ActionEvent ae) -> login(primaryStage);
		logIn.setOnAction(log);
		
		Scene mainscene = new Scene(pane);
		
		primaryStage.setTitle("ATM System");
		primaryStage.setWidth(375);
		primaryStage.setHeight(400);
		primaryStage.setScene(mainscene);
		primaryStage.show();
	}
	
	public void login(Stage secondaryStage) {
		String regex = "[0-9]{6}";
		boolean isTrue = false;
		boolean matchID = Pattern.matches(regex, pinNum.getText());
		for (user i : userList) {
			if (String.valueOf(userName.getText()).equals(i.getUser()) && matchID == true) {			
				bal.setText("Your current balance");
				balance.setPrefColumnCount(30);
				viewBal.setText("View Balance");
				
				balance.setEditable(false);
				
				with.setText("\nWithdraw from Balance");
				withDraw.setPrefColumnCount(30);
				witD.setText("WithDraw");
				
				dep.setText("\nDeposit");
				deposit.setPrefColumnCount(30);
				depD.setText("Deposit into Balance");
					
				pane1.getChildren().addAll(bal, balance, viewBal, with, withDraw, witD, dep, deposit, depD);
				pane1.setAlignment(Pos.CENTER);
				
				EventHandler<ActionEvent> accBal = (ActionEvent ae) -> balance();
				viewBal.setOnAction(accBal);
				
				EventHandler<ActionEvent> withBal = (ActionEvent ae) -> withDraw();
				witD.setOnAction(withBal);
				
				EventHandler<ActionEvent> depBal = (ActionEvent ae) -> deposit();
				depD.setOnAction(depBal);
				
				Scene pane = new Scene(pane1);
				secondaryStage.setTitle("ATM System");
				secondaryStage.setWidth(375);
				secondaryStage.setHeight(400);			
				secondaryStage.setScene(pane);
				secondaryStage.show();
			}
		}
		
		if (isTrue != true) {
			labelOutput.setText("Invalid value");
			if (userName.getText().isEmpty() && pinNum.getText().isEmpty()){
				labelOutput.setText("Username and PIN is empty");
			} else if (userName.getText().isEmpty()) {
				labelOutput.setText("Username is empty");
			} else if (pinNum.getText().isEmpty()){
				labelOutput.setText("PIN is empty");
		    } 
	    }
		
	}
		
	public static void exitWin() {
		System.exit(1);
	}
	
	public void withDraw() {
		for (user i : userList) {
			if (userName.getText().equals(i.getUser())) {
				if (Double.valueOf(withDraw.getText()) < i.getBalance()) {
					double acct = i.getBalance() - Double.valueOf(withDraw.getText());
					i.setBalance(acct);
					withDraw.setText("You have withdrawn " + String.valueOf(acct));
				} else {
					withDraw.setText("Your account balance is insuficient");
				}
				
			}
			
		}
	}
	
	public void deposit() {
		for (user i : userList) {
			if (userName.getText().equals(i.getUser())) {
				double acct = i.getBalance() + Double.valueOf(deposit.getText());
				i.setBalance(acct);
				deposit.setText("You have deposit " + String.valueOf(acct));
			}
			
		}
	}
	
	public void balance() {
		for (user i : userList) {
			if (userName.getText().equals(i.getUser())) {
				balance.setText(String.valueOf(i.getBalance()));
			}
			
		}
	}
}
