/**
 * 
 */
package application;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * @author tania
 *
 */
public class TasksController implements Initializable{
	
// configure the table
@FXML
private TableView<Task> tableView;
@FXML
private TableColumn<Task, String> taskDescriptionColumn;
@FXML
private TableColumn<Task, String> etaColumn;
@FXML
private TableColumn<Task, String> checkBoxColumn;

// These instance variables are used to create new Task objects
@FXML
private TextField description;
@FXML
private TextField eta;
@FXML
private CheckBox select;

@FXML
private Label timerLabel;
@FXML
private Label robotInfo;
private Timeline animation;
private int tmp;
private String s = "";

private ObservableList<Task> data = FXCollections.observableArrayList();
private ObservableList<Task> randomSelection = FXCollections.observableArrayList();

	
	public ObservableList<Task> getData(){
		data.add(new Task("do the dishes", "1000"));
		data.add(new Task("sweep the house", "3000"));
		data.add(new Task("do the laundry", "10000"));
		data.add(new Task("take out the recycling", "4000"));
		data.add(new Task("make a sammich", "7000"));
		data.add(new Task("mow the lawn", "20000"));
		data.add(new Task("rake the leaves", "18000"));
		data.add(new Task("give the dog a bath", "14500"));
		data.add(new Task("bake some cookies", "8000"));
		data.add(new Task("wash the car", "20000"));
		
		Random rand = new Random();

		for(int i=0; i<5; i++) {
			Task temp = data.get(rand.nextInt(data.size()));
			if (!randomSelection.contains(temp)) {
				randomSelection.add(temp);
			}
			else
				i-=1;
		}

		return randomSelection; 
	}


	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		// set up the columns in the table
		
		taskDescriptionColumn.setCellValueFactory(new PropertyValueFactory<Task, String>("description"));
		etaColumn.setCellValueFactory(new PropertyValueFactory<Task, String>("eta"));
		checkBoxColumn.setCellValueFactory(new PropertyValueFactory<Task, String>("select"));
		tableView.setItems(getData());
		etaColumn.setVisible(false);
		//continuously runs timelabel() 
		animation = new Timeline(new KeyFrame(Duration.seconds(1), e -> timelabel()));
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.play();
		robotInfo.setText("My Robot: "+ Controller.name + " the " + Controller.type);

	}
	  //checks if a checkbox has been selected, and if so calls timeCounter
	  public void timelabel() {
		for(Task task: randomSelection) {
			if(!task.getSelect().isDisabled() && task.getSelect().isSelected()) {
				tmp = Integer.parseInt(task.getETA())/1000;
				task.getSelect().setDisable(true);
				animation = new Timeline(new KeyFrame(Duration.seconds(1), e -> timeCounter()));
				animation.setCycleCount(tmp);
				animation.play();

			}
		}
	}    
	
	//counts down eta of task with help of animation in timelabel()
	public void timeCounter() {
		s = "Time left: "+ tmp + " secs";
		
		if(tmp > 0 ) {
			tmp --;
		} 
		if (tmp == 0) {
			s = "Completed task!";
		}
		
		timerLabel.setText(s);
	}
	
	@FXML
	//deletes selected checkBoxes (completed tasks) when delete row button is clicked
	public void deleteSelectedRows(ActionEvent event) {
		ObservableList<Task> rowsToRemove = FXCollections.observableArrayList();
		
		for(Task task: randomSelection) {
			if(task.getSelect().isSelected())
				rowsToRemove.add(task);
		}
		randomSelection.removeAll(rowsToRemove);
	}
	
	/**
	 * When this method is called, it will change the Scene to the Main scene
	 */
	public void changeScreenButtonPushed(ActionEvent event) throws IOException {

		try {
			
			Parent root = FXMLLoader.load(getClass().getResource("/FXMLDocument.fxml"));

			Scene scene = new Scene(root);
			
			// This line gets the Stage information
			Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

			window.setScene(scene);
			
			window.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}