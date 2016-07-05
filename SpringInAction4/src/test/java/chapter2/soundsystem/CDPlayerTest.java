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
import org.junit.contrib.java.lang.system.StandardOutputStreamLog;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CDPlayerConfig.class)
public class CDPlayerTest {

	@Rule
	public final StandardOutputStreamLog log = new StandardOutputStreamLog();

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
		assertEquals("Playing Sgt. Pepper's Lonely Hearts Club Band"
				+ " by The Beatles", log.getLog());
	}
	
	//@Test
	public void shouldPlayDiscFromFactoryTest(){
		String title = "Test Q";
		
		CompactDisc disc = compactDiscFactory.getCompactDisc(title);
		disc.play();
		assertEquals("Playing "+title
				+ " by "+title+"-Artist\n", log.getLog());
	}
}