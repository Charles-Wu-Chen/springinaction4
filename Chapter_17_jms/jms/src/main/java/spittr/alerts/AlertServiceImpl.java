package spittr.alerts;

import java.io.IOException;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.core.JmsOperations;
import org.springframework.jms.core.MessageCreator;

import com.fasterxml.jackson.databind.ObjectMapper;

import spittr.domain.Spittle;

public class AlertServiceImpl implements AlertService {

  private JmsOperations jmsOperations;

  public AlertServiceImpl(JmsOperations jmsOperations) {
    this.jmsOperations = jmsOperations;
  }

//  public void sendSpittleAlert(final Spittle spittle) {
//    jmsOperations.send(
//      "spittle.alert.queue", 
//      new MessageCreator() {
//        public Message createMessage(Session session) 
//                       throws JMSException {
//          return session.createObjectMessage(spittle);
//        }
//      }
//    );
//  }


  public void sendSpittleAlert(final Spittle spittle) {
    jmsOperations.send(
      new MessageCreator(){
        public Message createMessage(Session session) 
                       throws JMSException {
          Message message =  session.createTextMessage(convertSpittleToJson(spittle));
          System.out.println("---------"+Spittle.class.getName());
          message.setStringProperty("classType",Spittle.class.getName());
          return message;
        }
      }
    );
  }

  public String convertSpittleToJson(Spittle spittle){
	         
	        ObjectMapper mapperObj = new ObjectMapper();
	         
	        try {
	            // get Spittle object as a json string
	            String jsonStr = mapperObj.writeValueAsString(spittle);
	            System.out.println(jsonStr);
	            return jsonStr;
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        return "";
	   }
  
  //Message converter gives a loot of troubles
  
  
//  public void sendSpittleAlert(Spittle spittle) {
//    jmsOperations.convertAndSend(spittle);
//  }
//  
//  public Spittle getSpittleAlert() {
//    try {
//    ObjectMessage message = (ObjectMessage) jmsOperations.receive();
//    return (Spittle) message.getObject();
//    } catch (JMSException e) {
//      throw JmsUtils.convertJmsAccessException(e);
//    }
//  }
  
  public Spittle retrieveSpittleAlert() {
    return (Spittle) jmsOperations.receiveAndConvert();
  }

}
