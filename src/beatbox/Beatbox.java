package beatbox;

import java.io.File;
import java.util.Map;
import java.util.Set;

import main.Game;
import actionlisteners.PulseEndListener;
import actionlisteners.PulseStartListener;
import actionlisteners.TickListener;
import events.PulseEndEvent;
import events.PulseStartEvent;
import events.TickEvent;

public class Beatbox implements TickListener, PulseStartListener, PulseEndListener {
	protected Game game;
	
	protected Mixer mixer;
	protected Panel panel = new Panel();
	protected Metronome metronome = new Metronome();
	protected Score score = new Score();
	
	public Beatbox(Game game) {
		this.game = game;
		mixer = new Mixer(game);
	}
	
	public void initialize() {
		
		metronome.setBPM(120);
		metronome.setSubdivision(2);
		metronome.setAllowedTimeDifference((long)(0.2 * Game.NANOSECONDS_PER_SECOND));
		metronome.setMixer(mixer);
		metronome.addTickListener(this);
		metronome.addTickListener(score);
		
		score.addScoreListener(this);
	}
	
	public ClickButton addClickButton(final int xPosition, final int yPosition, final String id, final File soundFile) {
		final ClickButton button = new ClickButton(0, 0, id, soundFile);	
		panel.addButton(button);
		return button;
	}
	
	public PressButton addPressButton(final int xPosition, final int yPosition, final String id, final File soundFile) {
		final PressButton button = new PressButton(0, 0, id, soundFile);	
		panel.addButton(button);
		return button;
	}
	
	public void setScore(final Map<Integer, Set<String>> scoreMap) {
		for(final Integer key : scoreMap.keySet()) {
			score.setButtons(key, scoreMap.get(key));
		}
	}
	
	public void start() {
		metronome.start();
	}
	
	public boolean buttonClicked(final String id) {
		Button button = panel.getButton(id);
		return metronome.playClip(button.getSoundFile(), id);
	}

	@Override
	public void metronomeTicked(final TickEvent e) {
		mixer.playUpcommingClips();
	}

	@Override
	public void pulseStart(final PulseStartEvent e) {
		System.out.println("pulseStart");
		final Set<String> buttons = e.getButtonIDs();
		for(final String id : buttons) {
			final Button button = panel.getButton(id);
			final Pulse pulse = new Pulse(button, Game.PULSE_NUMBER_OF_TICKS);
			button.addPulse(pulse);
			pulse.addPulseEndedListener(this);
			metronome.addUpdateListener(pulse);
			metronome.addTickListener(pulse);
		}
	}

	@Override
	public void pulseEnd(final PulseEndEvent e) {
		Pulse pulse = e.getPulse();
		metronome.removeUpdateListener(pulse);
		metronome.removeTickListener(pulse);
		pulse.getButton().removePulse(pulse);
		game.pulseEnded(pulse.getButton().getID());
	}
}
