package beatbox;

import java.io.File;
import java.util.Map;
import java.util.Set;

import view.BeatboxView;
import main.Game;
import actionlisteners.PulseEndListener;
import actionlisteners.PulseStartListener;
import actionlisteners.TickListener;
import actionlisteners.UpdateListener;

public class Beatbox {
	protected BeatboxView view;
	
	protected Mixer mixer;
	protected Panel panel = new Panel();
	protected Metronome metronome = new Metronome();
	protected Score score = new Score();
	
	protected boolean checked = true;
	
	public Beatbox() {
		mixer = new Mixer(this);
	}
	
	public void initialize() {
		metronome.setBPM(120);
		metronome.setSubdivision(2);
		metronome.setAllowedTimeDifference((long)(0.2 * Game.NANOSECONDS_PER_SECOND));
		metronome.setMixer(mixer);
		metronome.addTickListener(new TickListener(this));
		
		score.addScoreListener(new PulseStartListener(this));
	}
	
	public boolean isChecked() {
		return checked;
	}
	
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	
	public ClickButton addClickButton(final int xPosition, final int yPosition, final String id, final File soundFile) {
		final ClickButton button = new ClickButton(xPosition, yPosition, id, soundFile);	
		panel.addButton(button);
		return button;
	}
	
	public PressButton addPressButton(final int xPosition, final int yPosition, final String id, final File soundFile) {
		final PressButton button = new PressButton(xPosition, yPosition, id, soundFile);	
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
	
	public void stop() {
		metronome.start();
	}
	
	public boolean buttonClicked(final String id) {
		Button button = panel.getButton(id);
		if(checked) {
			return metronome.playClip(button.getSoundFile(), id);
		}
		else {
			mixer.playClip(button.getSoundFile(), id, 0);
			return true;
		}
	}
	
	public void buttonReleased(final String id) {
		mixer.stopClip(id);
	}

	public void metronomeTicked(final int tickValue) {
		score.metronomeTicked(tickValue);
		mixer.playUpcommingClips();
	}
	
	public void metronomeUpdated(final long elapsedTime) {
		panel.metronomeUpdated(elapsedTime);
	}

	public void pulseStart(final Set<String> buttonIDs) {
		for(final String id : buttonIDs) {
			final Button button = panel.getButton(id);
			final Pulse pulse = new Pulse(button, Game.PULSE_NUMBER_OF_TICKS);
			button.addPulse(pulse);
			pulse.addPulseEndedListener(new PulseEndListener(this));
			metronome.addUpdateListener(new UpdateListener(this));
			metronome.addTickListener(new TickListener(this));
		}
	}

	public void pulseEnd(final Pulse pulse) {
		Button button = pulse.getButton();
		button.removePulse(pulse);
		view.setNormal(button.getID());
	}
	
	public void soundStarted(String id) {
		view.setHit(id);
	}
	
	public void soundEnded(String id) {
		mixer.removeClip(id);
		view.setNormal(id);
	}
}
