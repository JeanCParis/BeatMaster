package main;

import java.io.File;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

import view.GameView;
import beatbox.Beatbox;

public class Game {
	
	public static final int PULSE_SPEED = 1;
	public static final int PULSE_MAX_VALUE = 1;
	public static final int BUTTON_SIZE = 40;
	public static final long MICROSECONDS_PER_SECOND = 1000000000l;
	
	protected long allowedInputTimeDifference;
			
	protected GameView view;
	
	protected final Beatbox beatbox;

	public Game() {
		beatbox = new Beatbox();
		view = new GameView(beatbox);
		
		initialize();
	}
	
	private void initialize() {
		beatbox.initialize();
		
		/*Demo*/
		
		final File ohohooh = new File("sounds/ohohooh.wav");
		view.addClickButton(beatbox.addClickButton(0, 0, "cb1", ohohooh));
		
		HashMap<Integer, Set<String>> map = new HashMap<Integer, Set<String>>();
		TreeSet<String> sigs = new TreeSet<String>();
		sigs.add("cb1");
		map.put(1, sigs);
		map.put(11, sigs);
		map.put(21, sigs);
		beatbox.setScore(map);
	}
	
	public void start() {
		beatbox.start();
	}
}