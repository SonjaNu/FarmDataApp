package farmDataExercise.FarmDataApp.domain;

import java.time.OffsetDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;

//Entity class that is mapped with database
@Entity
@Table(name = "farmdata")
public class Farm {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	@Column(name = "id")
	private long id;

	@Column(name = "location")
	private String location;

	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
	@Column(name = "datetime")
	private OffsetDateTime datetime;

	@Column(name = "sensorType")
	private String sensorType;

	@Column(name = "value")
	private double value;

	// Set the parameters according to the columns in the csv file
	public Farm(String location, OffsetDateTime datetime, String sensorType, double value) {
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

	public OffsetDateTime getDatetime() {
		return datetime;
	}

	public void setDatetime(OffsetDateTime datetime) {
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
		return "Farm [id=" + id + ", location=" + location + ", datetime=" + datetime + ", sensorType=" + sensorType
				+ ", value=" + value + "]";
	}

}