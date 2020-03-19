package bar;

import javax.swing.JOptionPane;

import sun.audio.AudioPlayer;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class PlayMusic {


	public static void playMusic(String filepath) {
		InputStream music;
		
		try {
			music = new FileInputStream(new File(filepath));
			AudioStream audios = new AudioStream(music);
			AudioPlayer.player.start(audios);
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Error");
		}
	}
}

