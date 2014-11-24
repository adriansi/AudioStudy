package binaura;

import java.applet.Applet;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import com.softsynth.jsyn.LineOut;
import com.softsynth.jsyn.Synth;
import com.softsynth.jsyn.SynthAlert;
import com.softsynth.jsyn.SynthException;
import com.softsynth.jsyn.SynthMixer;

public class Test1 extends Applet implements ItemListener {

	private static final long serialVersionUID = 1L;
	LineOut lineOut;
	SineGenerator osc[];
	SynthMixer leftMixer;
	SynthMixer rightMixer;

	public static void main(String args[]) {

		Test1 app = new Test1();
		app.start();
	}

	public void itemStateChanged(ItemEvent e) {

	}

	public void start() {
		try {
			Synth.startEngine(0);

			osc = new SineGenerator[2];

			for (int i = 0; i < osc.length; i++) {
				osc[i] = new SineGenerator();
			}
			leftMixer = new SynthMixer(2, 1);
			rightMixer = new SynthMixer(2, 1);
			lineOut = new LineOut();

			osc[0].frequency.set(440);
			osc[0].bBeat.set(4.5);
			osc[1].frequency.set(240);
			osc[1].bBeat.set(2.5);

			for (int i = 0; i < osc.length; i++) {
				leftMixer.connectInput(i, osc[i].leftOutput, 0);
				leftMixer.setGain(i, 0, 1.0);
				rightMixer.connectInput(i, osc[i].rightOutput, 0);
				rightMixer.setGain(i, 0, 1.0);
			}

			leftMixer.connectOutput(0, lineOut.input, 0);
			rightMixer.connectOutput(0, lineOut.input, 1);

			lineOut.start();
			leftMixer.start();
			rightMixer.start();

			for (int i = 0; i < 2; i++) {
				osc[i].start();
			}

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
}
