/*
 * Created on 2004/2/9
 * 
 * To change the template for this generated file go to Window - Preferences -
 * Java - Code Generation - Code and Comments
 */

/**
 * @author Leonard
 * 
 * To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */
package binaura;
import com.softsynth.jsyn.*;

public class Mixer {
	SynthMixer left;
	SynthMixer right;

	public Mixer() {
	}

	public Mixer(SineGenerator osc[]) {
		connectInput(osc);
	}
	
	public void connectInput(SineGenerator osc[]) {
		int n = osc.length;
		left = new SynthMixer(n, 1);
		right = new SynthMixer(n, 1);

		for (int i = 1; i < n; i++) {
			left.connectInput(i, osc[i].leftOutput, 0);
			right.connectInput(i, osc[i].rightOutput, 0);
		}
		setGain(osc);
	}

	
	/**
	 * Avoid clipping
	 */
	private void setGain(SineGenerator osc[]) {
		for (int i=0; i<osc.length; i++){
		left.setGain(i,0,1.0);
		right.setGain(i,0,1.0);
		}
	}

	public void start() {
		left.start();
		right.start();
	}
}
