package bar;

import jplay.Sound;

public class BGM {
	
	static float valor;
	private static Sound musica;
	
	public static void play (String audio) {
		stop();
		musica = new Sound(audio);
		BGM.musica.play();
		BGM.musica.setRepeat(true);
	}
	
	public static void stop () {
		if(BGM.musica != null) {
			musica.stop();
		}
	}
	
	public static void baixar (float value){
		BGM.musica.decreaseVolume(value);
		valor=value;		
	}
	public static void maximo (){
		BGM.musica.increaseVolume(valor);
		valor=0;
	}
	
}
