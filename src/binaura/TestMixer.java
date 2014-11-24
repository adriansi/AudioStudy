package binaura;

import java.applet.Applet;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import com.softsynth.jsyn.LineOut;
import com.softsynth.jsyn.Synth;
import com.softsynth.jsyn.SynthAlert;
import com.softsynth.jsyn.SynthException;

public class TestMixer extends Applet implements ItemListener {
	public static void main(String[] args) {
		TestMixer app = new TestMixer();
		app.start();
	}

	public void start() {
		try {
			// Start JSyn synthesizer.
			Synth.startEngine(0);
			// Creates generator
			SineGenerator[] osc = new SineGenerator[3];
			osc[0] = new SineGenerator(440, 0.6, 0.2);
			osc[1] = new SineGenerator(330, 2.6, 0.2);
			osc[2] = new SineGenerator(220, 4.6, 0.2);

			Mixer mixer = new Mixer();
			LineOut lineOut = new LineOut();

			// Mixer connect
			mixer.connectInput(osc);

			// Connect mixer to both left and right channels of output.
			mixer.left.connectOutput(0, lineOut.input, 0);
			mixer.right.connectOutput(0, lineOut.input, 1);

			// Mixer
			// Start the unit generators so they make sound.
			lineOut.start();
			mixer.start();

			for (int i = 0; i < osc.length; i++) {
				osc[i].start();
			}

			// Sleep for awhile so we can hear the sound.
			Synth.sleepUntilTick(2000);
			stop();
		} catch (SynthException e) {
			System.out.println("Caught " + e);
			e.printStackTrace();
		}
	}

	public void stop() {
		try {
			// remove components from Applet panel.
			removeAll();
			// Stop units and delete them to reclaim their resources.
			// Stop JSyn synthesizer.
			Synth.stopEngine();
		} catch (SynthException e) {
			SynthAlert.showError(this, e);
		}
	}

	public void itemStateChanged(ItemEvent e) {

		// TODO Auto-generated method stub
	}
}