package main;

import view.GameView;

public class Game {
	protected GameView view;
	
	public Game() {
		
	}
	
	public void start() {
		view = new GameView(this);
	}
}
