package bar;

import jplay.Sound;

public class SFX {
	private static Sound musica;
	
	public static void play (String audio) {
		stop();
		musica = new Sound(audio);
		SFX.musica.play();
		SFX.musica.setRepeat(false);
		SFX.musica.increaseVolume(6.0f);
	}
	
	public static void stop () {
		if(SFX.musica != null) {
			musica.stop();
		}
	}
}
