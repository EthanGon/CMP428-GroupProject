/*
* This is a class that will allow for music to be played during gameplay. Class can be adjust futher for sound effects. 
* To call in main:
* public BackgroundMusic music (at top)
* in init
* music = new BackgroundMusic("../file path for music saved")
* music.loop();
*/


import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class BackgroundMusic {
    
    Clip clip;

    public BackgroundMusic(String filename) {
        try {
            // Load the audio file
            File file = new File(filename);
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(file);
            
            // Create a Clip, which is Java's way of holding audio in memory
            clip = AudioSystem.getClip();
            clip.open(audioInput);
            
        } catch (Exception e) {
            System.out.println("Failed to load sound: " + filename);
            e.printStackTrace();
        }
    }

    // Play the sound once (sound effects if wanted)
    public void play() {
        if (clip != null) {
            clip.setFramePosition(0); // Rewind to the beginning
            clip.start();
        }
    }

    // Loop the sound forever
    public void loop() {
        if (clip != null) {
            clip.setFramePosition(0);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    // Stop the sound completely
    public void stop() {
        if (clip != null) {
            clip.stop();
        }
    }

}
