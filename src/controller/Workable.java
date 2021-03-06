package controller;

import com.softsynth.jsyn.*;
// import java.util.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
// must import event handling from AWT 1.1
import java.applet.Applet;
import java.util.*;

/**
 * Simple JSyn program that plays binaural beats with two oscillator.
 * 
 * @author Leonard Huang @created 2004/2/11
 */
public class Workable extends Applet implements ItemListener {
	// Declare unit generators that we will use.
	public LineOut lineOut;
	/**
	 * Description of the Method
	 * 
	 * @param args
	 *            Description of the Parameter
	 */
	public static void main(String args[]) {
		/*
		 * Test1 applet = new Test1(); AppletFrame frame = new
		 * AppletFrame("Test JSyn", applet); frame.setSize(300, 100);
		 * frame.show(); frame.test();
		 */
		Workable app = new Workable();
		app.start();
	}

	/**
	 * Start JSyn, play some sounds, then stop JSyn.
	 */
	public void start() {
		try {
			// Start JSyn synthesizer.
			Synth.startEngine(0);

			SineGenerator osc = new SineGenerator();
			//ArrayList sinGen= new ArrayList();
			//SineGenerator osc1 = new SineGenerator();
			//SineGenerator osc2 = new SineGenerator();
			//SynthMixer leftMixer= new SynthMixer(2,1);
			//SynthMixer rightMixer= new SynthMixer(2,1);
			lineOut = new LineOut();

			osc.frequency.set(440);
			osc.bBeat.set(2.2);

			//osc[1].frequency.set(240);
			//osc[1].bBeat.set(2.5);
			/*
			 * for (int i=0; i <2;i++){
			 * leftMixer.connectInput(i,osc[0].left.output,0);
			 * rightMixer.connectInput(i,osc[0].right.output,1);
			 */
			// SineGenerator sinGenArray[]=new SineGenerator[2];
			// Create some unit generators.
			//AddUnit rightMixer = new AddUnit();

			// Connect oscillator to both left and right channels of output.
			osc.leftOutput.connect(0, lineOut.input, 0);
			osc.rightOutput.connect(0, lineOut.input, 1);

			// Mixer
			// mixer.output.connect(0, lineOut.input, 0);
			//mixer.output.connect(0, lineOut.input, 1);
			//rightMixer.output.connect(0, lineOut.input, 1);

			// Start the unit generators so they make sound.
			lineOut.start();
			osc.start();

			Synth.sleepUntilTick(2000);

			//Synth.sleepForTicks(1400);

			// Sleep for awhile so we can hear the sound.
			// Synth.sleepForTicks(1400);

			// Change the frequency of the oscillator.
			// osc1.frequency.set(210.0);
			// Synth.sleepForTicks(1400);
			
			stop();

		} catch (SynthException e) {
			System.out.println("Caught " + e);
			e.printStackTrace();
		}
	}

	/**
	 * Description of the Method
	 */
	public void stop() {
		try {
			/*
			 * Your cleanup code goes here.
			 */
			removeAll();
			// remove components from Applet panel.
			// Stop units and delete them to reclaim their resources.
			/*
			 * osc1.stop(); osc1.delete(); osc2.stop(); osc2.delete();
			 * lineOut.stop();
			 */
			// Stop JSyn synthesizer.
			Synth.stopEngine();
		} catch (SynthException e) {
			SynthAlert.showError(this, e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.ItemListener#itemStateChanged(java.awt.event.ItemEvent)
	 */
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub

	}
}