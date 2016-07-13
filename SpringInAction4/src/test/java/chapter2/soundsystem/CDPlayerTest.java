package chapter2.soundsystem;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CDPlayerConfig.class)
public class CDPlayerTest {



	@Autowired
	private MediaPlayer player;

	@Autowired
	@Qualifier("lonelyHeartsClub")
	private CompactDisc cd;

	@Autowired
	ApplicationContext ctx;

	
	@Autowired
	CompactDiscFactory compactDiscFactory;
	@Test
	public void cdShouldNotBeNullTest() {

		String[] beanDefinitionNames = ctx.getBeanDefinitionNames();

		for (String bean : beanDefinitionNames) {
			System.out.println("Bean-------> : " + bean);
		}

		assertNotNull(cd);
	}

	@Test
	public void playTest() {
		player.play();
		
	}
	
	//@Test
	public void shouldPlayDiscFromFactoryTest(){
		String title = "Test Q";
		
		CompactDisc disc = compactDiscFactory.getCompactDisc(title);
		disc.play();

	}
}