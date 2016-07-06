package chapter3.concert;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class Audience {
	
	@Before("execution (** chapter3.concert.Performance.perform(..))")
	public void silencePhones(){
		System.out.println("Turn off handphones before performance");
	}
}
