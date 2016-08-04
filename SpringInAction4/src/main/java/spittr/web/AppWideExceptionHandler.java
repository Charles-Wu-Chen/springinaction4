package spittr.web;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AppWideExceptionHandler {

  @ExceptionHandler(DuplicateSpittleException.class)
  public String handleDuplicate() {
    return "error/duplicate";
  }
  
  @ExceptionHandler(SpittleNotFoundException.class)
  public String handleNotFound() {
    return "error/spittlenotfound";
  }

}
