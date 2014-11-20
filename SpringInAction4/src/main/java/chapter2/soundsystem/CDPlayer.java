package chapter2.soundsystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class CDPlayer implements MediaPlayer {
	private CompactDisc cd;

	@Autowired
	public CDPlayer(@Qualifier("lonelyHeartsClub") CompactDisc cd) {
		this.cd = cd;
	}

	public void play() {
		cd.play();
	}
}