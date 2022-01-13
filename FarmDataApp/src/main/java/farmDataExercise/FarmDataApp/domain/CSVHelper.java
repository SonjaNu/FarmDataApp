package farmDataExercise.FarmDataApp.domain;

import java.io.BufferedReader;



import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.QuoteMode;
import org.springframework.web.multipart.MultipartFile;

//class for reading and writing csv files
public class CSVHelper {
  public static String TYPE = "text/csv";
  static String[] HEADERs = { "Location", "Datetime", "SensorType", "Value" }; //only headers of csv files (not id)

  //check if the file format is CSV (true) or not (false)
  //retrieve a file's MIME type with method getContentType()
  public static boolean hasCSVFormat(MultipartFile file) {
    if (TYPE.equals(file.getContentType())		
    		|| file.getContentType().equals("application/vnd.ms-excel")) {
      return true;
    }

    return false;
  }

  //csvToFarms reads the CSV File data
  //Read data from a character-input stream with public class BufferedReader --> buffers characters 
  //InputStreamReader reads bytes and decodes them into characters using a specified charset
  //CSVParser object is created from BufferedReader and InputStream --> calls a predefined method getRecords(), which returns the content present in the csv file in the form of record
  public static List<Farm> csvToFarms(InputStream is) {
    try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        CSVParser csvParser = new CSVParser(fileReader,
            CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

      List<Farm> farmList = new ArrayList<>();
      
      //iterate over each record
      Iterable<CSVRecord> csvRecords = csvParser.getRecords();
    
      //get the value of each field with get() method
      for (CSVRecord csvRecord : csvRecords) {

    	  //Validation data format
    	  if(!csvRecord.get("Location").equals("") && !csvRecord.get("Datetime").equals("") && !csvRecord.get("SensorType").equals("") && !csvRecord.get("Value").equals("") && csvRecord.get("Value").matches("[0-9.-]*") ) {
    		 
//        	  Farm farm = new Farm(
//                      csvRecord.get("Location"),
//                      csvRecord.get("Datetime"),
//                      csvRecord.get("SensorType"),
//                      Double.parseDouble(csvRecord.get("Value"))
//                    );
//        	  
        	  Farm farm = new Farm(
                      csvRecord.get("Location"),
                      OffsetDateTime.parse(csvRecord.get("Datetime")),                     
                      csvRecord.get("SensorType"),
                      Double.parseDouble(csvRecord.get("Value"))
                    );
	  
        	  //Validation with given rules
        	  if (farm.getSensorType().equals("pH") && ( farm.getValue() >= 0.0 && farm.getValue() <= 14.0 ) ) {
        		  farmList.add(farm);
        	  } else if ( farm.getSensorType().equals("temperature") && (farm.getValue() >= -50.0 && farm.getValue() <= 100.0 ))  {
        		  farmList.add(farm);
        	  } else if ( farm.getSensorType().equals("rainFall") && (farm.getValue() >= 0.0 && farm.getValue() <= 500.0 ))  {
        		  farmList.add(farm);
        	  }
    	  }
    	  

      }

      return farmList;
    } catch (IOException e) {
      throw new RuntimeException("Problems to parse csv file");
    }
  }
  //farmsToCSV writes the data in the csv file from the db table
  //Public class ByteArrayInputStream implements an output stream in which the data is written into a byte array. The buffer automatically grows as data is written to it.
  public static ByteArrayInputStream farmsToCSV(List<Farm> farmList) {
    final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);  //DEFAULT is Standard Comma Separated Value format but allowing empty lines

    try (ByteArrayOutputStream out = new ByteArrayOutputStream();
        CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {  //print the value in csv file
      for (Farm farm : farmList) {			//iterate the list 
        List<String> data = Arrays.asList(	//and store values in the form of a String List
              farm.getLocation(),
              String.valueOf(farm.getDatetime()),
              farm.getSensorType(),
              String.valueOf(farm.getValue())
            );

        csvPrinter.printRecord(data);
      }
      
      
//      try (ByteArrayOutputStream out = new ByteArrayOutputStream();
//    	        CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {  //print the value in csv file
//    	      for (Farm farm : farmList) {			//iterate the list 
//    	        List<String> data = Arrays.asList(	//and store values in the form of a String List
//    	              farm.getLocation(),
//    	              farm.getDatetime(),
//    	              farm.getSensorType(),
//    	              String.valueOf(farm.getValue())
//    	            );
//
//    	        csvPrinter.printRecord(data);
//    	      }
    	      
      //flush out all the characters or stream data into the final csv file
      csvPrinter.flush();
      return new ByteArrayInputStream(out.toByteArray());	//Retrieve data using toByteArray()
    } catch (IOException e) {
      throw new RuntimeException("Problems to import data to csv file");
    }
  }
}
