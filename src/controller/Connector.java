/*
 * Created on 2004/2/19
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
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
public class Connector {
	ArrayList sinGenList;
	SineGenerator[] osc;
	Mixer mixer;
	LineOut lineOut;
	/**
	 *  
	 */
	public Connector() {
		init();
	}
	/**
	 *  
	 */
	private void init() {
		// Creates generator
		Synth.startEngine(0);
		sinGenList = new ArrayList();
		mixer = new Mixer();
		lineOut = new LineOut();
	}
	/**
	 *  
	 */
	public void add(double freq, double bb, double amp) {
		sinGenList.add(new SineGenerator(freq, bb, amp));
	}
	/**
	 * @param osc
	 * @param mixer
	 * @param lineOut
	 */
	public void allConnect() {
		osc = (SineGenerator[]) sinGenList.toArray(new SineGenerator[sinGenList
				.size()]);
		mixer.connectInput(osc);
		mixer.connectOutput(lineOut);
	}
	/**
	 * @param osc
	 * @param mixer
	 * @param lineOut
	 */
	public void allStart() {
		for (int i = 0; i < osc.length; i++) {
			osc[i].start();
		}
		mixer.start();
		lineOut.start();
	}
	public void allStop() {
		try {
			//removeAll();
			for (int i = 0; i < osc.length; i++) {
				osc[i].stop();
			}
			mixer.stop();
			lineOut.stop();
		} catch (SynthException e) {
			SynthAlert.showError(e);
		}
	}
}
