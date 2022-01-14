package farmDataExercise.FarmDataApp.domain;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

//class for defining endpoints or API to upload, download or read
@CrossOrigin("http://localhost:8080")  //configure the origins which are allowed to call this API
@Controller
@RequestMapping("/application")
public class CSVController {

  @Autowired
  CSVService fileService;

  //upload the csv file
  @PostMapping("/upload")
  public ResponseEntity<Message> uploadFile(@RequestParam("file") MultipartFile file) {
    String message = "";

    if (CSVHelper.hasCSVFormat(file)) {  //if the file is in csv format --> save
      try {
        fileService.save(file);

        message = "Uploaded the file successfully: " + file.getOriginalFilename();
        
        String csvDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()  //get the current URL
                .path("/application/download/")
                .path(file.getOriginalFilename())
                .toUriString();

        return ResponseEntity.status(HttpStatus.OK).body(new Message(message,csvDownloadUri));
      
      } catch (Exception e) {
        message = "Failed to upload file: " + file.getOriginalFilename() + "!";
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new Message(message,""));
      }
    }

    message = "The file type is incorrect. Please upload a csv file!";
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Message(message,""));
  }
  //get all the list of farms from the db
  @GetMapping("/farms")
  public ResponseEntity<List<Farm>> getAllFarms() {
    try {
      List<Farm> farms = fileService.getAllFarms();

      if (farms.isEmpty()) {  //if the list is empty, return status 204 (the server has successfully fulfilled the request but there is no content to send in the response payload body
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(farms, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  //In order to download the csv file
  @GetMapping("/download/{fileName:.+}")
  public ResponseEntity<Resource> downloadFile(@PathVariable String fileName) {
    InputStreamResource file = new InputStreamResource(fileService.load());

    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
        .contentType(MediaType.parseMediaType("application/csv"))
        .body(file);
  }
}

