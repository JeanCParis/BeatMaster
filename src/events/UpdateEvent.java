package events;

import java.util.EventObject;

import beatbox.Metronome;

public class UpdateEvent extends EventObject {
	protected Metronome metronome;
	protected long elapsedTime;
	
	public UpdateEvent(final Metronome metronome, final long elapsedTime) {
		super(metronome);
		this.metronome = metronome;
		this.elapsedTime = elapsedTime;
	}
	
	public Metronome getMetronome() {
		return metronome;
	}
	
	public long getElapsedTime() {
		return elapsedTime;
	}
}
