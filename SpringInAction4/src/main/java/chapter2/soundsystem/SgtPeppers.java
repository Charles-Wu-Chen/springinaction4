package chapter2.soundsystem;

import org.springframework.stereotype.Component;

@Component("lonelyHeartsClub")
public class SgtPeppers implements CompactDisc {
	private String title = "Sgt. Pepper's Lonely Hearts Club Band";
	private String artist = "The Beatles";
	
	@Override
	public void play() {
		System.out.print("Playing " + title + " by " + artist);
	}
	
	@Override
	public void playTrack(int trackNumber){
		
	}
	
}