package main;

import java.io.File;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

import view.GameView;
import beatbox.Beatbox;

public class Game {
	
	public static final int PULSE_NUMBER_OF_TICKS = 4;
	public static final int BUTTON_SIZE = 100;
	public static final long NANOSECONDS_PER_SECOND = 1000000000;
	public static final int ANIMATION_UPDATES_PER_SECOND = 60;
			
	protected GameView view;
	
	protected final Beatbox beatbox;

	public Game() {
		beatbox = new Beatbox(this);
		view = new GameView(this);
		
		initialize();
	}
	
	private void initialize() {
		beatbox.initialize();
		
		/*Demo*/
		
		final File boum = new File("sounds/boum.wav");
		final File clap = new File("sounds/clap.wav");
		
		view.addClickButton(beatbox.addClickButton(0, 0, "boum1", boum));
		view.addClickButton(beatbox.addClickButton(0, 120, "clap1", clap));
		
		final HashMap<Integer, Set<String>> map = new HashMap<Integer, Set<String>>();
		final TreeSet<String> set1 = new TreeSet<String>();
		set1.add("boum1");
		final TreeSet<String> set2 = new TreeSet<String>();
		set2.add("clap1");
		map.put(0, set1);
		map.put(2, set2);
		map.put(4, set1);
		map.put(5, set1);
		map.put(6, set2);
		map.put(7, set1);
		map.put(8, set1);
		map.put(10, set2);
		map.put(12, set1);
		map.put(13, set1);
		map.put(14, set2);
		map.put(15, set1);
		map.put(16, set1);
		map.put(18, set2);
		//beatbox.setScore(map);
	}
	
	public void start() {
		beatbox.start();
		view.start();
	}
	
	public boolean buttonClicked(String id) {
		return beatbox.buttonClicked(id);
	}
	
	public void soundStarted(String id) {
		view.setHit(id);
	}
	
	public void soundEnded(String id) {
		view.setNormal(id);
	}
	
	public void pulseEnded(String id) {
		view.setNormal(id);
	}
}