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
	public static final int X_SCREEN_SIZE = 400;
	public static final int Y_SCREEN_SIZE = 400;
			
	protected GameView view;
	
	protected Beatbox beatbox;

	public Game() {
		
		view = new GameView(this);
	}
	
	private void initializeBeatbox() {
		beatbox = new Beatbox();
		beatbox.initialize();
		
		final File boum = new File("sounds/boum2.wav");
		final File clap = new File("sounds/clap2.wav");
		final File n1 = new File("sounds/n12.wav");
		final File n2 = new File("sounds/n21.wav");
		final File ohohooh = new File("sounds/ohohooh.wav");
				
		view.addClickButton(beatbox.addClickButton(0, 0, "boum1", boum));
		view.addClickButton(beatbox.addClickButton(0, 100, "n11", n1));
		view.addClickButton(beatbox.addClickButton(0, 200, "n12", n1));
		
		view.addClickButton(beatbox.addClickButton(100, 0, "clap1", clap));
		view.addClickButton(beatbox.addClickButton(100, 100, "n21", n2));
		view.addClickButton(beatbox.addClickButton(100, 200, "n22", n2));
		
		view.addClickButton(beatbox.addClickButton(200, 0, "ohohooh1", ohohooh));
		view.addClickButton(beatbox.addClickButton(200, 100, "ohohooh2", ohohooh));
		view.addPressButton(beatbox.addClickButton(200, 200, "ohohooh3", ohohooh));
	}
	
	private void initializeTrack() {
		beatbox = new Beatbox();
		beatbox.initialize();
		
		final File boum = new File("sounds/boum2.wav");
		final File clap = new File("sounds/clap2.wav");
		final File n1 = new File("sounds/n12.wav");
		final File n2 = new File("sounds/n21.wav");
		final File ohohooh = new File("sounds/ohohooh.wav");
				
		view.addClickButton(beatbox.addClickButton(0, 0, "boum1", boum));
		view.addClickButton(beatbox.addClickButton(0, 100, "n11", n1));
		view.addClickButton(beatbox.addClickButton(0, 200, "n12", n1));
		
		view.addClickButton(beatbox.addClickButton(100, 0, "clap1", clap));
		view.addClickButton(beatbox.addClickButton(100, 100, "n21", n2));
		view.addClickButton(beatbox.addClickButton(100, 200, "n22", n2));
		
		view.addClickButton(beatbox.addClickButton(200, 0, "ohohooh1", ohohooh));
		view.addClickButton(beatbox.addClickButton(200, 100, "ohohooh2", ohohooh));
		view.addPressButton(beatbox.addClickButton(200, 200, "ohohooh3", ohohooh));
		
		
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
		beatbox.setScore(map);
	}
	
	public Beatbox getBeatbox() {
		return beatbox;
	}
	
	public void doAction(Action action) {
		switch(action) {
		case LAUNCH_BEATBOX :
			initializeBeatbox();
			view.loadBeatbox();
			beatbox.start();
			break;
		case LAUNCH_TRACK :
			
			break;
		case LAUNCH_MENU :
			beatbox.stop();
			view.loadMenu();
			break;
		}
	}
}