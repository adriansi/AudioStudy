
package ui;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import mediator.Caller;

import com.softsynth.jsyn.Synth;
import com.softsynth.jsyn.SynthException;

public class TestToken extends JFrame {

	public static void main(String args[]) {
		TestToken application = new TestToken();
		application.addWindowListener(

				new WindowAdapter() {

					public void windowClosing(WindowEvent windowEvent) {
						System.exit(0);
					}
				} // end anonymous inner class
				); // end call to addWindowListener
	} // end method main
	private JTextField inputField;
	private JTextArea outputArea;
	private JLabel promptLabel;
	// set up GUI and event handling
	public TestToken() {
		super("Testing Class StringTokenizer");
		Container container = getContentPane();
		container.setLayout(new FlowLayout());
		promptLabel = new JLabel("Enter a sentence and press Enter");
		container.add(promptLabel);
		inputField = new JTextField(20);
		inputField.addActionListener(
		// anonymous inner class
				new ActionListener() {
					// handle text field event
					public void actionPerformed(ActionEvent event) {
						String stringToToken = event.getActionCommand();
						// 1-pass to split them by space
						double[][] gen = stringToGenDouble(stringToToken);
						outputArea.setText("");
						display(gen);
						generate(gen);
					}
				} // end anonymous inner class
				); // end call to addActionListener
		container.add(inputField);
		outputArea = new JTextArea(10, 20);
		outputArea.setEditable(false);
		container.add(new JScrollPane(outputArea));
		setSize(275, 260); // set the window size
		show(); // show the window
	}
	/**
	 * @param gen
	 */
	protected void generate(double[][] gen) {
		try {
			Caller caller=new Caller();
			caller.add(220,5,.1);

			caller.allConnect();
			caller.allStart();
			Synth.sleepUntilTick(2500);
			caller.allStop();
			Synth.stopEngine();
		} catch (SynthException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * @param tokens
	 */
	private void display(double[][] tokens) {
		for (int i = 0; i < tokens.length; i++) {
			outputArea.append("No." + i + " String:\n");
			outputArea.append("\tFrequency:\t" + tokens[i][0] + "\n");
			outputArea.append("\tBBeat:\t" + tokens[i][1] + "\n");
			outputArea.append("\tAmplitude:\t" + tokens[i][2] + "\n");
			outputArea.append("\n");
		}
	}
	/**
	 * @param string
	 */
	protected String[][] stringToSineGen(String tokens) {
		String[][] bbAttribute = null;
		// Trim
		tokens = tokens.trim();
		// 1-pass: split by space
		String[] temp = tokens.split("\\s");
		// 2-pass: split by [+-*/=]
		bbAttribute = new String[temp.length][];
		for (int i = 0; i < temp.length; i++) {
			bbAttribute[i] = temp[i].split("[\\+\\-\\*\\/\\=]");
		}
		return bbAttribute;
	}
	/**
	 * @param string
	 */
	protected double[][] stringToGenDouble(String tokens) {
		String[][] bbAttribute = stringToSineGen(tokens);
		double[][] genDouble = new double[bbAttribute.length][];
		for (int i = 0; i < bbAttribute.length; i++) {
			genDouble[i] = new double[bbAttribute[i].length];
			for (int j = 0; j < bbAttribute[i].length; j++) {
				genDouble[i][j] = Double.parseDouble(bbAttribute[i][j]);
			}
		}
		return genDouble;
	}
} // end class TokenTest
