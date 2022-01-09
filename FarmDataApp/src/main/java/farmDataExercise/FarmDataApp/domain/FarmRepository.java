package farmDataExercise.FarmDataApp.domain;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



//Repository that communicates with database

@Repository  //this interface performs all sorts of CRUD operations like create, read, update, delete
public interface FarmRepository extends JpaRepository<Farm, Integer>{  //entity class name and data type of the PK (long/integer)
	
	 List<Farm> findBySensorType(String sensorType);
}
