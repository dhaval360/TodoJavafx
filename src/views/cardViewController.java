package views;

import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import model.taskObject;

public class cardViewController {
	
	public List<taskObject> list = new ArrayList<>();

	@FXML
	private Label title;
	
	public VBox vbox;
	public AnchorPane parent;
	
	@FXML
	private Label Discription;
	
	@FXML
	private Button complete;
	
//	public cardViewController(taskObject task) {
//		// TODO Auto-generated constructor stub
//		title.setText(task.getTitle());
//		Discription.setText(task.getDiscription());
//	}

	@FXML
	private void onComplete() {
		int index = vbox.getChildren().indexOf(parent);
		vbox.getChildren().remove(parent);
		list.remove(index);
		//System.out.println(index);
		
	}

	public void getObject(taskObject task) {
		title.setText(task.getTitle());
        Discription.setText(task.getDiscription());
	}
	
	public void getPerent(VBox vbox,AnchorPane parent) {
		this.vbox = vbox;
		this.parent = parent;
	}
	
	public void getList(List<taskObject> list) {
		this.list = list; 
	}
}
