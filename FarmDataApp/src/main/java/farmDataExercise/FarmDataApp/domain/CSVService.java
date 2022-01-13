package farmDataExercise.FarmDataApp.domain;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

//class for saving csv file data to db and reading the data from db
@Service
public class CSVService {
  @Autowired  //inject automatic dependency
  FarmRepository farmRepository;

  //save the data from csv files to the db
  public void save(MultipartFile file) {
    try {
      List<Farm> farms = CSVHelper.csvToFarms(file.getInputStream());
      farmRepository.saveAll(farms);
    } catch (IOException e) {
      throw new RuntimeException("Problems to store csv data ");
    }
  }

  //read the data from the db and return in the form of ByteArrayInputStream
  public ByteArrayInputStream load() {
    List<Farm> farms = farmRepository.findAll();

    ByteArrayInputStream in = CSVHelper.farmsToCSV(farms);
    return in;
  }
 
  //read the data from the db and return the list of farms
  public List<Farm> getAllFarms() {
	    return farmRepository.findAll();
	  }
  
  
//  public List<Farm> getAllFarms(String keyword) {
//	  if (keyword != null) {
//          return farmRepository.search(keyword);
//      }
//      return farmRepository.findAll();
//  }
}
