package views;

import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;

public class setViewController {
	
	public StackPane root = new StackPane();
	
	public void setRoot(StackPane root) {
		this.root = root;
	}
	
	@FXML
	private TextField newUsername;
	
	@FXML
	private TextField newPassword;
	
	@FXML
	public void onDone() {
		if(newUsername.getText() != null) {
			Main.username = newUsername.getText();
		}else {
			newUsername.setText("Enter new username");
		}
		if(newPassword.getText() != null) {
			Main.password = newPassword.getText();
		}else {
			newPassword.setText("Enter new password");
		}
		root.getChildren().remove(2);
	}
	
	@FXML
	public void onCancel() {
		root.getChildren().remove(2);
	}
}
