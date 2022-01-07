package farmDataExercise.FarmDataApp.domain;

import javax.persistence.Column;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//Entity class that is mapped with database
@Entity
@Table(name = "farmdata") //Database in this project includes one table
public class Farm {

	  @Id
	  @GeneratedValue(strategy = GenerationType.AUTO) //The id is generated automatically because the csv files do not contain an id column.
	  @Column(name = "id")
	  private long id; 

	  @Column(name = "location")
	  private String location;

	  @Column(name = "datetime")
	  private String datetime;

	  @Column(name = "sensorType")
	  private String sensorType;
	  
	  @Column(name = "value")
	  private double value;

	public Farm(String location, String datetime, String sensorType, double value) { //Set the parameters according to the columns in the csv file.
		this.location = location;
		this.datetime = datetime;
		this.sensorType = sensorType;
		this.value = value;
	}

	public Farm() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	public String getSensorType() {
		return sensorType;
	}

	public void setSensorType(String sensorType) {
		this.sensorType = sensorType;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Farm [id=" + id + ", location=" + location + ", datetime=" + datetime
				+ ", sensorType=" + sensorType + ", value=" + value + "]";
	}
	
	
	  
	  

}