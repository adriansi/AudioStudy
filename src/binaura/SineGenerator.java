/**
 * @author Leonard
 * 
 * To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */
package binaura;
import com.softsynth.jsyn.*;

public class SineGenerator extends SynthCircuit {
	SineOscillator left, right;
	/*
	 * Input Attribute frequency; amplitude; bBeat for "binaural beats";
	 */
	public SynthInput frequency;
	public SynthInput amplitude;
	public SynthInput bBeat;
	/* Split output for left and right */
	public SynthOutput leftOutput;
	public SynthOutput rightOutput;
	// For right output
	private AddUnit freqAdder;

	public SineGenerator() throws SynthException {
		makeCircuit();
		addAllPort();
	}

	public SineGenerator(double freq, double bb, double amp) throws SynthException {
		makeCircuit();
		frequency.set(freq);
		bBeat.set(bb);
		amplitude.set(amp);
		addAllPort();
	}
	
	void makeCircuit() {
		/* Add SynthUnits to circuit */
		add(left = new SineOscillator());
		add(right = new SineOscillator());
		add(freqAdder = new AddUnit());

		// 
		// For SynthInput to connect to multiple SynthOutput
		frequency =
			new SynthDistributor(this, "frequency", Synth.SIGNAL_TYPE_OSC_FREQ);
		amplitude = new SynthDistributor(this, "amplitude");

		/* Connect units together. */
		frequency.connect(left.frequency);
		frequency.connect(freqAdder.inputA);
		bBeat = freqAdder.inputB;
		freqAdder.output.connect(right.frequency);
		amplitude.connect(left.amplitude);
		amplitude.connect(right.amplitude);

		/* Connect units together. */
		/* Set signal types for more conveniant control. */

		/* Set ports to useful values and ranges. */
		frequency.setup(0, 300, 1000);
		amplitude.setup(-1.0, 0.5, 1.0);
		bBeat.setup(-100, 7.8, 100);
		}

	void addAllPort() throws SynthException {
		// AddAllPort!!
		addPort(leftOutput = left.output, "leftOutput");
		addPort(rightOutput = right.output, "rightOutput");
		addPort(frequency);
		addPort(bBeat);
		addPort(amplitude);		
	}

	/* TODO: split flow for Generator */
}