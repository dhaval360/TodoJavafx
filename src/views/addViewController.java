package views;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import model.taskObject;

public class addViewController {
	
	private StackPane root;
	
	public List<taskObject> list = new ArrayList<>();
	public List<taskObject> todaylist = new ArrayList<>();
	public List<taskObject> tommorowlist = new ArrayList<>();
	public List<taskObject> upcominglist = new ArrayList<>();
	
	private cardViewController cardController;
	
	@FXML 
	private TextField title;
	
	@FXML 
	private TextArea discription;
	
	@FXML 
	private Slider slider;
	
	@FXML 
	private DatePicker endDatePicker;
	
	@FXML 
	private DatePicker startDatePicker;
	
	@FXML 
	private TextField textPreference;

	private todoViewController todocon;
	
	
	public void getRoot(StackPane root, todoViewController todocon) {
		this.root = root;
		this.todocon = todocon;
	}
	
	public void initialize() {
        slider.valueProperty().addListener((observable, oldValue, newValue) -> {

            textPreference.setText(String.valueOf(newValue.intValue()));


        });
        title.setText(null);
        discription.setText(null);
        
        slider.setValue(0);
        endDatePicker.setValue(null);
        startDatePicker.setValue(null);
        textPreference.setText(null);
        

	}
	
	public void getCardController(cardViewController cardController) {
		this.cardController = cardController;
	}
	
	
	@FXML
	private void onDone() {
		
		String Title;
		String Discription;
		LocalDate endDate;
		LocalDate startDate = null ;
		int preference;
		taskObject task = new taskObject();
		
		if(!title.getText().equals(null)) {
			Title = title.getText();
			task.setTitle(Title);
		}
		
		if(!discription.getText().equals(null)) {
			Discription = discription.getText();
			task.setDiscription(Discription);
		}else {
			Discription = "----nil----";
			task.setDiscription(Discription);
		}
		
		if(endDatePicker.getValue() != null) {
			endDate = endDatePicker.getValue();
			task.setEndDate(endDate.toString());
		}
		
		if(startDatePicker.getValue() != null) {
			startDate = startDatePicker.getValue();
			task.setStartDate(startDate.toString());
		}
		
		if(Integer.valueOf(textPreference.getText()) >= 0 && Integer.valueOf(textPreference.getText()) <= 100) {
			preference = Integer.valueOf(textPreference.getText());
			task.setPreference(preference);
		}else {
			preference = 0;
			task.setPreference(0);
		}
		
		list.add(task);
		LocalDate date = null;
		date = LocalDate.now();
		
//		System.out.println(startDate);
//		System.out.println(date);
//		System.out.println(date.plusDays(1));
		
		if(startDate.equals(date)) {
			todaylist.add(task);
			System.out.println("hello");
		}else if(startDate.equals(date.plusDays(1))) {
			tommorowlist.add(task);
			System.out.println("hello");
		}else {
			upcominglist.add(task);
			System.out.println("hello");
		}
		
		
//		cardViewController card = new cardViewController();
//		card.getObject();
		//cardController.getObject(task);
		//System.out.print(task.getTitle()+" "+task.getDiscription());
		
		root.getChildren().remove(2);
		if(todocon.temp.getText().equals("Today Tasks")) {
			todocon.onToday();
		}else if(todocon.temp.getText().equals("Tommorow Tasks")) {
			todocon.onTommorow();
		}else {
			todocon.onUpcoming();;
		}
		
		title.setText(null);
        discription.setText(null);
        
        slider.setValue(0);
        endDatePicker.setValue(null);
        startDatePicker.setValue(null);
        textPreference.setText(null);
		  
	}
	
	@FXML
	private void onCancle() {
		root.getChildren().remove(2);
		title.setText(null);
        discription.setText(null);
        
        slider.setValue(0);
        endDatePicker.setValue(null);
        startDatePicker.setValue(null);
        textPreference.setText(null);
		
	}

	public void getList(List<taskObject> list,List<taskObject> todaylist, List<taskObject> tommorowlist, List<taskObject> upcominglist) {
		// TODO Auto-generated method stub
		this.list = list;
		this.todaylist = todaylist;
		this.tommorowlist = tommorowlist;
		this.upcominglist = upcominglist;
	}
		
}
