package chapter2.soundsystem;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CompactDiscFactoryImpl implements CompactDiscFactory {

	@Autowired
	Map<String, CompactDisc> discMap;
	
	@Override
	public CompactDisc getCompactDisc(String discName) {
		
		if (discName == null) return null;
		else {
			return discMap.get(discName);
		}

	}

}
