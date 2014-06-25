package beatbox;

import java.io.File;
import java.util.Map;
import java.util.Set;

import main.Game;
import view.BeatboxView;
import actionlisteners.PulseEndListener;
import actionlisteners.PulseStartListener;
import actionlisteners.TickListener;
import events.PulseEndEvent;
import events.PulseStartEvent;
import events.TickEvent;

public class Beatbox implements TickListener, PulseStartListener, PulseEndListener {
	protected Mixer mixer = new Mixer();
	protected Panel panel = new Panel();
	protected Metronome metronome = new Metronome();
	protected Score score = new Score();
	
	protected BeatboxView view;
	
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
	
	public ClickButton addClickButton(final int xPosition, final int yPosition, final String signature, final File soundFile) {
		final ClickButton button = new ClickButton(0, 0, signature, soundFile);	
		panel.addButton(button);
		return button;
	}
	
	public PressButton addPressButton(final int xPosition, final int yPosition, final String signature, final File soundFile) {
		final PressButton button = new PressButton(0, 0, signature, soundFile);	
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
	
	public void ButtonClicked(final Button button) {
		metronome.playClip(button.getSoundFile(), button.getSignature());
	}

	@Override
	public void metronomeTicked(final TickEvent e) {
		mixer.playUpcommingClips();
	}

	@Override
	public void pulseStart(final PulseStartEvent e) {
		System.out.println("pulseStart");
		final Set<String> buttons = e.getButtonSignatures();
		for(final String signature : buttons) {
			final Button button = panel.getButton(signature);
			final Pulse pulse = new Pulse(button, Game.PULSE_NUMBER_OF_TICKS, Game.PULSE_SPEED);
			pulse.addPulseEndedListener(this);
			metronome.addUpdateListener(pulse);
			metronome.addTickListener(pulse);
		}
	}

	@Override
	public void pulseEnd(final PulseEndEvent e) {
		metronome.removeUpdateListener(e.getPulse());
		metronome.removeTickListener(e.getPulse());
	}
}
