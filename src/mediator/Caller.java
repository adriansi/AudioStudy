package mediator;

import controller.*;

import com.softsynth.jsyn.*;

public class Caller extends Connector {
	public static void main(String[] args) {
		try {

			Connector co = new Connector();

			co.add(220, 5, .1);
			co.add(320, 2, .1);
			co.add(420, 1, .2);
			co.allConnect();
			co.allStart();
			Synth.sleepUntilTick(2000);
		} catch (SynthException e) {
			System.out.println("Caught " + e);
			e.printStackTrace();
		}
	}
}