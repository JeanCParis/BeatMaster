package beatbox;

import java.io.File;
import java.util.Map;
import java.util.Set;

import main.Game;
import events.PulseEndEvent;
import events.PulseStartEvent;
import events.TickEvent;
import actionlisteners.PulseEndListener;
import actionlisteners.PulseStartListener;
import actionlisteners.TickListener;

public class Beatbox implements TickListener, PulseStartListener, PulseEndListener {
	protected Mixer mixer = new Mixer();
	protected Panel panel = new Panel();
	protected Metronome metronome = new Metronome();
	protected Score score = new Score();
	
	public void initialize() {
		mixer.setTickSound(new File("sounds/tick.wav"));
		
		metronome.setBPM(120);
		metronome.setSubdivision(2);
		metronome.setAllowedTimeDifference((long)(0.2 * Game.MICROSECONDS_PER_SECOND));
		metronome.setMixer(mixer);
		metronome.addTickListener(this);
		metronome.addTickListener(score);
		
		score.addScoreListener(this);
	}
	
	public ClickButton addClickButton(int xPosition, int yPosition, String signature, File soundFile) {
		final ClickButton button = new ClickButton(0, 0, signature, soundFile);	
		panel.addButton(button);
		return button;
	}
	
	public PressButton addPressButton(int xPosition, int yPosition, String signature, File soundFile) {
		final PressButton button = new PressButton(0, 0, signature, soundFile);	
		panel.addButton(button);
		return button;
	}
	
	public void setScore(Map<Integer, Set<String>> scoreMap) {
		for(Integer key : scoreMap.keySet()) {
			score.setButtons(key, scoreMap.get(key));
		}
	}
	
	public void start() {
		metronome.start();
	}
	
	public void ButtonClicked(final Button button) {
		metronome.playClip(button.getSoundFile(), button.getSignature());
	}

	@Override
	public void metronomeTicked(final TickEvent e) {
		mixer.playUpcommingClips();
	}

	@Override
	public void pulseStart(PulseStartEvent e) {
		System.out.println("pulseStart");
		Set<String> buttons = e.getButtonSignatures();
		for(String signature : buttons) {
			Button button = panel.getButton(signature);
			Pulse pulse = new Pulse(button, Game.PULSE_SPEED, Game.PULSE_MAX_VALUE);
			pulse.addPulseEndedListener(this);
			metronome.addUpdateListener(pulse);
		}
	}

	@Override
	public void pulseEnd(PulseEndEvent e) {
		//metronome.removeUpdateListener(e.getPulse());
	}
}
