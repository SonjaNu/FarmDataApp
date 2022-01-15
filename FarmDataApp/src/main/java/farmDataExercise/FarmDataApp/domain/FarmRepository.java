package farmDataExercise.FarmDataApp.domain;

import java.util.List;
import java.util.Optional;
import javax.annotation.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

//Repository that communicates with database

@Repository  //this interface performs all sorts of CRUD operations
@Transactional
public interface FarmRepository extends JpaRepository<Farm, Long>{  //entity class name and data type of the PK (long/integer)


	@Resource public Optional<Farm> findByLocation(@Param("location") String location);
	@Resource public Optional<Farm> findBySensorType(@Param("sensor_type") String sensorType);
	
	 List<Farm> findByValueBetween(double startValue, double endValue);
	 
	 @Query(value="SELECT *  FROM farmdata d WHERE d.location LIKE %:keyword% OR d.sensor_type LIKE %:keyword%", nativeQuery = true) 	
	 public List<Farm> findByKeyword(@Param("keyword") String keyword);
	 
	 @Query(value="SELECT *  FROM farmdata d WHERE EXTRACT(MONTH FROM d.datetime) LIKE %:month% AND EXTRACT(YEAR FROM d.datetime) LIKE %:year% AND d.sensor_type LIKE %:sensor% AND d.location LIKE %:loc%", nativeQuery = true)  
	 public List<Farm> findByMonth(@Param("month") String month, @Param("sensor") String sensor, @Param("loc") String loc, @Param("year") String year);
	 
	 @Query(value="SELECT * FROM farmdata d WHERE d.sensor_type LIKE %:sensor% AND  d.value BETWEEN :min AND :max", nativeQuery = true)
	 public List<Farm> findBetween(@Param("sensor") String sensor, @Param("min") Double min, @Param("max") Double max);

	 @Query(value="SELECT AVG(value) from farmdata d WHERE EXTRACT(MONTH FROM d.datetime) LIKE %:key1% AND EXTRACT(YEAR FROM d.datetime) LIKE %:key2% AND d.location LIKE %:key3% AND d.sensor_type LIKE %:key4% ", nativeQuery = true)
	 public Double AVG(@Param("key1") String month, @Param("key2") String sensor, @Param("key3") String loc, @Param("key4") String key4);
	 
	 @Query(value="SELECT MIN(value) from farmdata d WHERE EXTRACT(MONTH FROM d.datetime) LIKE %:key5% AND EXTRACT(YEAR FROM d.datetime) LIKE %:key6% AND d.location LIKE %:key7% AND d.sensor_type LIKE %:key8% ", nativeQuery = true)
	 public Double findMin(@Param("key5") String key5, @Param("key6") String key6, @Param("key7") String key7, @Param("key8") String key8);
	 
	 @Query(value="SELECT MAX(value) from farmdata d WHERE EXTRACT(MONTH FROM d.datetime) LIKE %:key1% AND EXTRACT(YEAR FROM d.datetime) LIKE %:key2% AND d.location LIKE %:key3% AND d.sensor_type LIKE %:key4% ", nativeQuery = true)
	 public Double findMax(@Param("key1") String month, @Param("key2") String sensor, @Param("key3") String loc, @Param("key4") String key4);


}
