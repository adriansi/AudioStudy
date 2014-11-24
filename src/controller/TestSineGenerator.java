/*
 * Created on 2004/2/19
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package controller;
import junit.framework.TestCase;
import com.softsynth.jsyn.*;
/**
 * @author Leonard
 * 
 * To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */
public class TestSineGenerator extends TestCase {
	protected SineGenerator osc;
	public static void main(String[] args) {
	}
	/*
	 * @see TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		Synth.startEngine(0);
		osc = new SineGenerator();
	}
	/*
	 * @see TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
		osc.stop();
		osc = null;
		Synth.stopEngine();
	}
	/**
	 * Constructor for TestSineGenerator.
	 * 
	 * @param name
	 */
	public TestSineGenerator(String name) {
		super(name);
	}
	/*
	 * Class to test for void SineGenerator(double, double, double)
	 */
	public void testSineGeneratordoubledoubledouble() {
		osc.set(220, 0.4, .2);
		osc.start();
		Synth.sleepUntilTick(Synth.getTickCount() + 1);
		assertEquals("Left frequency not match.", 220.0, osc.left.frequency
				.get(), 0);
		assertEquals("Adder frequency not match.", 220.4, osc.freqAdder.output
				.get(), 0.01);
		assertEquals("Right frequency not match.", 220.4, osc.right.frequency
				.getCurrent(), 0.01);
		assertEquals("Binaural beat not match.", .4, osc.bBeat.get(), 0);
		assertEquals("Left amplitude not match.", 0.2,
				osc.left.amplitude.get(), 0);
		assertEquals("Right amplitude not match.", 0.2, osc.right.amplitude
				.get(), 0);
	}
}
