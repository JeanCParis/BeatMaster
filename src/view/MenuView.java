package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import actionlisteners.MenuListener;
import main.Action;
import main.Game;

public class MenuView extends JPanel {
	
	public MenuView(final Game game) {
		setBackground(Color.white);
		setLayout(null);
		
		JTextArea text = new JTextArea("BeatMaster");
		text.setBounds(Game.X_SCREEN_SIZE/2 - 65, 30, 200, 50);
		text.setFont(new Font("Serif",Font.PLAIN,30));
		add(text);
		
		JButton freestyle = new JButton("Freestyle");
		freestyle.setBounds(Game.X_SCREEN_SIZE/2 - 50, 130, 100, 30);
		freestyle.addMouseListener(new MenuListener(game, Action.LAUNCH_BEATBOX));
		add(freestyle);
		
		JButton track = new JButton("Track");
		track.setBounds(Game.X_SCREEN_SIZE/2 - 50, 170, 100, 30);
		track.addMouseListener(new MenuListener(game, Action.LAUNCH_TRACK));
		add(track);
	}
}
