package application;
	
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import model.taskListWrapper;
import model.taskObject;
import views.addViewController;
import views.cardViewController;
import views.loginViewController;
import views.todoViewController;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.*;


public class Main extends Application {
	
	private static List<taskObject> list = new ArrayList<>();
	private static List<taskObject> todaylist = new ArrayList<>();
	private static List<taskObject> tommorowlist = new ArrayList<>();
	private static List<taskObject> upcominglist = new ArrayList<>();
	public static String username = "";
	public static String password = "";

	
	public static void main(String[] args) {
		
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		
		
		FXMLLoader loginLoader = new FXMLLoader();
		FXMLLoader todoLoader = new FXMLLoader();
		FXMLLoader cardLoader = new FXMLLoader();
		FXMLLoader addLoader = new FXMLLoader();
		
		StackPane loginlayout = new StackPane();
		AnchorPane todolayout = new AnchorPane();
		AnchorPane cardLayout = new AnchorPane();
		AnchorPane addLayout = new AnchorPane();
	
		
		ImageView img1 = new ImageView(new Image(new FileInputStream("/C:/Users/IT28/Downloads/photo-1432821596592-e2c18b78144f.jpg")));
		img1.fitWidthProperty().bind(primaryStage.widthProperty());
		img1.fitHeightProperty().bind(primaryStage.heightProperty());
		
		StackPane root = new StackPane();
		root.getChildren().add(0, img1);
		
		
		try {
			todoLoader.setLocation(getClass().getResource("/views/todoView.fxml"));
			loginLoader.setLocation(getClass().getResource("/views/loginView.fxml"));
			cardLoader.setLocation(getClass().getResource("/views/cardView.fxml"));
			addLoader.setLocation(getClass().getResource("/views/addTaskView.fxml"));
			
			loginlayout = (StackPane) loginLoader.load();
			todolayout = (AnchorPane) todoLoader.load();  
			cardLayout = (AnchorPane) cardLoader.load() ;
			addLayout = (AnchorPane) addLoader.load() ;

			
			
		}catch(Exception  e) {
			e.printStackTrace();
			System.out.println("probem ");
		}
		
		root.getChildren().add(1, loginlayout);
		//root.getChildren().add(2, cardLayout);
		
		addViewController addCon = addLoader.getController();
		cardViewController cardCon = cardLoader.getController();
		loginViewController controller = loginLoader.getController();
		todoViewController todocon = todoLoader.getController();
		
		
		todocon.getCardlayout(cardLayout,addLayout,root,addCon);
		addCon.getRoot(root,todocon);
		addCon.getCardController(cardCon);
		addCon.getList(list,todaylist,tommorowlist,upcominglist);
		todocon.getLists(list,todaylist,tommorowlist,upcominglist);
		
		
		
		Scene sceneLogin = new Scene(root);
		
		loginViewController.getResource(primaryStage, todolayout, root);
		
		primaryStage.setScene(sceneLogin);
		primaryStage.setTitle("ToDo Application");
		primaryStage.show();
		
		File file = new File("task.xml");
		setPersonFilePath(file);
		LocalDate date;
		date = LocalDate.now();
		file = getPersonFilePath();
		//System.out.println(file.getPath());
		loadPersonDataFromFile(file);
		
		for(int i=0; i<list.size();i++) {
			if(list.get(i).getStartDate().equals(date.toString())) {
				todaylist.add(list.get(i));
			}else if(list.get(i).getStartDate().equals(date.plusDays(1).toString())) {
				tommorowlist.add(list.get(i));
			}else {
				upcominglist.add(list.get(i));
			}
		}
	}
	
	@Override
	public void stop() {
		list.clear();
		list.addAll(todaylist);
		list.addAll(tommorowlist);
		list.addAll(upcominglist);
		File file;
		file = getPersonFilePath();
		savePersonDataToFile(file);
	}
	
	public static File getPersonFilePath() {
	    Preferences prefs = Preferences.userNodeForPackage(Main.class);
	    System.out.print(prefs.absolutePath());
	    String filePath = prefs.get("filePath", null);
	    //System.out.println(filePath);
	    if (filePath != null) {
	        return new File(filePath);
	    } else {
	        return null;
	    }
	}
	
	public static void setPersonFilePath(File file) {
	    Preferences prefs = Preferences.userNodeForPackage(Main.class);
	    if (file != null) {
	        prefs.put("filePath", file.getPath());

	        // Update the stage title.
	        //primaryStage.setTitle("AddressApp - " + file.getName());
	    } else {
	        prefs.remove("filePath");

	        // Update the stage title.
	       // primaryStage.setTitle("AddressApp");
	    }
	}
	
	public static void loadPersonDataFromFile(File file) {
	    try {
	        JAXBContext context = JAXBContext
	                .newInstance(taskListWrapper.class);
	        Unmarshaller um = context.createUnmarshaller();

	        // Reading XML from the file and unmarshalling.
	        taskListWrapper wrapper = (taskListWrapper) um.unmarshal(file);

	        list.clear();
	        list.addAll(wrapper.getTasks());
	        username = wrapper.getUsername();
	        password = wrapper.getPassword();
//
//	        // Save the file path to the registry.
	        setPersonFilePath(file);
//	        System.out.println(file.getPath());

	    } catch (Exception e) { // catches ANY exception
	        Alert alert = new Alert(AlertType.ERROR);
	        alert.setTitle("Error");
	        alert.setHeaderText("Could not load data");
	        alert.setContentText("Could not load data from file:\n" + file.getPath());

	        alert.showAndWait();
	    }
	}
	
	public static void savePersonDataToFile(File file) {
	    try {
	        JAXBContext context = JAXBContext
	                .newInstance(taskListWrapper.class);
	        Marshaller m = context.createMarshaller();
	        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

	        // Wrapping our person data.
	        taskListWrapper wrapper = new taskListWrapper();
	        wrapper.setTasks(list);
	        wrapper.setUsername(username);
	        wrapper.setPassword(password);

	        // Marshalling and saving XML to the file.
	        m.marshal(wrapper, file);

	        // Save the file path to the registry.
	        setPersonFilePath(file);
	    } catch (Exception e) { // catches ANY exception
	        Alert alert = new Alert(AlertType.ERROR);
	        alert.setTitle("Error");
	        alert.setHeaderText("Could not save data");
	        alert.setContentText("Could not save data to file:\n" + file.getPath());

	        alert.showAndWait();
	    }
	}
	
	
}
