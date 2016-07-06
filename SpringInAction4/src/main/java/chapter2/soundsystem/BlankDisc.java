package chapter2.soundsystem;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class BlankDisc implements CompactDisc {
	private String title;
	private String artist;
	private List<String> tracks;

	public BlankDisc(String title, String artist) {
		this.title = title;
		this.artist = artist;
	}

	@Override
	public void play() {
		System.out.println("Playing " + title + " by " + artist);
	}
	
	@Override
	public void playTrack(int trackNumber){
		String trackName = trackNumber<=tracks.size()&&trackNumber>0?tracks.get(trackNumber-1):"NA";
		System.out.println("Playing " + title + " by " + artist + " track name " + trackName);
	}
	
	public void setTracks(List<String> tracks){
		this.tracks = tracks;
	}

}
