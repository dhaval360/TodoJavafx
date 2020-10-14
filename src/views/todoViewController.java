package views;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import model.taskObject;

public class todoViewController {
	
	public String css;
	
	public List<taskObject> list = new ArrayList<>();
	public List<taskObject> todaylist = new ArrayList<>();
	public List<taskObject> tommorowlist = new ArrayList<>();
	public List<taskObject> upcominglist = new ArrayList<>();
	
	public void getLists(List<taskObject> list ,List<taskObject> todaylist, List<taskObject> tommorowlist, List<taskObject> upcominglist) {
		this.todaylist = todaylist;
		this.tommorowlist = tommorowlist;
		this.upcominglist = upcominglist;
		this.list = list;
	}
	
	@FXML
	private VBox vbox;
	
	@FXML
	private ScrollPane scroll;
	
	public AnchorPane cardlayout;
	
	public AnchorPane addLayout;
	
	public StackPane root;

	private addViewController addCon;
	
	public void getCardlayout(AnchorPane cardlayout,AnchorPane addLayout,StackPane root,addViewController addCon) {
		this.cardlayout = cardlayout;
		this.addLayout = addLayout;
		this.root = root;
		this.addCon = addCon;
		//tilePane.getChildren().add(cardlayout);
	}
	
	
	@FXML
	private Button todayButton;

	@FXML
	private Button tommorowButton;
	
	@FXML
	private Button upcomingButton;
	
	@FXML
	public Label temp;
	
	
	
	@FXML
	public void onAddTask() {
		root.getChildren().add(2,addLayout);
		//temp.setText("done");
	}
	
	
	@FXML
	public void onToday() { 
		
		vbox.getChildren().clear();
		temp.setText("Today Tasks");
	//	vbox.getChildren().add(cardlayout);
		System.out.println(todaylist);
		for(int i=0; i<todaylist.size(); i++) {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/views/cardView.fxml"));
			try {
				cardlayout = (AnchorPane)loader.load();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			cardViewController controller = loader.getController();
			controller.getObject(todaylist.get(i));
			vbox.getChildren().add(cardlayout);
			controller.getPerent(vbox,cardlayout);
			controller.getList(todaylist);
		}
		
		//		tilePane.setTileAlignment(Pos.TOP_LEFT);


	}
	
	@FXML
	public void onTommorow() {
		vbox.getChildren().clear();
		temp.setText("Tommorow Tasks");
		System.out.println(tommorowlist);
	//	vbox.getChildren().add(cardlayout);
		
		for(int i=0; i<tommorowlist.size(); i++) {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/views/cardView.fxml"));
			try {
				cardlayout = (AnchorPane)loader.load();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			cardViewController controller = loader.getController();
			controller.getObject(tommorowlist.get(i));
			vbox.getChildren().add(cardlayout);
			controller.getPerent(vbox,cardlayout);
			controller.getList(tommorowlist);
		}
		
		
		
	}
	
	@FXML
	public void onUpcoming() {
		vbox.getChildren().clear();
		temp.setText("Upcoming Tasks");
		System.out.println(upcominglist);
	//	vbox.getChildren().add(cardlayout);
		
		for(int i=0; i<upcominglist.size(); i++) {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/views/cardView.fxml"));
			try {
				cardlayout = (AnchorPane)loader.load();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			cardViewController controller = loader.getController();
			controller.getObject(upcominglist.get(i));
			vbox.getChildren().add(cardlayout);
			controller.getPerent(vbox,cardlayout);
			controller.getList(upcominglist);
		}
		
	}
	
	public void initialize() {
		vbox.prefWidthProperty().bind(scroll.widthProperty());
		onToday();
	}
	
	@FXML
	public void onLogout() {
		
		if(root.getChildren().size() == 3) {
			root.getChildren().remove(2);
		}
		root.getChildren().remove(1);
				
		StackPane loginlayout = new StackPane();
		FXMLLoader loginLoader = new FXMLLoader();
		loginLoader.setLocation(getClass().getResource("/views/loginView.fxml"));
		try {
			loginlayout = (StackPane) loginLoader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		root.getChildren().add(1,loginlayout);
		loginViewController controller = loginLoader.getController();
	}
	
	@FXML
	public void onChange() {
		AnchorPane layout = new AnchorPane();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/views/setUandPView.fxml"));
		
		try {
			layout = (AnchorPane) loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setViewController controller = loader.getController();
		root.getChildren().add(2,layout);
		controller.setRoot(root);
	}
	
}
