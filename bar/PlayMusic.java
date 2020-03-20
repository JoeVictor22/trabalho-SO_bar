package bar;

import javax.swing.JOptionPane;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequencer;


public class PlayMusic {


	public static void playMusic(String filepath) {
		try {
			Sequencer sequencer = MidiSystem.getSequencer();
			sequencer.open();
			InputStream music = new BufferedInputStream(new FileInputStream(new File(filepath)));
	        sequencer.setSequence(music);
	        sequencer.start();
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Error");
		}
	}
}
