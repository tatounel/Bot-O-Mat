/**
 * 
 */
package application;


import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * @author tania
 *
 */
public class Controller {
	@FXML
    private ChoiceBox typeBox;
	@FXML
	private TextField roboName;
	static String name;
	static String type;
	private ObservableList<String> data = FXCollections.observableArrayList();

	
	public ObservableList<String> getData(){
		
		data.add(("Unipedal"));
		data.add(("Bipedal"));
		data.add(("Quadrupedal"));
		data.add(("Arachnid"));
		data.add(("Radial"));
		data.add(("Aeronautical"));
		return data; 
	}
	
	/**
	 * When this method is called, it will change the Scene to the Tasks scene
	 */
	public void changeScreenButtonPushed(ActionEvent event) throws IOException {
		
		name = roboName.getText();
		type = typeBox.getValue().toString();
		Robot robo = new Robot(type, name);
		
		try {
			
			Parent root = FXMLLoader.load(getClass().getResource("/Tasks.fxml"));
			Scene scene = new Scene(root);
			
			// This line gets the Stage information
			Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
			
			window.setScene(scene);
			
			window.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	@FXML
	public void initialize () {
		typeBox.setValue("Unipedal");
		typeBox.setItems(getData());
	}
}
