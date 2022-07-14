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
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class ATMGUI extends Application {

	TextField userName = new TextField();
	TextField pinNum = new TextField();
	TextField balance = new TextField();
	
	Label user = new Label();
	Label pin = new Label();
	Label bal = new Label();
	
	FlowPane pane = new FlowPane();
	
	Button display = new Button();
	Button close = new Button();
	
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
		balance.setPrefColumnCount(30);
		
		user.setText("Enter your Username");
		pin.setText("Enter your PIN");
		bal.setText("Your current balance");
		display.setText("Display Balance");
		close.setText("close ATM");
	
		balance.setEditable(false);
		
		pane.getChildren().addAll(user, userName, pin, pinNum, bal, balance, display, close);
		pane.setAlignment(Pos.CENTER);
		
		EventHandler<ActionEvent> db = (ActionEvent ae) -> balance();
		display.setOnAction(db);
		
		EventHandler<ActionEvent> exit = (ActionEvent ae) -> exitWin();
		close.setOnAction(exit);
		
		Scene mainscene = new Scene(pane);
		
		primaryStage.setTitle("ATM System");
		primaryStage.setWidth(425);
		primaryStage.setHeight(400);
		primaryStage.setScene(mainscene);
		primaryStage.show();
	}
	
	public void balance() {
		
		String regex = "[0-9]{6}";
		boolean isTrue = false;
		boolean matchID = Pattern.matches(regex, pinNum.getText());
		for (user i : userList) {
			if (String.valueOf(userName.getText()).equals(i.getUser()) && matchID == true) {
				balance.setText(String.valueOf(i.getBalance()));
				isTrue = true;
			} 
		}
			
		if (isTrue != true) {
			balance.setText("Invalid value");
			if (userName.getText().isEmpty() && pinNum.getText().isEmpty()){
				balance.setText("Username and PIN is empty");
			} else if (userName.getText().isEmpty()) {
				balance.setText("Username is empty");
			} else if (pinNum.getText().isEmpty()){
				balance.setText("PIN is empty");
		    }
	   }	
	}
	
	public static void exitWin() {
		System.exit(1);
	}
}
