package views;

import model.user;
import application.Main;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class loginViewController {
	
	private String userName = "";
	private String passWord = "";
	
	static Stage primaryStage = new Stage();
	static AnchorPane todolayout = new AnchorPane();
	static StackPane root;
	
	@FXML
	private TextField username;
	
	@FXML
	private TextField password;
	
	@FXML
	private Label newUser;
	 
	@FXML
	private void onLogin() {
		
				
		user User = new user(username.getText(),password.getText());
		
		//System.out.print(User.getUsername()+" "+password.getText());
		if(username.getText().equals(Main.username) && password.getText().equals(Main.password)){
			newUser.setText("login sucessful");
			//System.out.print(username.getText()+" "+password.getText());
			root.getChildren().remove(1);
			root.getChildren().add(1, todolayout);

		}else {
			newUser.setText("login unsucessful");
		}
	}
	
	public static void getResource(Stage primaryStage, AnchorPane todolayout, StackPane root) {
		loginViewController.primaryStage = primaryStage;
		loginViewController.todolayout = todolayout;
		loginViewController.root = root;
	}
	
	public String getUsername() {
		return userName;
	}
	
	public String getPassword() {
		return userName;
	}
	
	public void setPassword(String password) {
		this.passWord = password;
	}
	
	public void setUsername(String username) {
		this.userName = username;
	}
	

}
