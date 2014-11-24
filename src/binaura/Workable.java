package binaura;

// must import event handling from AWT 1.1
import java.applet.Applet;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import com.softsynth.jsyn.LineOut;
import com.softsynth.jsyn.Synth;
import com.softsynth.jsyn.SynthAlert;
import com.softsynth.jsyn.SynthException;

// import java.util.*;

public class Workable extends Applet implements ItemListener {

	public LineOut lineOut;

	public static void main(String args[]) {

		Workable app = new Workable();
		app.start();
	}

	public void start() {
		try {

			Synth.startEngine(0);

			SineGenerator osc = new SineGenerator();

			lineOut = new LineOut();

			osc.frequency.set(440);
			osc.bBeat.set(2.5);

			osc.leftOutput.connect(0, lineOut.input, 0);
			osc.rightOutput.connect(0, lineOut.input, 1);

			lineOut.start();
			osc.start();

			Synth.sleepUntilTick(2000);

			stop();

		} catch (SynthException e) {
			System.out.println("Caught " + e);
			e.printStackTrace();
		}
	}

	public void stop() {
		try {

			removeAll();

			Synth.stopEngine();
		} catch (SynthException e) {
			SynthAlert.showError(this, e);
		}
	}

	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub

	}
}