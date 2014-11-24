/*
 * Created on 2004/2/15
 * 
 * To change the template for this generated file go to Window - Preferences -
 * Java - Code Generation - Code and Comments
 */
package binaura;

import com.softsynth.jsyn.LineOut;
import com.softsynth.jsyn.SineOscillator;
import com.softsynth.jsyn.Synth;
import com.softsynth.jsyn.SynthAlert;
import com.softsynth.jsyn.SynthException;
import com.softsynth.jsyn.SynthMixer;

/**
 * @author Leonard
 * 
 * To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */
public class SynthMixerTest {
	SineOscillator osc1, osc2;
	SynthMixer leftMixer, rightMixer;
	LineOut lineOut;

	public static void main(String[] args) {
		SynthMixerTest app = new SynthMixerTest();
		app.start();
	}

	public void start() {
		Synth.startEngine(0);

		osc1 = new SineOscillator();
		osc2 = new SineOscillator();
		leftMixer = new SynthMixer(2, 1);
		rightMixer = new SynthMixer(2, 1);
		lineOut = new LineOut();

		try {
			osc1.frequency.set(300);
			osc1.amplitude.set(0.5);
			osc2.frequency.set(250);
			osc2.amplitude.set(0.5);

			leftMixer.connectInput(0, osc1.output, 0);
			leftMixer.connectInput(1, osc2.output, 0);
			rightMixer.connectInput(0, osc1.output, 0);
			rightMixer.connectInput(1, osc2.output, 0);

			leftMixer.setGain(0, 0, 1);
			leftMixer.setGain(1, 0, 1);
			rightMixer.setGain(0, 0, 1);
			rightMixer.setGain(1, 0, 1);

			leftMixer.connectOutput(0, lineOut.input, 0);
			rightMixer.connectOutput(0, lineOut.input, 1);

			lineOut.start();
			leftMixer.start();
			rightMixer.start();
			osc1.start();
			osc2.start();

			Synth.sleepUntilTick(2000);

			stop();

		} catch (SynthException e) {
			System.out.println("Caught " + e);
			e.printStackTrace();
		}
	}
	public void stop() {
		try {
			//removeAll();
			Synth.stopEngine();
		} catch (SynthException e) {
			SynthAlert.showError(e);
		}
	}
}
