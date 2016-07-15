package chapter3.concert;

import org.springframework.stereotype.Component;

@Component
public class RealPerformance implements Performance {

	@Override
	public void perform() {
		System.out.println("Real Performance is on");

	}

}
