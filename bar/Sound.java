package bar;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound {
  private AudioFormat audioFormat;
  
  private AudioInputStream audioInputStream;
  
  private SourceDataLine sourceDataLine;
  
  private boolean stop;
  
  private boolean pause;
  
  private float volume;
  
  private boolean volumeChanged;
  
  private Song song;
  
  private boolean loop;
  
  private String fileName;
  
  public Sound(String fileName) {
    this.loop = false;
    this.fileName = fileName;
    load(fileName);
  }
  
  public void load(String fileName) {
    try {
      this.audioInputStream = AudioSystem.getAudioInputStream(new File(
            fileName));
    } catch (UnsupportedAudioFileException ex) {
      Logger.getLogger(Sound.class.getName()).log(Level.SEVERE, (String)null, ex);
    } catch (IOException ex) {
      Logger.getLogger(Sound.class.getName()).log(Level.SEVERE, (String)null, ex);
    } 
    this.audioFormat = this.audioInputStream.getFormat();
    DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, 
        this.audioFormat);
    try {
      this.sourceDataLine = (SourceDataLine)AudioSystem.getLine(dataLineInfo);
    } catch (LineUnavailableException ex) {
      Logger.getLogger(Sound.class.getName()).log(Level.SEVERE, (String)null, ex);
    } 
  }
  
  public void increaseVolume(float value) {
    this.volume += value;
    this.volumeChanged = true;
  }
  
  public void decreaseVolume(float value) {
    this.volume -= value;
    this.volumeChanged = true;
  }
  
  public void setVolume(float value) {
    this.volume = value;
    this.volumeChanged = true;
  }
  
  public void play() {
    if (!this.pause) {
      this.song = new Song();
      this.song.start();
    } else {
      this.pause = false;
    } 
  }
  
  public void stop() {
    this.stop = true;
    if (this.song == null)
      this.song = null; 
  }
  
  public void pause() {
    this.pause = true;
  }
  
  private class Song extends Thread {
    byte[] tempBuffer = new byte[1000];
    
    public void run() {
      try {
        Sound.this.sourceDataLine.open(Sound.this.audioFormat);
        Sound.this.sourceDataLine.start();
        int count = 0;
        while (count != -1 && !Sound.this.stop) {
          if (!Sound.this.pause) {
            count = Sound.this.audioInputStream.read(this.tempBuffer, 0, 
                this.tempBuffer.length);
          } else {
            count = 0;
          } 
          if (count > 0) {
            if (Sound.this.volumeChanged) {
              FloatControl volControl = (FloatControl)Sound.this.sourceDataLine
                .getControl(FloatControl.Type.MASTER_GAIN);
              volControl.setValue(Sound.this.volume);
              Sound.this.volumeChanged = false;
            } 
            Sound.this.sourceDataLine.write(this.tempBuffer, 0, count);
          } 
        } 
        Sound.this.sourceDataLine.drain();
        Sound.this.sourceDataLine.close();
        if (Sound.this.loop && !Sound.this.stop) {
          Sound.this.load(Sound.this.fileName);
          Sound.this.setVolume(Sound.this.volume);
          Sound.this.song = new Song();
          Sound.this.song.start();
        } 
        Sound.this.stop = false;
        Sound.this.song = null;
      } catch (Exception e) {
        e.printStackTrace();
      } 
    }
    
    private Song() {}
  }
  
  public void setRepeat(boolean value) {
    this.loop = value;
  }
  
  public boolean isExecuting() {
    return (this.song != null);
  }
}