/**
 * 
 */
package application;

import javafx.beans.property.SimpleStringProperty;

/**
 * @author tania
 *
 */
public class Robot {
	private SimpleStringProperty type;
	private SimpleStringProperty name;
	
	/* constructor */
	Robot(String type, String name){
		this.type = new SimpleStringProperty(type);
		this.name = new SimpleStringProperty(name);
	}
	
	/* getter and setter methods */
	
	public String getType() {
        return type.get();
    }

    public void setType(String text) {
		this.type = new SimpleStringProperty(text);
	}
    
    public String getName() {
        return type.get();
    }

    public void setName(String text) {
		this.type = new SimpleStringProperty(text);
	}
    
    /* methods to complete tasks 
     *  do map with key value; key is task description, value is eta???????
     */
    
    
}

