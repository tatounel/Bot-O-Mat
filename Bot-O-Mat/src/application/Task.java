/**
 * 
 */
package application;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.CheckBox;

/**
 * @author tania
 *
 */
public class Task {
	private SimpleStringProperty description;
	private SimpleStringProperty eta;
	private CheckBox select;
	
	/* constructor */
	Task(String description, String eta){
		this.description = new SimpleStringProperty(description);
		this.eta = new SimpleStringProperty(eta);
		this.select = new CheckBox();
	}
	
	/* getter and setter methods */
	
	public String getDescription() {
        return description.get();
    }

    public void setDescription(String text) {
		this.description = new SimpleStringProperty(text);
	}
    
    public String getETA() {
        return eta.get();
    }

    public void setETA(String text) {
		this.eta = new SimpleStringProperty(text);
	}
    
    public CheckBox getSelect() {
    	return select;
    }
    
    public void setSelect(CheckBox select) {
    	this.select = select;
    }
    
    
    
}

