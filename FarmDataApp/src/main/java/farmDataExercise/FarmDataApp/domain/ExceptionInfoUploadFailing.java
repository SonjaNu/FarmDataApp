package farmDataExercise.FarmDataApp.domain;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


//class for handling the exception when the uploading the file of size is more than as configured value (application.properties)
@ControllerAdvice
public class ExceptionInfoUploadFailing extends ResponseEntityExceptionHandler {

@SuppressWarnings("rawtypes")
@ExceptionHandler(MaxUploadSizeExceededException.class)
  public ResponseEntity handleMaxSizeException(MaxUploadSizeExceededException exc) {
    return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new Message("File is too large! Maximum size is 4 MB.",""));
  }
}

