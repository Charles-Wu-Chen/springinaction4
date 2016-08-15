package spittr.api;

import java.io.IOException;
import java.io.Writer;
import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import spittr.Spittle;
import spittr.data.SpittleNotFoundException;
import spittr.data.SpittleRepository;

@RestController
@RequestMapping("/spittles")
public class SpittleApiController {

  private static final String MAX_LONG_AS_STRING = "9223372036854775807";

  private SpittleRepository spittleRepository;

  @Autowired
  public SpittleApiController(SpittleRepository spittleRepository) {
    this.spittleRepository = spittleRepository;
  }

  @RequestMapping(method=RequestMethod.GET, produces="application/json")
  public List<Spittle> spittles(
      @RequestParam(value="max", defaultValue=MAX_LONG_AS_STRING) long max,
      @RequestParam(value="count", defaultValue="20") int count) {
    return spittleRepository.findSpittles(max, count);
  }
  
  @RequestMapping(value="/{id}", method=RequestMethod.GET, produces="application/json")
  public Spittle spittleById(@PathVariable Long id) {
	  Spittle s = null;
	  try{
		  s =  spittleRepository.findOne(id);
	  }catch (Exception e){
		  throw new SpittleNotFoundException(id);
	  }
	  return s;
  }
  
  @RequestMapping(method=RequestMethod.POST, consumes="application/json")
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<Spittle> saveSpittle(@RequestBody Spittle spittle, UriComponentsBuilder ucb) {
    Spittle saved = spittleRepository.save(spittle);
    
    HttpHeaders headers = new HttpHeaders();
    URI locationUri = ucb.path("/spittles/")
        .path(String.valueOf(saved.getId()))
        .build()
        .toUri();
    headers.setLocation(locationUri);
    
    ResponseEntity<Spittle> responseEntity = new ResponseEntity<Spittle>(saved, headers, HttpStatus.CREATED);
    return responseEntity;
  }
  
  @ExceptionHandler(SpittleNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public @ResponseBody Error spittleNotFound(SpittleNotFoundException e) {
    long spittleId = e.getSpittleId();
    return new Error(4, "Spittle [" + spittleId + "] not found");
  }
//
//@ExceptionHandler(SpittleNotFoundException.class)
//@ResponseStatus(HttpStatus.NOT_FOUND)
//@ResponseBody
//public void handleException(final SpittleNotFoundException e, final HttpServletRequest request,
//        Writer writer)
//{
//    try {
//		writer.write(String.format(
//		        "{\"error\":{\"java.class\":\"%s\", \"message\":\"%s\"}}",
//		        e.getClass(), e.getMessage()));
//	} catch (IOException e1) {
//		// TODO Auto-generated catch block
//		e1.printStackTrace();
//	}
//}
  
  
  
//@ExceptionHandler(SpittleNotFoundException.class)
//@ResponseStatus(HttpStatus.NOT_FOUND)
//public  @ResponseBody ResponseEntity<Error> spittleNotFound(SpittleNotFoundException e) {
//  long spittleId = e.getSpittleId();
//  
//  return new ResponseEntity<Error>(new Error(4, "Spittle [" + spittleId + "] not found"), HttpStatus.NOT_FOUND);
//}
}
